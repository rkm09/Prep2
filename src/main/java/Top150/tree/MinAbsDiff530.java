package Top150.tree;

import GenDS.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MinAbsDiff530 {
    static List<Integer> countNodes = new ArrayList<>();
    static TreeNode prevNode;
    static int minDifference = Integer.MAX_VALUE;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(1);
        TreeNode left2 = new TreeNode(3);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right = new TreeNode(6);
        TreeNode root = new TreeNode(4, left, right);
        System.out.println(getMinimumDifference3(root));
    }

//    inorder traversal without list; time: O(n), space: O(n)
    public static int getMinimumDifference3(TreeNode root) {
        inorderTraversal(root);
        return minDifference;
    }
    private static void inorderTraversal(TreeNode node) {
        if(node == null) return;
        inorderTraversal(node.left);
        if(prevNode != null) {
            minDifference = Math.min(node.val - prevNode.val, minDifference);
        }
        prevNode = node;
        inorderTraversal(node.right);
    }

//    inorder traversal using list; time: O(n), space: O(n)
//    BST inorder is already sorted
    public static int getMinimumDifference2(TreeNode root) {
        inorder(root);
        int n = countNodes.size();
        int minDiff = Integer.MAX_VALUE;
        for(int i = 1; i < n ; i++) {
            minDiff = Math.min(minDiff, countNodes.get(i) - countNodes.get(i-1));
        }
        return minDiff;
    }
    private static void inorder(TreeNode node) {
        if(node == null) return;
        inorder(node.left);
        countNodes.add(node.val);
        inorder(node.right);
    }

//    recursive dfs; time: O(nlogn), space: O(n)
    public static int getMinimumDifference1(TreeNode root) {
        dfs(root);
        System.out.println(countNodes);
        Collections.sort(countNodes);
        int n = countNodes.size();
        int minDiff = Integer.MAX_VALUE;
        for(int i = 1; i < n ; i++) {
            minDiff = Math.min(minDiff, countNodes.get(i) - countNodes.get(i-1));
        }
        return minDiff;
    }
    private static void dfs(TreeNode node) {
        if(node == null) return;
        countNodes.add(node.val);
        dfs(node.left);
        dfs(node.right);
    }

//    [def]; iterative with stack; time: O(nlogn)
    public static int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> li = new ArrayList<>();
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            li.add(node.val);
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.sort(li);
        int res = Integer.MAX_VALUE;
        for(int i = 1 ; i < li.size() ; i++) {
            res = Math.min(res, li.get(i) - li.get(i-1));
        }
        return res;
    }
}

/*
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
Input: root = [4,2,6,1,3]
Output: 1
Input: root = [1,0,48,null,null,12,49]
Output: 1
Constraints:
The number of nodes in the tree is in the range [2, 104].
0 <= Node.val <= 105

same as leet 783.
 */
