package com.tuf.freekaTree.traversal.applications;

public class Cameras_In_BinaryTree {
    private static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data){
            this.data = data;
        }
    }
    // We return 3 statuses
    // -1 - A node indicating it is in need of camera.
    // 0 - A node indicating it has camera.
    // 1 - A node indicating it does not need camera and has been taken care of by its children.
    private static int findCameraCntHelper(Node root, int[] cnt){

        if(root == null){ // Node with no children default condition returning node does not hae camera but has been taken care of
            return 1;
        }
        int leftCamera = findCameraCntHelper(root.left, cnt);
        int rightCamera = findCameraCntHelper(root.right, cnt);
        if(leftCamera == -1 || rightCamera == -1){ // One of my children need camera so I have camera and I will conver my choldren and parents.
            cnt[0] += 1;
            return 0;
        }
        if(leftCamera == 0 || rightCamera == 0){// One of my children have camera hence I am covered.
            return 1;
        }
        if(leftCamera == 1 && rightCamera == 1){ // Both of my children are covered by my descendents hence I would need camera for myself I ask my parent
            return -1;
        }
        return -1;
    }

    private static int findCameraCnt(Node root){
        int[] cnt = new int[1];
        int finCnt = findCameraCntHelper(root, cnt);
        if(finCnt == -1){// Indicates root is in need of a camera
            cnt[0] += 1;
        }
        return cnt[0];
    }

    public static void main(String[] args){
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;
        Node leftLeft1 = new Node(4);
        Node leftRight1 = new Node(5);
        left.left = leftLeft1;
        left.right = leftRight1;
        Node leftLeft2 = new Node(8);
        leftRight1.left = leftLeft2;
        Node leftRight3 = new Node(11);
        leftLeft2.right = leftRight3;
        Node leftLeft4 = new Node(12);
        Node leftRight4 = new Node(13);
        leftRight3.left = leftLeft4;
        leftRight3.right = leftRight4;
        Node rightLeft1 = new Node(6);
        Node rightRight1 = new Node(7);
        right.left = rightLeft1;
        right.right = rightRight1;
        Node rightLeft2 =  new Node(9);
        Node rightRight2 =  new Node(10);
        rightRight1.left = rightLeft2;
        rightRight1.right = rightRight2;
        Node rightLeft3 = new Node(14);
        rightRight2.left = rightLeft3;

        System.out.println("Total cameras needed => "+findCameraCnt(root));
    }
}
