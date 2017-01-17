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
}
