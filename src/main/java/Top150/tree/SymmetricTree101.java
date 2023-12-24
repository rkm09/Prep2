package Top150.tree;

import GenDS.TreeNode;

public class SymmetricTree101 {
    public static void main(String[] args) {
        TreeNode leftChild2 = new TreeNode(3);
        TreeNode rightChild2 = new TreeNode(3);
        TreeNode left = new TreeNode(2, null, leftChild2);
        TreeNode right = new TreeNode(2, null, rightChild2);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(isSymmetric(root));
    }
    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
//        return isSymmetric(root.left)
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
 */