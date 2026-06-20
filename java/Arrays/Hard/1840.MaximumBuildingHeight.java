class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> list = new ArrayList<>();
        for(int[] r :restrictions){
            list.add(new int[]{r[0],r[1]});
        }
        list.add(new int[]{1,0});
        list.sort((a,b)-> Integer.compare(a[0],b[0]));
        if(list.get(list.size()-1)[0] != n){
            list.add(new int[]{n,n-1});
        }
        int m = list.size();
        for(int i=1;i<m;i++){
            int dist = list.get(i)[0] - list.get(i-1)[0];
            list.get(i)[1] = Math.min(list.get(i)[1],list.get(i-1)[1]+dist);
        }
        for(int i=m-2;i>=0;i--){
            int dist = list.get(i+1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(list.get(i)[1],list.get(i+1)[1]+dist);
        }
        long ans = 0;
        for(int i=1;i<m;i++){
            long x1 = list.get(i-1)[0];
            long h1 = list.get(i-1)[1];
            long x2 = list.get(i)[0];
            long h2 = list.get(i)[1];
            long d = x2 -x1;
            ans = Math.max(ans,(h1+h2+d)/2);
        }
        return (int) ans;
    }
}