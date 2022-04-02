package com.tuf.freekaTree.traversal;

import java.util.*;

public class Right_Left_View {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static HashMap<String, List<Integer>> leftRightView(Node root){
        List<Integer> leftView = new ArrayList<>();
        List<Integer> rightView = new ArrayList<>();
        HashMap<String, List<Integer>> result = new HashMap<>();
        boolean startFlag = true;

        Queue<Node> nodeQ = new ArrayDeque<>();
        nodeQ.add(root);
        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            startFlag = true;
            Node current = null;
            while(size -- > 0) {
                current = nodeQ.remove();
                if(startFlag){
                    leftView.add(current.data);
                    startFlag = false;
                }
                if(current.left != null){
                    nodeQ.add(current.left);
                }
                if(current.right != null){
                    nodeQ.add(current.right);
                }
            }
            rightView.add(current.data);
        }
        result.put("LEFT", leftView);
        result.put("RIGHT", rightView);

        return result;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node leftLeft = new Node(4);
        Node leftRight = new Node(5);
        Node left2 = new Node(6);
        Node rightRight = new Node(7);

        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        leftRight.left = left2;
        right.right = rightRight;

        HashMap<String, List<Integer>> lrView = leftRightView(root);
        System.out.println(lrView.get("LEFT"));
        System.out.println(lrView.get("RIGHT"));
    }


}
