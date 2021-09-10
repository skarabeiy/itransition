package task3;

public class WinOrLose {
    int yourMove;
    int computerMove;
    int numberMoves;
    int result;

    public WinOrLose(int a, int b, int c, int d) {
        yourMove = a;
        computerMove = b;
        numberMoves = c;
        result = d;
    }

    int whoWin() {                            //algorithm that determines who won
        int x = (numberMoves - 1) / 2;        //count the number of wins / losses
        if (yourMove == computerMove) {
            result = 0;                       //draw case
        }
        int a = yourMove;
        for (int i = 1; i <= x; i++) {     //go right
            a++;
            if (a > numberMoves) {         //go to the beginning if we went beyond the number of moves
                a = 0;
                i--;
            }
            if (a == computerMove) {       //if the values match, it means lose
                result = 1;
            }
        }
        if (result != 0 && result != 1) {    //if not lose and not a draw, then victory
            result = 2;
        }
        return result;
    }
}
