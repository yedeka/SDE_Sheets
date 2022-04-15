package com.tuf.freekaTree.traversal;

public class BST_DLL_Morris_Traversal {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static void convertBSTDLLMorrisTraversal(Node root, Node[] prev){
        Node current = root;
        while(current != null){
            Node left = current.left;
            if(left == null){ // We have reached to the left most node of a subtree which is the smallest node of subtree
                prev[0].right = current;
                current.left = prev[0];
                prev[0] = current;
                current = current.right;
            } else {
                while(left.right != null && left.right != current){ // Check for rightmost node of left subtree or a thread
                    left = left.right;
                }
                if(left.right == null){ // Reached to rightmost node of left subtree build a thread here
                    left.right = current;
                    current = current.left;
                } else {
                    // You have reached at the thread
                    left.right = null;
                    current.left = prev[0];
                    prev[0].right = current;
                    prev[0] = current;
                    current = current.right;// Since I have already parsed my left subtree now move to right.
                }
            }
        }
    }

    private static Node convertBSTDLL(Node root){
        Node dummy = new Node(-1);
        if(root == null){
            return null;
        }
        Node[] prev = new Node[]{dummy};
        convertBSTDLLMorrisTraversal(root, prev);
        // Attach first and last node here to create a circle
        Node head = dummy.right;
        head.left = prev[0];
        prev[0].right = head;
        dummy.right = null;// we need to get rid of dummy since we have attached head to tail
        return head;
    }

    private static Node preapareTree(){
       Node root = new Node(25);
       Node left = new Node(20);
       Node right = new Node(36);
       root.left = left;
       root.right = right;
        // Left subtree
       Node leftLeft = new Node(10);
       Node leftRight = new Node(22);
       left.left = leftLeft;
       left.right = leftRight;
       Node leftLeft1 = new Node(5);
       Node leftRight1 = new Node(12);
       leftLeft.left = leftLeft1;
       leftLeft.right = leftRight1;
        // Right subtree
       Node rightLeft1 = new Node(30);
       Node rightRight1 = new Node(40);
       right.left = rightLeft1;
       right.right = rightRight1;
       Node rightLeft2 = new Node(28);
       rightLeft1.left = rightLeft2;
       Node rightLeft21 = new Node(38);
       Node rightRight21 = new Node(48);
       rightRight1.left = rightLeft21;
       rightRight1.right = rightRight21;

       return root;
    }

    public static void main(String[] args){
        Node root = preapareTree();
        Node head = convertBSTDLL(root);
        System.out.println("DONE");
    }
}
