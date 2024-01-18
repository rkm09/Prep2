package Top150.tree;

import GenDS.Pair;
import GenDS.TreeNode;

import java.util.Stack;


public class FlattenToList114 {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right2 = new TreeNode(6);
        TreeNode right = new TreeNode(5, null, right2);
        TreeNode root = new TreeNode(1, left, right);
    }

//    recursion dfs; time: O(n), space: O(n); fastest;
    public static void flatten(TreeNode root) {
        flattenTree(root);
    }
    private static TreeNode flattenTree(TreeNode node) {
        if(node == null) {
            return null;
        }

        if(node.left == null && node.right == null) {
            return node;
        }

        TreeNode leftTail = flattenTree(node.left);
        TreeNode rightTail = flattenTree(node.right);

        if(leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return rightTail != null ? rightTail : leftTail;
    }

//    Iterative dfs (stack); time: O(n), space: O(n)
    public static void flatten1(TreeNode root) {
        if(root == null) {
            return;
        }

        final int START = 1, END = 2;
        TreeNode tailNode = null;
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, START));

        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> nodeData = stack.pop();
            TreeNode node = nodeData.getKey();
            int recursionState = nodeData.getValue();
            if(node.left == null && node.right == null) {
                tailNode = node;
                continue;
            }
            if(recursionState == START) {
                if(node.left != null) {
                    stack.push(new Pair<>(node, END));
                    stack.push(new Pair<>(node.left, START));
                } else {
                    stack.push(new Pair<>(node.right, START));
                }
            } else {
//                process this node's subtree
                TreeNode rightNode = node.right;
                if(tailNode != null) {
                    tailNode.right = node.right;
                    node.right = node.left;
                    node.left = null;
                }
                if(rightNode != null) {
                    stack.push(new Pair<>(rightNode, START));
                }
            }
        }
    }

//    time: O(n), space: O(1)
//    constant space (like morris traversal);
//    unlike recursion we don't wait till we process the left and right subtrees entirely first; we rather rewire the connections on the fly;
    public static void flatten2(TreeNode root) {
        if(root == null) {
            return;
        }
        TreeNode node = root;
        while(node != null) {
            if(node.left != null) {
//                find the rightmost node for this
                TreeNode rightMost = node.left;
                while(rightMost.right != null) {
                    rightMost = rightMost.right;
                }

//                rewire the connection
                rightMost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }
}

/*
Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
Example 2:
Input: root = []
Output: []
Example 3:
Input: root = [0]
Output: [0]
Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */