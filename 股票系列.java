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
    
    
    
}
