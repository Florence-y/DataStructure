package task;

import implDataStruce.TreeNode;

import java.util.Arrays;
import java.util.TreeMap;

import static until.FlorenceUntil.getFibonacciArr;

/**
 * @author Florence
 */
public class Week12 {
    public static void main(String[] args) {
        int[] arr = {7,8,9,5,1,3,4,3,5,2,1,1,1,1000};
        String string1 = Arrays.toString(arr);
        System.out.println("排序前："+string1);
        shellSort(arr);
        String string = Arrays.toString(arr);
        System.out.println("排序后："+string);
    }

    public  void fixTheLostNode(TreeNode<Integer> root){
        if (root==null){
            return;
        }
        if (root.getLeft()==null&&root.getRight()==null){
            return;
        }
        //左右节点不为空，继续深入
        if (root.getLeft()!=null&&root.getRight()!=null){
            fixTheLostNode(root.getLeft());
            fixTheLostNode(root.getRight());
        }
        //
        else if (root.getLeft()!=null&&root.getRight()==null){
            Integer data =root.getLeft().getData()+1;
            TreeNode<Integer> rightNode=new TreeNode<>(data);
            root.setRight(rightNode);
            fixTheLostNode(root.getLeft());
        }
        else if (root.getLeft()==null&&root.getRight()!=null){
            Integer data =root.getRight().getData()-1;
            TreeNode<Integer> leftNode=new TreeNode<>(data);
            root.setLeft(leftNode);
            fixTheLostNode(root.getRight());
        }
    }
    public static void shellSort(int[] arr){
        int length=arr.length;
        //获取斐波那契数列
        Integer[] fibonacciArr = getFibonacciArr(length);
        int gapIndex=fibonacciArr.length-1;
        while (gapIndex>=0){
            int realGap=fibonacciArr[gapIndex];
            for (int i=realGap;i<length;i++){
                for (int j=i;j>=realGap&&arr[j]<arr[j-realGap];j-=realGap){
                    int temp=arr[j];
                    arr[j]=arr[j-realGap];
                    arr[j-realGap]=temp;
                }
            }
            gapIndex--;
        }
    }
}
