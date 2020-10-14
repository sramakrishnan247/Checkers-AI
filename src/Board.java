import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Board {
    static final int DARK = 1, LIGHT = 2, DARK_KING = 3, LIGHT_KING = 4, SIZE = 8;
    public int countLightKings = 0,countDarkKings = 0;

    //Config for some new game
    public int countLightPieces = 12, countDarkPieces = 12;
    int[][] board = new int[SIZE][SIZE];

    int playerColor = 2;

    public Board(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if((i == 0 || i == 2) && (j % 2 == 1)){
                    board[i][j] = DARK;
                }
                else if((i == 1) && (j % 2 == 0)){
                    board[i][j] = DARK;
                }
                else if((i == 5 || i == 7) && (j % 2 == 0)){
                    board[i][j] = LIGHT;
                }
                else if((i == 6) && (j % 2 == 1)){
                    board[i][j] = LIGHT;
                }
                else
                    board[i][j] = 0;
            }
        }
        //Try Random end games for debuggin purposes.
        //        board[1][4] = DARK;
        //
        //        board[2][5] = LIGHT;
        //
        //        board[3][2] = DARK;
        //        board[3][4] = DARK;
        //
        //        board[4][1] = LIGHT;
        //        board[4][3] = LIGHT;
        //        board[4][5] = LIGHT;
        //
        //        board[5][6] = LIGHT;
        //        board[6][1] = LIGHT;
        //        board[6][5] = LIGHT;
        //        board[7][0] = LIGHT;

    }

    public int[][] getBoard() {
        return board;
    }

    public int getCountDarkKings() {
        return countDarkKings;
    }

    public int getCountDarkPieces() {
        return countDarkPieces;
    }

    public int getCountLightKings() {
        return countLightKings;
    }

    public int getCountLightPieces() {
        return countLightPieces;
    }

    public void setCountLightPieces(int countLightPieces) {
        this.countLightPieces = countLightPieces;
    }

    public void setCountLightKings(int countLightKings) {
        this.countLightKings = countLightKings;
    }

    public void setCountDarkPieces(int countDarkPieces) {
        this.countDarkPieces = countDarkPieces;
    }

    public void setCountDarkKings(int countDarkKings) {
        this.countDarkKings = countDarkKings;
    }

    public void setBoard(int[][] board) {
        for(int i = 0; i < this.SIZE; i++){
            for(int j = 0;j < this.SIZE;j++){
                this.board[i][j] = board[i][j];
            }
        }
    }

    public int getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(int playerColor) {
        this.playerColor = playerColor;
    }

    //Copy constructor to get board clone.
    public Board(Board anotherBoard){
        this.setBoard(anotherBoard.getBoard());
        this.setCountDarkKings(anotherBoard.getCountDarkKings());
        this.setCountLightKings(anotherBoard.getCountLightKings());
        this.setCountDarkPieces(anotherBoard.getCountDarkPieces());
        this.setCountLightPieces(anotherBoard.getCountLightPieces());
        this.setPlayerColor(anotherBoard.getPlayerColor());
    }
    public void displayBoard() {
        for(int i = 0; i<8;i++){
            for(int j = 0; j < 8; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();

        }
        System.out.println();
    }

    // Returns contents of the square in a specified row and col
    int piecesAt(int row, int col){
        return board[row][col];
    }
    // Enumerate all possible options and get the legal moves for a given player.
    ArrayList<Move> getLegalMoves(){
        int curPlayer = getPlayerColor();
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        ArrayList<Tuple> playerSquares = new ArrayList<Tuple>();
        int king = (curPlayer == DARK ) ? DARK_KING: LIGHT_KING;
        //Enumerate all jumps
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(board[i][j] == curPlayer || board[i][j] == king){
                    int player = board[i][j];
                    playerSquares.add(new Tuple(i,j));
                    if(isValidJump(player, i,j,i+1,j+1, i+2,j+2)){
                        ArrayList<Move> validJumps = getJumpLeaves(player, i,j,i+1,j+1, i+2,j+2);
                        for(Move jump: validJumps) {
                            legalMoves.add(jump);
                        }
                    }
                    if(isValidJump(player, i,j,i-1,j-1, i-2,j-2)){
                        ArrayList<Move> validJumps = getJumpLeaves(player, i,j,i-1,j-1, i-2,j-2);
                        for(Move jump: validJumps) {
                            legalMoves.add(jump);
                        }
                    }
                    if(isValidJump(player, i,j,i+1,j-1, i+2,j-2)){
                        ArrayList<Move> validJumps = getJumpLeaves(player, i,j,i+1,j-1, i+2,j-2);
                        for(Move jump: validJumps) {
                            legalMoves.add(jump);
                        }
                    }
                    if(isValidJump(player, i,j,i-1,j+1, i-2,j+2)){
                        ArrayList<Move> validJumps = getJumpLeaves(player, i,j,i-1,j+1, i-2,j+2);
                        for(Move jump: validJumps) {
                            legalMoves.add(jump);
                        }
                    }
                }
            }
        }

        //Try normal moves if no jumps were found
        if(legalMoves.isEmpty()){
            for(Tuple square: playerSquares){
                int i = (int) square.x;
                int j = (int) square.y;
                int player = board[i][j];
                if(isValidMove(player, i,j,i+1,j+1))
                    legalMoves.add(new Move(i,j,i+1,j+1));
                if(isValidMove(player, i,j,i-1,j-1))
                    legalMoves.add(new Move(i,j,i-1,j-1));
                if(isValidMove(player, i,j,i+1,j-1))
                    legalMoves.add(new Move(i,j,i+1,j-1));
                if(isValidMove(player, i,j,i-1,j+1))
                    legalMoves.add(new Move(i,j,i-1,j+1));
            }
        }

        return legalMoves;

    }

    boolean isLeafNode(ArrayList<Move> neighbors, HashSet<String> visited){
        for(Move m: neighbors){
            String to = Integer.toString(m.destRow)+"#"+Integer.toString(m.destCol);
            if(!visited.contains(to))
                return false;
        }
        return true;
    }

    //
    ArrayList<Tuple> getJumpPath(HashMap<String, String> parent, String src, String dest){
        ArrayList<Tuple> path = new ArrayList<>();
        String key = dest;
        while (parent.containsKey(key)){
            int x = Character.getNumericValue(key.charAt(0));
            int y = Character.getNumericValue(key.charAt(2));
            Tuple square = new Tuple(x,y);
            path.add(square);
            key = parent.get(key);
        }

        int srcX = Character.getNumericValue(src.charAt(0));
        int srcY = Character.getNumericValue(src.charAt(2));
        Tuple srcSquare = new Tuple(srcX,srcY);
        path.add(srcSquare);

        return path;
    }

    //Returns the list of nodes that will be skipped after a jump move.
    //These squares will be set to empty subsequently.
    ArrayList<Tuple> getSkippedNodes(HashMap<String, String> parent, String src, String dest){
        ArrayList<Tuple> skipped = new ArrayList<>();

        String key = dest;
        while (parent.containsKey(key)){
            int px = Character.getNumericValue(key.charAt(0));
            int py = Character.getNumericValue(key.charAt(2));
            key = parent.get(key);
            int cx = Character.getNumericValue(key.charAt(0));
            int cy = Character.getNumericValue(key.charAt(2));

            int squareX = (int) (px+cx)/2;
            int squareY = (int) (py+cy)/2;
            Tuple square = new Tuple(squareX, squareY);
            skipped.add(square);
        }

        return skipped;
    }

    ArrayList<Move> getJumpLeaves(int player, int srcRow, int srcCol, int skipRow, int skipCol, int curRow, int curCol){

        Stack<Move> stack = new Stack<>();
        ArrayList<Move> validMoves = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String,String> parent = new HashMap<>();
        String srcNode = Integer.toString(srcRow)+"#"+Integer.toString(srcCol);
        String dstNode = Integer.toString(curRow)+"#"+Integer.toString(curCol);
        parent.put(dstNode,srcNode);

        stack.add(new Move(srcRow,srcCol,curRow,curCol));
        visited.add(srcNode);

        while(!stack.empty()){
            Move top = stack.pop();
            ArrayList<Move> neighbors = getLegalJumps(player, top.destRow, top.destCol);
            String from = Integer.toString(top.destRow)+"#"+Integer.toString(top.destCol);

            visited.add(Integer.toString(top.srcRow)+"#"+Integer.toString(top.srcCol));
            visited.add(Integer.toString(top.destRow)+"#"+Integer.toString(top.destCol));

            if(neighbors.isEmpty() || isLeafNode(neighbors,visited)){
                ArrayList<Tuple> path = getJumpPath(parent,srcNode,from);
                ArrayList<Tuple> skipped = getSkippedNodes(parent,srcNode,from);
                Move newValidMove = new Move(srcRow,srcCol,top.destRow,top.destCol, skipped,path,true);

                validMoves.add(newValidMove);
                visited.remove(from);
            }

            for(Move m : neighbors){
                String to = Integer.toString(m.destRow)+"#"+Integer.toString(m.destCol);

                  if(!visited.contains(to)){
                    stack.push(m);
                    parent.put(to,from);
                }

            }
        }
        if(validMoves.isEmpty()){
            validMoves.add(new Move(srcRow,srcCol,curRow,curCol));
        }

        return validMoves;
    }

    ArrayList<Move> getLegalJumps(int player, int i, int j){
        int current = board[i][j];
        this.board[i][j] = player;
        ArrayList<Move> legalMoves = new ArrayList<>();
        int king = (player == DARK ) ? DARK_KING: LIGHT_KING;
        if(true){
            if(isValidJump(player, i,j,i-1,j-1, i-2,j-2))
                legalMoves.add(new Move(i,j,i-2,j-2));
            if(isValidJump(player, i,j,i+1,j+1, i+2,j+2))
                legalMoves.add(new Move(i,j,i+2,j+2));
            if(isValidJump(player, i,j,i+1,j-1, i+2,j-2))
                legalMoves.add(new Move(i,j,i+2,j-2));
            if(isValidJump(player, i,j,i-1,j+1, i-2,j+2))
                legalMoves.add(new Move(i,j,i-2,j+2));
        }

        this.board[i][j] = current;
        return legalMoves;
    }

    //Check if (x,y) is outside the checkerboard
    boolean isOutOfBounds(int x, int y){
        if(x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return true;
        return false;
    }

    boolean isFilled(int x, int y){
        return board[x][y] != 0;
    }

    // Check if move from (srcRow,srcCol) -> (destRow, destCol) is legal
    boolean isValidMove(int player, int srcRow, int srcCol, int destRow, int destCol){
        if(isOutOfBounds(destRow,destCol))
            return false;
        if(isFilled(destRow,destCol))
            return false;
        if(player == DARK || player == DARK_KING){
            if(board[srcRow][srcCol] == DARK && srcRow > destRow)
                return false;//DARK PAWN cannot move up!
            else
                return true;//DARK KING can move in any direction.
        }
        if(player == LIGHT || player == LIGHT_KING) {
            if (board[srcRow][srcCol] == LIGHT && srcRow < destRow)
                return false;//LIGHT PAWN cannot move down!
            else
                return true;//LIGHT KING can move in any direction.
        }
        return false;
    }

    // Check if jump from (srcRow,srcCol) -> (skipRow, skipCol) ->(destRow, destCol) is legal
    boolean isValidJump(int player, int srcRow, int srcCol, int skipRow, int skipCol, int destRow, int destCol){
        if(isOutOfBounds(destRow,destCol))
            return false;
        if(isFilled(destRow, destCol))
            return false;
        if(!isFilled(skipRow,skipCol))
            return false;
        if(player == DARK || player == DARK_KING){
            if(board[srcRow][srcCol] == DARK && srcRow > destRow)
                return false;//DARK PAWN cannot move up!
            if(board[skipRow][skipCol] == DARK || board[skipRow][skipCol] == DARK_KING)
                return false; //DARK pieces cannot skip DARK pieces!
            else
                return true;//DARK pieces can skip LIGHT pieces.
        }
        if(player == LIGHT || player == LIGHT_KING) {
            if (board[srcRow][srcCol] == LIGHT && srcRow < destRow)
                return false;//LIGHT PAWN cannot move down!
            if(board[skipRow][skipCol] == LIGHT || board[skipRow][skipCol] == LIGHT_KING )
                return false; //LIGHT pieces cannot skip LIGHT pieces!
            else
                return true;//DARK pieces can skip LIGHT pieces.
        }
        return false;
    }

    void performMove(Move move){

        int srcRow, srcCol, destRow, destCol;
        srcRow = move.srcRow;
        srcCol = move.srcCol;
        destRow = move.destRow;
        destCol = move.destCol;
        int current = board[srcRow][srcCol];

        //Promote pawn to king
        if(destRow == SIZE-1 && current == DARK){
            current = DARK_KING;
            countDarkKings+=1;
        }
        else if(destRow == 0 && current == LIGHT){
            current = LIGHT_KING;
            countLightKings+=1;
        }

        board[destRow][destCol] = current;
        board[srcRow][srcCol] = 0;

        //Remove skipped moves for jumps
        if(!move.skipped.isEmpty()){
            for(Tuple square : move.skipped){
                int row = (int) square.x;
                int col = (int) square.y;

                int removedPiece = board[row][col];

                if(removedPiece == DARK || removedPiece == DARK_KING){
                    if(removedPiece == DARK_KING)
                        countDarkKings-=1;
                    countDarkPieces-=1;
                }
                if(removedPiece == LIGHT || removedPiece == LIGHT_KING){
                    if(removedPiece == LIGHT_KING)
                        countLightKings-=1;
                    countLightPieces-=1;
                }
                board[row][col] = 0;
            }
        }

        //Alter color after each move
        if(this.playerColor == DARK){
            this.playerColor = LIGHT;
        }
        else{
            this.playerColor = DARK;
        }

        return;
    }

    double heuristic(){
//        System.out.println(countDarkKings+","+countDarkPieces+","+countLightKings+","+countDarkKings);
        return -countLightPieces + countDarkPieces - (countLightKings*0.5 - countDarkKings*0.5);
    }

    int getWinner(){
        System.out.println("Light(Player) Pieces: "+Integer.toString(getCountLightPieces())+","
                +"Dark(AI) Pieces: " + Integer.toString(getCountDarkPieces()));
        if(getCountDarkPieces() <=0 ){
            return LIGHT;
        }
        else if(getCountLightPieces() <= 0){
            return DARK;
        }
        ArrayList<Move> legalMoves = getLegalMoves();
        if(legalMoves.isEmpty())
            return -1;
        return 0;
    }

    boolean isGameOver(){
        if((getCountLightPieces() == 0) || getCountDarkPieces() == 0)
            return true;
        return false;
    }

}
