package com.tuf.days.day5.linkedlist;

public class ReverseLinkedList {
    private static class Node {
        int data;
        Node next;

        public Node(int data){
            this.data = data;
        }
    }
    private static Node reverseList(Node head){
        Node current = head, prev= null;
        while(current != null){
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
    public static void main(String[] args){
        Node first = new Node(3);
        Node second = new Node(6);
        Node third = new Node(8);
        Node fourth = new Node(10);


        first.next = second;
        second.next = third;
        third.next = fourth;

        Node newHead = reverseList(first);
        System.out.println("DONE");
    }
}
