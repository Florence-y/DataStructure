package implDataStruce;

/**
 * @author Florence
 * 树的节点
 */
public class TreeNode<T> {
    T data;
    TreeNode<T> left=null;
    TreeNode<T> right=null;
    public TreeNode(T data){
        this.data=data;
    }

    @Override
    public String toString() {
        return "data=" + data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
