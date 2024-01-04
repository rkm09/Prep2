package Top150.tree;

import GenDS.TreeNode;

import java.util.Stack;

public class PathSum112 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);
        int target = 4;
        System.out.println(hasPathSum1(root, target));
    }
//    time: O(n), space: O(n)
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        targetSum -= root.val;
        if(root.left == null && root.right == null) {
            return targetSum == 0;
        }
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

//    Iterative dfs; time: O(n), space: O(n)
    public static boolean hasPathSum1(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(targetSum - root.val);
        while(!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int currSum = sumStack.pop();
            if(node.left == null && node.right == null && currSum == 0) {
                return true;
            }
            if(node.left != null) {
                nodeStack.push(node.left);
                sumStack.push(currSum - node.left.val);
            }
            if(node.right != null) {
                nodeStack.push(node.right);
                sumStack.push(currSum - node.right.val);
            }
        }
        return false;
    }

}
/*
Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
A leaf is a node with no children.
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.
Example 3:
Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.
Constraints:
The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
 */
