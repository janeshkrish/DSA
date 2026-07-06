class Solution {
    
    public int rob(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        return Math.max(robber(nums,0,nums.length-2),
        robber(nums,1,nums.length-1));
    }
    public int robber(int[] arr,int i,int j){
        if(i==j){
            return arr[i];
        }
         int[] dp = new int[arr.length];
         dp[i] = arr[i];
         dp[i+1] = Math.max(dp[i],arr[i+1]);
         for(int k=2;k<=j;k++){
            int pick = arr[k] + dp[k-2];
            int skip = dp[k-1];
            dp[k] = Math.max(pick,skip);
         }
         return dp[j];
    }
}