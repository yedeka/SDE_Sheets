package com.tuf.freekaTree.height_based;

public class Find_Diameter {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static int findDiameter(Node root, int[] diameter){
        if(root == null){
            return 0;
        }
        int lh = findDiameter(root.left, diameter);
        int rh = findDiameter(root.right, diameter);

        diameter[0] = Math.max(diameter[0], lh+rh);
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node rightLeft = new Node(4);
        Node rightLeft2 = new Node(5);
        Node rightLeft3 = new Node(9);
        Node rightRight = new Node(6);
        Node rightRight2 = new Node(7);
        Node rightRight3 = new Node(8);

        root.left = left;
        root.right = right;
        right.left = rightLeft;
        rightLeft.left = rightLeft2;
        rightLeft2.left = rightLeft3;

        right.right = rightRight;
        rightRight.right = rightRight2;
        rightRight2.right = rightRight3;

        int[] diameter = new int[]{0};
        findDiameter(root, diameter);

        System.out.println("Diameter of tree => "+diameter[0]);
    }
}
