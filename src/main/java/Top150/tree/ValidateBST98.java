package Top150.tree;

import GenDS.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateBST98 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(2, left, right);
        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST1(TreeNode root) {
        boolean isValid = true;
        Deque<TreeNode> queue = new ArrayDeque<>() {{ offer(root); }};
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left != null) {
                if(node.left.val >= node.val && node.left.val >= root.val) {
                    return !isValid;
                }
                queue.offer(node.left);
            }
            if(node.right != null) {
                if(node.right.val <= node.val && node.right.val <= root.val) {
                    return !isValid;
                }
                queue.offer(node.right);
            }
        }
        return isValid;
    }

    public static boolean isValidBST(TreeNode root) {
         return helper(root, root.val);
    }
    private static boolean helper(TreeNode node, int val) {
        if(node.left != null) {
            if(node.left.val >= node.val || node.left.val >= val) {
                return false;
            }
            helper(node.left, val);
        }
        if(node.right != null) {
            if(node.right.val <= node.val || node.right.val <= val) {
                return false;
            }
            helper(node.right, val);
        }
        return true;
    }
}

/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).
A valid BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Input: root = [2,1,3]
Output: true
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
Constraints:
The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */