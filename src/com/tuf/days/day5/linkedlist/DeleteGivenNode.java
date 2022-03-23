package com.tuf.days.day5.linkedlist;

public class DeleteGivenNode {
    private static class Node<T>{
        T data;
        Node next;

        public Node(T data){
            this.data = data;
        }
    }

    private static void deleteNode(Node deleteNode){
        Node next = deleteNode.next;
        deleteNode.data = next.data;
        deleteNode.next = next.next;
    }
    public static void main(String[] args){
        Node<Integer> head = new Node<>(1);
        Node<String> first = new Node<>("DELETE_ME");
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        head.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;

        deleteNode(first);
        System.out.println("DONE");
    }
}
