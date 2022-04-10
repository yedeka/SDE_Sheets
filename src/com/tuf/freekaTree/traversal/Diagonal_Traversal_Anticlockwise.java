package com.tuf.freekaTree.traversal;

import java.util.*;

public class Diagonal_Traversal_Anticlockwise {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }
    private static List<List<Integer>> diagonal_anticlockwise(Node root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<Node> nodeQ = new ArrayDeque<>();
        nodeQ.add(root);
        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            ArrayList<Integer> diagonal = new ArrayList<>();
            while(size-- > 0){
                Node current = nodeQ.remove();
                while(current != null){
                    diagonal.add(current.data);
                    if(current.right != null){
                        nodeQ.add(current.right);
                    }
                    current = current.left;
                }
            }
            result.add(diagonal);
        }
        return result;
    }
    public static void main(String[] args){
        Node root = new Node(10);
        Node left = new Node(30);
        Node right = new Node(20);
        root.left = left;
        root.right = right;
        // Left subtree
        Node leftLeft1 = new Node(60);
        Node leftRight1 = new Node(80);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node leftLeft2 = new Node(8);
        Node leftRight2 = new Node(9);
        leftLeft1.left = leftLeft2;
        leftLeft1.right = leftRight2;
        Node leftRight3 = new Node(7);
        leftRight2.right = leftRight3;
        Node leftRight4 = new Node(1);
        leftLeft2.right = leftRight4;
        Node leftRight5 = new Node(0);
        leftRight4.right = leftRight5;
        // Right subtree
        Node rightLeft1 = new Node(70);
        Node rightRight1 = new Node(90);
        right.left = rightLeft1;
        right.right = rightRight1;
        Node rightLeft2 = new Node(6);
        Node rightRight2 = new Node(100);
        rightRight1.left = rightLeft2;
        rightRight1.right =rightRight2;
        Node rightLeft3 = new Node(5);
        Node rightRight3 = new Node(1);
        rightLeft2.left = rightLeft3;
        rightLeft2.right = rightRight3;
        System.out.println(diagonal_anticlockwise(root));

    }
}
