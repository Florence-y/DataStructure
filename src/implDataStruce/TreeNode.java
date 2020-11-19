package implDataStruce;

import java.util.Objects;

/**
 * @author Florence
 * 树的节点
 */
public class TreeNode<T> {
    T data;
    TreeNode<T> left=null;
    TreeNode<T> right=null;

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    TreeNode<T> parent=null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TreeNode)) {
            return false;
        }
        TreeNode<?> treeNode = (TreeNode<?>) o;
        return Objects.equals(getData(), treeNode.getData()) &&
                Objects.equals(getLeft(), treeNode.getLeft()) &&
                Objects.equals(getRight(), treeNode.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData(), getLeft(), getRight());
    }
}
