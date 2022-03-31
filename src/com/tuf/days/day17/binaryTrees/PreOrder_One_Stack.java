package com.tuf.days.day17.binaryTrees;

import java.util.*;

public class PreOrder_One_Stack {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static List<Integer> preOrderIterativeSingleStack(Node root){
        Stack<Node> st = new Stack<>();
        Node temp = null, current = root;
        List<Integer> result = new ArrayList<>();

        while(st.size() > 0 || current != null){
            if(current != null){
                st.push(current);
                current = current.left;
            } else {
                temp = st.peek().right;
                if(temp == null){
                    temp = st.pop();
                    result.add(temp.data);
                    while(st.size() > 0 && temp == st.peek().right){
                        temp = st.pop();
                        result.add(temp.data);
                    }

                } else {
                    current = temp;
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left1 = new Node(2);
        Node left2 = new Node(3);
        Node Left2right1 = new Node(4);
        Node Left2right2 = new Node(5);
        Node Left2right3 = new Node(6);
        Node right1 = new Node(7);
        Node rightleft1 = new Node(8);
        Node rightright1 = new Node(9);
        Node rightright2 = new Node(10);

        // Construct left subtree
        root.left = left1;
        left1.left = left2;
        left2.right = Left2right1;
        Left2right1.right = Left2right2;
        Left2right2.right = Left2right3;
        // Construct right subtree
        root.right = right1;
        right1.left = rightleft1;
        right1.right = rightright1;
        rightright1.right = rightright2;
        List<Integer> postOrderList = preOrderIterativeSingleStack(root);
        System.out.println(postOrderList);
    }
}
