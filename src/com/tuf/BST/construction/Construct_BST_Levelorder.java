package com.tuf.BST.construction;

import java.util.ArrayDeque;
import java.util.Queue;

public class Construct_BST_Levelorder {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static class Pair{
        Node parent;
        int lr;
        int rr;

        public Pair(){
            parent = null;
            lr = -(int)1e9;
            rr = (int)1e9;
        }

        public Pair(Node parent, int lr, int rr) {
            this.parent = parent;
            this.lr = lr;
            this.rr = rr;
        }
    }

    private static Node prepareBSTFromLO(int[] levelOrder){
        Queue<Pair> nodeQ = new ArrayDeque<>();
        nodeQ.add(new Pair());
        Node root = null;
        int idx = 0;
        while(nodeQ.size() > 0 && idx < levelOrder.length){
            int element = levelOrder[idx];
            Pair current = nodeQ.remove();
            if(element < current.lr || element > current.rr){
                continue;
            } else {
                Node newNode = new Node(element);
                idx++;
                if(current.parent == null){
                    root = newNode;
                } else {
                    // Find out to which parent current node will attach
                    Node parent = current.parent;
                    if(element <= parent.data){ // left child
                        parent.left = newNode;
                    } else {
                        parent.right = newNode;
                    }
                }
                // Add current node's left boundary and right boundary
                nodeQ.add(new Pair(newNode, current.lr, element));
                nodeQ.add(new Pair(newNode, element, current.rr));
            }
        }
        return root;
    }

    public static void main(String[] args){
        int[] levelOrder = new int[]{50, 17, 72, 12, 23, 54, 76, 9, 14, 19, 67};
        Node root = prepareBSTFromLO(levelOrder);
        System.out.println("DONE");
    }
}
