package task;


import implDataStruce.List;
import implDataStruce.Node;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import static until.FlorenceUntil.showListBeforeRecursion;

/**
 * @author Florence
 */
public class Week4 {
    static class BitNode{
        public double bitVal;
        public int bitPower;
        public BitNode next=null;
        public BitNode(double val,int power){
            bitVal=val;
            bitPower=power;
        }
        public BitNode(){

        }
    }
    public static void main(String[] args) {
//        Integer[] test = new Integer[]{1,2,3,4,5,6,7,8,9};
//        Node<Integer> rightOrderList = List.getRightOrderList(test);
//        inverseList(rightOrderList);
//        showListBeforeRecursion(rightOrderList);
        multiplyAdd(getMultiply());
    }
    public static <T> void insertNode(Node<T> fore,Node<T> wanToInsert ){
        wanToInsert.next=fore.next;
        fore.next=wanToInsert;
    }

    /**
     * 在A后插入B（可能没有A）
     * @param head 头节点
     * @param NodeA 在A后面插入B
     * @param NodeB B节点
     * @param <T> 泛型
     */
    public static <T> void addAfterAndDataFieldMaxOne(Node<T> head,Node<T> NodeA,Node<T> NodeB){
        Node<T> cur = head.next;
        while (cur!=null&&cur.data!=NodeA.data){
            cur=cur.next;
        }
        if (cur!=null){
            insertNode(cur,NodeB);
        }
    }

    /**
     * 在多个a后面插入B
     * @param head 头节点
     * @param NodeA A节点
     * @param NodeB B节点
     * @param <T> 泛型参数
     */
    public static <T> void addAfterNodeAAndDataFieldMany(Node<T> head,Node<T> NodeA,Node<T> NodeB){
        Node<T> cur=head;
        //直到走到末尾
        while (cur!=null){
            //查询到符合我们要插入节点的域
            while (cur!=null&&cur.data!=NodeA.data){ cur=cur.next; }
            if (cur!=null){
                //如果恰好cur的next为空，那么直接赋值cur的下一个为NodeB即可
                if (cur.next==null){
                    cur.next=NodeB;
                    cur=null;
                }
                //否则调用插入函数
                else {
                    insertNode(cur,new Node<>(NodeB.data));
                    cur=cur.next.next;
                }
            }
        }
    }

    /**
     * 在A前面插入B
     * @param head 头节点
     * @param NodeA A节点
     * @param NodeB B节点
     * @param <T> 泛型
     */
    public static <T> void addBeforeNodeA(Node<T> head,Node<T> NodeA,Node<T> NodeB){
        Node<T> cur =head;
        while (cur!=null&&cur.next!=null&&cur.next.data!=NodeA.data){
            cur=cur.next;
        }
        if (cur.next!=null){
            insertNode(cur,new Node<>(NodeB.data));
        }
    }

    /**
     * 在每一个A后面插入A
     * @param head 头节点
     * @param NodeA A节点
     * @param <T> 泛型
     */
    public static <T> void addNodeAAfterNodeA(Node<T> head, Node<T> NodeA){
        Node<T> cur=head;
        while (cur!=null){
            while (cur!=null&&cur.data!=NodeA.data){cur=cur.next;}
            if (cur!=null){
                //不是末尾
                if (cur.next!=null) {
                    insertNode(cur, new Node<>(NodeA.data));
                    //跳两步
                    cur=cur.next.next;
                }
                //到达末尾
                else{
                    cur.next=new Node<>(NodeA.data);
                    return;
                }
            }
        }
    }

    /**
     * 删除点A后面的(至多一个a节点)
     * @param head 头节点
     * @param NodeA A节点
     * @param <T> 泛型
     */
    public static <T> void deleteAfterNodeA(Node<T> head,Node<T> NodeA){
        Node<T> cur = head;
        while (cur.next!=null&&cur.data!=NodeA.data){ cur=cur.next; }
        //到达最后面的点
        if (cur.next==null){ return; }
        //删除节点
        cur.next=cur.next.next;
    }

    /**
     * 删除节点A前面的节点
     * @param head 头节点
     * @param NodeA 节点A
     * @param <T> 泛型参数
     */
    public static <T> void deleteBeforeNodeA(Node<T> head,Node<T> NodeA){
        Node<T> cur = head;
        while (cur.next!=null&&cur.next.next!=null&&cur.next.next.data!=NodeA.data){ cur=cur.next; }
        if (cur.next==null||cur.next.next==null){return;}
        cur.next=cur.next.next;
    }

    /**
     * 删除所有A节点
     * @param head 头节点
     * @param NodeA A节点
     * @param <T> 泛型
     */
    public static <T> void deleteAllNodeA(Node<T> head,Node<T> NodeA) {
        Node<T> cur = head;
        Node<T> temp = null;
        while (cur != null) {
            while (cur != null && cur.data != NodeA.data) {
                cur = cur.next;
            }
            while (cur != null && cur.data == NodeA.data && cur.next != null && cur.next.next != null) {
                //删除本身,如果后面的节点还存在
                cur.data = (T) cur.next.data;
                cur.next = cur.next.next;
            }
            if (cur.next.next == null) {
                cur.next = cur.next.data == NodeA.data ? null : cur.next;
                return;
            }
        }
    }

    /**
     * 根据步长为二交换元素
     * @param head 头指针
     * @param <T> 泛型
     */
    public static <T> void exchangeNodeByStepTwo(Node<T> head){
        Node<T> cur =head;
        Node<T> temp;
        while (cur.next!=null&&cur.next.next!=null){
            temp=cur.next;
            cur.next=cur.next.next;
            temp.next=temp.next.next;
            cur.next.next=temp;
            cur=cur.next.next;
        }
    }

    /**
     * 倒置链表
     * @param head 头指针
     * @param <T> 泛型
     */
    public static <T> void inverseList(Node<T> head){
        Node<T> after=head.next;
        Node<T> before=null;
        Node<T> temp;
        while (after!=null){
            temp=after.next;
            after.next=before;
            before=after;
            after=temp;
        }
        head.next=before;
    }

    /**
     * 合并链表
     * @param listA 列表a
     * @param listB 列表b
     * @param <T>
     * @return
     */
    public static <T> Node mergeList(Node<T> listA, Node<T> listB, Comparator comparator){
        Node<T> headA=listA.next;
        Node<T> headB=listB.next;
        Node<T> tempA;
        Node<T> tempB=null;
        while (headA!=null&&headB!=null&&headA.next!=null&&headB.next!=null){
            if (comparator.compare(headA,headB)<0){
                tempA=headA.next;
                insertNode(tempB,headA);
                headA=tempA;
            }
            tempB=headB;
            headB=headB.next;
        }
        if (headA.next==null){
            headA.next=headB;
        }
        if (headB.next==null){
            headB.next=headA;
        }
        return listA;
    }

    /**
     *
     * @param head 头节点
     * @param <T> 泛型
     * @return 分割的链表
     */
    public static <T> java.util.List<Node> divideList(Node head,T oneKind){
        java.util.List<Node> nodes = new LinkedList<>();
        Node<T> listA=new Node<T>();
        Node listB=new Node();
        Node cur =head.next;
        while (cur!=null){
            //如果是同类
            if (cur.data.getClass().isAssignableFrom(oneKind.getClass())){
                listA.next=new Node(cur.data);
            }
            else {
                listB.next=new Node(cur.data);
            }
            cur=cur.next;
        }
        nodes.add(listA);
        nodes.add(listB);
        return nodes;
    }

    /**
     * 多项式相加
     * @param multiply 多项式链头
     * @return
     */
    public static double multiplyAdd(BitNode multiply){
        double res=0;
        multiply=multiply.next;
        while (multiply!=null){
            if (multiply.bitVal!=0) {
                res += Math.pow(multiply.bitVal, multiply.bitPower);
                multiply = multiply.next;
            }
        }
        System.out.println(res);
        return res;
    }
    public static BitNode getMultiply(){
        BitNode head = new BitNode();
        BitNode temp=head;
        Scanner scanner = new Scanner(System.in);
        double base;
        int power;
        while ((base=scanner.nextDouble())!=0){
            power=scanner.nextInt();
            temp.next=new BitNode(base,power);
            temp=temp.next;
        }
        return head;
    }
}
