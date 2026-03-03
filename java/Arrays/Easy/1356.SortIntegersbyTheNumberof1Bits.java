import java.util.*;

class Solution {
    public int[] sortByBits(int[] arr) {
        
        Integer[] temp = new Integer[arr.length];
        
        // Convert int[] to Integer[]
        for(int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        
        // Custom Sorting
        Arrays.sort(temp, (a, b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);
            
            if(countA == countB) {
                return a - b;  // sort by value if bits equal
            }
            
            return countA - countB; // sort by bit count
        });
        
        // Convert back to int[]
        for(int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
        
        return arr;
    }
}