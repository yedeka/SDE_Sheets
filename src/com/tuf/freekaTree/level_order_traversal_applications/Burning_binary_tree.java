package com.tuf.freekaTree.level_order_traversal_applications;

import java.util.ArrayDeque;
import java.util.Queue;

public class Burning_binary_tree {
    private static class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        boolean isVisited;

        public Node(int data){
            this.data = data;
            this.isVisited = false;
        }
    }

    private static Node populateParents(Node root, int val){
        Queue<Node> nodeQ = new ArrayDeque<>();
        Node result = null;
        nodeQ.add(root);

        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            while(size-- > 0){
                Node current = nodeQ.remove();
                if(current.left != null){
                    current.left.parent = current;
                    nodeQ.add(current.left);
                    if(current.left.data == val){
                        result = current.left;
                    }
                }
                if(current.right != null){
                    current.right.parent = current;
                    nodeQ.add(current.right);
                    if(current.right.data == val){
                        result = current.right;
                    }
                }
            }
        }

        return result;
    }

    private static int timToBurn(Node root, int burnVal){
        Node seedNode = populateParents(root, burnVal);
        Queue<Node> nodeQ = new ArrayDeque<>();
        nodeQ.add(seedNode);
        int time = -1;

        while(nodeQ.size() > 0){
            int size = nodeQ.size();
            time+= 1;
            while(size-- > 0){
                Node current = nodeQ.remove();
                current.isVisited = true;
                if(current.left !=null && !current.left.isVisited){
                    nodeQ.add(current.left);
                }
                if(current.right !=null && !current.right.isVisited){
                    nodeQ.add(current.right);
                }
                if(current.parent !=null && !current.parent.isVisited){
                    nodeQ.add(current.parent);
                }
            }
        }

        return time;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;
        Node leftLeft2 = new Node(4);
        left.left = leftLeft2;
        Node leftRight3 = new Node(7);
        leftLeft2.right = leftRight3;
        Node rightLeft2 = new Node(5);
        Node rightRight2 = new Node(6);
        right.left = rightLeft2;
        right.right = rightRight2;
        int val = 2;
        System.out.println("Time to burn binary tree from node"+val+" => "+timToBurn(root, val));

    }

}
