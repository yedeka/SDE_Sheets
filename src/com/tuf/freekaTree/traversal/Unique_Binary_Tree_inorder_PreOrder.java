package com.tuf.freekaTree.traversal;

import java.util.HashMap;

public class Unique_Binary_Tree_inorder_PreOrder {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node constructUniqueBTree(int[] inOrder, int[] preOrder){
        // Step 1 - Create a frequency map of inorder
        HashMap<Integer, Integer> nodeMap = new HashMap<>();
        for(int i=0; i<inOrder.length; i++){
            nodeMap.put(inOrder[i], i);
        }
        return constructTree(inOrder, 0, inOrder.length-1, preOrder, 0, preOrder.length-1, nodeMap);
    }

    private static Node constructTree(int[] inOrder, int inStart, int inEnd, int[] preOrder, int preStart, int preEnd, HashMap<Integer, Integer>nodeMap){
        if(inStart > inEnd || preStart > preEnd){
            return null;
        }

        Node root = new Node(preOrder[preStart]);
        int inRootIdx = nodeMap.get(root.data);
        int leftHalf = inRootIdx - inStart;

        root.left =  constructTree(inOrder, inStart, inRootIdx -1, preOrder, preStart + 1, preStart + leftHalf, nodeMap);
        root.right =  constructTree(inOrder, inRootIdx+1, inEnd, preOrder, preStart + leftHalf + 1, preEnd, nodeMap);
        return root;
    }

    public static void main(String[] args){
        int[] inOrder = new int[]{40, 20, 50, 10, 60, 30};
        int[] preOrder = new int[]{10, 20, 40, 50, 30, 60};
        Node root = constructUniqueBTree(inOrder, preOrder);
        System.out.println("Done");
    }
}
