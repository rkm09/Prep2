package Top150.tree;

import GenDS.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreePreIn105 {
    private static int preorderIndex;
    private static Map<Integer, Integer> inorderIndexMap;
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        buildTree(preorder, inorder);
    }
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();
        for(int i = 0 ; i < inorder.length ; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, preorder.length - 1);
    }
    private static TreeNode arrayToTree(int[] preorder, int left, int right) {
//        invalid; a leaf's imagined left / right ;)
        if(left > right) {
            return null;
        }
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }
}

/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]
Constraints:
1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.

 */