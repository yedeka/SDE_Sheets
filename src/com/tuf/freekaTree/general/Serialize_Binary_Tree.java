package com.tuf.freekaTree.general;

import java.util.Stack;

public class Serialize_Binary_Tree {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static class Pair {
        Node node;
        int level;

        public Pair(Node node, int lvl){
            this.node = node;
            this.level = lvl;
        }
    }


    private static void serializeTree(Node root, StringBuffer sb){
        if(root == null){
            sb.append("null,");
            return;
        }
        sb.append(root.data+",");
        serializeTree(root.left, sb);
        serializeTree(root.right, sb);
    }

    private static Node deserialize(String[] arr){
        Stack<Pair> st = new Stack<>();
        Node root = new Node(Integer.parseInt(arr[0]));
        st.push(new Pair(root, 1));

        for(int i=1; i<arr.length-1; i++){
            Pair tos = st.peek();
            int currLevel = tos.level;
            Node currentNode = tos.node;
            String strCurrent = arr[i];

            if(strCurrent .equals("null")) {
                tos.level++;
                if(tos.level == 3) {
                    st.pop();
                }
            } else {
                int element = Integer.parseInt(strCurrent);
                Node newNode = new Node(element);
                if(currLevel == 1){ // We have a left subtree coming so add the coming node to left of top of stack
                    currentNode.left = newNode;
                    tos.level += 1;
                    st.push(new Pair(newNode, 1));
                }else if(currLevel == 2){//We got right subtree coming so add the incoming node to right pointer of top
                    currentNode.right = newNode;
                    st.pop();// Since the parent is completely handled pop it out before adding the incoming node.
                    st.push(new Pair(newNode, 1));
                }
            }
        }
        return root;
    }

    public static void main(String[] args){
        Node root = new Node(8);
        Node left = new Node(3);
        Node right = new Node(10);
        root.left = left;
        root.right = right;
        Node leftLeft = new Node(1);
        Node leftRight = new Node(6);
        left.left = leftLeft;
        left.right = leftRight;
        Node left2 = new Node(4);
        Node right2 = new Node(7);
        leftRight.left = left2;
        leftRight.right = right2;
        Node rightRight = new Node(14);
        right.right = rightRight;
        Node rightLeft2 = new Node(13);
        rightRight.left = rightLeft2;

        StringBuffer sb = new StringBuffer();
        serializeTree(root, sb);
        System.out.println(sb.toString());

        Node generatedRoot = deserialize(sb.toString().split(","));
        StringBuffer sb1 = new StringBuffer();
        serializeTree(generatedRoot, sb1);
        System.out.println("Serializing generated tree => "+sb1);

    }
}
