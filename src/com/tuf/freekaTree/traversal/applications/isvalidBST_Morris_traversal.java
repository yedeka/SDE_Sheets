package com.tuf.freekaTree.traversal.applications;

public class isvalidBST_Morris_traversal {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static boolean isValidBST_Morris(Node root){
        Node current = root, prev = null;
        while(current != null){
            Node left = current.left;
            if(left == null){ // We have reached the leftmost node so now check
                if(prev!= null && prev.data > current.data){
                    return false;
                }
                prev = current;
                current = current.right;
            } else {
                while(left.right != null && left.right != current){// Parse the right subtree of first left child to create threads
                    left = left.right;
                }
                if(left.right == null){// No thread exists create the thread
                    left.right = current;
                    current = current.left;// Thread is created hence move to next left node for subsequent thread creation.
                }
                if(left.right == current){ // We are backtracking from parent hence again check
                    if(prev.data > current.data){
                        return false;
                    }
                    // Break the thread
                    left.right = null;
                    prev = current;
                    current = current.right;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        Node root = new Node(30);
        Node left = new Node(15);
        Node right = new Node(45);
        root.left = left;
        root.right = right;
        Node leftLeft1 = new Node(7);
        Node leftRight1 = new Node(22);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node leftLeft2 = new Node(17);
        Node leftRight2 = new Node(27);
        leftRight1.left = leftLeft2;
        leftRight1.right = leftRight2;
        Node rightLeft1 = new Node(45);
        Node rightRight1 = new Node(75);
        right.left = rightLeft1;
        right.right = rightRight1;

        System.out.println(isValidBST_Morris(root));
    }
}
