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

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: Get length
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 2: Bottom-up merge
        for (int size = 1; size < length; size *= 2) {
            ListNode curr = dummy.next;
            ListNode tail = dummy;

            while (curr != null) {
                ListNode left = curr;
                ListNode right = split(left, size);
                curr = split(right, size);

                tail = merge(left, right, tail);
            }
        }

        return dummy.next;
    }

    // 🔸 Split list into two parts
    private ListNode split(ListNode head, int size) {
        while (head != null && size > 1) {
            head = head.next;
            size--;
        }

        if (head == null) return null;

        ListNode second = head.next;
        head.next = null;
        return second;
    }
    private ListNode merge(ListNode l1, ListNode l2, ListNode tail) {
        ListNode curr = tail;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 != null) curr.next = l1;
        else curr.next = l2;

        // Move to end
        while (curr.next != null) {
            curr = curr.next;
        }

        return curr;
    }
}