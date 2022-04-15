package com.tuf.freekaTree.traversal;

public class BST_To_DLL {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node preapreTree(){
        Node root = new Node(25);
        Node left = new Node(20);
        Node right = new Node(36);
        root.left = left;
        root.right = right;
        // Left subtree
        Node leftLeft = new Node(10);
        Node leftRight = new Node(22);
        left.left = leftLeft;
        left.right = leftRight;
        Node leftLeft1 = new Node(5);
        Node leftRight1 = new Node(12);

        return root;
    }

    public static void main(String[] args){

    }
}
