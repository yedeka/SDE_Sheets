package com.tuf.freekaTree.general;

import java.util.ArrayList;

public class Single_Child_Nodes_HeapMover {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void countSingleChildNodes(Node root, ArrayList<Integer> result){
        if(root == null || root.left == null && root.right == null){
            return;
        }
        if(root.left == null || root.right == null){
            result.add(root.data);
        }
        countSingleChildNodes(root.left, result);
        countSingleChildNodes(root.right, result);
    }

    public static ArrayList<Integer> countSCNodesWrapper(Node root){
        ArrayList<Integer> result = new ArrayList<Integer>();
        countSingleChildNodes(root, result);
        return result;
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

        System.out.println(countSCNodesWrapper(root));
    }

}
