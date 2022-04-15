package com.tuf.freekaTree.traversal;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Path_Sum_II {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void findPathSumII(Node root, int target, List<List<Integer>> result, List<Integer> subResult){
        if(root == null){
            return;
        }
        subResult.add(root.data);
        if(root.left == null && root.right == null){
            if(target - root.data == 0){
                ArrayList<Integer> ans = new ArrayList<>(subResult);
                result.add(ans);
            }
        }
        findPathSumII(root.left, target - root.data, result, subResult);
        findPathSumII(root.right, target - root.data, result, subResult);
        subResult.remove(subResult.size() - 1);
    }

    private static List<List<Integer>> pathSumII(Node root, int target){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subResult = new ArrayList<>();

        if(root == null) {
            return result;
        }
        findPathSumII(root, target, result, subResult);
        return result;
    }

    private static Node prepareTree(){
        Node root = new Node(7);
        Node left = new Node(3);
        Node right = new Node(12);
        root.left = left;
        root.right = right;
        //Prepare left subtree
        Node leftLeft = new Node(1);
        left.left = leftLeft;
        Node leftLeft1 = new Node(0);
        Node leftRight1 = new Node(2);
        leftLeft.left = leftLeft1;
        leftLeft.right = leftRight1;
        // Prepare right subtree
        Node rightLeft = new Node(9);
        Node rightRight = new Node(13);
        right.left = rightLeft;
        right.right = rightRight;
        Node rightLeft1 = new Node(-17);
        Node rightRight1 = new Node(-22);
        rightLeft.left = rightLeft1;
        rightLeft.right = rightRight1;
        Node rightLeft12 = new Node(15);
        rightRight.left = rightLeft12;
        Node rightRight2 = new Node(19);
        rightLeft12.right = rightRight2;
        Node rightLeft2 = new Node(10);
        rightRight1.left = rightLeft2;
        return root;
    }


    public static void main(String[] args){
        Node root = prepareTree();
        int target = 11;
        pathSumII(root, target);
    }
}
