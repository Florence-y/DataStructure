import implDataStruce.FlorenceStack;

public class testStack {
    public static void main(String[] args) {
        FlorenceStack<Integer> florenceStack = new FlorenceStack<>();
        florenceStack.push(1);
        florenceStack.push(2);
        florenceStack.push(3);
        florenceStack.push(4);
        florenceStack.push(5);
        florenceStack.push(6);
        for(Integer integer:florenceStack){
            System.out.println(integer);
        }
    }

}
