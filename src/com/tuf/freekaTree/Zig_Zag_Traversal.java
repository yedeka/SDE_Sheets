package com.tuf.freekaTree;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Zig_Zag_Traversal {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }

    private static List<Integer[]> performZigZagTraversal(Node root){
        Queue<Node> nodeQ = new ArrayDeque<>();
        nodeQ.add(root);
        List<Integer[]> result = new ArrayList<>();
        boolean LRFlag = true;
        while(!nodeQ.isEmpty()){
            int size = nodeQ.size();
            Integer[] levelData = new Integer[size];
            for(int i=0;i <size; i++){
                Node current = nodeQ.remove();
                int index =  LRFlag == true ? i : size - 1 -i;
                levelData[index] = current.data;
                if(current.left != null){
                    nodeQ.add(current.left);
                }
                if(current.right != null){
                    nodeQ.add(current.right);
                }
            }
            LRFlag = !LRFlag;
            result.add(levelData);
        }
        return result;
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node leftLeft = new Node(4);
        Node leftRight = new Node(5);
        Node rightLeft = new Node(6);

        root.left = left;
        left.left = leftLeft;
        left.right = leftRight;

        root.right = right;
        right.left = rightLeft;

        List<Integer[]> traversal = performZigZagTraversal(root);
        for(Integer[] level: traversal){
            for(int element: level){
                System.out.print(element+" ");
            }
            System.out.println();
        }
    }
}
