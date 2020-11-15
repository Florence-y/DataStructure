package implDataStruce;

/**
 * @author Florence
 */
public class HuffmanTreeNode {
    private int value;
    private HuffmanTreeNode left=null;
    private HuffmanTreeNode right=null;
    private HuffmanTreeNode root=null;

    public HuffmanTreeNode(int weight) {
        value=weight;
    }

    public int getData() {
        return value;
    }

    public void setData(int  value) {
        this.value = value;
    }

    public HuffmanTreeNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanTreeNode left) {
        this.left = left;
    }

    public HuffmanTreeNode getRight() {
        return right;
    }

    public void setRight(HuffmanTreeNode right) {
        this.right = right;
    }

    public HuffmanTreeNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanTreeNode root) {
        this.root = root;
    }
}
