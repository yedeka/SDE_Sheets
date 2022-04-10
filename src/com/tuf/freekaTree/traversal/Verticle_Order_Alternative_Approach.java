package com.tuf.freekaTree.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Verticle_Order_Alternative_Approach {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static class Pair{
        Node node;
        int level;

        public Pair(Node node, int level){
            this.node = node;
            this.level = level;
        }
    }

    private static void findWidth(Node root, int hl ,int[]width){
        if(root == null) return;
        width[0] = Math.min(width[0], hl);
        width[1] = Math.max(width[1], hl);
        findWidth(root.left, hl-1, width);
        findWidth(root.right, hl+1, width);
    }

    private static ArrayList<ArrayList<Integer>> findverticleOrder(Node root){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root ==null){
            return result;
        }
        int[] width = new int[2];
        findWidth(root, 0, width);
        int length = width[1] - width[0] + 1;
        for(int i=0; i<length; i++){
            result.add(new ArrayList<>());
        }
        // Now start iteration.
        Queue<Pair> nodeQ = new ArrayDeque<>();
        nodeQ.add(new Pair(root, Math.abs(width[0]))); // We take absolute here to cater for skewed tree
        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            while(size-- > 0){
                Pair current = nodeQ.remove();
                int level = current.level;
                Node currentNode = current.node;
                result.get(level).add(currentNode.data);
                if(currentNode.left != null){
                    nodeQ.add(new Pair(currentNode.left, level - 1));
                }
                if(currentNode.right != null){
                    nodeQ.add(new Pair(currentNode.right, level + 1));
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        Node root = new Node(3);
        Node left = new Node(1);
        Node right = new Node(6);
        root.left = left;
        root.right = right;
        Node leftLeft1 = new Node(0);
        Node leftRight1 = new Node(2);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node rightLeft1 = new Node(5);
        Node rightRight1 = new Node(10);
        right.left = rightLeft1;
        right.right = rightRight1;
        Node rightLeft2 = new Node(4);
        Node rightRight2 = new Node(15);
        rightLeft1.left = rightLeft2;
        rightLeft1.right = rightRight2;
        Node rightLeft21 = new Node(9);
        Node rightRight21 = new Node(11);
        rightRight1.left = rightLeft21;
        rightRight1.right = rightRight21;
        Node rightLeft3 = new Node(12);
        rightLeft21.left = rightLeft3;
        Node rightLeft4 = new Node(13);
        Node rightRight4 = new Node(14);
        rightLeft3.left = rightLeft4;
        rightLeft3.right = rightRight4;

        ArrayList<ArrayList<Integer>> voList = findverticleOrder(root);
        System.out.println(voList);
    }
}
