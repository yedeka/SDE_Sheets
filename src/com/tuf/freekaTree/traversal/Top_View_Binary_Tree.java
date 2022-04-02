package com.tuf.freekaTree.traversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Top_View_Binary_Tree {
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

    private static ArrayList<Integer> topView(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Pair> nodeQ = new ArrayDeque<>();
        nodeQ.add(new Pair(root, 0));
        HashMap<Integer, ArrayList<Integer>> levelMap = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            while(size-- >0){
                Pair current = nodeQ.remove();
                Node currentNode = current.node;
                int currentLevel = current.level;
                ArrayList<Integer> levellist = levelMap.getOrDefault(currentLevel, new ArrayList<>());
                levellist.add(currentNode.data);
                levelMap.put(currentLevel, levellist);
                min = Math.min(min, currentLevel);
                max = Math.max(max, currentLevel);

                if(currentNode.left != null){
                    nodeQ.add(new Pair(currentNode.left, currentLevel - 1));
                }

                if(currentNode.right != null){
                    nodeQ.add(new Pair(currentNode.right, currentLevel + 1));
                }
            }
        }

        for(int i=min; i<=max; i++){
            result.add(levelMap.get(i).get(0));
        }

        return result;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node leftLeft = new Node(4);
        Node leftRight = new Node(5);
        Node left2 = new Node(6);
        Node right2 = new Node(7);

        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        leftRight.left = left2;
        right.right = right2;

        ArrayList<Integer> result = topView(root);
        System.out.println(result);
    }
}
