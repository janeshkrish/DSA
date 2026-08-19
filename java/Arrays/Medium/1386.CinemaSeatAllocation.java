import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> graph = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                int mask = graph.getOrDefault(row, 0);
                mask |= (1 << (col - 2));
                graph.put(row, mask);
            }
        }
        int maxFamilies = 2 * n; 
        
        for (int mask : graph.values()) {
            boolean left = (mask & 15) == 0;    
            boolean right = (mask & 240) == 0; 
            boolean mid = (mask & 60) == 0;     
            
            maxFamilies -= 2; 
            
            if (left && right) {
                maxFamilies += 2;
            } else if (left || right || mid) {
                maxFamilies += 1;
            }
        }
        
        return maxFamilies;
    }
}