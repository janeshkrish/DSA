class Solution {
    public ListNode swapPairs(ListNode head) {
        
        // Dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        
        // Traverse in pairs
        while (prev.next != null && prev.next.next != null) {
            
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            // Swap nodes
            first.next = second.next;
            second.next = first;
            prev.next = second;
            
            // Move pointer to next pair
            prev = first;
        }
        
        return dummy.next;
    }
}