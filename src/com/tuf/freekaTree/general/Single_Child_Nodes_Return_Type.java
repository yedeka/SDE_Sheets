package com.tuf.freekaTree.general;

public class Single_Child_Nodes_Return_Type {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static int returnSingleChildNodes(Node root){
        if(root == null || root.left == null && root.right == null){
            return 0;
        }

        int leftCnt = returnSingleChildNodes(root.left);
        int rightCnt = returnSingleChildNodes(root.right);
        int total = leftCnt + rightCnt;

        if(root.left == null || root.right == null){
            total += 1;
        }
        return total;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;

        //Left subtree
        Node leftLeft = new Node(4);
        Node leftRight = new Node(5);
        left.left = leftLeft;
        left.right = leftRight;
        Node leftLeft1 = new Node(8);
        Node leftRight1 = new Node(9);
        leftLeft.left = leftLeft1;
        leftLeft.right = leftRight1;
        Node leftRight12 = new Node(10);
        leftRight.right = leftRight12;
        Node leftLeft2 = new Node(12);
        Node leftRight2 = new Node(13);
        leftRight1.left = leftLeft2;
        leftRight1.right = leftRight2;

        //Right subtree
        Node rightLeft = new Node(6);
        Node rightRight = new Node(7);
        right.left = rightLeft;
        right.right = rightRight;
        Node rightLeft1 = new Node(11);
        rightRight.left = rightLeft1;
        Node rightLeft2 = new Node(14);
        rightLeft1.left = rightLeft2;

        System.out.println(returnSingleChildNodes(root));
    }
}
