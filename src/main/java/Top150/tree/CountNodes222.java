package Top150.tree;

import GenDS.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CountNodes222 {
//    static int count = 0;
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, null);
        System.out.println(countNodes1(root));
    }

    //    recursion; time: O(n), space: O(logn) [fast]
    public static int countNodes(TreeNode root) {
        return (root != null) ? 1 + countNodes(root.left) + countNodes(root.right) : 0;
    }

//    ---------------------------------


//    return tree depth in O(d) time
    private static int computeDepth(TreeNode node) {
        int d = 0;
        while(node.left != null) {
            node = node.left;
            d++;
        }
        return d;
    }

//    last level nodes enumerated from 0 to 2^d - 1 (left->right)
//    return true if last level's node idx exists
//    binary search with O(d) complexity
    private static boolean exists(int idx, int d, TreeNode node) {
        int left = 0, right = (int) Math.pow(2, d) - 1;
        for(int i = 0 ; i < d ; i++) {
            int pivot = left + (right - left) / 2;
            if(idx <= pivot) {
                node = node.left;
                right = pivot;
            } else {
                node = node.right;
                left = pivot + 1;
            }
        }
        return node != null;
    }

//  binary search; time: O(d^2)=> O(log^2n), space: O(1)
    public static int countNodes1(TreeNode root) {
        if(root == null) return 0;
        int d = computeDepth(root);
        if(d == 0) return 1;
//     indexes from 0 to 2^d - 1 at the last level
        int left = 1, right = (int) Math.pow(2,d) - 1;
        while(left <= right) {
            int pivot = left + (right - left) / 2;
            if(exists(pivot, d, root)) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }

//        all nodes until level d-1 => 2^d - 1 + last level nodes
        return (int)Math.pow(2, d) - 1 + left;
    }


    //    [def];
    public static int countNodesN(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count++;
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return count;
    }
}

/*
Given the root of a complete binary tree, return the number of the nodes in the tree.
According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
Design an algorithm that runs in less than O(n) time complexity.
Input: root = [1,2,3,4,5,6]
Output: 6
Example 2:
Input: root = []
Output: 0
Example 3:
Input: root = [1]
Output: 1
Constraints:
The number of nodes in the tree is in the range [0, 5 * 104].
0 <= Node.val <= 5 * 104
The tree is guaranteed to be complete.
 */