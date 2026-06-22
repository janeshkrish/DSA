class Solution {
    
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        if(nums.length==1){
            return nums[0];
        }
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
           int pick = nums[i] + dp[i-2];
           int skip = dp[i-1];
           dp[i] = Math.max(pick,skip);
        }
        return dp[nums.length-1];
    }
}