public class Solution {
    /**    
     * @param A: an array of integers
     * @return: an integer
     如果给出 [1,2,0], return 3
     如果给出 [3,4,-1,1], return 2
     */
     
     //寻找丢失的数
    public int firstMissingPositive(int[] A) {
        // write your code here  
        //hashset also good
        //extra array is also good
        //in-memory?: 因为是positive num， 所以比如[x], 下标0，第一个应该出现1
        //于是稍改进一下， 把下标i 的地方放值i+1 ,
        
        if(A == null || A.length == 0) return 1;
        
        for(int i = 0; i < A.length; i++) {
            while(A[i] > 0 && A[i] <= A.length && A[i] != i + 1){
                int tmp = A[A[i]-1];
                if(tmp == A[i]) break;
                A[A[i]-1] = A[i];
                A[i] = tmp;
            }
        }
        
        for(int i = 0; i < A.length; i++) {
            if(A[i] != i+1) {
                return i+1;
            }
        }
        
        return A.length + 1; //[1,2,3]这种
    }
    
    
    //搜索rotated sorted array II 注意A[start] == A[mid]情况
    /** 
     * param A : an integer ratated sorted array and duplicates are allowed
     * param target :  an integer to be search
     * return : a boolean 
     */
    public boolean search(int[] A, int target) {
        //write your code here
        if(A == null || A.length == 0) return false;
        
        int start = 0, end = A.length - 1;
        
        while(start + 1 < end) {
            int mid = start + (end - start)/2;
            if(A[start] > A[mid]) {
                if(A[mid] < target && target <= A[end]) start = mid;
                else end = mid;
            }
            else if(A[start] == A[mid]){
                start ++;
            }
            else{
                if(A[start] <= target && target <= A[mid]) end = mid;
                else start = mid;
            }
        }
        
        if(A[start] == target) return true;
        if(A[end] == target) return true;
        return false;
        
    }
    
    
    
    
    //trapping rain water!!!!!!!
    //一定要理解反复多看！！！
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
     //对任意位置i，在i上的积水，由左右两边最高的bar：A[left] = max{A[j], j<i}, A[right] = max{A[j], j>i}决定。定义Hmin = min(A[left], A[right])，则积水量Si为：
        //Hmin <= A[i]时，Si = 0
        //Hmin > A[i]时，Si = Hmin - A[i]
    public int trapRainWater(int[] heights) {
        // write your code here
        if(heights == null || heights.length == 0) return 0;
        
        // int[] left_height_max = new int[heights.length];
        // int[] right_height_max = new int[heights.length];
        
        // for(int i = 1, j = heights.length - 2; i < heights.length && j >=0; i++, j--) {
        //     left_height_max[i] = Math.max(left_height_max[i-1], heights[i-1]);
        //     right_height_max[j] = Math.max(right_height_max[j+1], heights[j+1]);
        // }
        
        // int water = 0;
        // for(int i = 1; i < heights.length-1; i++) {
        //     int min_height = Math.min(left_height_max[i], right_height_max[i]);
        //     if(min_height > heights[i]) {
        //         water += min_height - heights[i];
        //     }
        // }
        
        // return water;
        
        int left = 0, right = heights.length - 1;
        int left_height_max = heights[left];
        int right_height_max = heights[right];
        
        int water = 0;
        
        while(left < right) {
            if(left_height_max <= right_height_max) {
                left++;
                if(left_height_max > heights[left]){
                    water += left_height_max - heights[left];
                }
                else{
                    left_height_max = heights[left];
                }
            }else{
                right--;
                if(right_height_max > heights[right]){
                    water += right_height_max - heights[right];
                }
                else{
                    right_height_max = heights[right];
                }
            }
        }
        
        return water;
    }
}
