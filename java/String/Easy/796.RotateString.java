class Solution {
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()){
            return false;
        }
        String com = s + s;
        return com.contains(goal);
        
    }
}