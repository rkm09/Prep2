package Top150.tree;

import GenDS.TreeNode;

import java.util.*;

public class SymmetricTree101 {
    public static void main(String[] args) {
        TreeNode leftChild2 = new TreeNode(3);
        TreeNode rightChild2 = new TreeNode(3);
        TreeNode left = new TreeNode(2, null, leftChild2);
        TreeNode right = new TreeNode(2, null, rightChild2);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(isSymmetric1(root));
    }

//  recursive; time: O(n), space: O(n) [faster]
    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private static boolean isMirror(TreeNode t1 , TreeNode t2) {
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && (isMirror(t1.left, t2.right))
                && (isMirror(t1.right, t2.left));
    }

//  iterative; time: O(n), space: O(n); bfs
    public static boolean isSymmetric1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1 == null && t2 == null) continue;
            if(t1 == null || t2 == null) return false;
            if(t1.val != t2.val) return false;
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }

}

/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
Input: root = [1,2,2,3,4,4,3]
Output: true
Input: root = [1,2,2,null,3,null,3]
Output: false
Constraints:
The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
Follow up: Could you solve it both recursively and iteratively?
-----------------------------------------------------------------------
Two trees are a mirror reflection of each other if:
- Their two roots have the same value.
- The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
 */