package com.tuf.freekaTree.traversal;

public class Binary_Search_Tree_Iterator_Morris_Traversal {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    public static class BSTIterator{
        Node current = null;
        public BSTIterator(Node root){
            current = root;
        }

        private Node getRightMostNode(Node left){
            while(left.right != null && left.right != current){
                left = left.right;
            }
            return left;
        }

        // major Morris traversal loop that does the work
        private Node performMorrisTraversal(){
            Node result = null;
            while(this.current != null){
                Node left = current.left;
                if(left == null){
                    result = this.current;
                    // We have reached the last left child of left subtree. We already have a thread so just go over the thread.
                    this.current = this.current.right;
                    break;
                } else {
                    Node rmn = getRightMostNode(left);
                    if(rmn.right == null){ // I reached the rightmost child of left subtree thread creation time.
                        rmn.right = this.current;
                        this.current = this.current.left;
                    } else {// I am coming at thread in inroder traversal. Time to backtrack and hence we will capture current as result
                        result = this.current;
                        // Break thread
                        rmn.right = null;
                        this.current = this.current.right;
                        break;
                    }
                }
            }
            return result;
        }

        public int next() {
            Node result = performMorrisTraversal();
            return result.data;
        }

        public boolean hasNext() {
            return this.current != null;
        }
    }

    public static void main(String[] args){
        Node root = new Node(30);
        Node left = new Node(15);
        Node right = new Node(60);
        root.left = left;
        root.right = right;
        // Left subtree
        Node leftLeft = new Node(7);
        Node leftRight = new Node(22);
        left.left = leftLeft;
        left.right = leftRight;
        Node leftLeft1 = new Node(17);
        Node leftRight1 = new Node(27);
        leftRight.left = leftLeft1;
        leftRight.right = leftRight1;
        // Right subtree
        Node rightLeft = new Node(45);
        Node rightRight = new Node(75);
        right.left = rightLeft;
        right.right = rightRight;
        Node rightLeft1 = new Node(73);
        rightRight.left = rightLeft1;

        // Testing tree iterator
        BSTIterator itr = new BSTIterator(root);
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
