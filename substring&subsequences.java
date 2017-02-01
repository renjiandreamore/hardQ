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
    
    
    //distinct subsequences
    public int numDistinct(String S, String T) {
        // write your code here
        if(S == null || S.length() == 0 || T == null) return 0;
        int m = S.length(), n = T.length();
        int[][] dp = new int[m+1][n+1];
        
        for(int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(i >= j) {
                    dp[i][j] = dp[i-1][j];
                    if(S.charAt(i-1) == T.charAt(j-1)) {
                        dp[i][j] += dp[i-1][j-1];
                    }
                }
            }
        }
        
        return dp[m][n];
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
    /*  最长公共子串
    *   给出A=“ABCD”，B=“CBCE”，返回 2
    */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if(A == null || B == null || A.length() == 0 || B.length() == 0) return 0;
        
        int m = A.length(), n = B.length();
        
        int[][] dp = new int[m][n];
        
        for(int i = 0; i < n; i++) {
            dp[0][i] = A.charAt(0) != B.charAt(i) ? 0 : 1;
        }
        
        for(int j = 0; j < m; j++) {
            dp[j][0] = A.charAt(j) != B.charAt(0) ? 0 : 1;
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }
        
        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        
        return max;
    }
    
    /**
     * @param s: a string
     * @return: an integer 
     在"abcabcbb"中，其无重复字符的最长子字符串是"abc"，其长度为 3
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if(s == null || s.length() == 0) return 0;
        
        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int sum = 0;
        while(right < s.length()){
            if(!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }
            else{
                set.remove(s.charAt(left));
                left++;
            }
            sum = Math.max(sum, right - left);
        }
        
        return sum;
    }
    
    
    /**重要！！！！！！     最小子串window
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
                         给出source = "ADOBECODEBANC"，target = "ABC" 满足要求的解  "BANC"
     */
    public String minWindow(String source, String target) {
        // write your code
        if(source == null || source.length() == 0 || target == null || target.length() == 0) return "";
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < target.length(); i++) {
            if(!map.containsKey(target.charAt(i))) {
                map.put(target.charAt(i), 1);
            }else{
                map.put(target.charAt(i), map.get(target.charAt(i))+1);
            }
        }
        
        int left = 0, right = 0;
        int count = 0;
        int start_point = 0, min_len = 0x7fffffff;
        
        while(right < source.length()) {
            if(map.containsKey(source.charAt(right))) {
                map.put(source.charAt(right), map.get(source.charAt(right))-1);
                if(map.get(source.charAt(right)) >= 0) count++;
                
                while(count == target.length()) {
                    if(right - left + 1 < min_len) {
                        min_len = right - left + 1;
                        start_point = left;
                    }
                    if(map.containsKey(source.charAt(left))) {
                        map.put(source.charAt(left), map.get(source.charAt(left))+1);
                        if(map.get(source.charAt(left)) > 0) count--;
                    }
                    left++;
                }
            }
            right++;
        }
        
        if(min_len == 0x7fffffff) return "";
        return source.substring(start_point, start_point + min_len);
    }
    
    //最长回文子串
    /**
     * @param s input string
     * @return the longest palindromic substring
     */
     
     /**
      * abcba这样的奇数个长度可由c左右推，看是否相等判断。但是abba这样偶数长度，我们没法找到b和b之间的那个数，所以思想是无论奇偶我们都把长度扩大一倍，扩大后的偶数各位用#表示，扩大后的奇数各位用原字符串表示.
      * 
        abba --> #a#b#b#a ,后面再补一个#即可, 有效性是从第三个#开始左右移动判断。
        abcba --> #a#b#c#b#a, 后面再补一个#即可，有效性是从c开始左右移动判断
      */
    public String longestPalindrome(String s) {
        // Write your code here
        if(s == null || s.length() == 0 || s.length() == 1) return s;
        
        String res = "";
        int sum = 0;
        
        for(int i = 1; i < 2*s.length(); i++) {
            int count = 1;
            while(i - count >= 0 && i + count <= 2*s.length() && get(s, i-count) == get(s, i+count)) {
                count++;
            }
            count--;
            
            if(count > sum) {
                sum = count;
                res = s.substring((i - count)/2, (i + count)/2);
            }
        }
        return res;
    }
    
    public char get(String s, int index) {
        if(index % 2 == 0){
            return '_';
        }
        else{
            return s.charAt(index/2);
        }
    }
    
}
