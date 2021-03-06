
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
  
  
  /*
  Given org = [1,2,3], seqs = [[1,2],[1,3]]
  Return false
  Explanation:
  [1,2,3] is not the only one sequence that can be reconstructed, 
  because [1,3,2] is also a valid sequence that can be reconstructed.

  Given org = [1,2,3], seqs = [[1,2]]
  Return false
  Explanation:
  The reconstructed sequence can only be [1,2].

  Given org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
  Return true
  Explanation:
  The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
  */
  
  //本质是BFS找拓扑排序，看能否按照其顺序全部排完
  public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // Write your code here
        if((seqs.length == 0 || seqs[0].length == 0) && org.length > 0 ) return false;
        if(org.length == 0 && seqs[0].length == 0) return true;
        
        HashMap<Integer, Set<Integer>> neighbors = new HashMap<>();
        HashMap<Integer, Integer> indegree = new HashMap<>();
        
        for(int i : org) {
            neighbors.put(i, new HashSet<Integer>());
            indegree.put(i,0);
        }
        
        int n = org.length;
        for(int i = 0; i < seqs.length; i++) {
            if(seqs[i].length > 0 && (seqs[i][0] < 1 || seqs[i][0] > n)) return false;
            for(int j = 1; j < seqs[i].length; j++) {
                if(seqs[i][j] < 1 || seqs[i][j] > n) return false;
                if(!neighbors.get(seqs[i][j-1]).contains(seqs[i][j])){
                    neighbors.get(seqs[i][j-1]).add(seqs[i][j]);
                    indegree.put(seqs[i][j], indegree.get(seqs[i][j])+1);
                }
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i : org) {
            if(indegree.get(i) == 0){
                q.offer(i);
                break;
            }
        }
        
        int cnt = 0;
        while(!q.isEmpty()) {
            if(q.size()>1) return false;
            int top = q.poll();
            cnt++;
            for(int neighbor : neighbors.get(top)) {
                indegree.put(neighbor, indegree.get(neighbor)-1);
                if(indegree.get(neighbor) == 0){
                    q.offer(neighbor);
                }
            }
        }
        
        return cnt == n;
    }
    
    
    
    
    
    //邮局问题
    //邮局1
    /*
    Given a grid:

0 1 0 0
1 0 1 1
0 1 0 0
return 6. (Placing a post office at (1,1), the distance that post office to all the house sum is smallest.)


    */
    public int shortestDistance(int[][] grid) {
        // Write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        List<Integer> sumX = new ArrayList<Integer>();
        List<Integer> sumY = new ArrayList<Integer>();
        
        int houses = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    houses++;
                    x.add(i);
                    y.add(j);
                }
            }
        }
        if(houses == m*n) return -1;
        
        Collections.sort(x);
        Collections.sort(y);
        sumX.add(x.get(0));
        sumY.add(y.get(0));
        
        for(int i = 1; i < x.size(); i++) {
            sumX.add(sumX.get(i-1) + x.get(i));
            sumY.add(sumY.get(i-1) + y.get(i));
        }
        
        int res = 0x7fffffff;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    int getDistance_x = getDistanceSum(sumX, x, i, houses);
                    int getDistance_y = getDistanceSum(sumY, y, j, houses);
                    if(getDistance_x + getDistance_y < res) {
                        res = getDistance_x + getDistance_y;
                    }
                }
            }
        }
        
        return res;
    }
    
    public int getDistanceSum(List<Integer> sumX, List<Integer> x, int pos, int n){
        if(pos <= x.get(0)){
            return sumX.get(n-1) - n*pos;
        }
        
        int l = 0, r = n - 1;
        while(l + 1 < r) {
            int mid = (l + r)/2;
            if(x.get(mid) <= pos) {
                l = mid;
            }else{
                r = mid;
            }
        }
        
        int index = 0;
        if(x.get(r) <= pos) index = r;
        else index = l; //index+1 means the num of houses whose location <= pos
        
        int largerThan = sumX.get(n-1) - sumX.get(index) - (n - index - 1)*pos;
        int lessThan = (index + 1)*pos - sumX.get(index);
        
        return largerThan + lessThan;
    }
    
    //建立postofficeII     有障碍，不能越过，要用BFS
     /**
     * @param grid a 2D grid
     * @return an integer
     Given a grid:

0 1 0 0 0
1 0 0 2 1
0 1 0 0 0
return 8, You can build at (1,1). (Placing a post office at (1,1), 
the distance that post office to all the house sum is smallest.)
     */
    public int[] dx = {1,0,-1,0};
    public int[] dy = {0,1,0,-1};
    int[][] visitedTimes;
    int[][] pathSum;
    public int shortestDistance(int[][] grid) {
        // Write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        
        int m = grid.length, n = grid[0].length;
        List<Point> houses = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    houses.add(new Point(i,j));
                }
            }
        }
        
        if(houses.size() == m*n) return -1;
        this.visitedTimes = new int[m][n];
        this.pathSum = new int[m][n];
        
        for(Point p : houses) {
            bfs(p, visitedTimes, pathSum, grid);
        }
        
        List<Point> empties = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    empties.add(new Point(i,j));
                }
            }
        }
        
        int res = 0x7fffffff;
        for(Point empty : empties) {
            if(visitedTimes[empty.x][empty.y] != houses.size()){
                continue;
            }
            res = Math.min(res, pathSum[empty.x][empty.y]);
        }
        if(res == 0x7fffffff) return -1;
        
        return res;
    }
    
    public void bfs(Point start, int[][] visitedTimes, int[][] pathSum, int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<Point> q = new LinkedList<>();
        boolean[][] hash = new boolean[m][n];
        
        q.offer(start);
        hash[start.x][start.y] = true;
        
        int steps = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            steps++;
            for(int i = 0; i < size; i++) {
                Point p = q.poll();
                for(int j = 0; j < 4; j++) {
                    int ix = p.x + dx[j];
                    int iy = p.y + dy[j];
                    if(isValid(grid, ix, iy) && !hash[ix][iy]) {
                        q.offer(new Point(ix, iy));
                        hash[ix][iy] = true;
                        visitedTimes[ix][iy]++;
                        pathSum[ix][iy] += steps;
                    }
                }
            }
        }
    }
    
    public boolean isValid(int[][] grid, int i, int j) {
        if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 0) return true;
        return false;
    }
  
}


                                    //并查集                               UnionFind
                                                      //路径压缩
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Island2 {
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        if(operators == null || operators.length == 0 || n <= 0 || m <= 0) return res;
        
        int[][] island = new int[n][m];
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        
        UnionFind uf = new UnionFind(n, m);
        
        int count = 0;
        for(Point point : operators) {
            int x = point.x, y = point.y;
            int idA = getId(x, y, m);
            
            if(island[x][y] != 1) {
                island[x][y] = 1;
                count++;
                
                Queue<Point> queue = new LinkedList<>();
                queue.offer(point);
                while(!queue.isEmpty()) {
                    Point ild = queue.poll();
                    for(int i = 0; i < 4; i++) {
                        int ix = ild.x + dx[i];
                        int iy = ild.y + dy[i];
                        int idB = getId(ix, iy, m);
                        if(valid(island, ix, iy)) {
                            int parentA = uf.compressed_find(idA);
                            int parentB = uf.compressed_find(idB);
                            if(parentA != parentB) {
                                uf.union(parentA, parentB);
                                count--;
                            }
                        }
                    }
                }
                res.add(count);
            }
        }
        
        return res;
    }
    
    public int getId(int i, int j, int m) {
        return i*m + j;
    }
    
    class UnionFind{
        public HashMap<Integer, Integer> father;
        public int count;
        public UnionFind(int n, int m) {
            father = new HashMap<>();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    int id = getId(i, j, m);
                    father.put(id, id);
                }
            }
            this.count = m * n;
        }
        
        public int getNum() {
            return count;
        }
        
        public boolean isConnected(int id1, int id2) {
            return compressed_find(id1) == compressed_find(id2);
        }
        
        public int compressed_find(int id) { //find是找祖先，father只是父亲
            int ancestor = fahter.get(id);
            while(father.get(ancestor) != ancestor){
                ancestor = father.get(ancestor);
            }
            //路径压缩
            int fa = id;
            while(father.get(fa) != fa) {
                int tmp = father.get(fa);
                father.put(fa, ancestor);
                fa = tmp;
            }
            //end路径压缩
            
            return ancestor;
        }
        
        public void union(int a, int b) {
            int pa = compressed_find(a);
            int pb = compressed_find(b);
            
            if(pa != pb) {
                father.put(pa, pb); 
            }
        }
    }
    //a想与b搞上关系（a要连b），a说，这么着，我又不好意思，那我让我的祖先，
    //管您的祖先，叫声爹！这样您就跟我连上关系吧，我们俩之间也不用有什么纠结.
    
    public boolean valid(int[][] island, int i, int j) {
        if(i >= 0 && i < island.length && j >= 0 && j < island[0].length && island[i][j] == 1) return true;
        return false;
    }
}
