import java.util.ArrayList;
import java.util.Random;

public class AI {

    private int player;
    private int depth;

    public AI(int depth){
        this.depth = depth;
        this.player = 1;
    }

    public AI(int depth, int player){
        this.depth = depth;
        this.player = player;
    }

    public Board randomAI(Board board){
        ArrayList<Move> legalMoves = board.getLegalMoves();
        Random random = new Random();
        int randomIndex = random.nextInt(legalMoves.size());
        board.performMove(legalMoves.get(randomIndex));
        return board;
    }

    //Try every legal move possible and generate a list of possible board states.
    private ArrayList<Board> getSuccessors(Board board){
        ArrayList<Move> moves = board.getLegalMoves();
        ArrayList<Board> successors = new ArrayList<>();
        for(Move m: moves){
            Board tempBoard =  new Board(board);
            tempBoard.performMove(m);
            successors.add(tempBoard);
        }
        return successors;
    }

    //AI move() will be called from the Game class
    public Board move(Board state){
        Move action =  makeDecision(state);
        System.out.println(action.printMove());
        state.performMove(action);
        return state;
    }

    //Chooses best action based on minimax
    //Based on AIMA implementation.
    private Move makeDecision(Board state){
        double resultValue = Double.NEGATIVE_INFINITY;
        int player = 1;
        Move result = new Move();
        ArrayList<Move> actions = state.getLegalMoves();
        for(Move action: actions){
            Board nextState =  new Board(state);
            nextState.performMove(action);
            double value = minValue(nextState, depth, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY );
//            nextState.displayBoard();
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
        }
        System.out.println("Heuristic val(AI is winning if +ve): "+Double.toString(resultValue));
        return result;

    }

    private double minValue(Board state, int depth, double alpha, double beta) {
        if(depth == 0 || state.isGameOver()) {
            return state.heuristic();
        }
        double value = Double.POSITIVE_INFINITY;
        for(Move action: state.getLegalMoves()){
            Board nextState =  new Board(state);
            nextState.performMove(action);
            value = Math.min(value, maxValue(
                    nextState,
                    depth-1,
                    alpha,
                    beta
            ));
            if(value <= alpha)
                return value;
            beta = Math.min(beta, value);
        }
        return value;
    }

    private double maxValue(Board state, int depth, double alpha, double beta) {
        if(depth == 0 || state.isGameOver()) {
            return state.heuristic();
        }
        double value = Double.NEGATIVE_INFINITY;
        for(Move action: state.getLegalMoves()){
            Board nextState =  new Board(state);
            nextState.performMove(action);
            value = Math.max(value, minValue(
                    nextState,
                    depth-1,
                    alpha,
                    beta
            ));
            if(value >= beta)
                return value;
            alpha = Math.max(alpha, value);
        }
        return value;
    }
}

