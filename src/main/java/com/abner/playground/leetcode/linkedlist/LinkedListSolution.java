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

    //No.24 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        //引入dummy节点，其next指向head
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        //引入temp指向dummy，并利用该指针遍历链表
        ListNode temp = dummyHead;

        //三个指针temp, p, q 迭代到链表尾
        while(temp.next != null && temp.next.next != null){
            //temp作为每轮迭代的基指针，p和q基于temp
            ListNode p = temp.next;
            ListNode q = temp.next.next;
            //交换p和q
            temp.next = q;
            p.next = q.next;
            q.next = p;
            //更新temp开始新一轮迭代
            temp = q;
        }

        return dummyHead.next;
    }

    //No.142 环形链表II  返回环的入口， 链表五环返回null
    public ListNode detectCycle(ListNode head) {

        if(head == null) return null;

        ListNode fast = head;
        ListNode slow = head;

        //先定位到slow和fast 相遇的点，一定在环内
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                break;
            }
        }

        if(fast != slow || slow.next == null) return null;

        //此时slow继续往前走，新的指针ptr从head开始，两个指针首次相遇的位置就是环的入口
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

    //No.148 排序链表 按升序排列并返回排序后的链表
    public ListNode sortList(ListNode head) {
        //递归终止条件
        if(head == null || head.next == null) return head;

        //找到链表的中点，分治
        ListNode mid = findMiddle(head);

        ListNode rightHead = mid.next;
        mid.next = null; //将链表断开，开始分治

        //递归排序前半部分的链表和后半部分链表
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        //合并
        return mergeTowLists(left, right);
    }

    //合并两个有序链表
    private ListNode mergeTowLists(ListNode head1, ListNode head2){
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;

        while (head1 != null && head2 != null){
            if(head1.val < head2.val){
                curr.next = head1;
                head1 = head1.next;
            }else{
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        //将链表剩余节点挂在链尾
        if(head1 != null){
            curr.next = head1;
        }
        if(head2 != null){
            curr.next = head2;
        }

        return dummyHead.next;
    }

    //快慢指针法
    private ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
