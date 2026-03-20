package java.LinkedList.Easy;
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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;     // previous node
        ListNode curr = head;     // current node
        
        while (curr != null) {
            ListNode nextTemp = curr.next; // store next
            curr.next = prev;              // reverse link
            prev = curr;                  // move prev forward
            curr = nextTemp;              // move curr forward
        }
        
        return prev; // new head
    }
}