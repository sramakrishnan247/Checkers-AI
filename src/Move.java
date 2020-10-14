import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicReference;

public class Move {
    int srcRow, srcCol, destRow, destCol;
    ArrayList<Tuple> path;
    ArrayList<Tuple> skipped;
    boolean jump;
    String notation;

    Move(){ }
    Move(int srcRow, int srcCol, int destRow, int destCol){
        this.srcRow = srcRow;
        this.srcCol = srcCol;
        this.destRow = destRow;
        this.destCol = destCol;
        this.skipped = new ArrayList<>();
        this.path = new ArrayList<>();
        this.jump = Math.abs(srcRow - destRow) == 2;

        //Fill the basic path from src to dest
        Tuple srcNode = new Tuple(srcRow,srcCol);
        Tuple destNode = new Tuple(destRow,destCol);
        this.path.add(destNode);
        this.path.add(srcNode);

        this.notation = generateMoveNotation("-");
    }

    private String generateMoveNotation(String delimiter){
        String pathString = "";
        for(int j = path.size() - 1; j >= 0;j--){
            Tuple point = path.get(j);
            int square = Game.getNumberFromGrid(point);
            pathString += Integer.toString(square);
            pathString += delimiter;
        }
        pathString = pathString.substring(0,pathString.length() - 1);
        return pathString;
    }

    Move(int srcRow, int srcCol, int destRow, int destCol, ArrayList<Tuple>skipped, ArrayList<Tuple>path, boolean jump){
        this.srcRow = srcRow;
        this.srcCol = srcCol;
        this.destRow = destRow;
        this.destCol = destCol;
        this.skipped = skipped;
        this.path = path;
        this.jump = Math.abs(srcRow - destRow) == 2;
        this.notation = generateMoveNotation("x");
    }

    public String printMove() {
        String fromTo =  "Source: "+this.srcRow+","+this.srcCol+",  Dest: "+this.destRow+","+this.destCol + " Jump: "+this.jump;
        AtomicReference<String> skippedSquares = new AtomicReference<>("\nSkipped squares: ");
        for(Tuple t: skipped){
            skippedSquares.set(skippedSquares + "(" + t.x + "," + t.y + "),");
        }
        return fromTo + skippedSquares + "\nMove notation: " + notation;
    }
}
