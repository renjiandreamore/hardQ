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
}
