package task3;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        int flag = 0;
        while (flag != 1) {
            int flag1 = 0;
            int numberMoves = 0;

            while (flag1 != 1) {
                numberMoves = 0;
                Scanner in = new Scanner(System.in);                         //read user-entered string
                String input = in.nextLine();
                numberMoves++;
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) == ' ') {                        //counting the number of options for moves
                        numberMoves++;
                    }
                }
                if ((numberMoves >= 3) && (numberMoves % 2 != 0)) {          //then there are checks for the correctness of the entered data
                    flag1 = 1;
                } else {
                    System.out.println("Wrong data. The number of moves must be odd and greater than 1");
                }

                if (flag1 == 1) {
                    String[] words = input.split("\\s+");
                    for (int i = 0; i < words.length; i++) {                //checks if the entered moves are not repeated
                        int index = input.indexOf(words[i]);
                        int count = 0;
                        while (index != -1) {
                            count++;
                            input = input.substring(index + 1);
                            index = input.indexOf(words[i]);
                            if (count > 1) {
                                flag1 = 0;
                                System.out.println("Incorrect data, moves are repeated");
                            }
                        }
                    }
                }

            }
            ComputerMove computerMove = new ComputerMove(numberMoves);     //generating a key
            String key = computerMove.generationKey();

            int computerMov = computerMove.generationComputerMov();         //generate the computer's move

            String c = Integer.toString(computerMov);                      //connect the key and the stroke together
            String keyPlusMove = key + c;

            String Hmac = computerMove.generationHMAC(keyPlusMove);         //calculate HMAC
            System.out.println("HMAC:");
            System.out.println(Hmac);

            int flag2 = 0;
            int flag3 = 1;
            String m = "1";
            int yourMove = -1;
            while (flag2 != 1) {
                flag3 = 1;
                System.out.println("Available moves:");
                System.out.println("     1 - rock \n     2 - paper \n     3 - scissors");
                if (numberMoves >= 5) {
                    System.out.println("     4 - lizard \n     5 - spock");   //display the menu
                }
                for (int i = 6; i <= numberMoves; i++) {
                    System.out.println("Move " + i);
                }
                System.out.println("     0 - exit \n     ? - help");
                Scanner in = new Scanner(System.in);
                System.out.println("Enter your move:");
                m = in.nextLine();
                if (m.equals("?")) {
                    flag2 = 1;
                } else {
                    try {
                        yourMove = Integer.valueOf(m);                 //then there are checks for the correctness of the entered data
                    } catch (NumberFormatException e) {
                        flag3 = 0;
                    }

                    if (flag3 != 0 && ((yourMove >= 0) && (yourMove <= numberMoves))) {
                        flag2 = 1;
                    } else {
                        System.out.println("There is no such option");
                    }
                }
            }
            if (yourMove == 0) {                 // if the user wants to log out
                break;
            }

            if (m.equals("?")) {
                HelpTable helpTable = new HelpTable(numberMoves);     //displaying the help table
                helpTable.writeTable();
            } else {
                int result = -1;

                WinOrLose winOrLose = new WinOrLose(yourMove, computerMov, numberMoves, result);
                result = winOrLose.whoWin();     //calculate the winner

                System.out.println("Computer move: " + computerMov);
                if (result == 0) System.out.println("Draw!");
                if (result == 1) System.out.println("You lose!");
                if (result == 2) System.out.println("You win!");

                System.out.println("HMAC key:");
                System.out.println(key);
            }
            Scanner in = new Scanner(System.in);
            System.out.println("Repeat the task again? \n 1 - Yes");
            int a = in.nextInt();
            if (a == 1) {
            } else {
                flag = 1;
            }

        }
    }
}

