package com.tuf.days.day17.binaryTrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Pre_In_PostOrder_traversals {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static class Pair{
        Node member;
        int level;

        public Pair(Node member, int lvl){
            this.member = member;
            this.level = lvl;
        }
    }

    private static HashMap<String, List<Integer>> performAllTraversals(Node root){
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        Stack<Pair> nodeSt = new Stack<>();

        nodeSt.push(new Pair(root, 1));

        while(nodeSt.size() > 0){
            Pair current = nodeSt.pop();
            if(current.level == 1){
                preOrder.add(current.member.data);
                current.level += 1;
                nodeSt.push(current);
                if(current.member.left != null){
                    nodeSt.push(new Pair(current.member.left, 1));
                }
            } else if(current.level == 2){
                inOrder.add(current.member.data);
                current.level += 1;
                nodeSt.push(current);
                if(current.member.right != null){
                    nodeSt.push(new Pair(current.member.right, 1));
                }
            } else {
                postOrder.add(current.member.data);
            }
        }

        HashMap<String, List<Integer>> resultMap = new HashMap<>();
        resultMap.put("PREORDER", preOrder);
        resultMap.put("INORDER", inOrder);
        resultMap.put("POSTORDER", postOrder);

        return resultMap;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node leftLeft = new Node(3);
        Node leftRight = new Node(4);
        Node right = new Node(5);
        Node rightLeft = new Node(6);
        Node rightRight = new Node(7);

        root.left = left;
        left.left = leftLeft;
        left.right = leftRight;

        root.right = right;
        right.left = rightLeft;
        right.right = rightRight;

        HashMap<String, List<Integer>> traversalMap = performAllTraversals(root);

        System.out.println("PREORDER => "+traversalMap.get("PREORDER"));
        System.out.println("INORDER => "+traversalMap.get("INORDER"));
        System.out.println("POSTORDER => "+traversalMap.get("POSTORDER"));
    }
}
