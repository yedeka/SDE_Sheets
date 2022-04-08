package com.tuf.freekaTree.traversal.applications;

public class Recover_Binary_Tree {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    public static void recoverTree(Node root){
        Node first = null, second = null;
        // Step 1 - First perform a Morris traversal
        Node prev = null, current = root;
        while(current != null){
            Node left = current.left;
            if(left == null){
                // perform the check here
                if(prev != null && prev.data > current.data){
                    if(first == null){
                        first = prev;
                    }
                    second = current;
                }
                prev = current;
                current = current.right;
            } else {// We try to build a thread going to the rightmost leaf of currents left subtree
                while(left.right != null && left.right != current){
                    left = left.right;
                }
                if(left.right == null){// reached to the rightmost leaf of left subtree
                    // Build a thread here pointing back to parent
                    left.right = current;
                    current = current.left;
                }
                else {
                    // perform the check here
                    if(prev.data > current.data){
                        if(first == null){
                            first = prev;
                        }
                        second = current;
                    }
                    // break the thread
                    left.right = null;
                    prev = current;
                    current = current.right;
                }
            }
        }

        if(first != null) {
            int temp = second.data;
            second.data = first.data;
            first.data = temp;
        }
    }

    public static void main(String[] args){
        Node root = new Node(25);
        Node left = new Node(20);
        Node right = new Node(22);

        root.left = left;
        root.right = right;

        Node leftLeft1 = new Node(10);
        Node leftRight1 = new Node(36);
        left.left = leftLeft1;
        left.right = leftRight1;

        Node rightLeft1 = new Node(30);
        Node rightRight1 = new Node(40);
        right.left = rightLeft1;
        right.right = rightRight1;

        Node leftLeft2 = new Node(5);
        Node leftRight2 = new Node(12);
        leftLeft1.left = leftLeft2;
        leftLeft1.right = leftRight2;

        Node rightLeft2 = new Node(28);
        Node rightLeft21 = new Node(38);
        Node rightRight2 = new Node(48);
        rightLeft1.left = rightLeft2;
        rightRight1.left = rightLeft21;
        rightRight1.right = rightRight2;

        recoverTree(root);
        System.out.println("Done recovering");
    }
}
