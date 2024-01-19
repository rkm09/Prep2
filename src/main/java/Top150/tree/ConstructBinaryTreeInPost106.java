package Top150.tree;

import GenDS.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeInPost106 {
    private static int postOrderIndex;
    private static Map<Integer, Integer> inorderIndexMap;
    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        buildTree(inorder, postorder);
    }
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        postOrderIndex = n - 1;
        inorderIndexMap = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(postorder, 0, n - 1);
    }
    private static TreeNode arrayToTree(int[] postorder, int left, int right) {
        if(left > right) {
            return null;
        }
        int rootValue = postorder[postOrderIndex--];
        TreeNode root = new TreeNode(rootValue);
//        following the post order[LeftRootRight] logic, traversing from end of list...-> root -> node's right -> node's left
        root.right = arrayToTree(postorder, inorderIndexMap.get(rootValue) + 1, right);
        root.left = arrayToTree(postorder, left, inorderIndexMap.get(rootValue) - 1);
        return root;
    }
}

/*
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:
Input: inorder = [-1], postorder = [-1]
Output: [-1]
Constraints:
1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
 */