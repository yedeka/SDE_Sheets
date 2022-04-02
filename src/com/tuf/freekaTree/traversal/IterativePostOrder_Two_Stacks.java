package com.tuf.freekaTree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativePostOrder_Two_Stacks {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    //Perform post order using 2 stack
    private static List<Integer> findIterativePostOrder(Node root){
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        List<Integer> postOrder = new ArrayList<>();
        st1.push(root);
        while(st1.size() > 0){
            Node current = st1.pop();
            if(current.left != null){
                st1.add(current.left);
            }
            if(current.right != null){
                st1.add(current.right);
            }
            st2.push(current);
        }
        while(st2.size() > 0){
            postOrder.add(st2.pop().data);
        }
        return postOrder;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node firstLeft = new Node(2);
        Node firstLeftLeft = new Node(4);
        Node firstLeftRight = new Node(5);

        root.left = firstLeft;
        firstLeft.left = firstLeftLeft;
        firstLeft.right = firstLeftRight;

        Node firstRight = new Node(3);
        Node firsrRightLeft = new Node(6);
        Node secondRight = new Node(7);
        Node thirdRight = new Node(8);

        root.right = firstRight;
        firstRight.left = firsrRightLeft;
        firsrRightLeft.right = secondRight;
        secondRight.right = thirdRight;

        List<Integer> postOrder = findIterativePostOrder(root);
        for(int element: postOrder){
            System.out.print(element+" ");
        }
        System.out.println();
    }
}
