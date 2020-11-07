package until;


import implDataStruce.List;
import implDataStruce.Node;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
    public boolean isPowerOfTwo(int n) {
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
}
