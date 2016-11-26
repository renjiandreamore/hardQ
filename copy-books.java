public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        if(pages == null || pages.length == 0) return 0;
        
        int[][] cst = cost(pages);
        int n = pages.length;
        
        int[][] dp = new int[n + 1][k + 1];
        int ans = Integer.MIN_VALUE;
        
        if(n <= k){
            for(int i = 0; i < n; i++){
                ans = Math.max(ans, pages[i]);
            }
            return ans;
        }
        
        //init
        for(int i = 1; i<=n; i++){
            dp[i][1] = cst[1][i];
        }
        
        //dp[i][j] = min(dp[i][j], max(dp[s][j-1], cost[s+1][i]));
        //s = 0 --> i ;
        for(int j = 2; j <= k; j++){
            for(int i = j; i <= n; i++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int s = 0; s < i; s++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[s][j-1], cst[s+1][i]));
                }
            }
        }
        
        return dp[n][k];
    }
    
    public int[][] cost(int[] A){
        int n = A.length;
        int[][] c = new int[n+1][n+1];
        
        for(int i = 1; i<= n; i++){
            for(int j = i; j <= n; j++){
                for(int k = i; k <= j; k++){
                    c[i][j] += A[k - 1];
                }
            }
        }
        
        return c;
    }
}
