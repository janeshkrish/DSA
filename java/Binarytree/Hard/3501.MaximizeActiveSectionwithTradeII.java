class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }
        
        List<Integer> typeList = new ArrayList<>();
        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();
        
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            typeList.add(s.charAt(i) - '0');
            startList.add(i);
            endList.add(j - 1);
            i = j;
        }
        
        int N = typeList.size();
        int[] type = new int[N];
        int[] start = new int[N];
        int[] endIdx = new int[N];
        for (int i = 0; i < N; i++) {
            type[i] = typeList.get(i);
            start[i] = startList.get(i);
            endIdx[i] = endList.get(i);
        }
        
        int[] posToSeg = new int[n];
        for (int i = 0; i < N; i++) {
            for (int j = start[i]; j <= endIdx[i]; j++) {
                posToSeg[j] = i;
            }
        }
        
        int[] ans = new int[N];
        for (int i = 1; i < N - 1; i++) {
            if (type[i] == 1) {
                ans[i] = (endIdx[i - 1] - start[i - 1] + 1) + (endIdx[i + 1] - start[i + 1] + 1);
            }
        }
        
        int[] logTable = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }
        
        int K = logTable[N] + 1;
        int[][] st = new int[K][N];
        for (int i = 0; i < N; i++) {
            st[0][i] = ans[i];
        }
        
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= N; i++) {
                st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + (1 << (j - 1))]);
            }
        }
        
        List<Integer> res = new ArrayList<>();
        
        for (int[] q : queries) {
            int L = q[0];
            int R = q[1];
            
            int segL = posToSeg[L];
            int segR = posToSeg[R];
            
            if (segR - segL < 2) {
                res.add(totalOnes);
                continue;
            }
            
            int maxGain = 0;
            maxGain = Math.max(maxGain, evaluateEdge(segL + 1, L, R, segL, segR, type, start, endIdx));
            maxGain = Math.max(maxGain, evaluateEdge(segR - 1, L, R, segL, segR, type, start, endIdx));
            
            if (segL + 2 <= segR - 2) {
                int L_idx = segL + 2;
                int R_idx = segR - 2;
                int j = logTable[R_idx - L_idx + 1];
                int rmqVal = Math.max(st[j][L_idx], st[j][R_idx - (1 << j) + 1]);
                maxGain = Math.max(maxGain, rmqVal);
            }
            
            res.add(totalOnes + maxGain);
        }
        
        return res;
    }
    
    private int evaluateEdge(int i, int L, int R, int segL, int segR, int[] type, int[] start, int[] endIdx) {
        if (i <= segL || i >= segR) return 0;
        if (type[i] == 0) return 0;
        
        int leftLen = 0;
        if (i - 1 == segL) leftLen = Math.max(0, endIdx[i - 1] - L + 1);
        else leftLen = endIdx[i - 1] - start[i - 1] + 1;
        
        int rightLen = 0;
        if (i + 1 == segR) rightLen = Math.max(0, R - start[i + 1] + 1);
        else rightLen = endIdx[i + 1] - start[i + 1] + 1;
        
        return leftLen + rightLen;
    }
}