class Solution {
    public boolean uniformArray(int[] a) {
        int mn = Integer.MAX_VALUE;
        int oddCnt = 0;
        for (int x : a) {
            mn = Math.min(mn, x);
            if (x % 2 == 1) oddCnt++;
        }
        return mn % 2 != 0 || oddCnt == 0;
    }
}