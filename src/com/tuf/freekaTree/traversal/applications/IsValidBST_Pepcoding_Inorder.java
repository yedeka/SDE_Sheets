package com.tuf.freekaTree.traversal.applications;

public class IsValidBST_Pepcoding_Inorder {
    public static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static boolean isValidBST(Node root, Node[] prev){
        if(root == null){
            return true;
        }
        boolean isLeftBST = isValidBST(root.left, prev);
        // Work in inorder area
        Node prevPtr = prev[0];
        if(prevPtr == null || prevPtr.data < root.data){
            prev[0] = root;
        } else {
            return false;
        }
        boolean isRightBST = isValidBST(root.right, prev);
        return isLeftBST && isRightBST;
    }

    public static void main(String[] args){
        Node root = new Node(25);
        Node left = new Node(10);
        Node right = new Node(29);
        root.left = left;
        root.right = right;
        Node leftLeft1 = new Node(5);
        Node leftRight1 = new Node(15);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node leftLeft2 = new Node(11);
        leftRight1.left = leftLeft2;
        Node rightLeft1 = new Node(26);
        Node rightRight1 = new Node(48);
        right.left = rightLeft1;
        right.right = rightRight1;
        Node[] prev = new Node[]{null};
        System.out.println(isValidBST(root, prev));
    }
}
