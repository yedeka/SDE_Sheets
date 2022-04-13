package com.tuf.freekaTree.traversal;

import java.util.ArrayList;

public class Nodes_k_Distance {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    private static boolean findNodeToRootPath(Node root, int val, ArrayList<Node> result){
        if(root == null){
            return false;
        }
        if(root.data == val){
            result.add(root);
            return true;
        }
        boolean targetFoundLeft = findNodeToRootPath(root.left, val, result);
        if(targetFoundLeft){
            result.add(root);
        }
        boolean targetFoundRight = findNodeToRootPath(root.right, val, result);
        if(targetFoundRight){
            result.add(root);
        }
        return targetFoundLeft || targetFoundRight;
    }

    private static void addNodesAtDistanceK(Node root, Node blocker, int k, ArrayList<Integer> result){
        if(root == null || k < 0){
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


    public static ArrayList<Integer> findNodesAtKDistance(Node root, int val, int k){
        ArrayList<Node> nodeToRootPath = new ArrayList<>();
        findNodeToRootPath(root, val, nodeToRootPath);
        ArrayList<Integer> result = new ArrayList<>();
        int  count = nodeToRootPath.size();
        for(int i=0; i<count; i++){
            Node seed = nodeToRootPath.get(i);
            if(i == 0){
                addNodesAtDistanceK(seed, nodeToRootPath.get(0), k - i ,result);
            } else {
                addNodesAtDistanceK(seed, nodeToRootPath.get(i-1), k - i ,result);
            }

        }
        return result;
    }

    public static void main(String[] args) {
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
        System.out.println(findNodesAtKDistance(root, val, k));
    }
}