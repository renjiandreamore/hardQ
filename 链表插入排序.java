public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: The head of linked list.
     */
    public ListNode insertionSortList(ListNode head) {
        // write your code here
        if(head == null) return null;
        
        ListNode dummy = new ListNode(0);

        while(head != null){
            ListNode node = dummy;
            
            while(node.next != null && node.next.val < head.val){
                node = node.next;
            }
            
            ListNode tmp = head.next;
            head.next = node.next;
            node.next = head;
            head = tmp;
        }
        
        return dummy.next;
    }
}
