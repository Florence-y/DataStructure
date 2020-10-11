package task;

import implDataStruce.List;
import implDataStruce.Node;

import java.util.Comparator;

import static until.FlorenceUntil.showListBeforeRecursion;

/**
 * @author Florence
 * 第五周作业
 */
public class Week5<T> {
    /**
     * 给转换的时候用的
     */
    static Node oddListHead;
    static Node tempList;
    /**
     * 给还原的时候用的
     */
    static int count = 0;
    static Node dividePointerNodeEven;
    static Node dividePointerNodeOdd;
    static Node newHead = new Node();
    static Node cur = newHead;

    public static void main(String[] args) {
        Integer[] test = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Node<Integer> rightOrderList = List.getRightOrderList(test);
//        bubbleSortByList(rightOrderList, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//
//            @Override
//            public boolean equals(Object obj) {
//                return obj instanceof Integer;
//            }
//        });
        Node<Integer> oddReverseBeforeAndEvenAfter = getOddReverseBeforeAndEvenAfter(rightOrderList);
        Node<Integer> integerNode = recoverFromOddReverseBeforeAndEvenAfterList(oddReverseBeforeAndEvenAfter);
        showListBeforeRecursion(integerNode);
    }

    /**
     * 链表冒泡排序
     *
     * @param head       头节点
     * @param comparator 比较器
     * @param <T>        具体的data域类型
     */
    private static <T> void bubbleSortByList(Node<T> head, Comparator comparator) {
        int length = getListLength(head);
        for (int i = 0; i < length; i++) {
            //要想交换两个节点，必需站在他们前面一个节点
            Node<T> temp = head;
            while (temp != null && temp.next != null && temp.next.next != null) {
                if (comparator.compare(temp.next.data, temp.next.next.data) < 0) {
                    swap(temp, temp.next);
                }
                temp = temp.next;
            }
        }
    }

    /**
     * 1 2 3 4 5 6 7 8 9  变成 9 7 5 3 1 2 4 6 8
     *
     * @param head 要转化的头街点
     * @param <T>  data域类型
     * @return 得到新链表的表头
     */
    public static <T> Node<T> getOddReverseBeforeAndEvenAfter(Node<T> head) {
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node<T> oddListEnd = getGapFromSourceListReverse(head.next);
        Node<T> evenList = getGapFromSourceListOrder(head.next.next);
        Node<T> newHead = new Node<>();
        newHead.next = oddListHead;
        oddListEnd.next = evenList;
        return newHead;
    }

    /**
     * 从上面函数的变形中转回来
     *
     * @param head 上面链表的头部
     * @param <T>  data域类型
     * @return 返回的点
     */
    public static <T> Node<T> recoverFromOddReverseBeforeAndEvenAfterList(Node<T> head) {
        int listLength = getListLength(head);
        int dividePoint = listLength % 2 == 0 ? listLength / 2 : (listLength / 2) + 1;
        recurHelp(head, dividePoint);
        return newHead;
    }

    private static <T> void recurHelp(Node<T> head, int dividePoint) {
        if (count == dividePoint + 1) {
            dividePointerNodeEven = head;
            return;
        }
        count++;
        recurHelp(head.next, dividePoint);
        addNode(head.data);
        //如果能有下一对，那前面那个肯定有，所以上面不用判断，但是后面那个就不一定了，需要特别判断
        if (dividePointerNodeEven != null) {
            addNode(dividePointerNodeEven.data);
            dividePointerNodeEven = dividePointerNodeEven.next;
        }
    }

    public static int josephRoll(Node head) {
        int count = 0;
        while (head.next != head) {
            count++;
            if (count % 3 == 0) {
                head.next = head.next.next;
            }
        }
        return (int) head.data;
    }


    private static <T> void addNode(T data) {
        cur.next = new Node(data);
        cur = cur.next;
    }

    /**
     * 隔一个取一个但是是递归的  比如 1 2 3 4 5 6 7 8 9 起点定在1  结果  9->7->5->3->1
     *
     * @param head 要转换的源链表的头节点
     * @param <T>  data域类型
     * @return 得到的结果的链表尾部
     */
    private static <T> Node<T> getGapFromSourceListReverse(Node<T> head) {
        if (head == null || head.next == null) {
            T data = head == null ? null : head.data;
            oddListHead = new Node<>(data);
            tempList = oddListHead;
            return oddListHead;
        }
        getGapFromSourceListReverse(head.next.next);
        tempList.next = new Node<>(head.data);
        tempList = tempList.next;
        return tempList;
    }

    /**
     * 隔一个取一个，顺序
     *
     * @param firstNode 第一个点
     * @param <T>       具体data域的类型
     * @return 头节点
     */
    private static <T> Node<T> getGapFromSourceListOrder(Node<T> firstNode) {
        Node<T> first = new Node<>(firstNode.data);
        Node<T> cur = first;
        while (firstNode.next != null && firstNode.next.next != null) {
            cur.next = new Node<>(firstNode.next.next.data);
            firstNode = firstNode.next.next;
            cur = cur.next;
        }
        return first;
    }


    /**
     * 交换节点 是交换 head.next和head.next.next
     *
     * @param head 要交换的第一个节点的前面节点
     * @param next 要交换的第一个节点
     * @param <T>  data域类型
     */
    private static <T> void swap(Node<T> head, Node<T> next) {
        head.next = next.next;
        next.next = head.next.next;
        head.next.next = next;
    }

    /**
     * 获取链表的长度
     *
     * @param head 头节点
     * @param <T>  data域类型
     * @return 链表的长度
     */
    private static <T> int getListLength(Node<T> head) {
        int count = 0;
        while (head.next != null) {
            count++;
            head = head.next;
        }
        return count;
    }


}
