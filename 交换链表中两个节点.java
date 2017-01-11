/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param head a ListNode
     * @oaram v1 an integer
     * @param v2 an integer
     * @return a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // Write your code here
        
        //swap 两个， 需要知道prev1 prev2， node1 node2， 和其中一个node的after
        //这样就不会没有指针指向他们，不会被回收。
        //corner case： 是否v1 v2 都在ll里，不在则不swap
        //               
        //              是否交换的v1 v2相连：
        //                              是否v1 在 v2 后面
        //              相连一种解法，不相连另外一种解法
        
        if(head == null || v1 == v2) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        ListNode prev1 = null;
        ListNode prev2 = null;
        ListNode curr = head;
        
        while(curr.next != null){
            if(curr.next.val == v1){
                prev1 = curr;
            }
            else if(curr.next.val == v2){
                prev2 = curr;
            }
            curr = curr.next;
        }
        
        if(prev1 == null || prev2 == null){
            return dummy.next;
        }
        
        if(prev2.next == prev1){
            ListNode tmp = prev2.next;
            prev2.next = prev1.next;
            ListNode tmp2 = prev1.next.next;
            prev1.next.next = prev1;
            prev1.next = tmp2;
            return dummy.next;
        }
        
        ListNode node1 = prev1.next;
        ListNode node2 = prev2.next;
        ListNode after_2 = node2.next;
        
        if(prev1.next == prev2){
            prev1.next = node2;
            node2.next = node1;
            node1.next = after_2;
        }
        else {
            prev1.next = node2;
            node2.next = node1.next;
            prev2.next = node1;
            node1.next = after_2;
        }
       
        return dummy.next;
        
    }
}
