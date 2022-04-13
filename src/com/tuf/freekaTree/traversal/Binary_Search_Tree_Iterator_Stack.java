package com.tuf.freekaTree.traversal;

import java.util.Stack;

public class Binary_Search_Tree_Iterator_Stack {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    public static class BSTIterator{
        Node root = null;
        Stack<Node> st;

        public BSTIterator(Node root){
            this.root = root;
            st = new Stack<>();
            //First step of adding the entire left subtree is done here itself
            addLeft(root);
        }
        public boolean hasNext(){
            return st.size() != 0;
        }

        private void addLeft(Node current){
            while(current != null){
                st.push(current);
                current = current.left;
            }
        }

        public int next(){
            Node topNode = st.pop();
            // Add the right node and its left subtree
            addLeft(topNode.right);
            return topNode.data;
        }
    }

    public static void main(String[] args){
        Node root = new Node(30);
        Node left = new Node(15);
        Node right = new Node(60);
        root.left = left;
        root.right = right;
        //Left subtree
        Node leftLeft = new Node(7);
        Node leftRight = new Node(22);
        left.left = leftLeft;
        left.right = leftRight;
        Node leftLeft1 = new Node(17);
        Node leftRight1 = new Node(27);
        leftRight.left = leftLeft1;
        leftRight.right = leftRight1;
        Node leftLeft2 = new Node(16);
        leftLeft1.left = leftLeft2;

        //Right subtree
        Node rightLeft = new Node(45);
        Node rightRight = new Node(75);
        right.left = rightLeft;
        right.right = rightRight;
        Node rightLeft1 = new Node(73);
        rightRight.left = rightLeft1;

        BSTIterator itr = new BSTIterator(root);
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
