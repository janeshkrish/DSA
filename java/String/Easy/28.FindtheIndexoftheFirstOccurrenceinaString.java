class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if(m==0) return 0;
        for(int i=0;i<=n-m;i++){
            int j=0;
            while(j<m && haystack.charAt(i+j) == needle.charAt(j)){
                j++;
            }
            if(j==m){   
                return i;
            }
        }
        return -1;
    }
}