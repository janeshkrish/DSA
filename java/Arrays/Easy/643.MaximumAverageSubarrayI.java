class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double windowsum = 0;
        for(int i=0;i<k;i++){
            windowsum+=nums[i];
        }
        double maxsum = windowsum;
        for(int i=k;i<nums.length;i++){
            windowsum = windowsum - nums[i-k] + nums[i];
            maxsum = Math.max(windowsum,maxsum);
        }
        double avg = maxsum / k;
        return avg;
    }
}