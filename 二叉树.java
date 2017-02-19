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




public class 二叉树的路径和2 {
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     路径可以从任何节点出发和结束，但是需要是一条一直往下走的路线。也就是说，路径上的节点的层级是逐个递增的。
    1
   / \
  2   3
 /   /
4   2
给定目标值6。那么满足条件的路径有两条：

[
  [2, 4],
  [1, 3, 2]
]
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        List<Integer> path = new ArrayList<>();
        helper(root, target, res, path, 0);
        return res;
    }
    
    public void helper(TreeNode root, int target, List<List<Integer>> res, List<Integer> path, int level) {
       if(root == null) return;
       path.add(root.val);
       int tmp = target;
       
       for(int i = level; i >= 0; i--) {
           tmp -= path.get(i);
           //if(tmp < 0) break;
           //不能写break, 比如1->-2->1->2, t = 2
           //2是一个答案， 1,-2,1,2也是一个答案
           if(tmp == 0) {
               List<Integer> tmpt = new ArrayList<>();
               for(int x = i; x <= level; x++) {
                   tmpt.add(path.get(x));
               }
               res.add(tmpt);
           }
       }
       
       helper(root.left, target, res, path, level + 1);
       helper(root.right, target, res, path, level + 1);
       
       path.remove(path.size() - 1);
    }
}


public class 二叉树路径和3 {
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     给一棵这样的二叉树：

    1
   / \
  2   3
 /
4
和目标值 target = 6。你需要返回的结果为：

[
  [2, 4],
  [2, 1, 3],
  [3, 1, 2],
  [4, 2]
]
     */
    
   //不止往下还能往回走
     
    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        // Write your code here
        //按这道题的方法写一下2
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        
        List<Integer> path = new ArrayList<>();
        helper(root, target, res, path);
        
        return res;
    }
    
    public void helper(ParentTreeNode root, int target, List<List<Integer>> res, List<Integer> path) {
        if(root == null) return;
        
        getSum(root, target, res, path, null);
        
        helper(root.left, target, res, path);
        helper(root.right, target, res, path);
    }
    
    public void getSum(ParentTreeNode root, int target, List<List<Integer>> res, List<Integer> path, ParentTreeNode father) {
        
        if(root == null) return;
        path.add(root.val);
        target -= root.val;
        
        if(target == 0) {
            res.add(new ArrayList<Integer>(path));
        }
        
        if(root.parent != null && root.parent != father) {
            getSum(root.parent, target, res, path, root);
        }
        if(root.left != null && root.left != father) {
            getSum(root.left, target, res, path, root);
        }
        if(root.right != null && root.right != father) {
            getSum(root.right, target, res, path, root);
        }
        
        path.remove(path.size() - 1);
    }
}
