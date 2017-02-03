public class Solution {
    /**
     * @param n n pairs
     * @return All combinations of well-formed parentheses
     给定 n = 3, 可生成的组合如下:

     "((()))", "(()())", "(())()", "()(())", "()()()"
     */
    public ArrayList<String> generateParenthesis(int n) {
        // Write your code here
        ArrayList<String> res = new ArrayList<>();
        if(n <= 0) return res;
        
        helper(res, "", n, n);
        
        return res;
    }
    
    public void helper(ArrayList<String> res, String pare, int left, int right){
        if(left == 0 && right == 0){
            res.add(pare);
            return;
        }
        
        if(left > 0){
            //pare += "(";
            helper(res, pare +"(", left - 1, right);
        }
        
        if(right > 0 && left < right){
            //pare += ")";
            helper(res, pare +")", left, right - 1);
        }
    }
    
    
    
    
    //paint house2 
     /**
     * @param costs n x k cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // Write your code here
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        
        int n = costs.length;
        int k = costs[0].length;
        
        int[][] dp = new int[n][k];
        int min_1st = 0x7fffffff, min_2nd = 0x7fffffff;
        int min_index = -1;
        
        for(int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
            if(min_1st > costs[0][i]) {
                min_2nd = min_1st;
                min_1st = costs[0][i];
                min_index = i;
            }       
            else if(min_2nd > costs[0][i]) {
                min_2nd = costs[0][i];
            }
        }
        //学会了怎么在数组里一次找第一小第二小
        
        
        for(int i = 1; i < n; i++) {
            int curMin1 = 0x7fffffff, curMin2 = 0x7fffffff;
            int curMin1_index = -1;
            for(int j = 0; j < k; j++) {
                if(j != min_index) {
                    dp[i][j] = costs[i][j] + min_1st;
                }
                else{
                    dp[i][j] = costs[i][j] + min_2nd;
                }
                if(dp[i][j] < curMin1) {
                    curMin2 = curMin1;
                    curMin1 = dp[i][j];
                    curMin1_index = j;
                }
                else if(dp[i][j] < curMin2) {
                    curMin2 = dp[i][j];
                }
            }
            min_1st = curMin1;
            min_2nd = curMin2;
            min_index = curMin1_index;
        }
        return min_1st;
    }
    
    
    /*
    
    动态规划的方法，可以推广为n层楼，m个鸡蛋。如下分析： 假设f{n,m}表示n层楼、m个鸡蛋时找到最高楼层的最少尝试次数。
    当第一个鸡蛋从第i层扔下，如果碎了，还剩m-1个鸡蛋，为确定下面楼层中的安全楼层，还需要f{i-1,m-1}次，找到子问题；
    不碎的话，上面还有n-i层，还需要f[n-i,m]次，又一个子问题。 状态转移方程如下： 
    f{n, m} = min(1 + max(f{n - 1, m - 1}, f{n - i, m}) ) 其中： i为(1, n), f{i, 1} = 1
    int[][] dp = new int[m+1][n+1];
    */
    
    public int dropEggs2(int m, int n) {
        // Write your code here
        int[][] dp = new int[m+1][n+1];
        
        for(int i = 1; i <= m; i++) {
            dp[i][1] = 1;
        }
        
        for(int i = 1; i <= n; i++) {
            dp[1][i] = i;
        }
        
        for(int i = 2; i <= m; i++) {
            for(int j = 2; j <= n; j++) {
                dp[i][j] = 0x7fffffff;
                for(int k = 1; k <= j; k++) {
                    int broke = dp[i-1][k-1]; //剩i-1个球，从k-1层扔
                    int not_broke = dp[i][j-k]; //剩i个球，还有j-k层高度
                    int total_min = 1 + Math.max(broke, not_broke);
                    dp[i][j] = Math.min(dp[i][j], total_min);
                }
            }
        }
        
        return dp[m][n];
    }
}
