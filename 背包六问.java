public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     如果有4个物品[2, 3, 5, 7]
     如果背包的大小为11，可以选择[2, 3, 5]装入背包，最多可以装满10的空间。
     */
     
     //单次选择+最大体积
    public int backPack1(int m, int[] A) {
        // write your code here
        if(A == null || A.length == 0) return 0;
        
        int n = A.length;
        int[][] dp = new int[n+1][m+1];
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(j >= A[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-A[i-1]] + A[i-1]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[n][m];
    }
    
    
    //单次选择+最大价值
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        if(m < 0 || A.length == 0 || V.length == 0) return 0;
        int n = V.length;
        
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(j >= A[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - A[i-1]] + V[i-1]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[n][m];
    }
}
