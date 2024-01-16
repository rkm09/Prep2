package Top150.tree;

import GenDS.Pair;
import GenDS.TreeNode;

import java.util.*;

public class SumRootToLeaf129 {
    static List<String> li;
    static int rootToLeaf = 0;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(5);
        TreeNode left2 = new TreeNode(1);
        TreeNode left = new TreeNode(9, left1, left2);
        TreeNode right = new TreeNode(0);
        TreeNode root = new TreeNode(4, left, right);
        System.out.println(sumNumbers(root));
    }

//    iterative dfs; time: O(n), space: O(h) where h is the height; fast; 
    public static int sumNumbers(TreeNode root) {
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        int currNum, rootToLeaf = 0;
        stack.push(new Pair(root, 0));
        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            currNum = pair.getValue();
            if(node != null) {
                currNum = currNum * 10 + node.val;
                if(node.left == null && node.right == null) {
                    rootToLeaf += currNum;
                } else {
                    stack.push(new Pair(node.right, currNum));
                    stack.push(new Pair(node.left, currNum));
                }
            }
        }
        return rootToLeaf;
    }

//    recursive dfs; time: O(n), space: O(h); fastest
    public static int sumNumbers1(TreeNode root) {
        preOrder(root, 0);
        return  rootToLeaf;
    }
    private static void preOrder(TreeNode node, int currNum) {
        if(node != null) {
            currNum = currNum * 10 + node.val;
            if(node.left == null && node.right == null) {
                rootToLeaf += currNum;
            }
            preOrder(node.left, currNum);
            preOrder(node.right, currNum);
        }
    }

//    [def]; recursive dfs;
//    string concat over a large number of values is expensive
    public static int sumNumbersN(TreeNode root) {
        li = new ArrayList<>();
        int sum = 0;
        dfs(root, "");
        for(String num : li) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
    private static void dfs(TreeNode node, String num) {
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
