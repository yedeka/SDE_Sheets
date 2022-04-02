package com.tuf.freekaTree.Width_Of_Binary_Tree;

public class Shadow_Width {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void findMinMax(Node root, int[] arrMinMax, int level){
        if(root == null){
            return;
        }
        arrMinMax[0] = Math.min(arrMinMax[0], level);
        arrMinMax[1] = Math.max(arrMinMax[1], level);

        findMinMax(root.left, arrMinMax, level-1);
        findMinMax(root.right, arrMinMax, level+1);
    }


    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(6);
        root.left = left;
        root.right = right;

        Node leftLeft = new Node(7);
        Node leftRight = new Node(9);
        Node rightLeft = new Node(10);
        Node rightRight = new Node(11);

        left.left = leftLeft;
        left.right = leftRight;
        right.left = rightLeft;
        right.right = rightRight;

        Node left2 = new Node(8);
        leftLeft.left = left2;

        Node right2 = new Node(12);
        Node right3 = new Node(13);
        rightRight.right = right2;
        right2.right = right3;

        int[] arrMixMax = new int[2];
        findMinMax(root, arrMixMax, 0);

        System.out.println(arrMixMax[1] - arrMixMax[0] + 1);
    }
}
