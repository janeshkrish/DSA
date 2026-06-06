/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> l = new ArrayList<>();
        if(root == null){
            return;
        }
        preorder(root,l);
        for(int i=0;i<l.size()-1;i++){
            TreeNode temp = l.get(i);
            TreeNode next = l.get(i+1);
            temp.left = null;
            temp.right = next;
        }
    }
    void preorder(TreeNode node,List<TreeNode> l){
        if(node == null){
            return;
        }
        l.add(node);
        preorder(node.left,l);
        preorder(node.right,l);
    }
}