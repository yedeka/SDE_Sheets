package com.tuf.days.day5.linkedlist;

public class AddNumbersAsList {
    private static class Node<T>{
        T data;
        Node<T> next;

        public Node(T data){
            this.data =data;
        }
    }
    private static Node<Integer> addLists(Node<Integer> head1, Node<Integer> head2){
        Node<Integer> head = null;
        Node<Integer> returnHead = null;
        int carry = 0;
        while(head1!= null || head2!= null || carry == 1){
            int sum = 0;
            if(head1 != null){
                sum += head1.data;
                head1 = head1.next;
            }
            if(head2 != null){
                sum += head2.data;
                head2 = head2.next;
            }
            sum+= carry;
            carry = sum / 10;
            sum = sum  % 10;

            Node<Integer> answer = new Node<>(sum);
            if(head == null){
                head = answer;
                returnHead = head;
            } else {
                head.next = answer;
                head = head.next;
            }
        }

        return returnHead;
    }

    public static void main(String[] args){
        Node<Integer> head1 = new Node<>(2);
        Node<Integer> first1 = new Node<>(4);
        Node<Integer> second1 = new Node<>(3);
        head1.next = first1;
        first1.next = second1;
        Node<Integer> head2 = new Node<>(5);
        Node<Integer> first2 = new Node<>(6);
        Node<Integer> second2 = new Node<>(4);
        head2.next = first2;
        first2.next = second2;

        Node<Integer> addition = addLists(head1, head2);
        System.out.println("DONE");

    }
}
