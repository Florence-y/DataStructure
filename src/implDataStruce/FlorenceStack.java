package implDataStruce;

import java.util.Iterator;

/**
 * @author Florence
 */
public class FlorenceStack<T> implements Iterable<T> {
    T top = null;
    int size = 0;
    Node<T> head = new Node<>();

    /**
     * 采用头插法插入元素
     *
     * @param data 要插入的元素
     */
    public void push(T data) {
        Node<T> temp = new Node<>(data);
        temp.next = head.next;
        head.next = temp;
        size++;
        top = data;
    }

    /**
     * 弹出元素
     *
     * @return 返回一个值
     */
    public T pop() {
        if (size == 0) {
            System.out.println("栈为空");
            return null;
        }
        T data = head.next.data;
        head.next = head.next.next;
        size--;
        if (size != 0) {
            top = head.next.data;
        } else {
            top = null;
        }
        return data;
    }

    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return 布尔值 如果为空就是true 如果不为空就是false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 去顶端值
     *
     * @return 获取顶端的值
     */
    public T top() {
        return top;
    }

    @Override
    public Iterator<T> iterator() {
        return new FlorenceStackIterator();
    }


    class FlorenceStackIterator implements Iterator<T> {
        Node<T> tempNode = head.next;

        @Override
        public boolean hasNext() {
            return tempNode != null;
        }

        @Override
        public T next() {
            T data = tempNode.data;
            tempNode = tempNode.next;
            return data;
        }
    }
}
