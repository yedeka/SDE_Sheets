package com.tuf.freekaTree.traversal;

import java.util.HashMap;

public class Unique_Binary_Tree_Inorder_PostOrder {
    private static class Node{
        int data;

        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node createBinaryTree(int[] inOrder, int[] postOrder){
        // Step 1 - Create a node map
        HashMap<Integer, Integer> nodeIndexMap = new HashMap<>();
        for(int i=0; i<inOrder.length; i++){
            nodeIndexMap.put(inOrder[i], i);
        }
        Node root = constructTree(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length - 1, nodeIndexMap);
        return root;
    }

    private static Node constructTree(int[] inorder, int is, int ie, int[] postOrder, int ps, int pe, HashMap<Integer, Integer> nodeMap){
        if(is > ie || ps > pe){
            return null;
        }
        int rootVal = postOrder[pe];
        Node root = new Node(rootVal);
        int rootIndex = nodeMap.get(rootVal);
        int leftSubTree = rootIndex - is;
        root.left = constructTree(inorder, is, rootIndex - 1, postOrder, ps, ps+leftSubTree-1, nodeMap);
        root.right = constructTree(inorder, rootIndex + 1, ie , postOrder, ps+leftSubTree, pe-1, nodeMap);
        return root;
    }

    public static void main(String[] args){
        int[] inOrder = new int[]{40, 20, 50, 10, 60, 30};
        int[] postOrder = new int[]{40, 50, 20, 60, 30, 10};
        Node root = createBinaryTree(inOrder, postOrder);
        System.out.println("DONE");
    }
}
