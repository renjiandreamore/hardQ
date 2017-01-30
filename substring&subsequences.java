public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     给定 [5, 4, 2, 1, 3], 其最长上升连续子序列（LICS）为 [5, 4, 2, 1], 返回 4.

      给定 [5, 1, 2, 3, 4], 其最长上升连续子序列（LICS）为 [1, 2, 3, 4], 返回 4.
     */
  
   //最长连续上升子序列
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        if(A == null || A.length == 0) return 0;
        
        int sum = 1, max = 1;
        for(int i = 1; i < A.length; i++) {
            if(A[i] > A[i-1]) {
                sum++;
                max = Math.max(sum ,max);
            }else{
                sum = 1;
            }
        }
        
        sum = 1;
        for(int i = A.length - 2; i >= 0; i--) {
            if(A[i] > A[i+1]) {
                sum++;
                max = Math.max(sum, max);
            }else{
                sum = 1;
            }
        }
        
        return max;
    }
  
  
  /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     给出 [5,4,1,2,3]，LIS 是 [1,2,3]，返回 3
     给出 [4,2,4,5,3,7]，LIS 是 [2,4,5,7]，返回 4
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int max = 1;
        for(int i = 0; i < dp.length; i++) {
            max = Math.max(dp[i], max);
        }
        
        return max;
    }
  
  
  
  
}
