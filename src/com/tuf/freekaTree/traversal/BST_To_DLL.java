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

    private static void BSTDLLHelper(Node root, Node[] prev){
        if(root == null) return;
        BSTDLLHelper(root.left, prev);
        root.left = prev[0];
        prev[0].right = root;
        prev[0] = root;
        BSTDLLHelper(root.right, prev);
    }

    private static Node convertBSTTODLL(Node root){
        Node dummy = new Node(-1);
        Node[] prev = new Node[]{dummy};
        BSTDLLHelper(root, prev);
        return dummy;
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
        leftLeft.left = leftLeft1;
        leftLeft.right = leftRight1;
        // Right subtree
        Node rightLeft1 = new Node(30);
        Node rightRight1 = new Node(40);
        right.left = rightLeft1;
        right.right = rightRight1;
        Node rightLeft2 = new Node(28);
        rightLeft1.left = rightLeft2;
        Node rightLeft21 = new Node(38);
        Node rightRight21 = new Node(48);
        rightRight1.left = rightLeft21;
        rightRight1.right = rightRight21;

        return root;
    }

    public static void main(String[] args){
        Node root = preapreTree();
        convertBSTTODLL(root);
    }
}
