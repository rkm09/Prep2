package Top150.tree;

import GenDS.TreeNode;

public class SortedArrayToBST108 {
    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(nums));
    }
    public static TreeNode sortedArrayToBST(int[] nums) {
        final int n = nums.length;
        int mid = nums[n/2];
        TreeNode root = new TreeNode(mid);
        if(n == 1) return root;
        int i = mid - 1, j = mid + 1;
        while(i >= 0 && j < n) {
            TreeNode left = new TreeNode(nums[i]);
            TreeNode right = new TreeNode(nums[j]);
            root.left = left;
            root.right = right;
        }
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
 */