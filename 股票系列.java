public class BestTime2BuyAndSellStock {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
       只允许交易一次
       [3,2,3,1,2], 返回 1 
     */
    public int maxProfit(int[] prices) {
        // write your code here
        if(prices == null || prices.length == 0) return 0;
        
        int curMin = prices[0];
        int curMax = prices[0];
        int max = Integer.MIN_VALUE;
        
        for(int i = 1; i < prices.length; i++) {
            curMin = Math.min(curMin, prices[i]);
            curMax = prices[i];
            max = Math.max(max, curMax - curMin);
        }
        
        return max;
    }
    
    //可以买多次
    public int maxProfit2(int[] prices) {
        // write your code here
        if(prices == null || prices.length == 0) return 0;
        int profit = 0;
        
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] - prices[i-1] > 0) {
                profit += prices[i] - prices[i-1];
            }
        }
        
        return profit;
    }
    
    
    //给出一个样例数组 [4,4,6,1,1,4,2,5], 返回 6   最多买卖两次
    public int maxProfit3(int[] prices) {
        // write your code here
        if(prices == null || prices.length == 0) return 0;
        
        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        left[0] = 0;
        right[right.length - 1] = 0;
        
        int curMin = prices[0], curMax = prices[0];
        for(int i = 1; i < left.length; i++) {
            if(prices[i] < curMin) {
                curMin = prices[i];
            }
            curMax = prices[i];
            left[i] = Math.max(left[i-1], curMax - curMin);
        }
        
        curMin = curMax = prices[right.length-1];
        for(int i = right.length - 2; i >= 0; i--) {
            if(prices[i] > curMax) {
                curMax = prices[i];
            }
            curMin = prices[i];
            right[i] = Math.max(right[i+1], curMax - curMin);
        }
        
        int profit = 0;
        for(int i = 0; i < left.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }
        
        return profit;
    }
    
    
    //给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6.
    //最多交易K次，注意看return 上面的注释.
    public int maxProfit4(int k, int[] prices) {
        // write your code here
        if(prices == null || prices.length < 2) return 0;
        
        if(k >= prices.length){
            return maxProfit2(prices);
        }
        int n = prices.length;
        
        int[][] global = new int[n][k+1]; // 第n天交易k次，此天可不进行交易的max
        int[][] local = new int[n][k+1]; // 第n天交易k次，此天必须交易的max
        
        global[0][0] = local[0][0] = 0;
        
        for(int i = 1; i < n; i++) {
            int diff = prices[i] - prices[i-1];
            for(int j = 1; j <= k; j++) {
                local[i][j] = Math.max(local[i-1][j] + diff, global[i-1][j-1] + diff);
                /*
                locao[i][j] : [1,7,3,4,5,7] local[6][2] 前六天卖两次第6天
                必须卖, 此时3,4,5,7 意味着 local[5][2] 第五天卖了以后交易结束
                直接加上7-5的值就行了。
                **
                [1,7,3,4,2,7] local[6][2], 此时3,4,2,7 意味着global[5][1] which is
                7-1 = 6, 意思就是第五天也不卖， 加上diff which is 7 - 2, is max
                */
                global[i][j] = Math.max(local[i][j], global[i-1][j]);
            }
        }
        //[1,7,2,4,2,7] 在第i天，卖还是不卖？ 
        //卖： local[i][j], 不卖global[i-1][j];
        //那么 local[i][j]:
        //在第i-1天买入，还是i-1之前天买入?
        //i-1天买入: global[i-1][j-1] + diff;  之前天买入local[i-1][j] + diff
        return global[n-1][k];
    }
    
    public int maxProfit2(int[] A) {
        int profit = 0;
        for(int i = 1; i < A.length; i++) {
            if(A[i] - A[i-1] > 0) {
                profit += A[i] - A[i-1];
            }
        }
        return profit;
    }
}
