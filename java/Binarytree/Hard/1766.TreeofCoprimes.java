class Solution {
    List<Integer>[] graph;
    int[] nums;
    int[] ans;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        ans = new int[n];

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        List<int[]>[] path = new ArrayList[51];

        for (int i = 1; i < 51; i++) {
            path[i] = new ArrayList<>();
        }

        dfs(0, -1, 0, path);

        return ans;
    }

    void dfs(int node, int parent, int dept, List<int[]>[] path) {

        int res = -1;
        int maxDept = -1;

        for (int i = 1; i < 51; i++) {

            if (gcd(nums[node], i) != 1)
                continue;

            if (path[i].isEmpty())
                continue;

            int[] last = path[i].get(path[i].size() - 1);

            if (last[1] > maxDept) {
                maxDept = last[1];
                res = last[0];
            }
        }

        ans[node] = res;

        path[nums[node]].add(new int[]{node, dept});

        for (int nxt : graph[node]) {
            if (nxt != parent) {
                dfs(nxt, node, dept + 1, path);
            }
        }

        path[nums[node]].remove(path[nums[node]].size() - 1);
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}