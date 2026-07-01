class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) return 0;
        TreeSet <Integer> set = new TreeSet<>();
        for(int i:nums){
            set.add(i);
        }
        int num[] = new int[set.size()];
        int n = 0;
        for(int i:set){
            num[n++] = i;
        }
        int sequence=1;
        int long_sequence=1;
        for(int i=0;i<num.length-1;i++){
            if(num[i]==(num[i+1]-1)){
                sequence++;
                long_sequence=Math.max(long_sequence,sequence);
            }
            else{
                sequence=1;
            }
        }
        return long_sequence;
    }
}