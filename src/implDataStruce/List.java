package implDataStruce;

import task.Week3;

/**
 * @author Florence
 * 自定义链表
 */
public class List <T>{
    public void insertNode(Node fore,Node wanToInsert ){
        wanToInsert.next=fore.next;
        fore.next=wanToInsert;
    }
    /**
     * 获取一个链表
     * 头插法
     * @param data 链表含有的元素
     * @return 头链表
     */
    public static <T> Node getInverseOrderList(T[] data){
        Node<T> head=new Node<>();
        int count=0;
        while (count<data.length){
            Node<T> newNode =new Node<>(data[count]);
            newNode.next=head.next;
            head.next=newNode;
            count++;
        }
        return head;
    }

    /**
     * 获取链表
     * 尾插法
     * @param <T> 链表含有的参数
     * @param data 数据
     * @return 返回头节点
     */
    public static  <T> Node<T> getRightOrderList (T[] data){
        Node<T> head = new Node<>();
        Node<T> nowNode=head;
        int count=0;
        while (count<data.length){
            Node<T> newNode=new Node<>(data[count]);
            nowNode.next=newNode;
            nowNode=newNode;
            count++;
        }
        return head;
    }
}
