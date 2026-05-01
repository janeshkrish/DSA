class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;

        int sum = 0;
        int f = 0;

        // Step 1: calculate sum and F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f += i * nums[i];
        }

        int max = f;

        // Step 2: use relation to compute F(k)
        for (int k = 1; k < n; k++) {
            f = f + sum - n * nums[n - k];
            max = Math.max(max, f);
        }

        return max;
    }
}