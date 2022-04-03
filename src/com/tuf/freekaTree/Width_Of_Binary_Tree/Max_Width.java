package com.tuf.freekaTree.Width_Of_Binary_Tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class Max_Width {
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
        long index;

        public Pair(Node node, long index){
            this.node = node;
            this.index= index;
        }
    }

    private static long findMaxWidth(Node root){
        Queue<Pair> nodeQ = new ArrayDeque<>();
        nodeQ.add(new Pair(root, 0));
        long minIndex =0, maxIndex = 0, maxWidth = 0;
        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            boolean firstNode = true;
            Pair current = null;
            while(size -- >0){
                current = nodeQ.remove();
                if(firstNode){
                    minIndex = current.index;
                    firstNode = !firstNode;
                }
                if(current.node.left != null){
                    nodeQ.add(new Pair(current.node.left, current.index * 2 + 1));
                }
                if(current.node.right != null){
                    nodeQ.add(new Pair(current.node.right, current.index * 2 + 2));
                }
            }
            maxIndex = current.index;
            maxWidth = Math.max(maxWidth, maxIndex - minIndex + 1);
        }
        return maxWidth;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(3);
        Node right = new Node(2);
        Node leftLeft = new Node(5);
        Node leftRight = new Node(3);
        Node rightRight = new Node(9);

        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.right = rightRight;

        System.out.println("Maxwidth of tree => "+findMaxWidth(root));
    }
}
