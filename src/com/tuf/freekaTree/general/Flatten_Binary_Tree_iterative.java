package com.tuf.freekaTree.general;

import java.util.Stack;

public class Flatten_Binary_Tree_iterative {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node flattenTree(Node root){
        Node returnValue = root;
        Stack<Node> st = new Stack<>();
        st.add(root);

        while(st.size() > 0){
            Node current = st.pop();
            if(current.right != null) {
                st.push(current. right);
            }
            if(current.left != null) {
                st.push(current.left);
                current.left = null;
            }
            if(st.size() > 0){
                current.right = st.peek();
            }
        }
        return returnValue;
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
        flattenTree(root);
    }


}
