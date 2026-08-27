class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        // Frequency of characters in s
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // Try the position where we make the string greater.
        // Rightmost position is preferred.
        for (int i = n - 1; i >= 0; i--) {

            // Rebuild the frequency array for this pivot.
            int[] remain = cnt.clone();

            // Try to keep target[0 ... i-1] unchanged.
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (remain[x] == 0) {
                    possible = false;
                    break;
                }

                remain[x]--;
            }

            if (!possible)
                continue;

            // At position i, we need the smallest
            // available character strictly greater than target[i].
            int targetChar = target.charAt(i) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (remain[c] == 0)
                    continue;

                StringBuilder ans = new StringBuilder(target.substring(0, i));

                // Make the first difference here.
                ans.append((char) ('a' + c));

                remain[c]--;

                // Fill the rest in sorted order.
                for (int x = 0; x < 26; x++) {
                    for (int t = 0; t < remain[x]; t++) {
                        ans.append((char) ('a' + x));
                    }
                }

                return ans.toString();
            }
        }

        return "";
    }
}