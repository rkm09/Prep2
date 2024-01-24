package LeetDaily.medium;

import GenDS.Pair;
import GenDS.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;


public class PseudoPalindromic1457 {
    static int count;

    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(1);
        TreeNode left = new TreeNode(3, left1, left2);
        TreeNode right2 = new TreeNode(1);
        TreeNode right = new TreeNode(1, null, right2);
        TreeNode root = new TreeNode(2, left, right);
        System.out.println(pseudoPalindromicPaths(root));
    }

    //    iterative preorder dfs; time: O(n), space: O(h)
    public static int pseudoPalindromicPaths(TreeNode root) {
        int count = 0, path;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque();
        stack.push(new Pair(root, 0));
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            path = pair.getValue();
            if (node != null) {
//                compute occurrence of each digit in the corresponding register
                path = path ^ (1 << node.val);
                if (node.left == null && node.right == null) {
//                    atmost one digit has an odd frequency
                    if ((path & (path - 1)) == 0) {
                        ++count;
                    }
                } else {
                    stack.push(new Pair(node.right, path));
                    stack.push(new Pair(node.left, path));
                }
            }
        }
        return count;
    }

    private static void dfs(TreeNode root, int num) {
        if (root != null) {
            num = 10 * num + root.val;
            if (root.left == null && root.right == null) {
//                li.add(num);
//                if(isPalindrome(num)) count++;
//                return;
//
            }
            dfs(root.left, num);
            dfs(root.right, num);
        }
    }
}

/*
Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
Input: root = [2,3,1,3,1,null,1]
Output: 2
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
Input: root = [2,1,1,1,3,null,null,null,null,null,1]
Output: 1
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
Example 3:
Input: root = [9]
Output: 1
Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 9
 */