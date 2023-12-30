package Top150.tree;

import GenDS.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MinAbsDiff530 {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(1);
        TreeNode left2 = new TreeNode(3);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right = new TreeNode(6);
        TreeNode root = new TreeNode(4, left, right);
        System.out.println(getMinimumDifference(root));
    }
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
            res = Math.min(res, Math.abs(li.get(i) - li.get(i-1)));
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
 */
