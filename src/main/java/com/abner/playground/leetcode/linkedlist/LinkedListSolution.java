package com.abner.playground.leetcode.linkedlist;

import cn.hutool.db.DbRuntimeException;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LinkedListSolution {

    public static void main(String[] args) {
        LinkedListSolution solution = new LinkedListSolution();
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(2);
        ListNode fourth = new ListNode(1);
        head.next = second;
        second.next = third;
        third.next = fourth;

        solution.isPalindrome(head);
    }

    //No.142 环形链表II  返回环的入口， 链表五环返回null
    public ListNode detectCycle(ListNode head) {

        if(head == null) return null;

        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                break;
            }
        }

        if(fast != slow || slow.next == null) return null;

        ListNode ptr = head;
        while(slow != ptr){
            slow = slow.next;
            ptr = ptr.next;
        }
        return ptr;
    }

    //No.141 环形链表 判断链表是否有环
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    //No.234 回文链表
    public boolean isPalindrome(ListNode head) {

        //size 为1的链表是回文链表
        if(head == null || head.next == null) return true;

        ListNode curr = head;
        Stack<Integer> stack = new Stack<>();

        while(curr !=null){
            stack.push(curr.val);
            curr = curr.next;
        }

        int half = stack.size() / 2;

        while(half-- >=0){
            if(head.val != stack.pop())
                return false;
            head = head.next;
        }
        return true;
    }



    //No.160 相交链表 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode currA = headA;

        while(currA != null){
            visited.add(currA);
            currA = currA.next;
        }

        ListNode currB = headB;

        while(currB != null){
            if (visited.contains(currB)) {
                return currB;
            }
            currB = currB.next;
        }

        return null;
    }


    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next;}
}
