class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] freq = new int[101];
        // Count frequency of each number
        for(int num:nums){
            freq[num]++;
        }
        // Convert to prefix sum
        for(int i=1;i<101;i++){
            freq[i] += freq[i-1];
        }
        // Build result array
        int[] result = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 0){
                result[i]=0;
            }else{
                result[i] = freq[nums[i]-1];
            }
        }
        return result;
    }
}