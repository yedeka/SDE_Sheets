package com.tuf.freekaTree.general;
import java.util.ArrayList;
import java.util.List;

public class Root_Node_Path {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static boolean findRootToNodePath(Node root, List<Integer> result, int searchVal){
        if(root == null){
            return false;
        }
        result.add(root.data);
        if(root.data == searchVal){
            return true;
        }
        boolean leftval = findRootToNodePath(root.left, result, searchVal);
        boolean rightval = findRootToNodePath(root.right, result, searchVal);

        if(leftval || rightval){
            return true;
        }
        result.remove(result.size() - 1);
        return false;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node leftLeft2 = new Node(4);
        Node leftRight2 = new Node(5);
        Node leftLeft3 = new Node(6);
        Node leftRight3 = new Node(7);

        root.left = left;
        root.right = right;
        left.left = leftLeft2;
        left.right = leftRight2;
        leftRight2.left = leftLeft3;
        leftRight2.right = leftRight3;

        List<Integer> rootNodeList = new ArrayList<>();
        findRootToNodePath(root, rootNodeList, 7);
        System.out.println(rootNodeList);
    }

}
