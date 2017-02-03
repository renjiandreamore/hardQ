public class Solution {
    /**
     * @param n n pairs
     * @return All combinations of well-formed parentheses
     给定 n = 3, 可生成的组合如下:

     "((()))", "(()())", "(())()", "()(())", "()()()"
     */
    public ArrayList<String> generateParenthesis(int n) {
        // Write your code here
        ArrayList<String> res = new ArrayList<>();
        if(n <= 0) return res;
        
        helper(res, "", n, n);
        
        return res;
    }
    
    public void helper(ArrayList<String> res, String pare, int left, int right){
        if(left == 0 && right == 0){
            res.add(pare);
            return;
        }
        
        if(left > 0){
            //pare += "(";
            helper(res, pare +"(", left - 1, right);
        }
        
        if(right > 0 && left < right){
            //pare += ")";
            helper(res, pare +")", left, right - 1);
        }
    }
    
    
    
    
    //paint house2 
     /**
     * @param costs n x k cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // Write your code here
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        
        int n = costs.length;
        int k = costs[0].length;
        
        int[][] dp = new int[n][k];
        int min_1st = 0x7fffffff, min_2nd = 0x7fffffff;
        int min_index = -1;
        
        for(int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
            if(min_1st > costs[0][i]) {
                min_2nd = min_1st;
                min_1st = costs[0][i];
                min_index = i;
            }       
            else if(min_2nd > costs[0][i]) {
                min_2nd = costs[0][i];
            }
        }
        //学会了怎么在数组里一次找第一小第二小
        
        
        for(int i = 1; i < n; i++) {
            int curMin1 = 0x7fffffff, curMin2 = 0x7fffffff;
            int curMin1_index = -1;
            for(int j = 0; j < k; j++) {
                if(j != min_index) {
                    dp[i][j] = costs[i][j] + min_1st;
                }
                else{
                    dp[i][j] = costs[i][j] + min_2nd;
                }
                if(dp[i][j] < curMin1) {
                    curMin2 = curMin1;
                    curMin1 = dp[i][j];
                    curMin1_index = j;
                }
                else if(dp[i][j] < curMin2) {
                    curMin2 = dp[i][j];
                }
            }
            min_1st = curMin1;
            min_2nd = curMin2;
            min_index = curMin1_index;
        }
        
        
        
        return min_1st;
    }
}
