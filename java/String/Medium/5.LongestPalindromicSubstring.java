class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        
        // Transform string
        StringBuilder sb = new StringBuilder();
        sb.append("^");  // Starting boundary
        for (int i = 0; i < s.length(); i++) {
            sb.append("#").append(s.charAt(i));
        }
        sb.append("#$"); // Ending boundary
        
        String transformed = sb.toString();
        int n = transformed.length();
        int[] p = new int[n];
        
        int center = 0, right = 0;
        
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;
            
            if (i < right)
                p[i] = Math.min(right - i, p[mirror]);
            
            // Expand around center i
            while (transformed.charAt(i + (1 + p[i])) == 
                   transformed.charAt(i - (1 + p[i]))) {
                p[i]++;
            }
            
            // Update center and right boundary
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }
        
        // Find max palindrome length
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }
        
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }
}
