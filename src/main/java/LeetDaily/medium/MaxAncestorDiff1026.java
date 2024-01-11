package LeetDaily.medium;

import GenDS.TreeNode;


public class MaxAncestorDiff1026 {
    static int result;
    public static void main(String[] args) {
        TreeNode right11 = new TreeNode(3);
        TreeNode right1 = new TreeNode(0, right11, null);
        TreeNode right = new TreeNode(2, null, right1);
        TreeNode root = new TreeNode(1, null, right);
        System.out.println(maxAncestorDiff(root));
    }

//    recursion; time: O(n), space: O(n)
//    insight: given any two nodes on the same root to leaf path, they must have the same ancestral relation
    public static int maxAncestorDiff(TreeNode root) {
        if(root == null) return 0;
        return helper(root, root.val, root.val);
    }
    private static int helper(TreeNode node, int currMax, int currMin) {
//        it's a leaf, compute
        if(node == null) {
            return currMax - currMin;
        }
        currMax = Math.max(node.val, currMax);
        currMin = Math.min(node.val, currMin);
        int left = helper(node.left, currMax, currMin);
        int right = helper(node.right, currMax, currMin);
        return Math.max(left, right);
    }


//    basic recursion; time: O(n), space: O(n)
    public static int maxAncestorDiff1(TreeNode root) {
        if(root == null) return 0;
        result = 0;
        helper1(root, root.val, root.val);
        return result;
    }
    private static void helper1(TreeNode node, int currMax, int currMin) {
//        it's a leaf, compute
        if(node == null) {
            return;
        }
        int possibleResult = Math.max(Math.abs(currMax - node.val), Math.abs(node.val - currMin));
        result = Math.max(possibleResult, result);
        currMax = Math.max(node.val, currMax);
        currMin = Math.min(node.val, currMin);
        helper1(node.left, currMax, currMin);
        helper1(node.right, currMax, currMin);
    }
}

/*
Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
Input: root = [1,null,2,null,0,3]
Output: 3
Constraints:
The number of nodes in the tree is in the range [2, 5000].
0 <= Node.val <= 105
 */
