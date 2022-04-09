package com.tuf.BST.construction;

public class Construct_BST_PreOrder {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node bstFromPreOrder(int[] preOrder, int lr, int rr, int[] arrInd){
        if(arrInd[0] >= preOrder.length || preOrder[arrInd[0]] < lr || preOrder[arrInd[0]] > rr){
            return null;
        }
        Node root = new Node(preOrder[arrInd[0]]);
        arrInd[0] += 1;
        root.left = bstFromPreOrder(preOrder, lr, root.data, arrInd);
        root.right = bstFromPreOrder(preOrder, root.data, rr, arrInd);

        return root;
    }

    private static Node buildBST(int[] preOrder){
        int  n = preOrder.length;
        int lr = -(int)1e9-1;
        int rr = (int)1e9-1;
        int[] indx = new int[]{0};
        return bstFromPreOrder(preOrder, lr, rr, indx);
    }

    public static void main(String[] args){
        int[] preOrder = new int[]{30, 20, 10, 15, 25, 23, 39, 35, 42};
        Node root = buildBST(preOrder);
        System.out.println(root.data);
    }
}
