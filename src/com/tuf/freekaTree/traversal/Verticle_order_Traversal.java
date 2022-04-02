package com.tuf.freekaTree.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Verticle_order_Traversal {
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
        public Pair(Node node, int lvl){
            this.node = node;
            this.level = lvl;
        }
    }

    private static ArrayList<ArrayList<Integer>> findVerticleOrder(Node root){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Queue<Pair> nodeQ = new ArrayDeque<>();
        nodeQ.add(new Pair(root, 0));
        HashMap<Integer, ArrayList<Integer>> verticleMap = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        while(!nodeQ.isEmpty()){
            int size = nodeQ.size();
            while(size-- > 0){
                Pair current = nodeQ.remove();
                int currentLevel = current.level;
                min = Math.min(min, currentLevel);
                max = Math.max(max, currentLevel);
                ArrayList<Integer> levelList = verticleMap.getOrDefault(currentLevel, new ArrayList<>());
                levelList.add(current.node.data);
                verticleMap.put(currentLevel, levelList);

                if(current.node.left != null){
                    nodeQ.add(new Pair(current.node.left, currentLevel - 1));
                }
                if(current.node.right != null){
                    nodeQ.add(new Pair(current.node.right, currentLevel + 1));
                }
            }
        }

        for(int i=min; i<=max; i++){
            result.add(verticleMap.get(i));
        }
        return result;
    }

    public static void main (String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node leftLeft = new Node(4);
        Node leftRight = new Node(10);
        Node rightLeft = new Node(9);
        Node rightRight = new Node(10);
        Node leftRight2 = new Node(5);
        Node leftRight3 = new Node(6);

        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.left = rightLeft;
        right.right = rightRight;
        leftLeft.right = leftRight2;
        leftRight2.right = leftRight3;

        ArrayList<ArrayList<Integer>> result = findVerticleOrder(root);
        for(ArrayList<Integer> verticleOrder: result){
            System.out.println(verticleOrder);
        }
    }
}
