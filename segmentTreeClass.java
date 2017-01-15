public class Solution {
    /**
     *@param start, end: Denote an segment / interval
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if(start > end) return null;
        if(start == end) return new SegmentTreeNode(start, start);
        
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        
        int mid = (start + end)/ 2;
        root.left = build(start, mid);
        root.right = build(mid + 1, end);
        
        return root;
    }
    
    
    //build II
    public SegmentTreeNode buildII(int[] A) {
        // write your code here
        return helper(A, 0, A.length - 1);
    }
    
    public SegmentTreeNode helper(int[] A, int st, int ed){
        if(st > ed) return null;
        if(st == ed) return new SegmentTreeNode(st, ed, A[st]);
        
        
        int mid = (st + ed)/2;
        SegmentTreeNode left = helper(A, st, mid);
        SegmentTreeNode right = helper(A, mid + 1, ed);
        
        int max = Math.max(left.max, right.max);
        SegmentTreeNode root = new SegmentTreeNode(st, ed, max);
        root.left = left;
        root.right = right;
        
        return root;
    }
    
    
   // modify
   public void modify(SegmentTreeNode root, int index, int value) {
        // write your code here
        if(root == null || index < root.start || index > root.end) return;
        if(index == root.start && index == root.end){
            root.max = value;
            return;
        }
        
        int mid = (root.start + root.end) / 2;
        if(index >= root.start && index <= mid){
            modify(root.left, index, value);
        }
        if(index >= mid + 1 && index <= root.end){
            modify(root.right, index, value);
        }
        
        root.max = Math.max(root.left.max, root.right.max);
    }
    
    
    //query
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(root == null || end < root.start || start > root.end) return Integer.MIN_VALUE;
        if(root.start >= start && root.end <= end){
            return root.max;
        }
        
        int mid = (root.start + root.end) / 2;
        if(end <= mid){
            return query(root.left, start, end);
        }
        else if(start > mid){
            return query(root.right, start, end);
        }
        else { //if(start <= mid && end > mid)
            return Math.max(query(root.left, start, mid), query(root.right, mid + 1, end));
        }
        
       // return Integer.MIN_VALUE;
    }
    
    
    //queryII
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(root == null || end < root.start || start > root.end || root.start > root.end) return 0;
        if(start <= root.start && end >= root.end){
            return root.count;
        }
        
        int mid = (root.start + root.end) / 2;
        if(end <= mid){
            return query(root.left, start, end);
        }
        else if(start > mid) {
            return query(root.right, start, end);
        }
        else{  //start<= mid && end > mid
             return query(root.left, start, mid) + query(root.right, mid + 1, end);
        }
        
    }
}
