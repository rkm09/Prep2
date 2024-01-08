package LeetDaily.easy;

import GenDS.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RangeSumBST938 {
    private static int sum = 0;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(7);
        TreeNode right2 = new TreeNode(18);
        TreeNode left = new TreeNode(5, left1, left2);
        TreeNode right = new TreeNode(15, null, right2);
        TreeNode root = new TreeNode(10, left, right);
        int low = 7, high = 15;
        System.out.println(rangeSumBST3(root, low, high));
    }

//    recursive dfs; time :O(n), space: O(n); recursive faster;
//    make use of bst property to speed up
    public static int rangeSumBST3(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return sum;
    }
    private static void dfs(TreeNode node, int low, int high) {
        if(node != null) {
            if(node.val >= low && node.val <= high) {
                sum += node.val;
            }
            if(low < node.val) {
                dfs(node.left, low, high);
            }
            if(node.val < high) {
                dfs(node.right, low, high);
            }
        }
    }

//    iterative dfs; time :O(n), space: O(n)
    public static int rangeSumBST2(TreeNode root, int low, int high) {
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        int sum = 0;
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node != null) {
                if(node.val >= low && node.val <= high) {
                    sum += node.val;
                }
                if(low < node.val) {
                    stack.push(node.left);
                }
                if(node.val < high) {
                    stack.push(node.right);
                }
            }
        }
        return sum;
    }

//    iterative [mod def]; bfs; time :O(n), space: O(n)
    public static int rangeSumBST1(TreeNode root, int low, int high) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.val >= low && node.val <= high) {
                sum += node.val;
            }
            if(node.left != null && low < node.val) {
                queue.offer(node.left);
            }
            if(node.right != null && node.val < high) {
                queue.offer(node.right);
            }
        }
        return sum;
    }

//    [modified def]; recursive dfs; time: O(n), space: O(n)
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) {
            return 0;
        }
        sum += (root.val >= low && root.val <= high) ? root.val : 0;
        if(low < root.val) {
            rangeSumBST(root.left, low, high);
        }
        if(root.val < high) {
            rangeSumBST(root.right, low, high);
        }
        return sum;
    }
}

/*
Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
Constraints:
The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 105
1 <= low <= high <= 105
All Node.val are unique.
 */