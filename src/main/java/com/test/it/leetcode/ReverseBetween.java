package com.test.it.leetcode;

import java.util.Stack;

/**
 * leetcode 92
 * @Author: theonecai
 * @Date: Create in 2020/7/16 20:06
 * @Description:
 */
public class ReverseBetween {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next ==null) {
            return head;
        }

        Stack<ListNode> stack = new Stack<>();

        int i = 1;
        ListNode startPre = null;
        ListNode current = head;
        while (i < n) {
            if (i == m - 1) {
                startPre = current;
            }
            if (i >= m) {
                stack.push(current);
            }
            i++;
            current = current.next;
        }
        stack.push(current);

        ListNode endNext = current.next;

        if (startPre == null) {
            head = current;
        } else {
            current = startPre;
        }
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }
        current.next = endNext;

        return head;
    }

    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ReverseBetween reverseBetween = new ReverseBetween();
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        root.next.next.next.next.next.next = new ListNode(7);
        root.next.next.next.next.next.next.next = new ListNode(8);
        root.next.next.next.next.next.next.next.next = new ListNode(9);
        root.next.next.next.next.next.next.next.next.next = new ListNode(10);

        ListNode node = reverseBetween.reverseBetween(root, 2, 9);
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
    }
}
