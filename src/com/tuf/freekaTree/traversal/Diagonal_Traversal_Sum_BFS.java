package com.tuf.freekaTree.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Diagonal_Traversal_Sum_BFS {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static ArrayList<Integer> findDiagonalSumBFS(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> nodeQ = new ArrayDeque<>();
        nodeQ.add(root);

        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            int sum = 0;
            while(size-- > 0){
                Node current = nodeQ.remove();
                while(current != null){
                    sum += current.data;
                    if(current.left != null){
                        nodeQ.add(current.left);
                    }
                    current = current.right;
                }
            }
            result.add(sum);
        }
        return result;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(4);
        Node right = new Node(2);
        root.left = left;
        root.right = right;

        //left subtree
        Node leftLeft = new Node(8);
        left.left = leftLeft;

        //Right subtree
        Node rightLeft = new Node(5);
        Node rightRight = new Node(3);
        right.left = rightLeft;
        right.right = rightRight;
        Node rightLeft2 = new Node(9);
        Node rightRight2 = new Node(7);
        rightLeft.left = rightLeft2;
        rightLeft.right = rightRight2;
        Node rightLeft21 = new Node(6);
        rightRight.left = rightLeft21;
        Node rightRight3 = new Node(-2);
        rightLeft21.right = rightRight3;
        Node rightRight4 = new Node(-1);
        rightRight3.right = rightRight4;
        Node rightLeft5 = new Node(1);
        rightRight4.left = rightLeft5;

        System.out.println("Diagonal Sum by BFS => "+findDiagonalSumBFS(root));
    }
}
