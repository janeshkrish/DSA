class Solution {
    public int maxProfit(int[] prices) {
        int minprice = prices[0];
        int maxprice = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]<minprice){
                minprice = prices[i];
            }
            int profit = prices[i] - minprice;
            if(profit >maxprice){
                maxprice = profit;
            }
        }
        return maxprice;
    }
}