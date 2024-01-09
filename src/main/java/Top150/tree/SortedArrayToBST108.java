package Top150.tree;

import GenDS.TreeNode;

import java.util.Random;

public class SortedArrayToBST108 {
    static Random rand = new Random();
    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(nums));
    }

//    time: O(n), space: O(logn)
    public static TreeNode sortedArrayToBST(int[] nums) {
        final int n = nums.length;
        return helper(nums, 0, n-1);
    }
    //    Preorder traversal (always choose left elem as mid);
    private static TreeNode helper(int[] nums, int left, int right) {
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    //    Preorder traversal (always choose right elem as mid);
    private static TreeNode helper1(int[] nums, int left, int right) {
        if(left > right) return null;
        int mid = left + (right - left) / 2;
//        this actually represents an even length with 2 mid choices as right is n-1 and not an n.
        if((left + right) % 2 == 1) mid++;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    //    Preorder traversal (choose random elem as mid);
    private static TreeNode helper2(int[] nums, int left, int right) {
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        if((left + right) % 2 == 1) mid += rand.nextInt(2);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}
/*
Given an integer array nums where the elements are sorted in ascending order, convert it to a
height-balanced binary search tree.
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
Constraints:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
--------------------------------------------
Tree Traversals: DFS(preorder, inorder, postorder), BFS
Inorder traversal of BST is an array sorted in ascending order. However, inorder traversal does not uniquely identify a BST.
But postorder & preorder do.
Height Balanced => the depths of the two sub trees of every node never differ by more than one.
=> take the mid element as root each time.
 */