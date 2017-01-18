public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
     //前缀和
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int sum = 0;
        int max = Integer.MIN_VALUE;
        //前缀和版本
        // int preSumMin = 0;
        
        // for(int i = 0; i < nums.length; i++) {
        //     sum += nums[i];
        //     max = Math.max(sum - preSumMin, max);
        //     preSumMin = Math.min(sum, preSumMin);//加上你更小还是之前更小捏？
        // }
        
        //﹢补给版本
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(sum, max);
            if(sum < 0) sum = 0; //负的给不了 正补给
        }
        
        return max;
    }
    
    
    
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        if(nums == null || nums.size() == 0) return 0;
        int n = nums.size();
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            sum += nums.get(i);
            max = Math.max(sum, max);
            if(sum < 0) sum = 0;
            left[i] = max;
        }
        
        
        sum = 0; max = Integer.MIN_VALUE;
        for(int i = n - 1; i >= 0; i--) {
            sum += nums.get(i);
            max = Math.max(sum, max);
            if(sum < 0) sum = 0;
            right[i] = max;
        }
        
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < n - 1; i++) {
            res = Math.max(res, left[i] + right[i+1]);
        }
        
        return res;
    }
    
    
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     给定一个整数数组，找出两个不重叠的子数组A和B，使两个子数组和的差的绝对值|SUM(A) - SUM(B)|最大。
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMin = new int[n];
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        //leftMax
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            if(sum < 0) sum = 0;
            leftMax[i] = max;
        }
        
        sum = 0;
        //leftMin
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            min = Math.min(min, sum);
            if(sum > 0) sum = 0;
            leftMin[i] = min;
        }
        
        sum = 0;
        max = Integer.MIN_VALUE;
        //rightMax
        for(int i = n - 1; i >= 0; i--) {
            sum += nums[i];
            max = Math.max(max, sum);
            if(sum < 0) sum = 0;
            rightMax[i] = max;
        }
        
        sum = 0;
        min = Integer.MAX_VALUE;
        //rightMin
        for(int i = n - 1; i >= 0; i--) {
            sum += nums[i];
            min = Math.min(min, sum);
            if(sum > 0) sum = 0;
            rightMin[i] = min;
        }
        
        int diff1 = Integer.MIN_VALUE;
        int diff2 = Integer.MIN_VALUE;
        
        for(int i = 0; i < n - 1; i++) {
            diff1 = Math.max(leftMax[i] - rightMin[i+1], diff1);
        }
        
        for(int i = 0; i < n - 1; i++) {
            diff2 = Math.max(rightMax[i+1] - leftMin[i], diff2);
        }
        
        return Math.max(diff1, diff2);
    }
    
    
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     给定一个由 n 个整数组成的数组和一个正整数 s ，请找出该数组中满足其和 ≥ s 的最小长度子数组。如果无解，则返回 -1。
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        //maintain a window
        if(nums == null || nums.length == 0) return -1;
        
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int j = 0;
        
        for(int i = 0; i < nums.length; i++) {
            while(j < nums.length && sum < s) {
                sum += nums[j++];
            }
            if(sum >= s) {
                min = Math.min(min, j - i);
            }
            sum -= nums[i];
        }
        
        if(min == Integer.MAX_VALUE) return -1;
        return min;
    }
}
