package com.tuf.freekaTree.general;

public class Identicle_Trees {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static boolean areTreesSimillar(Node root1, Node root2){
        if(root1 == null || root2 == null){
            return root1 == root2;
        }
        return(root1.data == root2.data && areTreesSimillar(root1.left, root2.left) && areTreesSimillar(root1.right, root2.right));
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node root1 = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node rightLeft = new Node(4);
        Node rightRight = new Node(5);

        root.left = left;
        root.right = right;
        right.left = rightLeft;
        right.right = rightRight;

        root1.left = left;
        root1.right = right;

        System.out.println(areTreesSimillar(root, root1));
    }
}
