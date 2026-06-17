class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] lens = new long[n];
        long len = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*')
                len = Math.max(len - 1, 0L);
            else if (c == '#')
                len *= 2;
            else if (c != '%')
                len++;
            
            lens[i] = len;
        }

        if (k >= len) return '.';

        for (int i = n - 1; ; i--) {
            char c = s.charAt(i);
            switch (c) {
                case '*':
                    break;
                case '#':
                    if (k >= lens[i] / 2)
                        k -= lens[i] / 2;
                    break;
                case '%':
                    k = lens[i] - 1 - k;
                    break;
                default:
                    if (lens[i] == k + 1)
                        return c;
            }
        }
    }
}