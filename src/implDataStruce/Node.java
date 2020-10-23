package implDataStruce;


/**
 * @author Florence
 */
public class Node<T> {
    public T data;
    public Node<T> next = null;

    public Node(T data) {
        this.data = data;
    }

    public Node() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
