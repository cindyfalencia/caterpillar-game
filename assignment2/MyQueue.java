package assignment2;

import java.util.NoSuchElementException;

public class MyQueue<E>{
    private MyDoublyLinkedList<E> queue;

    public MyQueue(){
        queue = new MyDoublyLinkedList<>();
    }

    public void enqueue(E element){
        queue.addLast(element);
    }

    public E dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        } else{
            return queue.removeFirst();
        }
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public void clear(){
        queue.clear();
    }

    public int getSize() {
        return queue.getSize();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyQueue)) {
            return false;
        }
        MyQueue<?> other = (MyQueue<?>) o;
        return queue.equals(other.queue);
    }

}

