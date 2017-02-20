import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private MinPQ<SearchNode> q;
    
    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        q = new MinPQ<SearchNode>();
        q.insert(new SearchNode(initial, null, 0));
    }
    public boolean isSolvable()            // is the initial board solvable?
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    
    private class Solution implements Iterable<Board> {
        
        @Override
        public Iterator<Board> iterator(){
            
        }
        
        private class SolutionStep implements java.util.Iterator<Board> {
            
            public boolean hasNext()
            public Board next() {
                if (Solver.this.q.min().nmoves == 0) {
                    return Solver.this.q.min().board;
                } else if (Solver.this.q.min{
                    SearchNode minNode = Solver.this.q.delMin();
                    for (Board b : minNode.board.neighbors()) {
                        Solver.this.q.insert(new SearchNode(b, minNode.board, minNode.nmoves + 1));
                    }
                    return Solver.this.q.min().board;
                }
            }
        }
            
    }
    
    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int nmoves;
        private final Board prevBoard;
        
        public SearchNode(Board currBoard, Board prevBoard, int numMoves) {
            board = currBoard;
            this.prevBoard = prevBoard;
            nmoves = numMoves;
        }
        
        @Override
        public int compareTo(SearchNode that){
            int man1 = this.board.manhattan();
            int man2 = that.board.manhattan();
            
            if       (man1 < man2)     return -1;
            else if  (man1 > man2)     return  1;
            else                       return  0;
        }
        
    }
    
    public static void main(String[] args) // solve a slider puzzle (given below)
}