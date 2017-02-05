public class Solution {
    /**
     * @param n an integer
     * @return an integer
     */
    public int waysNCents(int n) {
        // Write your code here
        if(n < 0) return 0;
        int[] dp = new int[n+1];
        
        int[] coins = new int[]{1,5,10,25};
        dp[0] = 1;
        //dp[j] means 在有前x个coin基础上，凑够j分钱的数
        
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= n; j++) {
                if(j >= coins[i]) {
                    dp[j] += dp[j-coins[i]];
                }
            }
        }
        
        return dp[n];
    }
}
