package implDataStruce;

/**
 * @author Florence
 * 双向链表
 */
public class DoubleLinkedList<T> {
    Node<T> node = new Node<>(null);

    public void addNodeInPre(Node<T> cur, Node<T> wantToAdd) {
        if (cur == node || cur == null) {
            System.out.println("节点是头节点或者不存在");
            return;
        }
        wantToAdd.next = cur;
        wantToAdd.pre = cur.pre;
        cur.pre.next = wantToAdd;
        cur.pre = wantToAdd;
    }

    public void addNodeInNext(Node<T> cur, Node<T> wantToAdd) {
        wantToAdd.next = cur.next;
        wantToAdd.pre = cur;
        cur.next.pre = wantToAdd;
        cur.next = wantToAdd;
    }

    public T deletePre(Node<T> cur) {
        //存起来用于到时候清空野指针
        Node<T> pre = cur.pre;
        Node<T> temp = cur.pre.pre;
        temp.next = cur;
        cur.pre = temp;
        //清空删除节点的指向
        pre.next = null;
        pre.pre = null;
        return pre.data;
    }

    public T deleteNext(Node<T> cur) {
        //存起来用于到时候清空野指针
        Node<T> next = cur.next;
        Node<T> temp = cur.next.next;
        temp.pre = cur;
        cur.next = temp;
        //清空删除节点的指向
        next.next = null;
        next.pre = null;
        return next.data;
    }

    public T deleteCur(Node<T> cur) {
        //删除本身
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
        //清空指针
        cur.next = null;
        cur.pre = null;
        return cur.data;
    }

    class Node<T> {
        Node<T> pre;
        Node<T> next;
        T data;

        public Node(Object o) {
        }
    }
}
