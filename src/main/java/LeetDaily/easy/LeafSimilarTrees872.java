package LeetDaily.easy;

import GenDS.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeafSimilarTrees872 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root1 = new TreeNode(1, left, right);
        TreeNode root2 = new TreeNode(1, right, left);
        System.out.println(leafSimilar1(root1, root2));
    }

//    [def]; recursive dfs; time: O(n+m), space: O(n+m), where n & m are the length of the trees; faster;
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        helper(root1, leaves1);
        helper(root2, leaves2);
        return  leaves1.equals(leaves2);
    }
    private static void helper(TreeNode node, List<Integer> leaves) {
        if(node != null) {
            if(node.left == null && node.right == null) {
                leaves.add(node.val);
                return;
            }
            helper(node.left, leaves);
            helper(node.right, leaves);
        }
    }

//    [def]; iterative; time: O(n), space: O(n)
    public static boolean leafSimilar1(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        dfs(root1, leaves1);
        dfs(root2, leaves2);
        return leaves1.equals(leaves2);
    }
    private static void dfs(TreeNode root, List<Integer> leaves) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node != null) {
                if(node.left == null && node.right == null) {
                    leaves.add(node.val);
                } else {
                    stack.push(node.left);
                    stack.push(node.right);
                }
            }
        }
    }
}
/*
Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
Two binary trees are considered leaf-similar if their leaf value sequence is the same.
Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true
Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false
Constraints:
The number of nodes in each tree will be in the range [1, 200].
Both of the given trees will have values in the range [0, 200].
 */