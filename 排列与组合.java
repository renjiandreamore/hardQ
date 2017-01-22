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
}
