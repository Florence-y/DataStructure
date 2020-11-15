package task;

import implDataStruce.FlorenceStack;
import implDataStruce.TreeNode;



/**
 * @author Florence
 */
public class Week8 {
    public static void main(String[] args) {

    }

    /**
     * 前序
     * @param root 根节点
     * @param <T> data域类型
     */
    public static <T> void foreOrderShow(TreeNode<T> root) {
        if (root!=null) {
            System.out.println(root.getData());
            foreOrderShow(root.getLeft());
            foreOrderShow(root.getRight());
        }
    }
    /**
     * 中序
     * @param root 根节点
     * @param <T> data域类型
     */
    public static <T> void midOrderShow(TreeNode<T> root){
        if (root!=null){
            midOrderShow(root.getLeft());
            System.out.println(root.getData());
            midOrderShow(root.getRight());
        }
    }
    /**
     * 后序
     * @param root 根节点
     * @param <T> data域类型
     */
    public static <T> void rightOrderShow(TreeNode<T> root){
        if (root==null){
            rightOrderShow(root.getLeft());
            rightOrderShow(root.getRight());
            System.out.println(root.getData());
        }
    }

    /**
     * 前序建立二叉树
     */
    static int index=0;
    public static <T> TreeNode<T> buildBinaryTreeByForeOrder(T[] nodes,int height,int sourceHeightBinaryTree){
        if (height<=sourceHeightBinaryTree&&index<nodes.length){
            TreeNode<T> root = new TreeNode<>(nodes[index++]);
            root.setLeft(buildBinaryTreeByForeOrder(nodes,height+1,sourceHeightBinaryTree));
            root.setRight(buildBinaryTreeByForeOrder(nodes,height+1,sourceHeightBinaryTree));
            return root;
        }
        return null;
    }

    /**
     * 中序建立二叉树
     */
    public static <T> TreeNode<T> buildBinaryTreeByMidOrder(T[] nodes,int index,int height,int sourceHeightBinaryTree){
        if (height<=sourceHeightBinaryTree&&index<nodes.length){
            TreeNode root=new TreeNode(null);
            root.setLeft(buildBinaryTreeByMidOrder(nodes,index,height+1,sourceHeightBinaryTree));
            root.setData(nodes[index+1]);
            root.setRight(buildBinaryTreeByMidOrder(nodes,index+2,height+1,sourceHeightBinaryTree));
        }
        return null;
    }

    /**
     * 后序建立二叉树
     */
    public static <T> TreeNode<T> buildBinaryTreeByAfterOrder(T[] nodes,int index,int height,int sourceHeightBinaryTree){
        if (height<=sourceHeightBinaryTree&&index<nodes.length){
            TreeNode root=new TreeNode(null);
            root.setLeft(buildBinaryTreeByAfterOrder(nodes,index,height+1,sourceHeightBinaryTree));
            root.setRight(buildBinaryTreeByAfterOrder(nodes,index+1,height+1,sourceHeightBinaryTree));
            root.setData(nodes[index+2]);
        }
        return null;
    }

    /**
     * 非递归前序遍历二叉树
     * @param root 根节点
     * @param <T> 具体类型
     */
    public static <T> void noResIterateBinaryTreeForeOrder(TreeNode<T> root) throws Exception {
        if (root==null){
            throw new Exception("输入为空");
        }
        FlorenceStack<TreeNode<T>> stack = new FlorenceStack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode<T> node = stack.pop();
            //抽离出访问函数，降低耦合
            visit(node);
            stack.push(node.getRight());
            stack.push(node.getLeft());
        }
    }

    private static <T> void visit(TreeNode<T> node) {
        System.out.println(node.getData());
    }

    /**
     * 非递归中序遍历
     * @param root 根节点
     * @param <T> 泛型的具体的类型
     * @throws Exception 输入为空的异常
     */
    public static <T> void noResIterateBinaryTreeMidOrder(TreeNode<T> root) throws Exception {
        if (root==null){
            throw new Exception("输入为空");
        }
        FlorenceStack<TreeNode<T>> stack = new FlorenceStack<>();
        TreeNode<T> temp;
        stack.push(root);
        while (!stack.isEmpty()){
            temp = stack.top();
            while (temp!=null){
                stack.push(temp);
                temp=temp.getLeft();
            }
            TreeNode<T> nowNode = stack.pop();
            visit(nowNode);
            if (nowNode.getRight()!=null){
                stack.push(nowNode.getRight());
            }
        }
    }

    /**
     * 思路 （首先根据前序遍历去找根节点，然后分为左子树和右子树，然后递归右部分和左部分，然后结束条件）
     * @param foreOrder
     * @param startFore
     * @param endFore
     * @param midOrder
     * @param midStart
     * @param midEnd
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> getTreeFromForeOrderAndMidOrder(T[] foreOrder,int startFore,int endFore,T[] midOrder,int midStart,int midEnd){
        if (startFore>endFore||midStart>midEnd){
            return null;
        }
        TreeNode<T> root = new TreeNode<>(foreOrder[startFore]);
        //遍历寻找根节点
        for (int i=midStart;i<=midEnd;i++){
            if(midOrder[i].equals(foreOrder[startFore])){
                //递归遍历左子树
                root.setLeft( getTreeFromForeOrderAndMidOrder(foreOrder,startFore+1,startFore+i-midStart,
                        midOrder,midStart,i-1));
                //递归遍历右子树
                root.setRight(getTreeFromForeOrderAndMidOrder(foreOrder,i-midStart+startFore+1,endFore,midOrder,i+1,midEnd));
            }
        }

        return root;
    }

}
