class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] res = new long[k];
        long[] cnt = new long[k];
        for (int x : nums) {
            int mod = x % k;
            long[] tmp = new long[k];
            for (int i = 0; i < k; i++) {
                int newMod = (i * mod) % k;
                tmp[newMod] += cnt[i];
                res[newMod] += cnt[i];
            }
            res[mod]++;
            tmp[mod]++;
            cnt = tmp;
        }

        return res;
    }
}