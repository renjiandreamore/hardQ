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
    public int maxProfit(int[] prices) {
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
}
