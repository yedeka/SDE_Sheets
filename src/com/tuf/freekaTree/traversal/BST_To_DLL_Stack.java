package com.tuf.freekaTree.traversal;

import java.util.Stack;

public class BST_To_DLL_Stack {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }
    private static void convertBSTDLLStack(Node root, Node[] prev){
        Stack<Node> st = new Stack<>();
        // Step 1 - Fill the stack with entire left subtree
        while(root != null){
            st.push(root);
            root = root.left;
        }
        // Step 2 - Start popping the node from stacks and add the left subtree of right node
        while(st.size() > 0){
            Node current = st.pop();
            prev[0].right = current;
            current.left = prev[0];
            prev[0] = current;
            Node right = current.right;
            while( right != null){
                st.push(right);
                right = right.left;
            }
        }
    }

    private static Node convertBSTDLL(Node root){
        Node dummy = new Node(-1);
        Node[] prev = new Node[]{dummy};
        convertBSTDLLStack(root, prev);
        // COmplete DLL is created now connect head and tail
        Node head = dummy.right;
        Node tail = prev[0];
        head.left = tail;
        tail.right = head;
        dummy.right = null;
        return head;
    }

    private static Node preapareTree(){
        Node root = new Node(25);
        Node left = new Node(20);
        Node right = new Node(36);
        root.left = left;
        root.right = right;
        // Left subtree
        Node leftLeft = new Node(10);
        Node leftRight = new Node(22);
        left.left = leftLeft;
        left.right = leftRight;
        Node leftLeft1 = new Node(5);
        Node leftRight1 = new Node(12);
        leftLeft.left = leftLeft1;
        leftLeft.right = leftRight1;
        // Right subtree
        Node rightLeft1 = new Node(30);
        Node rightRight1 = new Node(40);
        right.left = rightLeft1;
        right.right = rightRight1;
        Node rightLeft2 = new Node(28);
        rightLeft1.left = rightLeft2;
        Node rightLeft21 = new Node(38);
        Node rightRight21 = new Node(48);
        rightRight1.left = rightLeft21;
        rightRight1.right = rightRight21;

        return root;
    }

    public static void main(String[] args){
        Node root = preapareTree();
        Node head = convertBSTDLL(root);
        System.out.println("DONE");
    }
}
