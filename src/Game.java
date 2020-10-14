import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Game {

    private static HashMap<String,String> numberToGrid;
    private static HashMap<String,String> gridToNumber;

    public Game(){
        fillNumberToGrid();
        fillGridToNumber();

    }
    void fillNumberToGrid(){
        numberToGrid = new HashMap<>();
        numberToGrid.put("1","0+1");
        numberToGrid.put("2","0+3");
        numberToGrid.put("3","0+5");
        numberToGrid.put("4","0+7");

        numberToGrid.put("5","1+0");
        numberToGrid.put("6","1+2");
        numberToGrid.put("7","1+4");
        numberToGrid.put("8","1+6");

        numberToGrid.put("9","2+1");
        numberToGrid.put("10","2+3");
        numberToGrid.put("11","2+5");
        numberToGrid.put("12","2+7");

        numberToGrid.put("13","3+0");
        numberToGrid.put("14","3+2");
        numberToGrid.put("15","3+4");
        numberToGrid.put("16","3+6");

        numberToGrid.put("17","4+1");
        numberToGrid.put("18","4+3");
        numberToGrid.put("19","4+5");
        numberToGrid.put("20","4+7");

        numberToGrid.put("21","5+0");
        numberToGrid.put("22","5+2");
        numberToGrid.put("23","5+4");
        numberToGrid.put("24","5+6");

        numberToGrid.put("25","6+1");
        numberToGrid.put("26","6+3");
        numberToGrid.put("27","6+5");
        numberToGrid.put("28","6+7");

        numberToGrid.put("29","7+0");
        numberToGrid.put("30","7+2");
        numberToGrid.put("31","7+4");
        numberToGrid.put("32","7+6");

    }
    void fillGridToNumber(){
        gridToNumber = new HashMap<>();
        gridToNumber.put("0+1","1");
        gridToNumber.put("0+3","2");
        gridToNumber.put("0+5","3");
        gridToNumber.put("0+7","4");

        gridToNumber.put("1+0","5");
        gridToNumber.put("1+2","6");
        gridToNumber.put("1+4","7");
        gridToNumber.put("1+6","8");

        gridToNumber.put("2+1","9");
        gridToNumber.put("2+3","10");
        gridToNumber.put("2+5","11");
        gridToNumber.put("2+7","12");

        gridToNumber.put("3+0","13");
        gridToNumber.put("3+2","14");
        gridToNumber.put("3+4","15");
        gridToNumber.put("3+6","16");

        gridToNumber.put("4+1","17");
        gridToNumber.put("4+3","18");
        gridToNumber.put("4+5","19");
        gridToNumber.put("4+7","20");

        gridToNumber.put("5+0","21");
        gridToNumber.put("5+2","22");
        gridToNumber.put("5+4","23");
        gridToNumber.put("5+6","24");

        gridToNumber.put("6+1","25");
        gridToNumber.put("6+3","26");
        gridToNumber.put("6+5","27");
        gridToNumber.put("6+7","28");

        gridToNumber.put("7+0","29");
        gridToNumber.put("7+2","30");
        gridToNumber.put("7+4","31");
        gridToNumber.put("7+6","32");
    }

    public static int getNumberFromGrid(Tuple t){
        int x = (int) t.x;
        int y = (int) t.y;
        String key = Integer.toString(x)+"+"+Integer.toString(y);
        return Integer.parseInt(gridToNumber.get(key));
    }

    public static Tuple getGridFromNumber(String number){
        String val = numberToGrid.get(number);
        int x = Integer.parseInt(String.valueOf(val.charAt(0)));
        int y = Integer.parseInt(String.valueOf(val.charAt(2)));
        return new Tuple(x,y);
    }

    public static void main(String[] args) {

        Game game = new Game();
        Board board = new Board();
        int depth = 0;
        System.out.println("Welcome!");
        System.out.println("Pick the difficulty!");
        System.out.println("1.Rookie");
        System.out.println("2.Intermediate");
        System.out.println("3.Pro");
        System.out.println("4.Grandmaster");
        Scanner scanner = new Scanner(System.in);
        String difficultyLevel = scanner.nextLine();
        HashSet<String> difficultySet = new HashSet<>();
        difficultySet.add("1");
        difficultySet.add("2");
        difficultySet.add("3");
        difficultySet.add("4");

        while(!difficultySet.contains(difficultyLevel)){
            System.out.println("Pick the difficulty(1-4)!");
            difficultyLevel = scanner.nextLine();

        }
        switch (difficultyLevel){
            case "1":depth = 1;break;
            case "2":depth = 3;break;
            case "3":depth = 5;break;
            case "4": depth = 9;break;
        }

        AI miniMax = new AI(depth);
        System.out.println("Player color: "+"2");
        System.out.println("AI color: "+"1");
        System.out.println("Player King: "+"4");
        System.out.println("AI King: "+"3");


        while(true){

            int winner = board.getWinner();
            if (gameOver(board, winner)) break;

            ArrayList<Move> actions = board.getLegalMoves();
            HashMap<String, Move> notationToMove = new HashMap<>();


            System.out.println("Current Board");
            board.displayBoard();
            System.out.println("Available Moves: ");
            int actionIndex = 1;
            for(Move action: actions){
                System.out.println(Integer.toString(actionIndex)+ ": " + action.notation);
                notationToMove.put(Integer.toString(actionIndex), action);
                actionIndex += 1;
            }

            System.out.println("Please choose move number:");
            String notation = scanner.nextLine();

            while(!notationToMove.containsKey(notation)){
                System.out.println("Please enter a valid number!");
                notation = scanner.nextLine();
            }

            board.performMove(notationToMove.get(notation));
            System.out.println("Player Played last...");

            //Check if the game is over!
            winner = board.getWinner();
            if (gameOver(board, winner)) break;

            //AI plays the move
            System.out.println("AI thinking...");
            miniMax.move(board);
            System.out.println("AI PLAYED LAST...");
            board.displayBoard();

        }

        return;

    }

    private static boolean gameOver(Board board, int winner) {
        if(winner!=0){
            System.out.println("Final Board Status");
            board.displayBoard();
            System.out.println(winner);
            if(winner == 1){
                System.out.println("AI WINS!");
            }
            else if(winner == 2){
                System.out.println("Congrats! You win!");
            }
            else{
                System.out.println("Game drawn. No moves left!");
            }

            return true;
        }
        return false;
    }
}
