package Top150.tree;

import GenDS.Pair;
import GenDS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrder102 {
    public static void main(String[] args) {
        TreeNode right1 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, right1, right2);
        TreeNode root = new TreeNode(3, left, right);
        List<List<Integer>> list = levelOrder(root);
        for(List<Integer> li : list) {
            System.out.println(li);
        }
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList();
        queue.offer(new Pair(root, 0));
        while(!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int level = pair.getValue();
            if(node != null) {
                if(res.size() == level) {
                    List<Integer> li = new ArrayList<>();
                    li.add(node.val);
                    res.add(li);
                } else {
                    res.get(level).add(node.val);
                }
                queue.offer(new Pair<>(node.left, level + 1));
                queue.offer(new Pair<>(node.right, level + 1));
            }
        }
        return res;
    }
}

/*
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Example 2:
Input: root = [1]
Output: [[1]]
Example 3:
Input: root = []
Output: []
Constraints:
The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
 */