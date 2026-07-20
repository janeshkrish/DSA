class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        int total = n * m;
        if(r * c != total){
            return mat;
        }
        int[][] res = new int[r][c];
        for(int i=0;i<total;i++){
            res[i/c][i%c] = mat[i/n][i%n];
        }
        return res;
    }
}