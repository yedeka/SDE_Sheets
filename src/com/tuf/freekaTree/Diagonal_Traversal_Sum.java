package com.tuf.freekaTree;

import java.util.ArrayList;

public class Diagonal_Traversal_Sum {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void findDiagonalSumRecursive(Node root, int lvl, ArrayList<Integer> result){
        if(root == null){
            return;
        }
        if(lvl == result.size()){
            result.add(root.data);
        } else {
            result.set(lvl, result.get(lvl) + root.data);
        }
        findDiagonalSumRecursive(root.left, lvl + 1, result);
        findDiagonalSumRecursive(root.right, lvl, result);
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(4);
        Node right = new Node(2);
        root.left = left;
        root.right = right;

        //Left subtree
        Node leftLeft = new Node(8);
        left.left = leftLeft;
        // Right subtree
        Node rightLeft = new Node(5);
        Node rightRight = new Node(3);
        right.left = rightLeft;
        right.right = rightRight;
        Node rightLeft1 = new Node(9);
        Node rightRight1 = new Node(7);
        rightLeft.left = rightLeft1;
        rightLeft.right = rightRight1;
        Node rightLeft12 = new Node(6);
        rightRight.left = rightLeft12;
        Node rightRight2 = new Node(-2);
        rightLeft12.right = rightRight2;
        Node rightRight3 = new Node(-1);
        rightRight2.right = rightRight3;
        rightRight3.left = new Node(1);
        ArrayList<Integer> sumList = new ArrayList<>();
        findDiagonalSumRecursive(root, 0 , sumList);
        System.out.println(sumList);
    }
}
