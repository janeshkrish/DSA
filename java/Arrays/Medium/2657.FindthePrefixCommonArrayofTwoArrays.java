class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {

        int n = A.length;

        int[] result = new int[n];

        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();

        int commonCount = 0;

        for (int i = 0; i < n; i++) {
            setA.add(A[i]);
            setB.add(B[i]);
            if (setB.contains(A[i])) {
                commonCount++;
            }
            if (A[i] != B[i] && setA.contains(B[i])) {
                commonCount++;
            }

            result[i] = commonCount;
        }

        return result;
    }
}