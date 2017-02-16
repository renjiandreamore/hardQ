public class Solution {
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
    1
   / \
  2   0
 /
3
Return 4 // 0-1-2-3-4
     */
     
     
     //Binary Tree Longest Consecutive Sequence
    public int longestConsecutive2(TreeNode root) {
        // Write your code here
        return helper(root).maxLen;
    }
    
    public ResultType helper(TreeNode root) {
        if(root == null) return new ResultType(0,0,0);
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int increase = 0, decrease = 0;
        if(root.left != null && root.left.val == root.val + 1) {
            increase = Math.max(increase, left.increase + 1);
        }
        if(root.right != null && root.right.val + 1 == root.val) {
            decrease = Math.max(decrease, right.decrease + 1);
        }
        
        if(root.left != null && root.left.val + 1 == root.val) {
            decrease = Math.max(decrease, left.decrease + 1);
        }
        if(root.right != null && root.right.val == root.val + 1) {
            increase = Math.max(increase, right.increase + 1);
        }
        
        int maxLen = increase + decrease + 1;
        maxLen = Math.max(maxLen, Math.max(left.maxLen, right.maxLen));
        
        return new ResultType(increase, decrease, maxLen);
    }
}

class ResultType{
    int increase, decrease, maxLen;
    public ResultType(int x, int y, int z) {
        this.increase = x;
        this.decrease = y;
        this.maxLen = z;
    }
}
