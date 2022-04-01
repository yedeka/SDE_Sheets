package com.tuf.freekaTree;

public class Check_Balanced_Binary_Tree {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }
    private static int checkBalanced(Node root){
        if(root == null){
            return 0;
        }
        int leftVal = checkBalanced(root.left);
        int rightVal = checkBalanced(root.right);
        if(leftVal == -1 || rightVal == -1) return -1;
        if(Math.abs(leftVal - rightVal) > 1){
            return -1;
        }
        return leftVal-rightVal + 1;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node left2 = new Node(3);
        Node left3 = new Node(9);

        root.left = left;
        left.left = left2;
        left2.left = left3;

        Node  right = new Node(4);
        Node rightLeft = new Node(5);
        Node right1 = new Node(6);
        Node right2 = new Node(7);
        Node right3 = new Node(8);

        root.right = right;
        right.left = rightLeft;
        right.right = right1;
        right1.right = right2;
        right2.right = right3;

        int balanceScore = checkBalanced(root);

        System.out.println("Is binary tree balanced => "+ balanceScore);
    }
}
