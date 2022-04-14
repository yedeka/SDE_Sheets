package com.tuf.freekaTree.traversal;

import java.util.ArrayList;

public class Nodes_K_Distance_Space_Optimized {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void addNodesAtDistanceK(Node root, Node blocker, int k, ArrayList<Integer> result){
        if(root == null || root == blocker){
            return;
        }
        if(k == 0){
            result.add(root.data);
            return;
        }
        if(root.left != blocker){
            addNodesAtDistanceK(root.left, blocker, k-1, result);
        }
        if(root.right != blocker){
            addNodesAtDistanceK(root.right, blocker, k-1, result);
        }
    }

    public static int findNodesAtK(Node root, int val, int k, ArrayList<Integer> result){
        if(root == null || k <0) return -1;
        if(root.data == val){
            addNodesAtDistanceK(root, null, k-0, result);
            return 1;
        }
        int ld = findNodesAtK(root.left, val, k, result);
        if(ld != -1){// I got a matching node in my left subtree
            addNodesAtDistanceK(root,root.left,k-ld,result);
            return ld + 1;
        }

        int rd = findNodesAtK(root.right, val, k, result);
        if(ld != -1){// I got a matching node in my left subtree
            addNodesAtDistanceK(root,root.right,k-rd,result);
            return rd + 1;
        }
        // The node does not exist to left or right both of the current node's subtree hence return -1.
        return -1;

    }

    public static void main(String[] args){
        Node root = new Node(3);
        Node left = new Node(5);
        Node right = new Node(1);
        root.left = left;
        root.right = right;
        Node leftLeft = new Node(6);
        Node leftRight = new Node(2);
        left.left = leftLeft;
        left.right = leftRight;
        Node leftLeft1 = new Node(7);
        Node leftRight1 = new Node(4);
        leftRight.left = leftLeft1;
        leftRight.right = leftRight1;
        Node rightLeft = new Node(0);
        Node rightRight = new Node(8);
        right.left = rightLeft;
        right.right = rightRight;
        int val = 6, k = 3;
        ArrayList<Integer> result = new ArrayList<>();
        findNodesAtK(root, val, k, result);
    }

}
