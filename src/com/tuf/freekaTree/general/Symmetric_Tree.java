package com.tuf.freekaTree.general;

public class Symmetric_Tree {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static boolean isTreeSymmetric(Node root1, Node root2){
        if(root1 == null || root2 == null ){
            return root1 == root2;
        }
        boolean leftSubTree = isTreeSymmetric(root1.left, root1.right);
        boolean rightSubTree = isTreeSymmetric(root1.right, root1.left);
        return leftSubTree && rightSubTree;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(2);
        Node LeftLeft = new Node(3);
        Node LeftRight = new Node(4);

        root.left = left;
        root.right = right;

        left.left = LeftLeft;
        left.right = LeftRight;

        right.left = LeftRight;
        right.right = LeftLeft;

        boolean isSymmetric = isTreeSymmetric(root, root);
        System.out.println(isSymmetric);
    }
}
