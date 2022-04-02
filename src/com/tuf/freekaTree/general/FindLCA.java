package com.tuf.freekaTree.general;

public class FindLCA {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node findLCA(Node root, int p, int q){
        // Base case
        if(root == null || root.data == p || root.data == q){
            return root;
        }
        Node leftSearch = findLCA(root.left, p, q); // Search for p or q in left subtree
        Node rightSearch = findLCA(root.right, p, q); // Search for p or q in left subtree
        if(leftSearch == null){
            return rightSearch;
        } else if(rightSearch == null){
            return leftSearch;
        } else {// Since both leftSearch and rightSearch are not null we found both the nodes under current node hence return the same.
            return root;
        }
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node rightLeft = new Node(4);
        Node rightRight = new Node(5);
        Node left2 = new Node(8);
        Node left21 = new Node(6);
        Node right21 = new Node(7);

        root.left = left;
        root.right = right;
        right.left = rightLeft;
        right.right = rightRight;
        rightLeft.left = left2;
        rightRight.left = left21;
        rightRight.left = right21;

        int searchVal1 = 7;
        int searchVal2 = 8;

        System.out.println("LCA of "+searchVal1+" and "+searchVal2+" => "+findLCA(root, searchVal1, searchVal2).data);
    }

}
