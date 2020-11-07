package task;

import implDataStruce.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Florence
 */
public class Week9 {
    static class ExpressAndValue {
        String express = null;
        Integer value = null;

        ExpressAndValue(String express, Integer value) {
            this.express = express;
            this.value = value;
        }
    }

    public static void main(String[] args) {
//        List<int[]> arrList =getPermutationIntByLength(4);
//        for (int[] arr:arrList){
//            System.out.println(isFixTwentyFivePoint(arr));
//        }
        TreeNode<Integer> treeForCalculateTwentyFourPointFirstType = getTreeForCalculateTwentyFourPointFirstType(new int[]{1, 2, 3, 4});
        getTwoNodeMaxGap(treeForCalculateTwentyFourPointFirstType);
    }

    /**
     * 计算叶子节点
     *
     * @param root 根节点
     * @param <T>  具体类型
     * @return 返回值
     */
    public static <T> int countAllLeafNode(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() != null && root.getRight() != null) {
            return 1;
        }
        return countAllLeafNode(root.getLeft()) + countAllLeafNode(root.getRight());
    }

    public static <T> int countBranchNode(TreeNode<T> root) {
        boolean isLeafNode = root.getRight() == null && root.getLeft() == null;
        //如果是叶子节点或者本身为空,则返回0
        if (root == null || isLeafNode) {
            return 0;
        }
        //否则f(现在)=1+f(左)+f(右)
        return countBranchNode(root.getLeft()) + countBranchNode(root.getRight()) + 1;
    }

    /**
     * 获取高度
     * @param root
     * @param <T>
     * @return
     */
    public static <T> int getDepth(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        //获取左子树最大深度
        int Rdepth = getDepth(root.getRight());
        //获取右子树最大深度
        int Ldepth = getDepth(root.getLeft());
        //计算当前高度并且加上左右子树的最大高度
        return Rdepth > Ldepth ? Rdepth + 1 : Ldepth + 1;
    }

    /**
     * 计算二十四点
     *
     * @param provideNum 提供的4个数字
     * @return
     */
    public static List<String> isFixTwentyFivePoint(int[] provideNum) {
        TreeNode<Integer> builderTreeFirstType = getTreeForCalculateTwentyFourPointFirstType(provideNum);
        TreeNode<Integer> builderTreeSecondType = getTreeForCalculateTwentyFourPointSecondType(provideNum);
        return getAllPossibleList(calculateByAfterOrderWay(builderTreeFirstType, provideNum), calculateByAfterOrderWay(builderTreeSecondType, provideNum));
    }


    /**
     * 将两个结果融合在一起
     *
     * @param calculateByAfterOrderWay
     * @param calculateByAfterOrderWay1
     * @return
     */
    private static List<String> getAllPossibleList(List<String> calculateByAfterOrderWay, List<String> calculateByAfterOrderWay1) {
        List<String> list = new ArrayList<>();
        list.addAll(calculateByAfterOrderWay);
        list.addAll(calculateByAfterOrderWay1);
        return list;
    }

    static char[] operator = {'+', '-', '*', '/'};

    /**
     * 四个数字固定，计算全部运算符
     *
     * @param builderTree 建立完的树
     * @param nums        四个值
     * @return
     */
    private static List<String> calculateByAfterOrderWay(TreeNode<Integer> builderTree, int[] nums) {
        List<String> expressList = new ArrayList<>();
        //三重循环遍历三个运算符的四种效果
        for (int operator1 = 0; operator1 < 4; operator1++) {
            for (int operator2 = 0; operator2 < 4; operator2++) {
                for (int operator3 = 0; operator3 < 4; operator3++) {
                    //是否符合结果  这里是个expressValue对象
                    ExpressAndValue expressSum = getExpressSum(builderTree, operator[operator1], operator[operator2], operator[operator3], 1);
                    if (expressSum
                            //的值
                            .value == 24) {
                        //获取他的字符串表达式
                        String expressString = expressSum.express;
                        expressList.add(expressString);
                    }
                }
            }
        }
        return expressList;
    }

    /**
     * 获取一个表达式生成的树的值
     *
     * @param root 树的根节点
     * @param operator1 操作符1
     * @param operator2 操作符2
     * @param operator3 操作符3
     * @param count 用到第几个操作符了
     * @return
     */
    private static ExpressAndValue getExpressSum(TreeNode<Integer> root, char operator1, char operator2, char operator3, int count) {
        if (root == null) {
            return new ExpressAndValue("", 0);
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return new ExpressAndValue(String.valueOf(root.getData()), root.getData());
        }
        char operator = 0;
        //获取左边的表达式获得的值
        ExpressAndValue expressSumLeft = getExpressSum(root.getLeft(), operator1, operator2, operator3, count + 1);
        //获取右边的表达式获得的值
        ExpressAndValue expressSumRight = getExpressSum(root.getRight(), operator1, operator2, operator3, count + 2);
        //根据次序计算
        if (count == 1) {
            operator = operator1;
        } else if (count == 2) {
            operator = operator2;
        } else if (count == 3) {
            operator = operator3;
        }
        return getResult(expressSumLeft, expressSumRight, operator);
    }

    /**
     * 根据传进来的操作符字符串计算值（返回的是一个对象，包含当前表达式的值，对应的字符串）
     * @param expressSumLeft
     * @param expressSumRight
     * @param operator
     * @return
     */
    private static ExpressAndValue getResult(ExpressAndValue expressSumLeft, ExpressAndValue expressSumRight, char operator) {
        try {
            switch (operator) {
                case '+':
                    return new ExpressAndValue("(" + expressSumLeft.express + "+" + expressSumRight.express + ")", expressSumLeft.value + expressSumRight.value);
                case '-':
                    return new ExpressAndValue("(" + expressSumLeft.express + "-" + expressSumRight.express + ")", expressSumLeft.value - expressSumRight.value);
                case '*':
                    return new ExpressAndValue("(" + expressSumLeft.express + "*" + expressSumRight.express + ")", expressSumLeft.value * expressSumRight.value);
                case '/':
                    return new ExpressAndValue("(" + expressSumLeft.express + "/" + expressSumRight.express + ")", expressSumLeft.value / expressSumRight.value);
                default:
                    return new ExpressAndValue("", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ExpressAndValue("wrong!!!!!!", 6666666);
        }
    }

    /**
     * 水平（a+b）+（c+d）
     *      #
     *    #   #
     * a   b  c  d
     *
     * @param provideNum
     * @return
     */
    private static TreeNode<Integer> getTreeForCalculateTwentyFourPointFirstType(int[] provideNum) {
        TreeNode<Integer> root = new TreeNode<>(null);
        TreeNode<Integer> rootLeft = new TreeNode<>(null);
        TreeNode<Integer> rootRight = new TreeNode<>(null);
        root.setLeft(rootLeft);
        root.setRight(rootRight);
        rootLeft.setLeft(new TreeNode<>(provideNum[0]));
        rootLeft.setRight(new TreeNode<>(provideNum[1]));
        rootRight.setLeft(new TreeNode<>(provideNum[2]));
        rootRight.setRight(new TreeNode<>(provideNum[3]));
        return root;
    }

    /**
     * 第二种形态
     *         #
     *      #    d
     *    #    c
     * a   b
     *
     * @param provideNum
     * @return
     */
    private static TreeNode<Integer> getTreeForCalculateTwentyFourPointSecondType(int[] provideNum) {
        TreeNode<Integer> root = new TreeNode<>(null);
        TreeNode<Integer> rootLeft = new TreeNode<>(null);
        TreeNode<Integer> rootRight = new TreeNode<>(provideNum[3]);
        //根节点的左右
        root.setLeft(rootLeft);
        root.setRight(rootRight);
        //左节点的左子树
        TreeNode<Integer> subRootLeft = new TreeNode<>(null);
        rootLeft.setRight(new TreeNode<>(provideNum[2]));
        rootLeft.setLeft(subRootLeft);
        //设置左节点的左子树的左右
        subRootLeft.setLeft(new TreeNode<>(provideNum[0]));
        subRootLeft.setRight(new TreeNode<>(provideNum[1]));
        return root;
    }

    /**
     * 获取两点的最大距离
     * @param root
     * @return
     */
    public static int getTwoNodeMaxGap(TreeNode<Integer> root) {
        int res = Integer.MIN_VALUE;
        //初始化编码树
        root.setData(-1);
        initTree(root);
        List<String> list = new ArrayList<>();
        //统计出所有的路径
        resGetTheEncodingStr(list, root, "");
        for (int i=1;i<list.size();i++){
            int gap= getTwoNodeGap(list.get(i-1),list.get(i));
            res=res>gap?res:gap;
        }
        return 0;
    }

    /**
     * 计算两点的距离
     * @param s1 编码完的字符串1
     * @param s2 编码完的字符串2
     * @return
     */
    private static int getTwoNodeGap(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        if (s1Length>s2Length){
            return getTwoNodeGap(s2,s1);
        }
        for (int i = 0; i< s1Length; i++){
            if (s1.charAt(i)!=s2.charAt(i)){
                return s1Length+s2Length-2*i+1;
            }
        }
        if (s1Length == s2Length){
            return 0;
        }
        else {
            return s2Length-s1Length-1;
        }

    }

    /**
     * 递归获取编码字符串
     * @param list
     * @param root
     * @param nowStr
     */
    private static void resGetTheEncodingStr(List<String> list, TreeNode<Integer> root, String nowStr) {
        if (root != null) {
            nowStr += root.getData();
            if (root.getRight() == null && root.getLeft() == null) {
                list.add(nowStr);
            }
            resGetTheEncodingStr(list, root.getLeft(), nowStr);
            resGetTheEncodingStr(list, root.getRight(), nowStr);
        }
    }

    /**
     * 对树进行初始化编码
     * @param root
     */
    private static void initTree(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        TreeNode<Integer> left = root.getLeft();
        if (left != null) {
            left.setData(0);
            initTree(left);
        }
        TreeNode<Integer> right = root.getRight();
        if (right != null) {
            right.setData(1);
            initTree(right);
        }
    }
}
