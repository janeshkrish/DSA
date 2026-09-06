class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int last = 1;

            for (int j = 1; j <= n; j++) {
                int cur = dp[j];

                int take = 0, notTake;
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    take = last;
                }
                notTake = cur;

                dp[j] = (take + notTake);
                last = cur;
            }
        }

        return dp[n];
    }
}