package Top150.tree;

import GenDS.TreeNode;


public class FlattenToList114 {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right2 = new TreeNode(6);
        TreeNode right = new TreeNode(5, null, right2);
        TreeNode root = new TreeNode(1, left, right);
    }

//    recursion dfs; time: O(n), space: O(n)
    public static void flatten(TreeNode root) {
        flattenTree(root);
    }
    private static TreeNode flattenTree(TreeNode node) {
        if(node == null) {
            return null;
        }

        if(node.left == null && node.right == null) {
            return node;
        }

        TreeNode leftTail = flattenTree(node.left);
        TreeNode rightTail = flattenTree(node.right);

        if(leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return rightTail != null ? rightTail : leftTail;
    }
}

/*
Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
Example 2:
Input: root = []
Output: []
Example 3:
Input: root = [0]
Output: [0]
Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */