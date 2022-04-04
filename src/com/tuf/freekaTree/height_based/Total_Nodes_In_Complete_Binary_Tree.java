package com.tuf.freekaTree.height_based;

public class Total_Nodes_In_Complete_Binary_Tree {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static int findTotalNodes(Node root){
        if(root == null){
            return 0;
        }
        int leftHeight = findLeftSubTreeHeight(root);
        int rightHeight = findRightSubTreeHeight(root);

        if(leftHeight == rightHeight){
            return (int)Math.pow(2, leftHeight + 1) - 1;
        }
        return findTotalNodes(root.left) + findTotalNodes(root.right) + 1;
    }

    private static int findLeftSubTreeHeight(Node root){
        int result = 0;
        while(root.left != null){
            result += 1;
            root = root.left;
        }
        return result;
    }

    private static int findRightSubTreeHeight(Node root){
        int result = 0;
        while(root.right != null){
            result += 1;
            root = root.right;
        }
        return result;
    }


    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;
        Node leftLeft2 = new Node(4);
        Node leftRight2 = new Node(5);
        left.left = leftLeft2;
        left.right = leftRight2;
        Node leftLeft21 = new Node(8);
        Node leftLeft2r1 = new Node(9);
        leftLeft2.left = leftLeft21;
        leftLeft2.right = leftLeft2r1;
        Node leftRight2l1 = new Node(10);
        Node leftRight21 = new Node(11);
        leftRight2.left = leftRight2l1;
        leftRight2.right = leftRight21;
        Node rightLeft = new Node(6);
        Node rightRight = new Node(7);
        right.left = rightLeft;
        right.right = rightRight;

        System.out.println(findTotalNodes(root));
    }
}
