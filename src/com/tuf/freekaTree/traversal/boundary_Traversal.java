package com.tuf.freekaTree.traversal;

import java.util.ArrayList;
import java.util.Stack;

public class boundary_Traversal {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void findLeftBoundary(Node root, ArrayList<Integer> result){
        if(root.left == null && root.right == null){
            return;
        }
        result.add(root.data);
        if(root.left != null){
            findLeftBoundary(root.left, result);
        } else if(root.right != null){
            findLeftBoundary(root.right, result);
        }
    }

    private static void findLeaves(Node root, ArrayList<Integer> result){
        if(root.left == null && root.right == null){ // Found the leaf nodes
            result.add(root.data);
        }
        if(root.left != null){
            findLeaves(root.left, result);
        }
        if(root.right != null){
            findLeaves(root.right, result);
        }
    }

    private static void findReverseRight(Node root, Stack<Integer> result){
        if(root.left == null && root.right == null){
            return;
        }
        result.add(root.data);
        if(root.right != null){
            findReverseRight(root.right, result);
        } else if(root.left != null){
            findReverseRight(root.left, result);
        }
    }

    private static ArrayList<Integer> performBoundaryTraversal(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        findLeftBoundary(root, result);
        findLeaves(root, result);
        Stack<Integer> rightStack = new Stack<>();
        findReverseRight(root, rightStack);
        while(rightStack.size() > 1){
            result.add(rightStack.pop());
        }
        return result;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node left1 = new Node(3);
        Node left1Right = new Node(4);
        Node left1RightLeft = new Node(5);
        Node left1RightRight = new Node(6);

        root.left = left;
        left.left = left1;
        left1.right = left1Right;
        left1Right.left = left1RightLeft;
        left1Right.right = left1RightRight;

        Node right = new Node(7);
        Node right1 = new Node(8);
        Node right1Left = new Node(9);
        Node right1LeftLeft = new Node(10);
        Node right1LeftRight = new Node(11);

        root.right = right;
        right.right = right1;
        right1.left = right1Left;
        right1Left.left = right1LeftLeft;
        right1Left.right = right1LeftRight;

        ArrayList<Integer> result = performBoundaryTraversal(root);
        System.out.println(result);
    }

}
