import java.util.*;

class Solution {
    long LIMIT = 1_000_000L;
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        String mid = "";
        int[] half = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                mid = "" + (char) ('a' + i);
            }
            half[i] = freq[i] / 2;
            halfLen += half[i];
        }
        if (countWays(half, halfLen) < k) {
            return "";
        }
        StringBuilder left = new StringBuilder();
        while (halfLen > 0) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0)
                    continue;
                half[c]--;
                long ways = countWays(half, halfLen - 1);
                if (ways >= k) {
                    left.append((char) ('a' + c));
                    halfLen--;
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }
        String right = new StringBuilder(left).reverse().toString();
        return left.toString() + mid + right;
    }
    private long countWays(int[] half, int total) {
        long ans = 1;
        int remain = total;
        for (int i = 0; i < 26; i++) {
            int cnt = half[i];
            if (cnt == 0)
                continue;
            ans *= nCr(remain, cnt);
            if (ans > LIMIT)
                return LIMIT;
            remain -= cnt;
        }
        return Math.min(ans, LIMIT);
    }
    private long nCr(int n, int r) {
        if (r > n)
            return 0;
        r = Math.min(r, n - r);
        long ans = 1;
        for (int i = 1; i <= r; i++) {
            ans = ans * (n - r + i) / i;
            if (ans > LIMIT)
                return LIMIT;
        }
        return ans;
    }
}