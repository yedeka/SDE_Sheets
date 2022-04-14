package com.tuf.freekaTree.traversal;

import com.tuf.freekaTree.level_order_traversal_applications.Burning_binary_tree;

public class Burn_Tree_DFS {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void burnDown(Node root, Node blocker, int time, int[] result){
        if(root == null || root == blocker){
            return;
        }
        result[0] = Math.max(result[0], time);
        burnDown(root.left, blocker, time+1, result);
        burnDown(root.right, blocker, time+1, result);
    }

    private static int startFire(Node root, int val, int[] result){
        if(root ==null) {
            return -1;
        }
        if(root.data == val){
            burnDown(root, null, 0, result);
            return 1;
        }
        int lt = startFire(root.left, val, result);
        if(lt != -1){
            // One thing to remember here is we always backtrack after burning that is why first node here will always be root.
            burnDown(root, root.left, lt, result);
            return lt + 1;
        }
        int rt = startFire(root.right, val, result);
        if(rt != -1){
            burnDown(root, root.right, rt, result);
            return rt + 1;
        }
        return -1;
    }

    public static int burnTree(Node root, int val){
        int[] maxTime = new int[1];
        startFire(root, val, maxTime);
        return maxTime[0];
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;
        Node leftLeft2 = new Node(4);
        left.left = leftLeft2;
        Node leftRight3 = new Node(7);
        leftLeft2.right = leftRight3;
        Node rightLeft2 = new Node(5);
        Node rightRight2 = new Node(6);
        right.left = rightLeft2;
        right.right = rightRight2;
        int val = 2;
        System.out.println("Max time to burn the binary tree from "+val+" => "+burnTree(root, val));
    }
}
