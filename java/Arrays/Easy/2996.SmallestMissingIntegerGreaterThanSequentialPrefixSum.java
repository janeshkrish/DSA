class Solution {
    public int missingInteger(int[] nums) {
        int sum= nums[0];
        for(int i = 1; i < nums.length;i++){
            if(nums[i] == (nums[i-1]+1)){
                sum+=nums[i];
            }else{
                break;
            }
        }
        Set<Integer> s = new HashSet<>();
        for(int i : nums){
            if(i >= sum){
                s.add(i);
            }
        }
        while(s.contains(sum)){
                sum++;
        }
        return sum;
    }
}