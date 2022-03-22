package com.tuf.days.day5.linkedlist;

public class MiddleOfTheList {
    private static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
        }
    }

    public static Node findMiddle(Node head){
        Node slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args){
        Node head = new Node(1);
        Node first = new Node(2);
        Node second = new Node(3);
        Node third = new Node(4);
        Node fourth = new Node(5);

        head.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;

        Node middle = findMiddle(head);
        System.out.println(middle.data);
    }
}
