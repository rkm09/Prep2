package Top150.tree;

import GenDS.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinaryTreeZigZagOrder103 {
    private static List<List<Integer>> zigZagList;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(7);
        TreeNode left = new TreeNode(9, left1, null);
        TreeNode right1 = new TreeNode(15);
//        TreeNode right2 = new TreeNode(7);
        TreeNode right = new TreeNode(20, right1, null);
        TreeNode root = new TreeNode(3, left, right);
        List<List<Integer>> zigZag = zigzagLevelOrder1(root);
        for(List<Integer> li : zigZag) {
            System.out.println(li);
        }
    }

//    [def]; recursive dfs; time : O(n), space: O(h)
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        zigZagList = new ArrayList<>();
        if(root == null) {
            return zigZagList;
        }
        helper(root, 0);
        int idx = 0;
        for(List<Integer> li : zigZagList) {
            if(idx % 2 != 0) {
                List<Integer> liR = new ArrayList<>();
                for(int i = li.size() - 1 ; i >= 0 ; i--) {
                    liR.add(li.get(i));
                }
                zigZagList.set(idx, liR);
            }
            idx++;
        }
        return zigZagList;
    }
    private static void helper(TreeNode node, int level) {
        if(zigZagList.size() == level) {
            zigZagList.add(new ArrayList<>());
        }
        zigZagList.get(level).add(node.val);
        if(node.left != null) {
            helper(node.left, level + 1);
        }
        if(node.right != null) {
            helper(node.right, level + 1);
        }
    }

//    [def] iterative bfs; time : O(n), space: O(d)
    public static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root == null) {
            return results;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>() {{ offer(root); }};
        int level = 1;
        while(!queue.isEmpty()) {
           int levelLength = queue.size();
           List<Integer> li = new ArrayList<>();
           for(int i = 0 ; i < levelLength ; i++) {
               TreeNode node = queue.poll();
               li.add(node.val);
               if(node.left != null) {
                   queue.offer(node.left);
               }
               if(node.right != null) {
                   queue.offer(node.right);
               }
           }
           level++;
           if(level % 2 != 0) {
               List<Integer> liR = new ArrayList<>();
               for(int i = li.size() - 1 ; i >= 0 ; i--) {
                   liR.add(li.get(i));
               }
               results.add(liR);
           } else {
               results.add(li);
           }
        }
        return results;
    }
}

/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:
Input: root = [1]
Output: [[1]]
Example 3:
Input: root = []
Output: []
Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 */