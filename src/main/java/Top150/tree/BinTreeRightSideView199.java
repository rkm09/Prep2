package Top150.tree;

import GenDS.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinTreeRightSideView199 {
    private static List<Integer> rightView;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(5);
        TreeNode left = new TreeNode(2, left1, null);
        TreeNode right2 = new TreeNode(4);
        TreeNode right = new TreeNode(3, null, right2);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(rightSideView1(root));
    }

    //    [def]; recursive dfs; time: O(n), space: O(h) ; fastest;
    public static List<Integer> rightSideView1(TreeNode root) {
        rightView = new ArrayList<>();
        dfs(root, 0);
        return rightView;
    }
    private static void dfs(TreeNode node, int level) {
        if(node != null) {
            if(rightView.size() == level) {
                rightView.add(level);
            }
            rightView.set(level, node.val);
            if(node.left != null) {
                dfs(node.left, level + 1);
            }
            if(node.right != null) {
                dfs(node.right, level + 1);
            }

        }
    }

//    [def]; time: O(n), space: O(n) ; fast;
    public static List<Integer> rightSideView(TreeNode root) {
        rightView = new ArrayList<>();
        if(root == null) {
            return rightView;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int levelLength = queue.size();
            for(int i = 0 ; i < levelLength ; i++) {
                TreeNode node = queue.poll();
                if(i == levelLength - 1) {
                    rightView.add(node.val);
                }
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return rightView;
    }

}

/*
Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
Example 2:
Input: root = [1,null,3]
Output: [1,3]
Example 3:
Input: root = []
Output: []
Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */