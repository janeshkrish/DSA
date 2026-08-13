class Solution {

    class Node {
        int len, pref, suff, best;
        char left, right;

        Node(int len, int pref, int suff, int best, char left, char right) {
            this.len = len;
            this.pref = pref;
            this.suff = suff;
            this.best = best;
            this.left = left;
            this.right = right;
        }
    }

    Node[] tree;

    Node merge(Node a, Node b) {
        Node res = new Node(
            a.len + b.len,
            a.pref,
            b.suff,
            Math.max(a.best, b.best),
            a.left,
            b.right
        );

        if (a.pref == a.len && a.right == b.left)
            res.pref = a.len + b.pref;

        if (b.suff == b.len && a.right == b.left)
            res.suff = b.len + a.suff;

        if (a.right == b.left)
            res.best = Math.max(res.best, a.suff + b.pref);

        return res;
    }

    void build(String s, int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(1, 1, 1, 1, s.charAt(l), s.charAt(l));
            return;
        }

        int mid = (l + r) / 2;

        build(s, node * 2, l, mid);
        build(s, node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            tree[node] = new Node(1, 1, 1, 1, c, c);
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid)
            update(node * 2, l, mid, idx, c);
        else
            update(node * 2 + 1, mid + 1, r, idx, c);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters,
                                  int[] queryIndices) {

        int n = s.length();

        tree = new Node[4 * n];

        build(s, 1, 0, n - 1);

        int[] ans = new int[queryCharacters.length()];

        for (int i = 0; i < queryCharacters.length(); i++) {

            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);

            update(1, 0, n - 1, idx, c);

            ans[i] = tree[1].best;
        }

        return ans;
    }
}