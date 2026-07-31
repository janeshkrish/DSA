class Solution {
    public int minimumPushes(String word) {
        Integer[] f = new Integer[26]; 
        Arrays.fill(f, 0); 
        for(int i = 0; i < word.length(); i++) f[word.charAt(i) - 'a']++; 
        Arrays.sort(f, Collections.reverseOrder()); 
        int ans = 0; 
        for(int i = 0; i < 26; i++) {
            if(f[i] != 0) {
                ans += (i / 8 + 1) * f[i]; 
            }
        }
        return ans; 
    }
}