package Top150.easy;


import GenDS.TreeNode;

public class SameTree100 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode p = new TreeNode(1, left, right);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        TreeNode q = new TreeNode(1, left1, right1);
        System.out.println(isSameTree(p, q));
    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        return false;
    }
}

/*
Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
Input: p = [1,2,3], q = [1,2,3]
Output: true
Input: p = [1,2], q = [1,null,2]
Output: false
Input: p = [1,2,1], q = [1,1,2]
Output: false
Constraints:
The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104
 */