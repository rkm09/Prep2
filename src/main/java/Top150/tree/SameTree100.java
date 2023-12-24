package Top150.tree;

import GenDS.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;


public class SameTree100 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode p = new TreeNode(1, left, right);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        TreeNode q = new TreeNode(1, left1, right1);
        System.out.println(isSameTree1(p, q));
    }

//    recursion; time: O(n), space: O(n)
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    //    iteration; time: O(n), space: O(n)
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(!isValid(p,q)) return false;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p); stack.push(q);
        while(!stack.isEmpty()) {
            TreeNode first = stack.pop();
            TreeNode second = stack.pop();
            if(!isValid(first, second)) return false;
            if(first != null) {
                if(!isValid(first.left, second.left)) return false;
                if(first.left != null) {
                    stack.add(first.left);
                    stack.add(second.left);
                }
                if(!isValid(first.right, second.right)) return false;
                if(first.right != null) {
                    stack.add(first.right);
                    stack.add(second.right);
                }
            }
        }
        return true;
    }
    private static boolean isValid(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return true;
    }

//    similar to other iterative but with ArrayDeque. more space
    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(!isValid(p,q)) return false;
        Deque<TreeNode> deqP = new ArrayDeque<>();
        Deque<TreeNode> deqQ = new ArrayDeque<>();
        deqP.addLast(p);
        deqQ.addLast(q);
        while(!deqP.isEmpty()) {
            TreeNode first = deqP.removeFirst();
            TreeNode second = deqQ.removeFirst();
            if(!isValid(first, second)) return false;
            if(first != null) {
                if(!isValid(first.left, second.left)) return false;
                if(first.left != null) {
                    deqP.addLast(first.left);
                    deqQ.addLast(second.left);
                }
                if(!isValid(first.right, second.right)) return false;
                if(first.right != null) {
                    deqP.addLast(first.right);
                    deqQ.addLast(second.right);
                }
            }
        }
        return true;
    }
}

/*
Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
Input: p = [1,2,3], q = [1,2,3]
Output: true
Input: p = [1,2], q = [1,null,2]
Output: false
Input: p = [1,2,1], q = [1,1,2]
Output: false
Constraints:
The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104
 */