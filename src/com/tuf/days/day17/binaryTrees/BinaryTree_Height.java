package com.tuf.days.day17.binaryTrees;

public class BinaryTree_Height {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static int findBinaryHeight(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max(findBinaryHeight(root.left), findBinaryHeight(root.right));
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node rightLeft1 = new Node(4);
        Node rightRight1 = new Node(6);
        Node rightLeft2 = new Node(5);

        root.left = left;
        root.right = right;
        right.left = rightLeft1;
        right.right = rightRight1;
        rightLeft1.left = rightLeft2;

        System.out.println(findBinaryHeight(root));
    }
}
