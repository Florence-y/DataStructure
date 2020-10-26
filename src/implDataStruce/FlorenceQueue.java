package implDataStruce;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Florence
 */
public class FlorenceQueue<E> implements Iterable<E>{
    private E peek=null;
    private Node<E> head=new Node<>();
    private Node<E> tail=head;
    int size=0;

    /**
     * 链尾入队
     * @param data
     */
    public void enQueue(E data){
        Node<E> newNode = new Node<>(data);
        tail.setNext(newNode);
        tail=newNode;
        size++;
    }

    /**
     * 链头出队
     * @return
     */
    public E deQueue(){
        if (size!=0){
            Node<E> dequeueNode = head.getNext();
            E data=dequeueNode.getData();
            head.setNext(dequeueNode.getNext());
            size--;
            if (size==0){
                tail=head;
            }
            return data;
        }
        else {
            return null;
        }
    }

    public E front(){
        return head.getNext().getData();
    }

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return  new Iterator<E>() {
            Node<E> cur=head;
            @Override
            public boolean hasNext() {
                return cur.getNext()!=null;
            }

            @Override
            public E next() {
                E data=cur.getNext().getData();
                cur=cur.getNext();
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
        }
        return stringBuilder.toString();
    }
}
