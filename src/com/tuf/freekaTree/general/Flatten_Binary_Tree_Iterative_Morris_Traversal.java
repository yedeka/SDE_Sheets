package com.tuf.freekaTree.general;

import java.util.Stack;

public class Flatten_Binary_Tree_Iterative_Morris_Traversal {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node flattenTreeMT(Node root){
        Node returnValue = root;
        Node current = root;
        while(current != null){
            if(current.left != null){
                Node prev = current.left;
                while(prev.right != null){
                    prev = prev.right;
                }
                prev.right = current.right; // Point the right pointer of rightmost leaf in the left subtree to roots right so now we can break the link.
                current.right = current.left; // reorganize currents pointers to take everything on right.
                current.left = null;
            }
            current = current.right;
        }
        return returnValue;
    }

    private static void printLL(Node head){
        while(head != null){
            System.out.print(head.data+" => ");
            head = head.right;
        }
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(5);
        root.left = left;
        root.right = right;
        Node leftLeft = new Node(3);
        Node leftRight = new Node(4);
        left.left = leftLeft;
        left.right = leftRight;
        Node rightRight2 = new Node(6);
        Node rightLeft3 = new Node(7);
        right.right = rightRight2;
        rightRight2.left = rightLeft3;
        Node transformedRoot = flattenTreeMT(root);
        printLL(transformedRoot);
    }


}
