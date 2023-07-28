package assignment2;

import java.util.NoSuchElementException;
public class MyStack<E> {
    private MyDoublyLinkedList<E> stack;

    public MyStack(){
        stack = new MyDoublyLinkedList<>();
    }

    public void push(E element){
        stack.addFirst(element);
    }

    public E pop(){
        if(isEmpty()){
            throw new NoSuchElementException();
        } else{
            return stack.removeFirst();
        }
    }

    public E peek(){
        if(isEmpty()){
            throw new NoSuchElementException();
        } else{
            return stack.peekFirst();
        }
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void clear(){
        stack.clear();
    }

    public int getSize(){
        return stack.getSize();
    }
}

