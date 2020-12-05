package task;

import implDataStruce.TreeNode;

import java.util.Arrays;

import static until.FlorenceUntil.getFibonacciArr;
import static until.FlorenceUntil.getNumbers;

/**
 * @author Florence
 */
public class Week12 {
    public static void main(String[] args) {
        int[] arr = getNumbers(10000);
        String string1 = Arrays.toString(arr);
        System.out.println("排序前：" + string1);
        long startTime = System.currentTimeMillis();
        shellSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时" + (endTime - startTime) + "ms");
        String string = Arrays.toString(arr);
        System.out.println("排序后：" + string);
    }

    public static void shellSort(int[] arr) {
        int length = arr.length;
        //获取斐波那契数列
        Integer[] fibonacciArr = getFibonacciArr(length);
        int gapIndex = fibonacciArr.length - 1;
        while (gapIndex >= 0) {
            int realGap = fibonacciArr[gapIndex];
            for (int i = realGap; i < length; i++) {
                for (int j = i; j >= realGap && arr[j] < arr[j - realGap]; j -= realGap) {
                    int temp = arr[j];
                    arr[j] = arr[j - realGap];
                    arr[j - realGap] = temp;
                }
            }
            gapIndex--;
        }
    }

    public void fixTheLostNode(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return;
        }
        //左右节点不为空，继续深入
        if (root.getLeft() != null && root.getRight() != null) {
            fixTheLostNode(root.getLeft());
            fixTheLostNode(root.getRight());
        }
        //右节点为空
        else if (root.getLeft() != null && root.getRight() == null) {
            Integer data = root.getLeft().getData() + 1;
            TreeNode<Integer> rightNode = new TreeNode<>(data);
            root.setRight(rightNode);
            fixTheLostNode(root.getLeft());
        }
        //左节点为空
        else if (root.getLeft() == null && root.getRight() != null) {
            Integer data = root.getRight().getData() - 1;
            TreeNode<Integer> leftNode = new TreeNode<>(data);
            root.setLeft(leftNode);
            fixTheLostNode(root.getRight());
        }
    }
}
