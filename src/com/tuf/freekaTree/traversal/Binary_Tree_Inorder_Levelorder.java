package com.tuf.freekaTree.traversal;

import java.util.HashSet;

public class Binary_Tree_Inorder_Levelorder {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node buildTree(int[] inorder, int is, int ie, int[] levelorder){
        if(is > ie){
            return null;
        }
        Node root = new Node(levelorder[0]);
        if(levelorder.length == 1) return root;
        int idx = is;
        while(inorder[idx] != root.data) idx++;
        // Now prepare a hashset of all the nodes in the left subtree
        HashSet<Integer> set = new HashSet<>();
        for(int i=is; i<idx; i++){
            set.add(inorder[i]);
        }
        int[] left = new int[idx - is]; // Left subtree level order
        int[] right = new int[ie-idx]; // Right subtree level order

        int leftIndex = 0, rightIndex = 0;
        for(int i=1; i<levelorder.length; i++){
            int element = levelorder[i];
            // Set contains the left subtree elements hence elements from set are in the left subtree for level order.
            if(set.size() > 0 && set.contains(element)){
                left[leftIndex++] = element;
                set.remove(element);
            } else {
                // Elements not in the set are in the right subtree.
                right[rightIndex++] = element;
            }
        }

        root.left = buildTree(inorder, is, idx - 1, left);
        root.right = buildTree(inorder, idx + 1, ie, right);
        return root;
    }

    private static Node constructTreeIOLO(int[] inorder, int[] levelorder){
        return buildTree(inorder, 0, inorder.length - 1, levelorder);
    }

    public static void main(String[] args){
        int[] inOrder = new int[]{3, 7, 5, 6, 11, 2, 15, 4, 9};
        int[] levelOrder = new int[]{2, 7, 15, 3, 6, 9, 5, 11, 4};
        Node root = constructTreeIOLO(inOrder, levelOrder);
        System.out.println("Reconstruct binary tree from LO -> IO");
    }
}
