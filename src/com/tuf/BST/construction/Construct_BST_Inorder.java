package com.tuf.BST.construction;

public class Construct_BST_Inorder {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node constructBST(int[] inorder, int is, int ie){
        if(is > ie){
            return null;
        }
        int mid = (ie + is) / 2;
        Node root = new Node(inorder[mid]);
        root.left = constructBST(inorder, is, mid - 1);
        root.right = constructBST(inorder,mid+1, ie);
        return root;
    }

    public static void main(String[] args){
        int[] inorder = new int[]{9, 12, 14, 17, 19, 23, 50, 54, 67, 72, 76};
        Node root = constructBST(inorder, 0, inorder.length - 1);
        System.out.println("DONE");
    }
}
