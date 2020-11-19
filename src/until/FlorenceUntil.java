package until;


import implDataStruce.FlorenceQueue;
import implDataStruce.Node;
import implDataStruce.TreeNode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Florence
 */
public class FlorenceUntil {
    public static void print(Object object) {
        System.out.println(object);
    }

    /**
     * 冒泡排序
     *
     * @param arrs 要排序的数组
     */
    public static void bubbleSort(int[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs.length - i - 1; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    swap(arrs, j, j + 1);
                }
            }
        }
    }

    /**
     * 交换两个下标的值
     *
     * @param sortArrays 要交换的数组地址
     * @param index1     下标一
     * @param index2     下标二
     */
    public static void swap(int[] sortArrays, int index1, int index2) {
        int temp = sortArrays[index1];
        sortArrays[index1] = sortArrays[index2];
        sortArrays[index2] = temp;
    }

    /**
     * 获取一维测试数组
     *
     * @param n 上限
     * @return 得到的数组
     */
    public static int[] getOneDimensionNumArr(int begin, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = begin + i + 1;
        }
        return arr;
    }

    /**
     * 获取二维数组
     *
     * @param n 要的大小
     * @return 得到的二维数组
     */
    public static int[][] getTwoDimensionNumArr(int begin, int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = getOneDimensionNumArr(n * i + begin, n);
        }
        return arr;
    }

    /**
     * 获取三维数组 n*n*n
     *
     * @param n 要获取的三维数组的大小
     * @return 获取到的三维数组
     */
    public static int[][][] getThreeDimensionNumArr(int n) {
        int[][][] arr = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = getTwoDimensionNumArr(n * n * i, n);
        }
        return arr;
    }

    /**
     * 展示我们的二维数组
     *
     * @param arr 展示的二维数组
     */
    public static void showTwoDimensionArr(int[][] arr) {
        for (int[] subArr : arr) {
            System.out.println(Arrays.toString(subArr));
        }
        System.out.println("**************************************************");
    }

    /**
     * 展示三维数组
     *
     * @param arr 要展示的三维数组
     */
    public static void showThreeDimension(int[][][] arr) {
        for (int[][] subArr : arr) {
            showTwoDimensionArr(subArr);
        }
    }

    /**
     * 深复制一个二维数组
     *
     * @param sourceArr     源数组
     * @param wantToCopyArr 想要复制到的数组
     * @return 复制完成的数组
     */
    public static int[][] copyArrTwoDimension(int[][] sourceArr, int[][] wantToCopyArr) {
        for (int i = 0; i < sourceArr.length; i++) {
            System.arraycopy(sourceArr[i], 0, wantToCopyArr[i], 0, sourceArr[i].length);
        }
        return wantToCopyArr;
    }

    public static int[][][] copyArrThreeDimension(int[][][] sourceArr, int[][][] wantToCopyArr) {
        for (int i = 0; i < sourceArr.length; i++) {
            copyArrTwoDimension(sourceArr[i], wantToCopyArr[i]);
        }
        return wantToCopyArr;
    }

    /**
     * 数组交换元素
     *
     * @param arr    要交换元素的数组地址
     * @param index1 下标一
     * @param index2 下标二
     * @param <T>    泛型数组
     */
    public static <T> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * 倒置算法
     *
     * @param object 具体的对象数组
     * @param <T>    对象类型
     * @return 反转后的数组地址
     */
    private static <T> T[] inverseArr(T[] object) {
        int left = 0, right = object.length - 1;
        while (left < right) {
            T temp = object[left];
            object[left] = object[right];
            object[right] = temp;
            left++;
            right--;
        }
        return object;
    }

    /**
     * 获取一个整数数组
     *
     * @param n 要获取数组的大小
     * @return 获取到的数组
     */
    public static Integer[] getIntegerArr(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    /**
     * 正序递归遍历
     *
     * @param head 头指针
     * @param <T>  泛型参数
     */
    public static <T> void showListBeforeRecursion(Node<T> head) {
        if (head == null) {
            return;
        }
        if (head.data != null) {
            System.out.println(head.data);
        }
        showListBeforeRecursion(head.next);
    }

    /**
     * 获取随机数   [bottomBound,topBound]
     *
     * @param bottomBound 下界
     * @param topBound    上界
     * @return 获取到的随机数
     */
    public static int getRandomNum(int bottomBound, int topBound) {
        int c = topBound - bottomBound + 1;
        double x = Math.random() * c;
        int y = (int) x;
        return y + bottomBound;
    }

    public static BufferedOutputStream getOutFromFile(String filePath, String fileName) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return new BufferedOutputStream(new FileOutputStream(file));
    }
    /**
     * 获取阶乘
     * @param  n 要获取的阶乘数目
     * @return 得到的阶乘数
     */
    public static double getFactorial(long n) {
        double sum = 1;
        for (int i = 2; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }
    public static double log2(double N) {
        //Math.log的底为e
        return Math.log(N)/Math.log(2);
    }
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 计算满二叉树的层数
     * @param length
     * @return
     */
    public int getBinaryTreeHeight(int length){
        int rowCount= (int) (isPowerOfTwo(length)?log2(length):Math.ceil(log2(length)));
        return rowCount;
    }

    /**
     * 全排列（类链表形式）
     * @param length 操作的长度
     */
    public static void permutationByList(java.util.List<int[]> permutationArrList,int length) {
        Integer[] integerArr = getIntegerArr(length);
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(integerArr));
        for (Integer integer : list) {
            dfs(permutationArrList,integer + "", getListExpectN(list, integer));
        }
    }

    private static void dfs(java.util.List<int[]> arrList,String str, LinkedList<Integer> list) {
        if (list.size() == 0) {
            System.out.println(str);
            int[] intArr = stringArrToNumArr(str.split("->"));
            arrList.add(intArr);
            return;
        }
        for (Integer integer : list) {
            dfs(arrList,str + "->" + integer, getListExpectN(list, integer));
        }
    }


    private static int[] stringArrToNumArr(String[] strings){
        int[] resArr = new int[strings.length];
        int i=0;
        for (String str:strings){
            resArr[i++]=Integer.parseInt(str);
        }
        return resArr;
    }

    public static LinkedList<Integer> getListExpectN(LinkedList<Integer> list, Integer n) {
        LinkedList<Integer> resList = new LinkedList<>();
        for (Integer integer : list) {
            if (!integer.equals(n)) {
                resList.add(integer);
            }
        }
        return resList;
    }

    public static  java.util.List<int[]> getPermutationIntByLength(int length){
        java.util.List<int[]> list = new ArrayList<>();
        permutationByList(list,length);
        return list;
    }
    public static <T> void showTree(TreeNode<T>[] treeNodes){
        int length=treeNodes.length-1;
        int rowCount= (int) (isPowerOfTwo(length)?log2(length):Math.ceil(log2(length)));
        int bottomLength= (int) Math.pow(2,rowCount-1);
        FlorenceQueue<TreeNode<T>> queue = new FlorenceQueue<>();
        queue.enQueue(treeNodes[1]);
        int count=1;
        while (!queue.isEmpty()){
            String blankAndData= getBlank(bottomLength/2-((count+count-1)/2));
            for (int i=0;i<count&&!queue.isEmpty();i++){
                blankAndData+=queue.deQueue().getData()+" ";
                int index = (count + i) * 2;
                //左节点入队列
                if (index <=length){
                    queue.enQueue(treeNodes[index]);
                }
                //右节点入队列
                if (index+1<=length){
                    queue.enQueue(treeNodes[index+1]);
                }
            }
            //输出这一层的内容
            System.out.println(blankAndData);
            count*=2;
        }
    }
    private static String getBlank(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<length;i++){
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
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
        java.util.List<String> list = new ArrayList<>();
        //统计出所有的路径
        resGetTheEncodingStrList(list, root, "");
        for (int i=1;i<list.size();i++){
            int gap= getTwoNodeGap(list.get(i-1),list.get(i));
            res=res>gap?res:gap;
        }
        return res;
    }

    public static int getTwoNodeMinGap(TreeNode<Integer> root){
        int res = Integer.MAX_VALUE;
        //初始化编码树
        root.setData(-1);
        initTree(root);
        java.util.List<String> list = new ArrayList<>();
        //统计出所有的路径
        resGetTheEncodingStrList(list, root, "");
        for (int i=1;i<list.size();i++){
            int gap= getTwoNodeGap(list.get(i-1),list.get(i));
            res=res<gap?res:gap;
        }
        return res;
    }

    /**
     * 计算两点的距离
     * @param s1 编码完的字符串1
     * @param s2 编码完的字符串2
     * @return
     */
    public static int getTwoNodeGap(String s1, String s2) {
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
            return s2Length-s1Length+1;
        }
    }

    /**
     * 递归获取编码字符串
     * @param list
     * @param root
     * @param nowStr
     */
    private static void resGetTheEncodingStrList(List<String> list, TreeNode<Integer> root, String nowStr) {
        if (root != null) {
            nowStr += root.getData();
            if (root.getRight() == null && root.getLeft() == null) {
                list.add(nowStr);
            }
            resGetTheEncodingStrList(list, root.getLeft(), nowStr);
            resGetTheEncodingStrList(list, root.getRight(), nowStr);
        }
    }
    /**
     * 递归获取编码字符串map，一个叶子节点对应的编码串
     * @param root
     * @param nowStr
     */
    public static void resGetTheEncodingStrMap(Map<TreeNode<Integer>, String> map, TreeNode<Integer> root, String nowStr) {
        if (root != null) {
            nowStr += root.getData();
            if (root.getRight() == null && root.getLeft() == null) {
                map.put(root,nowStr);
            }
            resGetTheEncodingStrMap(map, root.getLeft(), nowStr);
            resGetTheEncodingStrMap(map, root.getRight(), nowStr);
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
    public static int min(int a,int b){
        return a>b?b:a;
    }
    public static int max(int a,int b){
        return a>b?a:b;
    }
    /**
     * 反转map键值对(一定要是双射)
     * @param keyAndValue 键值对
     * @param <K> 键具体类型
     * @param <V> 值具体类型
     * @return
     */
    public static <K,V> Map<V, K> inverseMap(Map<K,V> keyAndValue) {
        Map<V,K> resMap=new HashMap<>(keyAndValue.size());
        Set<Map.Entry<K, V>> entries = keyAndValue.entrySet();
        for (Map.Entry<K,V> entry:entries){
            resMap.put(entry.getValue(),entry.getKey());
        }
        return resMap;
    }
}
