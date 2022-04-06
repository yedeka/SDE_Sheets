package com.tuf.freekaTree.traversal;

import java.util.*;

public class Morris_Inorder_Traversal {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }
    private static List<Integer> getMorrisInorder(Node root){
        List<Integer> inOrder = new ArrayList<>();
        Node curr = root;
        while(curr != null){
            if(curr.left == null){ // First corner case of empty left subtree
                inOrder.add(curr.data);
                curr = curr.right;
            } else {
                // We have to find the rightmost node in the left subtree
                Node prev = curr.left;
                while(prev.right != null && prev.right != curr){// Second condition allows us to break when we are moving to current node after following trail of pointers.
                    prev = prev.right; // Keep on moving to extreme right
                }
                if(prev.right == null){ // I have reached at the rightmost node hence my next will be root.
                    prev.right = curr;
                    // Add curr here in case of preorder since we need the order of Root-Left-Right. Hence instead of adding at the end after parsing we
                    // simply add it just before moving away from the root after the rightmost node is reached.
                    curr = curr.left; // Since I have properly pointed to root from rightmost start the regular traversal.
                } else { // This is the case where I am back to root pointed by right pointer of previous node.
                    prev.right = null;// break the thread.
                    inOrder.add(curr.data); // Since I have come back to root I have completed my left subtree traversal and hence add curr to inorder.
                    curr = curr.right; // Since the complete traversal is completed go to my right subtree.
                }
            }
        }
        return inOrder;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node leftLeft = new Node(4);
        Node leftRight = new Node(5);
        Node leftRight2 = new Node(6);

        root.left = left;
        root.right = right;
        left.left =leftLeft;
        left.right = leftRight;
        leftRight.right = leftRight2;

        List<Integer> morrisInorder = getMorrisInorder(root);
        for(int element: morrisInorder){
            System.out.print(element+", ");
        }
        System.out.println();
    }
}
