/*
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 /*ok，要知道root内的某两点间路径最大和, 我们需要知道
        1) root.left 可构成的某单一路径最大值max1, + root.right 可构成的某单一路径最大值max2, + root.val
        或者
        2)root.left 子树内某环形路径的最大值 || root.right 子树内某环形路径的最大值， 其中较大的那个
        
        然后比较1) 和 2),
        
        所以我们每个节点需要两个参数，单一路径最大值 和 以它为根的内部某环形路径最大值
        于是就有了wrap class
 */
 
public class Solution {
    public int maxPathSum(TreeNode root) {
        Wrap w = helper(root);
        return w.LCA_max;
    }
    
    public Wrap helper(TreeNode root){
        if(root == null){
            return new Wrap(0, Integer.MIN_VALUE);
        }
        
        Wrap left = helper(root.left);
        Wrap right = helper(root.right);
        
        int single_path_max = Math.max(left.single_path_max, right.single_path_max) + root.val;
        if(single_path_max < 0) single_path_max = 0;   
        
        //分两种情况：1.经过根 2. 不经过根(在根的子树里)
        int LCA_max = Math.max(left.LCA_max, right.LCA_max); // 不经过根
        LCA_max = Math.max(LCA_max, left.single_path_max + root.val + right.single_path_max);  //后半部分为经过根
        
        return new Wrap(single_path_max, LCA_max);
    }
}


class Wrap{
    int single_path_max; //以某点为根的，必须从它出发的单一路径最大值
    int LCA_max;         //以某点为公共根的，1.经过它的 2.可以不经过它的(可以是在它子树里的)路径最大值
    public Wrap(int single_path_max, int LCA_max){
        this.single_path_max = single_path_max;
        this.LCA_max = LCA_max;
    }
}
