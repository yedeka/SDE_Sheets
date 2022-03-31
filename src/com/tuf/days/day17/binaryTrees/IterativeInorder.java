package com.tuf.days.day17.binaryTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeInorder {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static List<Integer> IterativeInorder(Node root){
        List<Integer> inOrderList = new ArrayList<>();
        Stack<Node> processingSt = new Stack<>();
        Node currentNode = root;
        while(true){
            if(currentNode != null){
                processingSt.push(currentNode);
                currentNode = currentNode.left;
            } else {
                if(processingSt.size() == 0){
                    break;
                }
                Node parent = processingSt.pop();
                inOrderList.add(parent.data);
                currentNode = parent.right;
            }
        }
        return inOrderList;
    }

    public static void main(String[] args){
        Node  root = new Node(1);
        Node  firstLeft = new Node(2);
        Node  firstRight = new Node(3);
        Node  secondLeft = new Node(4);
        Node  secondRight = new Node(5);
        Node  thirdLeft = new Node(6);
        Node  thirdRight = new Node(7);

        root.left = firstLeft;
        root.right = firstRight;
        firstLeft.left = secondLeft;
        firstLeft.right = secondRight;
        secondRight.left = thirdLeft;
        secondRight.right = thirdRight;

        List<Integer> inorderList = IterativeInorder(root);
        for(Integer inorderNode: inorderList){
            System.out.print(inorderNode+" ");
        }
        System.out.println();
    }
}
