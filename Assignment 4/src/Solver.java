import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;

public class Solver {
    private SearchNode root;
    private SearchNode finalNode;
    
    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        root = new SearchNode(initial, null, 0);
        solve();
    }
    public boolean isSolvable() { return finalNode != null; }
    
    public int moves(){
        // min number of moves to solve initial board; -1 if unsolvable
        if (isSolvable()) { return finalNode.nmoves; }
        else              { return -1; }
    }
    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if (!isSolvable()) { return null; }
        else               { return new Solution(); }
    }
    
    private class Solution implements Iterable<Board> {
        
        @Override
        public Iterator<Board> iterator(){
            return new SolutionStep();
        }
        
        private class SolutionStep implements Iterator<Board> {
            Stack<SearchNode> nodeStack;
            
            public SolutionStep() {
                SearchNode currNode = Solver.this.finalNode;
                
                nodeStack = new Stack<SearchNode>();
                
                while (currNode.prevNode != null) {
                    nodeStack.push(currNode);
                    currNode = currNode.prevNode;
                }
                nodeStack.add(root);
            }
            
            @Override
            public boolean hasNext() { return !nodeStack.isEmpty(); }
            
            @Override
            public Board next() { return nodeStack.pop().board; }
        }
    }
    
    private void solve() {
        MinPQ<SearchNode> q = new MinPQ<>();
        MinPQ<SearchNode> twinQ = new MinPQ<>();
        SearchNode twinRoot = new SearchNode(root.board.twin(), null, 0);
        int n = root.board.dimension();
        Board goal = goalBoard(n);
        
        SearchNode twinCurrNode = twinRoot;
        twinQ.insert(twinRoot);
        
        SearchNode currNode = root;
        q.insert(root);
        
        while (!currNode.board.equals(goal) &&
               !twinCurrNode.board.equals(goal)) {
            twinCurrNode = twinQ.delMin();
            currNode = q.delMin();
            
//            System.out.println(currNode.board+"\n");
//            System.out.println(q.size());

            for (Board b : twinCurrNode.board.neighbors()) {
                if (twinCurrNode.prevNode == null ||
                    !b.equals(twinCurrNode.prevNode.board)) {
                    SearchNode newNode = new SearchNode(b, twinCurrNode, twinCurrNode.nmoves + 1);
                    twinQ.insert(newNode);
                    twinCurrNode.addNeighbor(newNode);
                }
            }

            for (Board b : currNode.board.neighbors()) {
                if (currNode.prevNode == null ||
                    !b.equals(currNode.prevNode.board)) {
                    SearchNode newNode = new SearchNode(b, currNode, currNode.nmoves + 1);
                    q.insert(newNode);
                    currNode.addNeighbor(newNode);
                }
            }
        }
        if (currNode.board.equals(goal)) {
            finalNode = currNode;
        } else {
            finalNode = null;
        }
    }
    
    private static Board goalBoard(int n) {
        int[][] g = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = i * n + j + 1;
            }
        }
        g[n-1][n-1] = 0;
        return new Board(g);
    }
    
    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int nmoves;
        private final SearchNode prevNode;
        private ArrayList<SearchNode> neighbors;
        
        public SearchNode(Board currBoard, SearchNode prevNode, int numMoves) {
            board = currBoard;
            this.prevNode = prevNode;
            nmoves = numMoves;
            neighbors = new ArrayList<SearchNode>();
        }
        
        public void addNeighbor(SearchNode neighbor) {
            neighbors.add(neighbor);
        }
        
        @Override
        public int compareTo(SearchNode that){
            int man1 = this.board.manhattan() + nmoves;
            int man2 = that.board.manhattan() + that.nmoves;
            
            if       (man1 < man2)     return -1;
            else if  (man1 > man2)     return  1;
            else                       return  0;
        }
    }

    
    public static void main(String[] args) {
        Board b = new Board( new int[][]{ {1, 8, 3},
                                          {4, 0, 2},
                                          {6, 7, 5}} );
        Solver s = new Solver(b);
        SearchNode c = s.finalNode;
        System.out.println(s.moves() + " moves");
        for (Board bi : s.solution()) {
            System.out.println(bi + "\n");
        }
//        while (c.prevNode != null) {
//            System.out.println(c.board+"\n");
//            c = c.prevNode;
//        }
//        System.out.println(c.board+"\n");
    }
}