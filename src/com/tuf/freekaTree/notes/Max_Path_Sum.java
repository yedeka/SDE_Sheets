package com.tuf.freekaTree.notes;

public class Max_Path_Sum {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static int findMaxPathSum(Node root, int[] maxSum){
        if(root == null){
            return 0;
        }
        int leftSum = findMaxPathSum(root.left, maxSum);
        int rightSum = findMaxPathSum(root.right, maxSum);
        if(leftSum < 0){
            leftSum = 0;
        }
        if(rightSum < 0){
            rightSum = 0;
        }

        maxSum[0] = Math.max(maxSum[0], leftSum + rightSum + root.data);

        return Math.max(leftSum, rightSum) + root.data;
    }

    public static void main(String[] args){
        Node root = new Node(15);
        Node left = new Node(10);
        Node right = new Node(20);

        Node rightLeft = new Node(-30);
        Node rightRight = new Node(-15);

        root.left = left;
        root.right = right;
        right.left = rightLeft;
        right.right = rightRight;

        int[] maxSum = new int[]{0};
        findMaxPathSum(root, maxSum);

        System.out.println("Max path sum => "+maxSum[0]);
    }
}
