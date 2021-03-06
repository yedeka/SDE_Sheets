package com.tuf.freekaTree.general;

public class Children_Sum_Property {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void adhereChildrenSum(Node root){
        if(root == null){// Written to handle the negative values
            return;
        }
        int sum = 0;
        if(root.left != null){
            sum += root.left.data;
        }
        if(root.right != null) {
            sum += root.right.data;
        }
        if(sum == 0) { // we are at leaf node
            return;
        }
        if(sum > root.data){
            root.data = sum;
        } else {
            if(root.left != null){
                root.left.data = root.data;
            }
            if(root.right != null){
                root.right.data = root.data;
            }
        }
        if(root.left != null){
            adhereChildrenSum(root.left);
            root.data += root.left.data;
        }
        if(root.right != null){
            adhereChildrenSum(root.right);
            root.data += root.right.data;
        }
    }

    public static void main(String[] args){
        Node root = new Node(50);
        Node left = new Node(7);
        Node right = new Node(2);
        root.left = left;
        root.right = right;
        Node leftLeft2 = new Node(3);
        Node leftRight2 = new Node(5);
        left.left = leftLeft2;
        left.right = leftRight2;
        Node rightLeft2 = new Node(1);
        Node rightRight2 = new Node(30);
        right.left = rightLeft2;
        right.right = rightRight2;

        adhereChildrenSum(root);

    }
}
