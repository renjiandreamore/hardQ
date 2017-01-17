public class Solution {
    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        if(numbers == null || numbers.length == 0) return new int[0];
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < numbers.length; i++) {
            if(!map.containsKey(numbers[i])) {
                map.put(target-numbers[i], i);
            }
            else{
                return new int[]{map.get(numbers[i])+1, i+1};
            }
        }
        
        return new int[0];
    }
    
    
    
    
    /**
     * @param nums an integer array
     * @param target an integer
     * @return the difference between the sum and the target
     */
    public int twoSumCloset(int[] nums, int target) {
        // Write your code here
        if(nums == null || nums.length == 0) return target;
        int diff = Integer.MAX_VALUE;

        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        
        while(i < j) {
            if(target - (nums[i] + nums[j]) > 0) {
                diff = Math.min(diff, target - (nums[i] + nums[j]));
                i++;
            } 
            else{
                diff = Math.min(diff, (nums[i] + nums[j]) - target);
                j--;
            }
        }
        
        return diff;
    }
    
    
    
    
    
    
}
