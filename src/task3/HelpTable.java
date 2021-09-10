package task3;

public class HelpTable {
    int numberMoves;

    public HelpTable(int a) {
        numberMoves = a;
    }

    void writeTable() {
        int result=-1;
        System.out.printf("  \\ User ");
        for (int i = 1; i <= numberMoves; i++) {     //draw a table
            System.out.printf("|      ");
        }
        System.out.println();
        System.out.printf("PC \\     ");
        for (int i = 1; i <= numberMoves; i++) {
            int num = 5 - ((int) Math.log10(i) + 1);      //these lines calculate, by length i, how many spaces to print
            System.out.printf("| " + i);
            for (int x = 1; x <= num; x++) {
                System.out.printf(" ");
            }
        }
        System.out.println();
        for(int i=1; i<=numberMoves;i++){
            System.out.printf("---------");
            for(int j=1;j<=numberMoves;j++){
                System.out.printf("+------");
            }
            System.out.println();

            System.out.printf("  "+i);
            int num = 7 - ((int) Math.log10(i) + 1);
            for (int x = 1; x <= num; x++) {
                System.out.printf(" ");
            }
            for(int j=1;j<=numberMoves;j++){
                result=-1;
                WinOrLose winOrLose = new WinOrLose(j,i,numberMoves,result);
                result=winOrLose.whoWin();                       //by using winOrLose determine what to write
                if (result==0) System.out.printf("| DRAW ");
                if (result==1) System.out.printf("| LOSE ");
                if (result==2) System.out.printf("| WIN  ");
            }
            System.out.println();
        }


    }
}
