package com.tuf.freekaTree.general;

import java.util.ArrayList;
import java.util.Stack;

public class Serialize_Deserialize_Nary_Tree {
    private static class Node {
        int data;
        ArrayList<Node> children;

        public Node(int data){
            this.data = data;
            children = new ArrayList<>();
        }
    }

    private static void serializeNaryTree(Node root, StringBuffer sb){
        sb.append(root.data+",");
        for(Node child: root.children){
            serializeNaryTree(child, sb);
        }
        sb.append("null,");
    }

    private static Node deserializeNaryTree(String[] arrNodes){
        if(arrNodes == null || arrNodes.length == 0){
            return null;
        }
        Node root = new Node(Integer.parseInt(arrNodes[0]));
        Stack<Node> st = new Stack<>();
        st.push(root);

        for(int i=1; i<arrNodes.length; i++){
            String strElement = arrNodes[i];
            if(strElement.equals("null")){
                st.pop();
            } else {
                Node current = new Node(Integer.parseInt(arrNodes[i]));
                st.peek().children.add(current);
                st.push(current);
            }
        }
        return root;
    }


    public static void main(String[] args){
        Node root = new Node(10);
        Node first = new Node(20);
        Node second = new Node(30);
        Node third = new Node(40);
        first.children.add(new Node(50));
        first.children.add(new Node(60));

        second.children.add(new Node(70));
        Node secondSecond = new Node(80);
        secondSecond.children.add(new Node(110));
        secondSecond.children.add(new Node(120));
        second.children.add(secondSecond);
        second.children.add(new Node(90));

        third.children.add(new Node(100));

        root.children.add(first);
        root.children.add(second);
        root.children.add(third);

        StringBuffer serializedAString = new StringBuffer();
        serializeNaryTree(root, serializedAString);
        System.out.println(serializedAString);
        Node reconstructedRoot = deserializeNaryTree(serializedAString.toString().split(","));
        serializedAString = new StringBuffer();
        serializeNaryTree(reconstructedRoot, serializedAString);
        System.out.println(serializedAString);
    }
}
