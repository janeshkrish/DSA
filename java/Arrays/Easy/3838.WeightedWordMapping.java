class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();
        for(String w:words){
            int s = 0;
            for(int i=0;i<w.length();i++){
                s+=weights[(w.charAt(i)&(1<<5)-1)-1];
            }
            sb.append((char)('z'- (s-((s*2521) >> ( 1<< 4)) * 26)));
        }return sb.toString();
    }
}