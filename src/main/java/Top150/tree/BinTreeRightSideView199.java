package Top150.tree;

import GenDS.TreeNode;

import java.util.*;

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

    //    [def]; recursive dfs; time: O(n), space: O(h) ; h is the height (in case of skewed tree h = n); fastest;
    public static List<Integer> rightSideView(TreeNode root) {
        rightView = new ArrayList<>();
        if(root == null) {
            return rightView;
        }
        dfs(root, 0);
        return rightView;
    }

    private static void dfs(TreeNode node, int level) {
        if(rightView.size() == level) {
            rightView.add(node.val);
        }
//       in this case setting at level is not needed as we traverse right to left
        if(node.right != null) {
            dfs(node.right, level + 1);
        }
        if(node.left != null) {
            dfs(node.left, level + 1);
        }
    }
    
// [def]; dfs
    private static void dfs1(TreeNode node, int level) {
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

//    [def] (bfs with level); time: O(n), space: O(d) ; fast; d is the tree diameter
    public static List<Integer> rightSideView1(TreeNode root) {
        rightView = new ArrayList<>();
        if(root == null) {
            return rightView;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>() {{ offer(root); }};

        while(!queue.isEmpty()) {
            int levelLength = queue.size();

            for(int i = 0 ; i < levelLength ; i++) {
                TreeNode node = queue.poll();
                if(i == levelLength - 1) {
                    rightView.add(node.val);
                }
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return rightView;
    }

//   bfs with sentinel; time: O(n), space: O(d); where d is the diameter; worst case complete bin tree; fast;
    public static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> rightView = new ArrayList<>();
        if(root == null) {
            return rightView;
        }
//        We can't use array deque here as we will store null to mark sentinel(level end), which is not supported by array deque
        Queue<TreeNode> queue = new LinkedList<>() {{ offer(root); offer(null); }};
        TreeNode curr = root;
        TreeNode prev;

        while(!queue.isEmpty()) {
            prev = curr;
            curr = queue.poll();

            while(curr != null) {
                if(curr.left != null) {
                    queue.offer(curr.left);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                }
                prev = curr;
                curr = queue.poll();
            }

            rightView.add(prev.val);
//            add sentinel to mark end of this level
            if(!queue.isEmpty()) {
                queue.offer(null);
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