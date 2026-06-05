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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> li = new ArrayList<>();
        TreeNode temp = root;
        dfs(temp,li,0);
        return li;
    }
    public void dfs(TreeNode temp,List li,int h){
        if(temp == null){
            return;
        }
        if(h == li.size()){
            li.add(temp.val);
        }
        dfs(temp.right,li,h+1);
        dfs(temp.left,li,h+1);
    }
}