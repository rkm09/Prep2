package Top150.tree;

import GenDS.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeaf129 {
    static List<String> li;
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(sumNumbers(root));
    }
    public static int sumNumbers(TreeNode root) {
        li = new ArrayList<>();
        int sum = 0;
        dfs(root, "");
        System.out.println(li);
        for(String num : li) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
    private static void dfs(TreeNode node, String num) {
        if(node == null) {
            li.add(num);
        }
        if(node != null) {
            num += node.val;
            if(node.left == null && node.right == null) {
                li.add(num);
            }
            if(node.left != null) {
                dfs(node.left, num);
            }
            if(node.right != null) {
                dfs(node.right, num);
            }
        }
    }
}

/*
You are given the root of a binary tree containing digits from 0 to 9 only.
Each root-to-leaf path in the tree represents a number.
For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
A leaf node is a node with no children.
Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.
 */
