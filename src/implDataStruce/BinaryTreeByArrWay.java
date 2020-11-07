package implDataStruce;

/**
 * @author Florence
 */
public class BinaryTreeByArrWay<T> {
    T[] data;

    public BinaryTreeByArrWay(T[] data) {
        this.data = data;
    }

    public T getLeft(int index){
        return data[index*2];
    }

    public T getRight(int index){
        return data[index*2+1];
    }

    public T getRoot(){
        return data[1];
    }
}
