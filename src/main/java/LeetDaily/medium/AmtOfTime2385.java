package LeetDaily.medium;

import GenDS.TreeNode;

import java.util.*;

public class AmtOfTime2385 {
    private static int maxDistance;
    public static void main(String[] args) {
        TreeNode left111 = new TreeNode(5);
        TreeNode left11 = new TreeNode(4, left111, null);
        TreeNode left1 = new TreeNode(3, left11, null);
        TreeNode left2 = new TreeNode(10);
        TreeNode left = new TreeNode(2, left1, null);
        TreeNode right21 = new TreeNode(9);
        TreeNode right22 = new TreeNode(2);
        TreeNode right2 = new TreeNode(4, right21, right22);
        TreeNode right = new TreeNode(6, null, null);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(amountOfTime(root, 2));
    }

//    dfs; one pass; time: O(n), space: O(n)
    public static int amountOfTime(TreeNode root, int start) {
        traverse(root, start);
        return maxDistance;
    }
    private static int traverse(TreeNode root, int start) {
        int depth = 0;
        if(root == null) {
            return depth;
        }
        int leftDepth = traverse(root.left, start);
        int rightDepth = traverse(root.right, start);

        if(root.val == start) {
//          to signify 'start' node
            maxDistance = Math.max(leftDepth, rightDepth);
            depth = -1;
        } else if(leftDepth >= 0 && rightDepth >= 0) {
//           no 'start' node in this subtree
            depth = Math.max(leftDepth, rightDepth) + 1;
        } else {
//           this subtree contains 'start' node somewhere
            int distance = Math.abs(leftDepth) + Math.abs(rightDepth);
            maxDistance = Math.max(maxDistance, distance);
//            to continue 'start' node consideration
            depth = Math.min(leftDepth, rightDepth) - 1;
        }
        return depth;
    }

//    time: O(n), space: O(n)
//    bfs after conversion to undirected graph. both cost O(n)
    public static int amountOfTime1(TreeNode root, int start) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        convert(root, 0, map);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int minute = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            while(levelSize > 0) {
                int current = queue.poll();
                for(int num : map.get(current)) {
                    if(!visited.contains(num)) {
                        visited.add(num);
                        queue.add(num);
                    }
                }
                levelSize--;
            }
            minute++;
        }
        return minute - 1;
    }

//    convert binary tree to graph
    private static void convert(TreeNode current, int parent, Map<Integer, Set<Integer>> map) {
        if(current == null) return;
        if(!map.containsKey(current.val)) {
            map.put(current.val, new HashSet<>());
        }
        Set<Integer> adjacencyList = map.get(current.val);
        if(parent != 0) {
            adjacencyList.add(parent);
        }
        if(current.left != null) {
            adjacencyList.add(current.left.val);
        }
        if(current.right != null) {
            adjacencyList.add(current.right.val);
        }
        convert(current.left, current.val, map);
        convert(current.right, current.val, map);
    }
}
/*
You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.
Each minute, a node becomes infected if:
The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.
Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.
Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.
Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
Each node has a unique value.
A node with a value of start exists in the tree.
 */