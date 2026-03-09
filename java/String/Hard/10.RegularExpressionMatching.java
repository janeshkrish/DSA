class Solution {
    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // Handle patterns like a*, a*b*, etc
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[j] = dp[j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {

            boolean prev = dp[0];
            dp[0] = false;

            for (int j = 1; j <= n; j++) {

                boolean temp = dp[j];

                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[j] = prev;
                }

                else if (p.charAt(j - 1) == '*') {

                    dp[j] = dp[j - 2];

                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[j] = dp[j] || temp;
                    }
                }

                else {
                    dp[j] = false;
                }

                prev = temp;
            }
        }

        return dp[n];
    }
}