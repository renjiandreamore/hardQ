
class Node{
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    /**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
    public int[] dx = {1,0,-1,0};
    public int[] dy = {0,1,0,-1};
    
    public void surroundedRegions(char[][] board) {
        // Write your code here
      /*
        x x x x x            x x x x x  
        x o o o x            x x x x x
        x x o o x      -->   x x x x x  
        o o x x o            o o x x o
        o x x o x            o x x o x
    */
        if(board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        for(int i = 0; i < m; i++) {
            bfs(board, i, 0);
            bfs(board, i, n - 1);
        }
        
        for(int i = 1; i < n - 1; i++){
            bfs(board, 0, i);
            bfs(board, m - 1, i);
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'F'){
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    public List<Node> expand(char[][] board, Node node){
        List<Node> list = new ArrayList<>();
        
        for(int i = 0; i < dx.length; i++){
            int x = node.x + dx[i];
            int y = node.y + dy[i];
            
            if(x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O'){
                board[x][y] = 'T'; //is neighbor of node
                list.add(new Node(x,y));
            }
        }
        
        return list;
    }
    
    public void bfs(char[][] board, int x, int y){
        if(board[x][y] != 'O'){
            return;
        }
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));
        
        while(!q.isEmpty()){
            Node head = q.poll();
            board[head.x][head.y] = 'F'; // free
            
            for(Node n : expand(board, head)){
                q.offer(n);
            }
        }
    }
  
  
  
  
  
  
  
}
