class Solution {

    int[] dp;

    public int maxJumps(int[] arr, int d) {

        int n = arr.length;
        dp = new int[n];

        int answer = 1;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dfs(arr, d, i));
        }

        return answer;
    }

    private int dfs(int[] arr, int d, int index) {
        if (dp[index] != 0) {
            return dp[index];
        }
        int maxVisit = 1;
        for (int i = index + 1; i <= Math.min(index + d, arr.length - 1); i++) {
            if (arr[i] >= arr[index]) {
                break;
            }

            maxVisit = Math.max(maxVisit, 1 + dfs(arr, d, i));
        }
        for (int i = index - 1; i >= Math.max(index - d, 0); i--) {
            if (arr[i] >= arr[index]) {
                break;
            }

            maxVisit = Math.max(maxVisit, 1 + dfs(arr, d, i));
        }

        dp[index] = maxVisit;

        return maxVisit;
    }
}