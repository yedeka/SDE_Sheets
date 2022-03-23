package com.tuf.days.day5.linkedlist;

public class RemoveNthNodeFromEnd {
    private static class Node {
        int data;
        Node next;

        public Node(int data){
            this.data = data;
        }
    }

    private static Node removeNthEnd(Node head, int n){
        Node returnHead = head;
        // Step 1 take aheadPointer and move it ahead by N nodes
        Node behindPtr=head, aheadPtr = head, prev=null;
        if(n > 0){
            while(n-- > 0){
                aheadPtr = aheadPtr.next;
            }
            while(aheadPtr != null){
                prev = behindPtr;
                behindPtr = behindPtr.next;
                aheadPtr = aheadPtr.next;
            }

            Node next = behindPtr.next;
            if(prev != null){
                prev.next = next;
                returnHead = head;
            } else {
                returnHead = next;
            }
        }

        return returnHead;
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
        int n = 0;

        Node newHead = removeNthEnd(head, n);
        System.out.println("DONE");
    }
}
