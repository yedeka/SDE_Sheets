package com.tuf.days.day17.binaryTrees;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrder_Traversal_Iterative {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static List<Integer> performLevelOrder(Node root){
        List<Integer> nodeList = new ArrayList<>();
        // perform level order using queue
        Queue<Node> nodeQ = new ArrayDeque<>();
        // Add root by default into the nodeq
        nodeQ.add(root);
        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            while(size -- > 0){
                Node current = nodeQ.remove();
                nodeList.add(current.data);
                if(current.left != null){
                    nodeQ.add(current.left);
                }
                if(current.right != null){
                    nodeQ.add(current.right);
                }
            }
        }
        return nodeList;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node leftLeft = new Node(4);
        Node leftRight = new Node(5);
        Node rightLeft = new Node(6);
        Node rightRight = new Node(7);

        root.left = left;
        root.right = right;

        left.left = leftLeft;
        left.right = leftRight;

        right.left = rightLeft;
        right.right = rightRight;

        List<Integer> nodeList =  performLevelOrder(root);
        System.out.println(nodeList);
    }
}
