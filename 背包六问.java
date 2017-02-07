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
    
    
    //重复选择+不同排列+装满可能性总数
    //Given nums = [1, 2, 4], target = 4

/*The possible combination ways are:
[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]
*/
    public int backPackVI(int[] nums, int target) {
        // Write your code here
        if(target < 0 || nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] dp = new int[target+1];
        dp[0] = 1;
        
        // for(int i = 0; i < nums.length; i++) {
        //     for(int j = 1; j <= target; j++) {
        //         if(j >= nums[i]){
        //             dp[j] += dp[j-nums[i]];
        //         }
        //     }
        // }  //这样输出的是单一答案 : [1,1,2] 和 [2,1,1] 是一样的， 是背包5的解
        
        for(int i = 1; i <= target; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(i >= nums[j]) {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        
        return dp[target];
    }
}
