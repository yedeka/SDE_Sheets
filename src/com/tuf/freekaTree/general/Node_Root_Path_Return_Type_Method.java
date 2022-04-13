package com.tuf.freekaTree.general;

import java.util.ArrayList;

public class Node_Root_Path_Return_Type_Method {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    public static ArrayList<Integer> findRootToNodePath(Node root, int value){
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null){
            return result;
        }
        if(root.data == value){
            result.add(root.data);
        } else {
            ArrayList<Integer> leftList = findRootToNodePath(root.left, value);
            if(leftList.size() > 0){
                result = leftList;
                result.add(root.data);
            } else {
                ArrayList<Integer> rightList = findRootToNodePath(root.right, value);
                if(rightList.size() > 0){
                    result = rightList;
                    result.add(root.data);
                }

            }
        }

        return result;
    }

    public static Node createTree(){
        Node root = new Node(2);
        Node left = new Node(7);
        Node right = new Node(5);
        root.left = left;
        root.right = right;
        //Left subtree
        Node leftLeft = new Node(2);
        Node leftRight = new Node(6);
        left.left = leftLeft;
        left.right = leftRight;
        Node leftLeft2 = new Node(5);
        Node leftRight2 = new Node(11);
        leftRight.left = leftLeft2;
        leftRight.right = leftRight2;
        // Right subtree
        Node rightRight = new Node(9);
        right.right = rightRight;
        Node rightRight1 = new Node(4);
        rightRight.right = rightRight1;

        return root;
    }

    public static void main(String[] args){
        Node root = createTree();
        System.out.println(findRootToNodePath(root, 5));
    }
}
