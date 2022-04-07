package com.tuf.freekaTree.threadedBTree;

public class ThreadedBinaryTree {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void prepareThreadedTree(Node root){
        Node current = root;
        while(current != null){
            if(current.left == null){ // Left subtree has finished hence we are not the returning end of Left - Root - Right so start exploring right subtree.
                current = current.right;
            } else {
                Node prev = current.left;
                while(prev.right != null && prev.right != current){
                    prev = prev.right;
                }
                if(prev.right == null){ // We have reached towards the rightmost child of left subtree
                    prev.right = current;// Create a thread towards the root
                } else { //We are at an alreaady threaded node. break the thread and start iteration towards right subtree
                    prev.right = null;
                    current = current.right;
                }
            }
        }
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(5);
        root.left = left;
        root.right = right;
        Node leftLeft1 = new Node(3);
        Node leftRight1 = new Node(4);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node rightRight1 = new Node(6);
        Node rightLeft2 = new Node(7);
        right.right = rightRight1;
        rightRight1.left = rightLeft2;
        prepareThreadedTree(root);
    }
}
