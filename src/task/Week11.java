package task;


import implDataStruce.TreeNode;

import java.util.HashMap;
import java.util.Map;

import static until.FlorenceUntil.*;

/**
 * @author Florence
 */
public class Week11 {
    static int count = 0;

    public static void main(String[] args) {
//        ThreadTreeNode<Integer> a=new ThreadTreeNode<>();
//        ThreadTreeNode<Integer> b=new ThreadTreeNode<>();
//        ThreadTreeNode<Integer> c=new ThreadTreeNode<>();
//        ThreadTreeNode<Integer> d=new ThreadTreeNode<>();
//        ThreadTreeNode<Integer> e=new ThreadTreeNode<>();
//        a.setLeft(b);
//        a.setRight(c);
//        b.setLeft(d);
//        c.setRight(e);
//        initThreadTree(a);
        TreeNode<Integer> a = new TreeNode<>(1);
        System.out.println(a.equals(a));
        TreeNode<Integer> b = new TreeNode<>(2);
        TreeNode<Integer> c = new TreeNode<>(3);
        TreeNode<Integer> d = new TreeNode<>(4);
        TreeNode<Integer> e = new TreeNode<>(5);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        c.setRight(e);
        randomZeroOneTwoExchange(a);
        System.out.println("*************************************");
    }

    public static <T> void initThreadTree(ThreadTreeNode<T> root) {
        if (root == null) {
            return;
        }
        ThreadTreeNode<T> left = root.getLeft();
        //如果左节点不为空，则将左节点的线索指向当前节点
        if (left != null) {
            //设置线索
            left.setThread(root);
            //递归左子树
            initThreadTree(root.getLeft());
        }
        ThreadTreeNode<T> right = root.getRight();
        //同上
        if (right != null) {
            right.setThread(root);
            initThreadTree(root.getRight());
        }
    }

    /**
     * 0:停止  1:跟左节点交换  2:跟右节点交换
     *
     * @param root 根节点
     * @param <T>
     */
    public static <T> void randomZeroOneTwoExchange(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        int randomNum = getRandomNum(0, 2);
        //相当于前序遍历
        int isContinue = visitNode(randomNum, root);
        if (isContinue == 0) {
            return;
        }
        randomZeroOneTwoExchange(root.getLeft());
        randomZeroOneTwoExchange(root.getRight());
    }

    private static <T> int visitNode(int randomNum, TreeNode<T> root) {
        count++;
        T data = root.getData();
        if (randomNum == 0) {
            System.out.println("step " + count + ":stop");
            return randomNum;
        }
        //跟左节点交换
        else if (randomNum == 1) {
            TreeNode<T> left = root.getLeft();
            //如果左节点不为空，则继续进行，否则就结束
            if (left != null) {
                root.setData(left.getData());
                left.setData(data);
                System.out.println("step " + count + ":exchange leftNodeAndRoot");
            } else {
                System.out.println("step " + count + ":stop");
                return 0;
            }
        }
        //跟右节点交换
        else if (randomNum == 2) {
            TreeNode<T> right = root.getRight();
            if (right != null) {
                root.setData(right.getData());
                right.setData(data);
                System.out.println("step " + count + ":exchange rightNodeAndRoot");
            } else {
                System.out.println("step " + count + ":stop");
                return 0;
            }
        }
        return 1;
    }

    public static void exchangeNearestLeafNode(TreeNode<Integer> root) {
        Map<TreeNode<Integer>, Boolean> isUse = new HashMap<>(20);
        Map<TreeNode<Integer>, String> nodeReflectEncodeString = new HashMap<>(20);
        resGetTheEncodingStrMap(nodeReflectEncodeString, root, "");
        //先拿出一个叶子节点，然后另外的不是这个叶子节点本身的进行对比长度
        for (Map.Entry<TreeNode<Integer>, String> mainTreeNodeStringEntry : nodeReflectEncodeString.entrySet()) {
            int minGap = Integer.MAX_VALUE;
            TreeNode<Integer> minGapNode = null;
            TreeNode<Integer> curNode = mainTreeNodeStringEntry.getKey();
            for (Map.Entry<TreeNode<Integer>, String> otherTreeNodeStringEntry : nodeReflectEncodeString.entrySet()) {
                //如果跟当前访问的不是同个节点
                if (!curNode.equals(otherTreeNodeStringEntry.getKey())) {
                    //获取两个叶子节点间的距离
                    int gap = getTwoNodeGap(mainTreeNodeStringEntry.getValue(), otherTreeNodeStringEntry.getValue());
                    //如果比当前距离小（转换）
                    if (gap < minGap) {
                        minGap = gap;
                        minGapNode = otherTreeNodeStringEntry.getKey();
                    }
                }
            }
            //如果当前节点没被交换过
            if (!isUse.get(curNode)) {
                exchangeTwoTreeNode(curNode, minGapNode);
                isUse.put(curNode, true);
            }
        }
    }

    /**
     * 交换两个叶子节点
     *
     * @param curNode
     * @param minGapNode
     */
    private static void exchangeTwoTreeNode(TreeNode<Integer> curNode, TreeNode<Integer> minGapNode) {
        TreeNode<Integer> curNodeParent = curNode.getParent();
        TreeNode<Integer> minGapNodeParent = minGapNode.getParent();
        //设置当前主动交换节点的相应的叶子节点
        if (curNodeParent.getLeft().equals(curNode)) {
            curNodeParent.setLeft(minGapNode);
        } else {
            curNodeParent.setRight(minGapNode);
        }
        //设置当前主动交换节点的相应的叶子节点
        if (minGapNodeParent.getLeft().equals(minGapNode)) {
            minGapNodeParent.setLeft(curNode);
        } else {
            minGapNodeParent.setRight(curNode);
        }

    }

    /**
     * 线索二叉树节点
     *
     * @param <T>
     */
    static class ThreadTreeNode<T> {
        private ThreadTreeNode<T> left = null;
        private ThreadTreeNode<T> right = null;
        private ThreadTreeNode<T> Thread = null;

        public ThreadTreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(ThreadTreeNode<T> left) {
            this.left = left;
        }

        public ThreadTreeNode<T> getRight() {
            return right;
        }

        public void setRight(ThreadTreeNode<T> right) {
            this.right = right;
        }

        public ThreadTreeNode<T> getThread() {
            return Thread;
        }

        public void setThread(ThreadTreeNode<T> thread) {
            Thread = thread;
        }
    }

}
