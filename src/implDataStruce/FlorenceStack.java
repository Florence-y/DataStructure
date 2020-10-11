package implDataStruce;

/**
 * @author Florence
 */
public class FlorenceStack<T> {
    T top;
    int size=0;
    Node<T> head=new Node<>();

    /**
     * 采用头插法插入元素
     * @param data 要插入的元素
     */
    public void push(T data){
        Node<T> temp= new Node<>(data);
        temp.next=head.next;
        head.next=temp;
        size++;
        top=data;
    }

    /**
     * 弹出元素
     * @return 返回一个值
     */
    public T pop(){
        if (size==0){
            System.out.println("栈为空");
            return null;
        }
        T data =head.next.data;
        head.next=head.next.next;
        top=head.next.next.data;
        size--;
        return data;
    }

    /**
     * 是否为空
     * @return 布尔值 如果为空就是true 如果不为空就是false
     */
    public boolean isExist(){
        return size==0;
    }

    /**
     * 去顶端值
     * @return 获取顶端的值
     */
    public T top(){
        return top;
    }
}
