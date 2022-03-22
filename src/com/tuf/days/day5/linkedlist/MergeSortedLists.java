package com.tuf.days.day5.linkedlist;

public class MergeSortedLists {
    public static class Node {
        int data;
        Node next;

        public Node(int data){
            this.data = data;
        }
    }

    // Optimal approach - O(N+M)
    public static Node mergeSortedList(Node head1, Node head2){
        Node mergedHead = new Node(-1), returnHead = mergedHead;

        while(head1 != null && head2 != null){
            if(head1.data <= head2.data){
                mergedHead.next = head1;
                head1 = head1.next;
            } else {
                mergedHead.next = head2;
                head2 = head2.next;
            }
            mergedHead = mergedHead.next;
        }
        while(head1!= null){
            mergedHead.next = head1;
            head1 = head1.next;
        }
        while(head2!= null){
            mergedHead.next = head2;
            head2 = head2.next;
        }
        return returnHead;
    }

    public static void main(String[] args){
        Node head1 = new Node(3);
        Node first1 = new Node(7);
        Node second1 = new Node(10);
        head1.next = first1;
        first1.next = second1;

        Node head2 = new Node(1);
        Node first2 = new Node(2);
        Node second2 = new Node(5);
        Node third2 = new Node(8);
        Node fourth2 = new Node(10);
        head2.next = first2;
        first2.next = second2;
        second2.next = third2;
        third2.next = fourth2;

        Node combinedList = mergeSortedList(head1, head2);
        System.out.println(combinedList.data);
    }
}
