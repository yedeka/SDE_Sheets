package com.tuf.freekaTree.traversal;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Verticle_Order_II {
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

    private static void findWidth(Node root, int hl, int[] width){
        if(root ==null){
            return;
        }
        width[0] = Math.min(width[0], hl);
        width[1] = Math.max(width[1], hl);
        findWidth(root.left, hl - 1, width);
        findWidth(root.right, hl + 1, width);
    }

    private static ArrayList<ArrayList<Integer>> sortedVerticleOrder(Node root){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        int[] width = new int[2];
        findWidth(root, 0, width);
        int length = width[1] - width[0] + 1;
        for(int i=0;i < length; i++){
            result.add(new ArrayList<>());
        }
        // Now start level order traversal with 2 priorityqueus to maintain the sorted list of elements at same level
        PriorityQueue<Pair> parentQ = new PriorityQueue<>((pair1, pair2) -> {
           return Integer.compare(pair1.node.data, pair2.node.data);});
        PriorityQueue<Pair> childQ = new PriorityQueue<>((pair1, pair2) -> {
            return Integer.compare(pair1.node.data, pair2.node.data);});
        parentQ.add(new Pair(root, Math.abs(width[0])));

        while(!parentQ.isEmpty()){
            int size = parentQ.size();
            while(size--> 0){
                Pair current = parentQ.remove();
                Node currentNode = current.node;
                int currentLvl = current.level;
                // Add the current node into it's desired place
                ArrayList<Integer> vLevel = result.get(currentLvl);
                vLevel.add(currentNode.data);
                result.set(currentLvl,vLevel);
                if(currentNode.left != null){
                    childQ.add(new Pair(currentNode.left, currentLvl - 1));
                }
                if(currentNode.right != null){
                    childQ.add(new Pair(currentNode.right, currentLvl + 1));
                }
            }
            // We have parsed entire queue hence now swap the queues
            PriorityQueue<Pair> temp = childQ;
            childQ = parentQ;
            parentQ = temp;
        }
        return result;
    }

    public static void main(String[] args){
        Node root = new Node(7);
        Node left = new Node(8);
        Node right = new Node(9);
        root.left = left;
        root.right = right;

        //Left subtree
        Node leftLeft1 = new Node(1);
        Node leftRight1 = new Node(3);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node leftLeft2 = new Node(6);
        Node leftRight2 = new Node(7);
        leftLeft1.left = leftLeft2;
        leftLeft1.right = leftRight2;
        Node leftLeft3 = new Node(6);
        Node leftRight3 = new Node(19);
        leftRight1.left = leftLeft3;
        leftRight1.right = leftRight3;
        //Right subtree
        Node rightLeft1 = new Node(2);
        Node rightRight1 = new Node(19);
        right.left = rightLeft1;
        right.right = rightRight1;
        Node rightLeft2 = new Node(4);
        Node rightRight2 = new Node(1);
        rightLeft1.left = rightLeft2;
        rightLeft1.right = rightRight2;
        Node rightLeft21 = new Node(-10);
        Node rightRight21 = new Node(10);
        rightRight1.left = rightLeft21;
        rightRight1.right = rightRight21;
        Node rightRight3 = new Node(21);
        rightRight21.right = rightRight3;
        System.out.println(sortedVerticleOrder(root));
    }
}
