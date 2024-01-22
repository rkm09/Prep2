package Top150.linkedlists;

public class PartitionList86 {
    public static void main(String[] args) {
        ListNode l6 = new ListNode(2);
        ListNode l5 = new ListNode(5, l6);
        ListNode l4 = new ListNode(2, l5);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(4, l3);
        ListNode head = new ListNode(1, l2);
        ListNode res = partition(head, 3);
        while(res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
    public static ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        int n = 1;
        while(node.next != null) {
            n++;
            node = node.next;
        }
        System.out.println(n);
        node = head;
        ListNode high = null;
        ListNode partition = null;
        ListNode prev = null;
        int i = 1;
        while(i < n) {
            System.out.println("i: "+i+", node.val: "+node.val);
            if(node.val == x) {
                partition = node;
                if(high == null) {
                    high = prev;
                }
            } else if(node.val > x && high == null) {
                high = prev;
            } else if(node.val < x && partition != null) {
                ListNode temp = node;
                ListNode temp2 = high;
                prev.next = temp.next;
                node.next = temp2.next;
                temp2.next = node;
                high = node;
                node = temp;
            }
            prev = node;
            node = node.next;
            i++;
        }
        return head;
    }
}

/*
Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:
Input: head = [2,1], x = 2
Output: [1,2]
Constraints:
The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
 */
