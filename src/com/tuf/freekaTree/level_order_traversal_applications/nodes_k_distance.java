package com.tuf.freekaTree.level_order_traversal_applications;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class nodes_k_distance {
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

    private static Node populateParents(Node root, int value){
        Queue<Node> nodeQ = new ArrayDeque<>();
        nodeQ.add(root);
        Node seedNode = null;
        while(nodeQ.size() > 0){
            int size;
            size = nodeQ.size();
            while(size -- >0){
                Node parent = nodeQ.remove();
                if(parent.left != null){
                    Node leftChild = parent.left;
                    leftChild.parent = parent;
                    nodeQ.add(leftChild);
                    if(leftChild.data == value){
                        seedNode = leftChild;
                    }
                }
                if(parent.right != null){
                    Node rightChild = parent.right;
                    rightChild.parent = parent;
                    nodeQ.add(rightChild);
                    if(rightChild.data == value){
                        seedNode = rightChild;
                    }
                }
            }
        }
        return seedNode;
    }

    private static List<Integer> findNodes(Node seedNode, int k){
        List<Integer> result = new ArrayList<>();
        Queue<Node> nodeq = new ArrayDeque<>();
        nodeq.add(seedNode);
        int currentDistance = k;
        while(nodeq.size() > 0){
            int size = nodeq.size();
            while(size-- > 0){
                Node current = nodeq.remove();
                current.isVisited = true;
                if(currentDistance == 0){
                    result.add(current.data);
                }
                if(current.left != null && !current.left.isVisited){
                    nodeq.add(current.left);
                }
                if(current.right != null && !current.right.isVisited){
                    nodeq.add(current.right);
                }
                if(current.parent != null && !current.parent.isVisited){
                    nodeq.add(current.parent);
                }
            }
            if(currentDistance == 0){
                break;
            }
            currentDistance -= 1;
        }
        return result;
    }

    private static List<Integer> nodes_k_distance(Node root, int value, int k){
        // Step 1 - Perform level order traversal to populate parents and seed node.
        Node seedNode = populateParents(root, value);
        List<Integer> result = new ArrayList<>();
        // Step 2 - Perform second level order traversal to find out nodes at k distance.
        if(seedNode != null) {
            result = findNodes(seedNode, k);
        }
        return result;
    }

    public static void main(String[] args){
        Node root = new Node(3);
        Node left = new Node(5);
        Node right = new Node(1);
        root.left = left;
        root.right = right;
        Node leftLeft2 = new Node(6);
        Node leftRight2 = new Node(2);
        left.left = leftLeft2;
        left.right = leftRight2;
        Node leftLeft3 = new Node(7);
        Node leftRight3 = new Node(4);
        leftRight2.left = leftLeft3;
        leftRight2.right = leftRight3;
        Node rightLeft2 = new Node(0);
        Node rightRight2 = new Node(8);
        right.left = rightLeft2;
        right.right = rightRight2;
        int value = 5;
        int k = 2;
        List<Integer> result = nodes_k_distance(root, value, k);
        System.out.println(result);
    }
}
