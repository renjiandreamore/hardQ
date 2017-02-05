public class Solution {
    //最小调整代价！！！！！！！！！！
    //0 - 100
    //对于数组[1, 4, 2, 3]和target=1，最小的调整方案是调整为[2, 3, 2, 3]，调整代价之和是2。返回2。
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        // write your code here
        if(A == null || A.size() == 0) return 0;
        int n = A.size();
        
        int[][] dp = new int[n+1][101];
        //dp[i][v] 前i个数，第i个数调整为v，满足相邻两数<=target，的代价
        //方程： dp[i][v] = dp[i-1][k] + (A[i]-v), (v - k <= target);
        
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= 100; j++) {
                dp[i][j] = 0x7fffffff;
            }
        }
        
        for(int i = 0; i <= 100; i++) dp[0][i] = 0;
        
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= 100; j++) { //j表示第i-1个的value
                for(int v = 0; v <= 100; v++) { //v表示第i个的value
                    if(Math.abs(v - j) <= target) {
                        dp[i][v] = Math.min(dp[i][v], dp[i-1][j] + Math.abs(A.get(i-1) - v));
                    }
                }
            }
        }
        
        int res = 0x7fffffff;
        for(int i = 0; i <= 100; i++) {
            res = Math.min(res, dp[n][i]);
        }
        
        return res;
    }
}
