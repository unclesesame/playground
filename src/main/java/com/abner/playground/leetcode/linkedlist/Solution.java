package com.abner.playground.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {

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
