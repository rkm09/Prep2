package LeetDaily.medium;

import GenDS.TreeNode;

import java.util.*;

public class AmtOfTime2385 {
//    static int time = 0;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(4);
        TreeNode left2 = new TreeNode(5);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(amountOfTime(root, 3));
    }
    public static int amountOfTime(TreeNode root, int start) {
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