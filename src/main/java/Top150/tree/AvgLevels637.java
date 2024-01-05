package Top150.tree;

import GenDS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AvgLevels637 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode right1 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);
        TreeNode right = new TreeNode(20, right1, right2);
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(averageOfLevels(root));
    }

//    dfs; time: O(n), space: O(h) where h is the height of the tree
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        average(root, 0, res, count);
        for(int i = 0 ; i < res.size() ; i++) {
            res.set(i, res.get(i) / count.get(i));
        }
        return res;
    }
    private static void average(TreeNode node, int i, List<Double> sum, List<Integer> count) {
        if(node == null) {
            return;
        }
        if(i < sum.size()) {
            sum.set(i, sum.get(i) + node.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * node.val);
            count.add(1);
        }
        average(node.left, i + 1, sum, count);
        average(node.right, i + 1, sum, count);
    }
}

/*
Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
Input: root = [3,9,20,null,null,15,7]
Output: [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence, return [3, 14.5, 11].
Input: root = [3,9,20,15,7]
Output: [3.00000,14.50000,11.00000]
Constraints:
The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */