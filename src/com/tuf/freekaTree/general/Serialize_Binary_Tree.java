package com.tuf.freekaTree.general;

public class Serialize_Binary_Tree {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void serializeTree(Node root, StringBuffer sb){
        if(root == null){
            sb.append("null,");
            return;
        }
        sb.append(root.data+",");
        serializeTree(root.left, sb);
        serializeTree(root.right, sb);
    }

    public static void main(String[] args){
        Node root = new Node(8);
        Node left = new Node(3);
        Node right = new Node(10);
        root.left = left;
        root.right = right;
        Node leftLeft = new Node(1);
        Node leftRight = new Node(6);
        left.left = leftLeft;
        left.right = leftRight;
        Node left2 = new Node(4);
        Node right2 = new Node(7);
        leftRight.left = left2;
        leftRight.right = right2;
        Node rightRight = new Node(14);
        right.right = rightRight;
        Node rightLeft2 = new Node(13);
        rightRight.left = rightLeft2;

        StringBuffer sb = new StringBuffer();
        serializeTree(root, sb);
        System.out.println(sb.toString());
    }
}
