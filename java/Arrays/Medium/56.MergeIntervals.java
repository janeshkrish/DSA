import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        result.add(current);
        for (int[] interval : intervals) {

            int currentEnd = current[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];
            if (nextStart <= currentEnd) {
                current[1] = Math.max(currentEnd, nextEnd);
            } 
            else {
                current = interval;
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}