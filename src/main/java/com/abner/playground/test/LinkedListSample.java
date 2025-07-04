package com.abner.playground.test;


class Node<T>{
    T data;
    Node next;

    public Node(T data){
        this.data = data;
        this.next =null;
    }
}
public class LinkedListSample<T> {
    Node head;

    public LinkedListSample(){
        this.head =null;
    }

    public void add(T data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
        }else{
            Node current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void remove(T data){
        if(head == null) return;
        if(head.data == data){
            head = head.next;
        }
        Node current = head;
        while (current.next != null){
            if(current.next.data == data){
                current.next = current.next.next;
            }
            current = current.next;
        }
    }
}
