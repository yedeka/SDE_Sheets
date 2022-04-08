package com.tuf.freekaTree.traversal.applications;

public class Longest_Zigzag_Path {
    private static class PathVal{
        int forwardVal = -1;
        int backwardVal = -1;
        int maxVal = 0;
    }
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }
    private static PathVal findLongestZigZag(Node root){
        if(root == null) return new PathVal();
        PathVal left = findLongestZigZag(root.left);
        PathVal right = findLongestZigZag(root.right);

        int currForward = left.backwardVal + 1;
        int currBackward = right.forwardVal + 1;
        int currMax = Math.max(Math.max(left.maxVal, right.maxVal), Math.max(left.backwardVal, right.forwardVal) + 1);

        PathVal currentVal = new PathVal();
        currentVal.forwardVal = currForward;
        currentVal.backwardVal = currBackward;
        currentVal.maxVal = currMax;

        return currentVal;
    }

    public static int findLongestzz(Node root){
        PathVal answer = findLongestZigZag(root);
        return answer.maxVal;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(1);
        Node right = new Node(1);
        root.left = left;
        root.right = right;
        Node leftRight1 = new Node(1);
        left.right = leftRight1;
        Node leftLeft2 = new Node(1);
        leftRight1.left = leftLeft2;
        Node leftRight3 = new Node(1);
        leftLeft2.right = leftRight3;
        System.out.println("Longest zig zag path => "+findLongestzz(root));

    }
}
