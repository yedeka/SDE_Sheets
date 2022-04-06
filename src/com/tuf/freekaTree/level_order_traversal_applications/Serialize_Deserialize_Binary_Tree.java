package com.tuf.freekaTree.level_order_traversal_applications;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

// For iterative version we use level order traversal.
public class Serialize_Deserialize_Binary_Tree {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }
    // Use level order traversal for praparing the serialization string
    // Do not use ArrayDeque here as it does not accept a null member.
    private static String serializeBTree(Node root){
        if(root == null) return "";
        Queue<Node> nodeQ = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        nodeQ.add(root);

        while(nodeQ.size() > 0){
            Node current = nodeQ.remove();
            if(current == null) {
                sb.append("#,");
                continue;
            }
            sb.append(current.data+",");
            nodeQ.add(current.left);
            nodeQ.add(current.right);
        }
        return sb.toString();
    }

    private static Node deserialize(String strTree){
        if(strTree.length() == 0){
            return null;
        }
        String[] arrNodes = strTree.split(",");
        Node root = new Node(Integer.parseInt(arrNodes[0]));
        Queue<Node> nodeQ = new ArrayDeque<>();
        nodeQ.add(root);

        for(int i=1; i<arrNodes.length; i+= 2){
            String strElement = arrNodes[i];
            Node current = nodeQ.remove();
            Node newNode = null;
            if(!strElement.equalsIgnoreCase("#")){
                newNode  = new Node(Integer.parseInt(strElement));
                current.left = newNode;
                nodeQ.add(newNode);
            }
            strElement = arrNodes[i+1];
            if(!strElement.equalsIgnoreCase("#")){
                newNode  = new Node(Integer.parseInt(strElement));
                current.right = newNode;
                nodeQ.add(newNode);
            }
        }

        return root;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node rightLeft = new Node(4);
        Node rightRight = new Node(5);

        root.left = left;
        root.right = right;
        right.left = rightLeft;
        right.right = rightRight;

        String strSerialized = serializeBTree(root);
        System.out.println(strSerialized);
        Node genRoot = deserialize(strSerialized);
        System.out.println("DONE");
    }
}