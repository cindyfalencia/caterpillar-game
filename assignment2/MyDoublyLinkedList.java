package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E> {
    private DNode head;
    private DNode tail;
    public void addFirst (E element){
        DNode newNode = new DNode();
        newNode.element = element;
        newNode.next = head;
        newNode.prev = null;

        if(head != null){
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }
    public void addLast(E element){
        DNode newNode = new DNode();
        newNode.element = element;
        newNode.next = null;
        newNode.prev = tail;

        if(tail != null){
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        E element = head.element;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return element;
    }

    public E removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        E element = tail.element;
        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return element;
    }

    public E peekFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    public E peekLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.element;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MyDoublyLinkedList<?>)) {
            return false;
        }
        if (getSize() != ((MyDoublyLinkedList<?>)obj).getSize()) {
            return false;
        }
        Iterator<E> iter1 = iterator();
        Iterator<?> iter2 = ((MyDoublyLinkedList<?>)obj).iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            E element1 = iter1.next();
            Object element2 = iter2.next();
            if (!(element1.equals(element2))) {
                return false;
            }
        }
        return !(iter1.hasNext() || iter2.hasNext());
    }

    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    @Override
    public void add(E element) {
        addLast(element);
    }
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }
    @Override
    public E remove() {
        return removeLast();
    }

    private class DNode {
        private E element;
        private DNode next;
        private DNode prev;
    }

    private class DLLIterator implements Iterator<E> {
        DNode curr;

        public DLLIterator() {
            this.curr = head;
        }

        public boolean hasNext() {
            return this.curr != null;
        }

        public E next() {
            if (!this.hasNext())
                throw new NoSuchElementException();

            E element = this.curr.element;
            this.curr = this.curr.next;
            return element;
        }
    }
//	public static void main(String[] args){
//		MyDoublyLinkedList<Integer> test = new MyDoublyLinkedList<>();
//		test.iterator();
//		test.add(1);
//		Iterator<Integer> iteratorTest = test.iterator();
//		while(iteratorTest.hasNext()){
//			iteratorTest.next();
//			System.out.println("pass");
//		}
//
//	}
}
