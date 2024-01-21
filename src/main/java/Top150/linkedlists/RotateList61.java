package Top150.linkedlists;

public class RotateList61 {
    public static void main(String[] args) {
        ListNode l5 = new ListNode(5);
        ListNode l4 = new ListNode(4, l5);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode head = new ListNode(1, l2);
        ListNode res = rotateRight(head, 1);
        while(res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

//    [def]; time: O(n), space: O(1)
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0 || head.next == null) {
            return head;
        }
        ListNode node = head;
        int n = 1;
        while(node.next != null) {
            n++;
            node = node.next;
        }
        node = head;
        int i = 1;
        k = k % n;
        if(k == 0) return head;
        while(i <= (n - k)) {
            if(i == (n - k)) {
                ListNode temp = node.next;
                node.next = null;
                node = head;
                head = temp;
                while(temp.next != null) {
                    temp = temp.next;
                }
                temp.next = node;
                break;
            } else {
                node = node.next;
            }
            i++;
        }
        return head;
    }
}

/*
Given the head of a linked list, rotate the list to the right by k places.
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Input: head = [0,1,2], k = 4
Output: [2,0,1]
Constraints:
The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
 */