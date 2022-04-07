package com.tuf.freekaTree.threadedBTree;

import java.util.ArrayList;

public class Morris_Traversal_Inorder {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static ArrayList<Integer> MorrisInorderTraversal(Node root){
        Node current = root;
        ArrayList<Integer> result = new ArrayList<>();
        while(current != null){
            Node prev = current.left;
            if(prev == null){
                result.add(current.data);
                current = current.right;
            } else {
                while(prev.right != null && prev.right != current){// Traverse till the rightmost child of left subtree or until we find a thread
                    prev = prev.right;
                }
                if(prev.right == null){
                    prev.right = current;// We found rightmost child connect it to the parent.
                    current = current.left;
                } else {
                    result.add(current.data); // Since I have come back to root I have completed my left subtree traversal and hence add curr to inorder.
                    current = current.right;
                    prev.right = null;// break the thread.
                }
            }
        }
        return  result;
    }

    public static void main(String[] args){
        /*Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(7);
        root.left = left;
        root.right = right;
        Node leftLeft1 = new Node(3);
        Node leftRight1 = new Node(4);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node leftLeft2 = new Node(5);
        Node leftRight2 = new Node(6);
        leftRight1.left = leftLeft2;
        leftRight1.right = leftRight2;
        Node rightRight1 = new Node(8);
        Node rightLeft2 = new Node(9);
        right.right = rightRight1;
        rightRight1.left = rightLeft2;*/

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

        ArrayList<Integer> inOrderList = MorrisInorderTraversal(root);
        System.out.println(inOrderList);
    }
}
