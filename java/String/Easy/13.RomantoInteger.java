import java.util.*;

class Solution {
    public int romanToInt(String s) {
        
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int current = map.get(s.charAt(i));

            if (i < s.length() - 1 && current < map.get(s.charAt(i + 1))) {
                sum -= current;
            } else {
                sum += current;
            }
        }

        return sum;
    }
}