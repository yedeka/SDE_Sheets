package com.tuf.freekaTree.general;

import java.util.ArrayList;

public class Root_Node_Path_Heap_Mover {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static boolean findRootNodePath(Node root, int searchVal, ArrayList<Integer> result){
        if(root == null) return false;
        if(root.data == searchVal){
            result.add(root.data);
            return true;
        }
        if(findRootNodePath(root.left, searchVal, result) || findRootNodePath(root.right, searchVal, result)){
            result.add(root.data);
            return true;
        }
        return false;
    }

    public static ArrayList<Integer> findRootToNodePath(Node root, int searchKey){
        ArrayList<Integer> result = new ArrayList<>();
        findRootNodePath(root, searchKey, result);
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
        int searchVal = 5;
        System.out.println(findRootToNodePath(root, searchVal));
    }
}
