package Top150.tree;

import GenDS.Pair;
import GenDS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrder102 {
    static List<List<Integer>> levels;
    public static void main(String[] args) {
        TreeNode right1 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, right1, right2);
        TreeNode root = new TreeNode(3, left, right);
        List<List<Integer>> list = levelOrder2(root);
        for(List<Integer> li : list) {
            System.out.println(li);
        }
    }

    //  [def]; recursion; time: O(n), space: O(n); faster;
    public static List<List<Integer>> levelOrder(TreeNode root) {
        levels = new ArrayList<>();
        if(root == null) return levels;
        helper(root, 0);
        return levels;
    }
    private static void helper(TreeNode node, int level) {
        if(node != null) {
            if(levels.size() == level) {
                levels.add(new ArrayList<>());
            }
            levels.get(level).add(node.val);
            helper(node.left, level + 1);
            helper(node.right, level + 1);
        }
    }

    //   iterative bfs; time: O(n), space: O(n)
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) return levels;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int level = 0;
        while(!queue.isEmpty()) {
            levels.add(new ArrayList<>());
            int levelLength = queue.size();
            for(int i = 0 ; i < levelLength ; i++) {
                TreeNode node = queue.poll();
                levels.get(level).add(node.val);
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return levels;
    }

//    [def] iterative bfs; time: O(n), space: O(n)
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList();
        queue.offer(new Pair(root, 0));
        while(!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int level = pair.getValue();
            if(res.size() == level) {
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val);
            if(node.left != null) {
                queue.offer(new Pair<>(node.left, level + 1));
            }
            if(node.right != null) {
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