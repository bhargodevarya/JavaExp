package com.bhargo.datastructure.lists.LLQuestions;

import com.bhargo.datastructure.lists.UserLinkedList;

public class ListQues {

    public static UserLinkedList<Integer> createDummyLL() {
        UserLinkedList<Integer> list = new UserLinkedList<>();
        list.daisyAdd(1).daisyAdd(2).daisyAdd(4)
                .daisyAdd(3).daisyAdd(2).daisyAdd(1);
        return list;
    }

    public static UserLinkedList<Integer> createLoopedDummyLL() {
        UserLinkedList<Integer> list = new UserLinkedList<>();
        list.daisyAdd(5).daisyAdd(9)
                .daisyAdd(2).daisyAdd(7).daisyAdd(1);
        UserLinkedList.Node loopPoint = list.getNode(2);
        list.getNode(list.getSize()).setNext(loopPoint);
        return list;
    }


    public static void demoLLDelete() {
        UserLinkedList<Integer> list = createDummyLL();
        list.remove(3);
        list.printList();
    }

    public static void recursiveFind(Integer valueToFind) {
        UserLinkedList<Integer> linkedList = createDummyLL();
        System.out.println(recFind(linkedList.getHead(), valueToFind));
    }

    public static UserLinkedList.Node recFind(UserLinkedList.Node node, Integer value) {
        System.out.println("Current value is "+node);
        if (value.equals(node.getValue()) || node.getNext() == null) {
            return node;
        }
        return recFind(node.getNext(), value);
    }

    public static UserLinkedList.Node getNthNodeFromLast(int n) {
        UserLinkedList<Integer> linkedList = createDummyLL();
        linkedList.printList();
        UserLinkedList.Node curr = linkedList.getHead();
        int index = 1;
        while (index < (linkedList.getSize() -n +1)) {
            curr = curr.getNext();
            index++;
        }
        return curr;
    }

    public static boolean detectLoop() {
        UserLinkedList<Integer> list = createLoopedDummyLL();
        boolean result = false;
        UserLinkedList.Node forward = list.getHead(), backward = list.getHead();
        while (forward != null && forward.getNext() != null) {
                forward = forward.getNext().getNext();
                backward = backward.getNext();
                if ((forward.getNext() != null) &&
                        (forward.getNext().equals(backward.getNext()) &&
                                forward.getValue().equals(backward.getValue()))) {
                    result = true;
                    System.out.println(forward.getNext());
                    break;
                }
        }
        return result;
    }

    static UserLinkedList<Integer> ll;

    public static boolean isPalindrome(UserLinkedList<Integer> list) {
        boolean result = false;
        ll = list;
        result = recReverseTraversal(list.getHead());
        return result;
    }

    static UserLinkedList.Node temp;

    private static boolean recReverseTraversal(UserLinkedList.Node node) {
        if(node == null) {
            return false;
        }
        temp = ll.getHead();
        recReverseTraversal(node.getNext());
        System.out.println(node.getValue() + " " + temp.getValue());
        if (node.getValue() != temp.getValue())
            return false;
        temp = temp.getNext();
        return true;
    }

    public static void reverseLinkedList(UserLinkedList<Integer> linkedList) {
        recursivelyReverseLinkedList(linkedList.getHead());
        UserLinkedList.Node head = newHead;
        while (head != null) {
            System.out.println(head.getValue());
            head = head.getNext();
        }
    }

    private static UserLinkedList.Node newHead;

    private static void recursivelyReverseLinkedList(UserLinkedList.Node node) {
        if (node.getNext() == null) {
            newHead = node;
            return;
        }
        recursivelyReverseLinkedList(node.getNext());
        //UserLinkedList.Node temp = node.getNext();
        //temp.setNext(node);
        node.getNext().setNext(node);
        node.setNext(null);
    }
}
