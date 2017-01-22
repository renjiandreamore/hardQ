public class Solution {
    /**
     * @param A an integer array
     * @return a long integer
     */
     
     //1. 排列序号1
    public long permutationIndex(int[] A) {
        // Write your code here
        //[3,2,5]
        //  : 比3xx小的所全排列的和 + 比32x小的所有全排列和 + 比325小的... + 325
        //  ==    (2 - 1) * (3 - 1)! + (1-1) * (2 - 1)! + (1-1) * (1 -1)! + 1
        //  3是第2大的数，于是比三小的有2-1个...同理...
        //  ==     2 + 1 = 3;
        
        
        if(A == null || A.length == 0) return 0;
        long fact = 1;
        long res = 0;
        
        for(int i = A.length - 1; i >= 0; i--) {
            int count = 0;
            for(int j = i + 1; j < A.length; j++) {
                if(A[j] < A[i]){
                    count ++;
                }
            }
            res += count * fact;
            fact *= A.length - i;
        }
        
        return res + 1;
    }
    
	
				//这道题要着重看！！！   是第几个排列呢？还tm带有重复数字？   
			// <A[i]的个数*它后面个数的！/(它后面元素里重复元素a出现的次数a! * 重复元素b出现次数b! *....) 
     /**
     * @param A an integer array
     * @return a long integer
     */
    public long permutationIndexII(int[] A) {
        // Write your code here
        if(A == null || A.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        long fact = 1, malFact = 1;
        long res = 0;
        
        for(int i = A.length - 1; i >= 0; i--){
            if(!map.containsKey(A[i])){
                map.put(A[i], 1);
            }
            else{
                map.put(A[i], map.get(A[i]) + 1);
                malFact *= map.get(A[i]);
            }
            int count = 0;
            for(int j = i + 1; j < A.length; j++) {
                if(A[i] > A[j]) {
                    count ++;
                }
            }
            
            res += count * fact / malFact;
            fact *= A.length - i;
        }
        
        return res + 1;
    }
    
    
    
    /**  上一个排列
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
		// write your code
		
		if(nums == null || nums.size() == 0) return new ArrayList<Integer>();
		
		int pos = nums.size();
		for(int i = nums.size() - 1; i>= 1; i--) {
		    if(nums.get(i) < nums.get(i - 1)) {
		        pos = i - 1;
		        break;
		    }
		}
		
		if(pos == nums.size()) {
		    reverse(nums, 0, nums.size() - 1);
		    return nums;
		}
		
		int fst_small = pos + 1;
		for(int i = nums.size() - 1; i>=pos+1; i--) {
		    if(nums.get(i) < nums.get(pos)) {
		        fst_small = i;
		        break;
		    }
		}
		
		int tmp = nums.get(pos);
		nums.set(pos, nums.get(fst_small));
		nums.set(fst_small, tmp);
		
		reverse(nums, pos + 1, nums.size() - 1);
		return nums;
    }
    
    public void reverse(ArrayList<Integer> nums, int start, int end) {
        for(int i = start, j = end; i < j; i++, j--) {
            int tmp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, tmp);
        }
    }
    
    
    
    /** 下一个排列
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
 public void nextPermutation(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0){
            return;
        }
        if(nums.length == 1) return;
        //1,2,4,3 --> 1, 3, 2, 4
        
        //find the last increse
        //1,2,4,3 ---- 2
        int index = nums.length; 
        for(int i = nums.length - 2; i>=0 ; i--){
            if(nums[i+1] > nums[i] ){
                index = i;
                break;
            }
        }
        
        if(index == nums.length){
            reverse(nums, 0, nums.length - 1);
            return;
        }
        
        //find the fisrt > nums[index] after index;
        int right = index + 1;
        for(int i = nums.length - 1; i>= right; i--){
            if(nums[i] > nums[index]){
                right = i;
                break;
            }
        }
        
        int tmp = nums[index];
        nums[index] = nums[right];
        nums[right] = tmp;
        
        reverse(nums, index + 1, nums.length - 1);
    }
    
    public void reverse(int[] nums, int left, int right){
        for(int i = left, j = right; i < j; i++, j--){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    
    
    
    
    /**  全排列
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
          res.add(new ArrayList<Integer>());
          return res;
        };
        
        List<Integer> path = new ArrayList<Integer>();
        
        helper(nums, path, res);
        return res;
    }
    
    public void helper(int[] nums, List<Integer> path, List<List<Integer>> res) {
        if(path.size() == nums.length) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(path.contains(nums[i])){
                continue;
            }
            path.add(nums[i]);
            helper(nums, path, res);
            path.remove(path.size() - 1);
        }
    }
    
    
    
     
    /**  带重复元素的全排列
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Write your code here
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        
        List<Integer> path = new ArrayList<>();
        helper(nums, res, path, visited);
        return res;
    } 
    
    public void helper(int[] nums, List<List<Integer>> res, List<Integer> path, boolean[] visited) {
        if(path.size() == nums.length) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(visited[i] || i != 0  && nums[i] == nums[i-1]&& !visited[i-1]) {
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;
            helper(nums, res, path, visited);
            visited[i] = false;
            path.remove(path.size()-1);
        }
    } 
}




class Combinations{
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
	
	//12345...n 这个数，里选k个，组成组合， 返回结果。	
    
     public List<List<Integer>> combine(int n, int k) {
         //write your code here
         List<List<Integer>> res = new ArrayList<>();
         List<Integer> path = new ArrayList<>();
         helper(n, k, path, res, 1);
         return res;
     }
     
     public void helper(int n, int k, List<Integer> path, List<List<Integer>> res, int index) {
         if(path.size() == k){
             res.add(new ArrayList<Integer>(path));
             return;
         }
         
         for(int i = index; i <= n; i++) {
             path.add(i);
             helper(n, k, path, res, i + 1);
             path.remove(path.size() - 1);
         }
     }
   
	
	
	
	
	//数字组合1
      public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        
        //boolean[] visited = new boolean[candidates.length];
        List<Integer> path = new ArrayList<>();
        helper(candidates, target, path, res, 0);
        
        return res;
    }
    
    public void helper(int[] candidates, int target, List<Integer> path, List<List<Integer>> res, int index) {
        //if(target < 0) return;
        
        if(target == 0) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        
        for(int i = index; i < candidates.length; i++) {
            if(target < candidates[i]) break;
            if(i != 0 && candidates[i] == candidates[i-1]) continue;
            
            path.add(candidates[i]);
            helper(candidates, target - candidates[i], path, res, i);
            path.remove(path.size() - 1);
        }
    }
	
	
	
	//数字组合2	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        
        boolean[] visited = new boolean[candidates.length];
        List<Integer> path = new ArrayList<>();
        helper(candidates, target, path, res, 0, visited);
        
        return res;
    }
    
    public void helper(int[] candidates, int target, List<Integer> path, List<List<Integer>> res, int index, boolean[] visited) {
        //if(target < 0) return;
        
        if(target == 0) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        
        for(int i = index; i < candidates.length; i++) {
            if(target < candidates[i]){
                break;
            }
            if(visited[i] || i != 0 && !visited[i-1] && candidates[i] == candidates[i-1]){
                continue;
            }
            path.add(candidates[i]);
            visited[i] = true;
            helper(candidates, target - candidates[i], path, res, i, visited);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
	
	
	//电话号码字母组合
	
/**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    public ArrayList<String> letterCombinations(String digits) {
        // Write your code here
        ArrayList<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) return res;
        
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a','b','c'});
        map.put('3', new char[]{'d','e','f'});
        map.put('4', new char[]{'g','h','i'});
        map.put('5', new char[]{'j','k','l'});
        map.put('6', new char[]{'m','n','o'});
        map.put('7', new char[]{'p','q','r','s'});
        map.put('8', new char[]{'t','u','v'});
        map.put('9', new char[]{'w','x','y','z'});
        
        StringBuilder sb = new StringBuilder();
        
        helper(digits, map, sb, res, 0);
        
        return res;
    }
    
    public void helper(String s, HashMap<Character, char[]> map, StringBuilder sb, ArrayList<String> res, int index) {
        //s = "234"
        if(sb.length() == s.length()) {
            res.add(sb.toString());
            return;
        }
        
        //要找到一个变量，可以通过单一的变化，从s.charAt(0)的char[]数组
        //直接跳到s.charAt(1)的char[] 数组
        //于是我们想到sb.length(); 或者加一个变量index表示指向input里的第几个char
        
        for(char c : map.get(s.charAt(index))){
            sb.append(c);
            helper(s, map, sb, res, index + 1);
            sb.deleteCharAt(sb.length()-1);
        }  
        
    }

}
