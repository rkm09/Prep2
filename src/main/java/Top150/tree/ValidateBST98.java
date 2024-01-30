package Top150.tree;

import GenDS.Pair;
import GenDS.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class ValidateBST98 {
    private static Integer prev;
    public static void main(String[] args) {
        TreeNode left = new TreeNode(4);
        TreeNode right1 = new TreeNode(3);
        TreeNode right2 = new TreeNode(7);
        TreeNode right = new TreeNode(6, right1, right2);
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(isValidBST(root));
    }

//    inorder recursion; time: O(n), space: O(n); fast
//    postOrder dfs: Left-Right-Root [bottom to top, left to right], preorder dfs: Root-Left-Right [top to bottom, left to right]
//    inorder dfs: Left-Root-Right, bfs : [top to bottom, left to right]
//    inorder bst traversal naturally ensures ascending order
    public static boolean isValidBST(TreeNode root) {
        prev = null;
        return inorder(root);
    }
    private static boolean inorder(TreeNode node) {
        if(node == null) {
            return true;
        }
        if(!inorder(node.left)) {
            return false;
        }
        if(prev != null && node.val <= prev) {
            return false;
        }
        prev = node.val;
        return inorder(node.right);
    }

//    inorder iterative; time: O(n), space: O(n)
    public static boolean isValidBST1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;
        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            root = root.right;
        }
        return true;
    }


    //    recursion; time: O(n), space: O(n); fast
    public static boolean isValidBST2(TreeNode root) {
        return validate(root, null, null);
    }
    private static boolean validate(TreeNode node, Integer low, Integer high) {
        if(node == null)
            return true;
        if((low != null && node.val <= low) || (high != null && node.val >= high)) {
            return false;
        }
        return validate(node.right, node.val, high) && validate(node.left, low, node.val);
    }

//    iterative with stack; time: O(n), space: O(n)
    public static boolean isValidBST3(TreeNode root) {
        boolean isValid = true;
        Deque<TreeNode> stack = new LinkedList<>() {{ push(root); }};
        Deque<Pair<Integer, Integer>> stackLimits = new LinkedList<>() {{ push(null); push(null); }};
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            Pair<Integer, Integer> pair = stackLimits.pop();
            if(node == null) continue;
            Integer low = (pair == null) ? null : pair.getKey();
            Integer high = (pair == null) ? null : pair.getValue();

            if((high != null && node.val >= high) || (low != null && node.val <= low)) {
                return !isValid;
            }
            stack.push(node.left);
            stackLimits.push(new Pair<>(low, node.val));
            stack.push(node.right);
            stackLimits.push(new Pair<>(node.val, high));
        }
        return isValid;
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