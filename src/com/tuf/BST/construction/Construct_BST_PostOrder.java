package com.tuf.BST.construction;

public class Construct_BST_PostOrder {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }
    public static Node BSTFromPostOrder(int[] postorder, int lr, int rr, int[] currIdx){
        if(currIdx[0] < 0 || postorder[currIdx[0]] < lr || postorder[currIdx[0]] > rr){
            return null;
        }
        Node root = new Node(postorder[currIdx[0]]);
        currIdx[0] -= 1;
        root.right = BSTFromPostOrder(postorder,root.data, rr, currIdx);
        root.left = BSTFromPostOrder(postorder, lr, root.data, currIdx);
        return root;
    }

    public static Node contructBSt(int[] postorder){
        if(postorder == null || postorder.length == 0){
            return null;
        }
        int lr = -(int)1e9-1;
        int rr = (int)1e9-1;
        int[] curridx = new int[]{postorder.length - 1};
        return BSTFromPostOrder(postorder, lr, rr, curridx);
    }

    public static void main(String[] args){
        int[] postOrder = new int[]{15, 10, 23, 25, 20, 35, 42, 39, 30};
        Node root = contructBSt(postOrder);
        System.out.println("Done");

    }
}
