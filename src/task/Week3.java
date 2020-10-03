package task;

import implDataStruce.Node;

/**
 * @author Florence
 */
public class Week3 {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        //do something
        visitNodeByGapN(1,getInverseOrderList(getIntegerArr(10)));
        long endTime=System.currentTimeMillis();
        System.out.println("run time : "+(endTime-startTime)+"ms");
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
    public static  <T> Node getRightOrderList (T[] data){
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

    /**
     * 迭代访问链表
     * @param head 头指针
     * @param <T> 数据类型
     */
    public static <T> void showListIteration(Node<T> head){
        while (head!=null){
            if (head.data!=null) {
                System.out.println(head.data);
            }
            head=head.next;
        }
    }

    /**
     * 正序递归遍历
     * @param head 头指针
     * @param <T> 泛型参数
     */
    public static <T> void showListBeforeRecursion(Node<T> head){
        if (head==null){
            return;
        }
        if (head.data!=null){
            System.out.println(head.data);
        }
        showListBeforeRecursion(head.next);
    }
    public static <T> void showListAfterRecursion(Node<T> head){
        if (head==null){
            return;
        }
        showListAfterRecursion(head.next);
        if (head.data!=null) {
            System.out.println(head.data);
        }
    }
    /**
     * 获取一个整数数组
     * @param n 要获取数组的大小
     * @return 获取到的数组
     */
    public static Integer[] getIntegerArr(int n){
        Integer[] arr = new Integer[n];
        for (int i=0;i<n;i++){
            arr[i]= i + 1;
        }
        return arr;
    }
    public static <T> void visitNodeByGapN(int n, Node<T> head){
        head=head.next;
        while (head!=null){
            //跳转步长
            boolean isNull=false;
            for (int i=0;i<n;i++){
                head=head.next;
                if (head==null){
                    isNull=true;
                    break;
                }
            }
            if (!isNull&&head.data!=null) {
                System.out.println(head.data);
            }
        }
    }
}
