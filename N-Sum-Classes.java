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
    
    //3Sum
    //排序后，[-4,-1,-1,0,1,2] i指在-4， left -1， right 2， left + right 要 == 4， 左右移动。 循环结束i++。 注意corner case！
    
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        // write your code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        if(numbers == null || numbers.length == 0) return res;
        Arrays.sort(numbers);
        
        for(int i = 0; i < numbers.length - 2; i++) {
            
            int left = i + 1, right = numbers.length - 1;
            int diff = 0 - numbers[i];
            if(i != 0 && numbers[i] == numbers[i-1]){
                continue;
            }
            while(left < right) {
                if(numbers[left] + numbers[right] == diff) {
                    ArrayList<Integer> path = new ArrayList<>();
                    path.add(numbers[i]);
                    path.add(numbers[left]);
                    path.add(numbers[right]);
                    res.add(path);
                    left++; right--;
                    while(left < right && numbers[left] == numbers[left - 1]){
                        left ++;
                    }
                    while(left < right && numbers[right] == numbers[right + 1]){
                        right --;
                    }
                }
                else if(numbers[left] + numbers[right] < diff) {
                    left ++;
                }
                else {
                    right --;
                }
            }
        }
        
        return res;
    } 
    
    
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers ,int target) {
        // write your code here
        if(numbers == null || numbers.length == 0) return 0;
        
        Arrays.sort(numbers);
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < numbers.length; i++) {
            int left = i + 1;
            int right = numbers.length - 1;
            while(left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if(Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                if(sum < target) {
                    left ++;
                }
                else if(sum > target){
                    right --;
                }
                else{
                    return target;
                }
            }
        }
        return ans;
    }
    
    
}
