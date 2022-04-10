package com.tuf.freekaTree.traversal;

import java.util.*;

public class Verticleorder_Sum_Iterative {
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

        public Pair(Node root, int lvl){
            this.node = root;
            this.level = lvl;
        }
    }

    private static void findWidth(Node root, int hl, int[] minMaxIdx){
        if(root == null){
            return;
        }
        minMaxIdx[0] = Math.min(hl, minMaxIdx[0]);
        minMaxIdx[1] = Math.max(hl, minMaxIdx[1]);
        findWidth(root.left, hl - 1, minMaxIdx);
        findWidth(root.right, hl + 1, minMaxIdx);
    }

    private static List<Integer> verticleOrderSum(Node root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        int[] minMax = new int[2];
        findWidth(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for(int i=0; i<width; i++){
            result.add(0);
        }
        Pair first = new Pair(root, Math.abs(minMax[0]));
        Queue<Pair> nodeQ = new ArrayDeque<>();
        nodeQ.add(first);

        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            while(size-- > 0){
                Pair current = nodeQ.remove();
                Node currentNode = current.node;
                int currentLevel = current.level;

                result.set(currentLevel, result.get(currentLevel) + currentNode.data);
                if(currentNode.left != null){
                    nodeQ.add(new Pair(currentNode.left, currentLevel - 1));
                }
                if(currentNode.right != null){
                    nodeQ.add(new Pair(currentNode.right, currentLevel + 1));
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;
        Node leftLeft = new Node(4);
        Node leftRight = new Node(5);
        left.left = leftLeft;
        left.right = leftRight;
        Node rightLeft = new Node(6);
        Node rightRight = new Node(7);
        right.left = rightLeft;
        right.right = rightRight;
        Node leftLeft2 = new Node(7);
        Node leftRight2 = new Node(8);
        leftLeft.left = leftLeft2;
        leftLeft.right = leftRight2;
        Node leftRight3 = new Node(9);
        leftRight2.right = leftRight3;
        System.out.println(verticleOrderSum(root));
    }
}
