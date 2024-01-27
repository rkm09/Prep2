package Top150.tree;

import GenDS.TreeNode;

import java.util.*;

public class BinaryTreeZigZagOrder103 {
    private static List<List<Integer>> zigZagList;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(7);
        TreeNode left = new TreeNode(9, left1, null);
        TreeNode right1 = new TreeNode(15);
//        TreeNode right2 = new TreeNode(7);
        TreeNode right = new TreeNode(20, right1, null);
        TreeNode root = new TreeNode(3, left, right);
        List<List<Integer>> zigZag = zigzagLevelOrder(root);
        for(List<Integer> li : zigZag) {
            System.out.println(li);
        }
    }

//    [def]; recursive dfs; time : O(n), space: O(n); fastest
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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

//    [def] iterative bfs; time : O(n), space: O(n)
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

//    iterative bfs; using method 2 (extra null); time :O(n), space: O(n)
    public static List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root == null) {
            return results;
        }
        LinkedList<TreeNode> queue = new LinkedList() {{ offer(root); offer(null); }};
        LinkedList<Integer> levelNodes = new LinkedList<>();
        boolean isLeftFirst = true;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                if(isLeftFirst) {
                    levelNodes.addLast(node.val);
                } else {
                    levelNodes.addFirst(node.val);
                }

                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            } else {
                results.add(levelNodes);
                levelNodes = new LinkedList<>();
                if(!queue.isEmpty()) {
                    queue.offer(null);
                }
                isLeftFirst = !isLeftFirst;
            }
        }
        return results;
    }

    //    recursive dfs; time :O(n), space: O(n)
    public static List<List<Integer>> zigzagLevelOrder4(TreeNode root) {
        zigZagList = new ArrayList<>();
        if(root == null) {
            return zigZagList;
        }
        dfs(root, 0);
        return zigZagList;
    }
    private static void dfs(TreeNode node, int level) {
        if(zigZagList.size() == level) {
            zigZagList.add(new LinkedList<>() {{ add(node.val); }});
        } else {
            if(level % 2 == 0) {
                zigZagList.get(level).add(node.val);
            } else {
                zigZagList.get(level).add(0, node.val);
            }
        }
        if(node.left != null) {
            dfs(node.left, level + 1);
        }
        if(node.right != null) {
            dfs(node.right, level + 1);
        }
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