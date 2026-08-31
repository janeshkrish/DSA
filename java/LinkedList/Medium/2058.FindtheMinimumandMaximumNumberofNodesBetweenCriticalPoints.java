/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        List<Integer> nums = new ArrayList<>();

        while(head != null){
            nums.add(head.val);
            head = head.next;
        }

        List<Integer> criticalPoints = new ArrayList<>();

        int n = nums.size();

        for(int i = 1; i < n - 1; i++){
            if(nums.get(i) > nums.get(i - 1) && nums.get(i) > nums.get(i + 1)){
                criticalPoints.add(i);
            }
            else if(nums.get(i) < nums.get(i - 1) && nums.get(i) < nums.get(i + 1)){
                criticalPoints.add(i);
            }
        }

        int m = criticalPoints.size();

        if(m < 2){
            return new int[]{-1, -1};
        }

        int minDist = Integer.MAX_VALUE;

        int maxDist = criticalPoints.get(m - 1) - criticalPoints.get(0);

        for(int i = 1; i < m; i++){
            minDist = Math.min(
                minDist,
                criticalPoints.get(i) - criticalPoints.get(i - 1)
            );
        }

        return new int[]{minDist, maxDist};
    }
}