package com.tuf.freekaTree.general;

public class Flatten_Binary_Tree_Recursive {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void flattenList(Node root, Node[] prev){
        if(root == null){
            return;
        }
        flattenList(root.right, prev);
        flattenList(root.left, prev);
        root.right = prev[0];
        root.left = null;
        prev[0] = root;
    }

    private static void printList(Node head){
        while(head != null){
            System.out.print(head.data+", ");
            head = head.right;
        }
        System.out.println();
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
        Node rightRight1 = new Node(6);
        Node rightLeft2 = new Node(7);
        right.right = rightRight1;
        rightRight1.left = rightLeft2;
        Node[] prev = new Node[]{null};
        flattenList(root, prev);
        printList(root);
    }
}
