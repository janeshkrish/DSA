class Solution {
    public void rotate(int[] nums, int k) {
        int order = k % nums.length;
        if(nums == null || order < 0){
            throw new IllegalArgumentException("Illegal issue");
        }
        int a = nums.length - order;
        reverse(nums,0,a-1);
        reverse(nums,a,nums.length-1);
        reverse(nums,0,nums.length-1);
    }
    public void reverse(int[] nums,int left,int right){
        if(nums == null || nums.length ==1){
            return;
        }
        while(left<right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}