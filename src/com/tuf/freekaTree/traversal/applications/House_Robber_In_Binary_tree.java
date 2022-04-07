package com.tuf.freekaTree.traversal.applications;

public class House_Robber_In_Binary_tree {
    private static class RobberyResult{
        int inclusiveSum;
        int exclusiveSum;

        public RobberyResult(int inclSum, int exclSum){
            this.inclusiveSum = inclSum;
            this.exclusiveSum = exclSum;
        }
    }
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static RobberyResult findMaxvalue(Node root){
        if(root == null){
            return new RobberyResult(0, 0);
        }
        RobberyResult leftResult = findMaxvalue(root.left);
        RobberyResult rightResult = findMaxvalue(root.right);

        int inclusiveResult = root.data + leftResult.exclusiveSum + rightResult.exclusiveSum;
        int exclusiveResult = Math.max(leftResult.inclusiveSum, leftResult.exclusiveSum) + Math.max(rightResult.inclusiveSum, rightResult.exclusiveSum);
        return new RobberyResult(inclusiveResult, exclusiveResult);
    }

    public static void main(String[] args){
        Node root = new Node(2);
        Node left = new Node(7);
        Node right = new Node(5);
        root.left = left;
        root.right = right;
        Node leftLeft1 = new Node(2);
        Node leftRight1 = new Node(6);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node leftLeft2 = new Node(5);
        Node leftRight2 = new Node(11);
        leftRight1.left = leftLeft2;
        leftRight1.right = leftRight2;
        Node rightRight1 = new Node(19);
        right.right = rightRight1;
        Node rightLeft2 = new Node(4);
        rightRight1.left = rightLeft2;
        RobberyResult result = findMaxvalue(root);
        int maxRobbedVal = Math.max(result.exclusiveSum, result.inclusiveSum);
        System.out.println("Max value of robbing homes => " +maxRobbedVal);

    }

}
