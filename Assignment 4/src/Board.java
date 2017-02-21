import java.util.Arrays;
import java.util.Iterator;

public class Board {
    private final int[][] board;
    
    public Board(int[][] blocks){
        // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        board = new int[blocks.length][blocks[0].length];
        System.arraycopy(blocks, 0, board, 0, blocks.length);
    }
    
    public int dimension() { return board.length; }
    
    public int hamming() {
        // number of blocks out of place
        int dist;
        
        dist = 0;
        
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] != 0 && 
                    board[i][j] != goalBoard(i, j)) {
                    dist++;
                }
            }
        }
        return dist;
    }
    
    public int manhattan(){
        // sum of Manhattan distances between blocks and goal
        int dist;
        
        dist = 0;
        
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] != 0) {
                    int[] coords = goalBoard(board[i][j]);
                    dist += Math.abs(coords[0] - i) + 
                            Math.abs(coords[1] - j);
                }
            }
        }
        return dist;
    }
    
    public boolean isGoal() {
        // is this board the goal board?
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] != goalBoard(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks
        int[][] twinBoard = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                twinBoard[i][j] = board[i][j];
            }
        }
        twinBoard[0][0] = board[0][1];
        twinBoard[0][1] = board[0][0];
        return new Board(twinBoard);
    }
    
    @Override
    public boolean equals(Object y){
        // does this board equal y?
//        if (this == y)                  return true;
        if (y == null)                  return false;
        if (getClass() != y.getClass()) return false;
        
        Board comp = (Board) y;
        return Arrays.deepEquals(this.board, comp.board);
    }
    
    public Iterable<Board> neighbors(){
        return new Neighbors();
    }

    private int goalBoard(int r, int c) {
        // gets goalBoa
        int n = dimension();
        
        if (r == c && r == n-1) {
            return 0;
        } else {
            return r * n + c + 1;
        }
    }
    
    private int[] goalBoard(int val) {
        int n = dimension();
        if (val == 0) {
            return new int[] {n - 1, n - 1};
        } else {
            int r = (val - 1) / n;
            int c = (val - 1) % n;
            return new int[] {r, c};
        }
    }
    
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (j == dimension() - 1 &&
                    i != dimension() - 1) {
                    res = res + board[i][j] + "\n";
                } else {
                    res = res + board[i][j] + " ";
                }
            }
        }
        return res;
    }
    
    private class Neighbors implements Iterable<Board> {
        
        @Override
        public Iterator<Board> iterator() 
            { return new NeighborsIterator(); }

        private class NeighborsIterator implements Iterator<Board> {
            private final int[] zeroCoords;
            private int ni;
            
            public NeighborsIterator() {
                zeroCoords = findZero();
                ni = 0;
            }
            
            @Override
            public boolean hasNext() {
                advanceNi();
                return ni <= 3;
            }
                    
            @Override
            public Board next() {
                advanceNi();
                
                int[] swapCoords = getSwapCoords(ni);
                int n = Board.this.dimension();
                int[][] nextNeighbor = new int[n][n];
                
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        nextNeighbor[i][j] = Board.this.board[i][j];
                    }
                }

                nextNeighbor[zeroCoords[0]][zeroCoords[1]] = Board.this.board[swapCoords[0]][swapCoords[1]];
                nextNeighbor[swapCoords[0]][swapCoords[1]] = 0;
                ni++;
                return new Board(nextNeighbor);
            }
            
            private int[] findZero() {
                int n = Board.this.dimension();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (Board.this.board[i][j] == 0)
                            { return new int[]{i, j}; }
                    }
                }
                throw new Error("No zero found in the following board:\n" + Board.this);
            }
            
            private int[] getSwapCoords(int idx) {
                if (idx < 0 || idx > 3) {
                    throw new IllegalArgumentException("getSwapCoords("+idx+") called. Input value should be 0, 1, 2, or 3.");
                }
                
                switch (idx) {
                    case 0:
                        return new int[]{zeroCoords[0] - 1, zeroCoords[1]};
                    case 1:
                        return new int[]{zeroCoords[0], zeroCoords[1] + 1};
                    case 2:
                        return new int[]{zeroCoords[0] + 1, zeroCoords[1]};
                    case 3:
                        return new int[]{zeroCoords[0], zeroCoords[1] - 1};
                    default:
                        throw new Error("Something went wrong in getSwapCoords.");
                }
            }
            
            private void advanceNi() {
                if (ni > 3) {
                    return;
                }
                
                while (ni <= 3) {
                    int[] swapCoords = getSwapCoords(ni);
                    if (!coordInBounds(swapCoords[0]) ||
                        !coordInBounds(swapCoords[1])) {
                        ni++;
                    } else {
                        break;
                    }
                }
            }
            
            private boolean coordInBounds(int idx) {
                int n = Board.this.dimension();
                
                if (idx < 0 || idx >= n) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }    
    
    public static void main(String[] args){
//        Board b = new Board(new int[3][3]);
//        int[][] gb = b.goalBoard();
//        String[] gbStr = new String[gb.length];
//        for (int i = 0; i<gb.length; i++){
//            gbStr[i] = Arrays.toString(gb[i]);
//        }
//        System.out.println(Arrays.toString(gbStr));

//        Board b = new Board( new int[][]{ {8, 1, 3},
//                                          {4, 0, 2},
//                                          {7, 6, 5}} );
//        Board c = new Board( new int[][]{ {8, 1, 3},
//                                          {4, 2, 0},
//                                          {7, 6, 5}} );
//        System.out.println(b.equals(c));
//        System.out.println(b);
//        System.out.println("Hamming Distance = " + b.hamming());
//        System.out.println("Manhattan Distance = " + b.manhattan());

        int[][] a;
        a = new int[][]{{1, 2, 3, 4},
                        {5, 6, 8, 7},
                        {10,9, 11,15},
                        {12,13,14,0}};
        Board A = new Board(a);
        for (Board n : A.neighbors()) {
            System.out.println(n + "\n");
        }
    }
}