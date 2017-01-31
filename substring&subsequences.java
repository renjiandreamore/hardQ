public class SubSequences {
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
  
  
  /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     给出"ABCD" 和 "EDCA"，这个LCS是 "A" (或 D或C)，返回1

        给出 "ABCD" 和 "EACB"，这个LCS是"AC"返回 2
     */
    
    //最长公共子序列
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        if(A == null || B == null || A.length() == 0 || B.length() == 0) return 0;
        
        int m = A.length(), n = B.length();
        int[][] dp = new int[m][n];
        
        for(int i = 0; i < n; i++) {
            if(A.charAt(0) == B.charAt(i)){
                dp[0][i] = 1;
                for(int j = i + 1; j < n; j++) {
                    dp[0][j] = 1;
                }
                break;
            }
        }
        
        for(int i = 0; i < m; i++) {
            if(B.charAt(0) == A.charAt(i)) {
                dp[i][0] = 1;;
                for(int j = i + 1; j < m; j++) {
                    dp[j][0] = 1;
                }
                break;
            }
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[m-1][n-1];
    }
  
}


public class SubStrings {
    
    
    
    
    
}
