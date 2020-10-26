import implDataStruce.FlorenceQueue;
import implDataStruce.FlorenceStack;

public class testStack {
    public static void main(String[] args) throws Exception {
//        FlorenceStack<Integer> florenceStack = new FlorenceStack<>();
//        florenceStack.push(1);
//        florenceStack.push(2);
//        florenceStack.push(3);
//        florenceStack.push(4);
//        florenceStack.push(5);
//        florenceStack.push(6);
//        for (Integer integer : florenceStack) {
//            System.out.println(integer);
//        }
        FlorenceQueue<Integer> florenceQueue = new FlorenceQueue<>();
        florenceQueue.enQueue(1);
        florenceQueue.enQueue(2);
        florenceQueue.enQueue(3);
        florenceQueue.enQueue(4);
        florenceQueue.enQueue(5);
//        for (Integer integer:florenceQueue){
//            System.out.println(integer);
//        }
        System.out.println(florenceQueue);
    }

}
