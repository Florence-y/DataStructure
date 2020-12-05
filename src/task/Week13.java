package task;

import implDataStruce.Node;

import java.util.Arrays;

import static until.FlorenceUntil.getNumbers;
import static until.FlorenceUntil.max;

/**
 * @author Florence
 */
public class Week13 {
    public static void main(String[] args) {
//        int[] tempArr = getNumbers(10000);
//        int[]  arr= new int[10000];
//        arr[0]=10000;
//        for (int i=0;i<tempArr.length;i++){
//            arr[i+1]=tempArr[i];
//        }
        int[] arr = getNumbers(10000);
        String string1 = Arrays.toString(arr);
        System.out.println("排序前：" + string1);
        long startTime = System.currentTimeMillis();
        radixSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时" + (endTime - startTime) + "ms");
        String string = Arrays.toString(arr);
        System.out.println("排序后：" + string);
    }

    /**
     * 大顶堆
     *
     * @param arr
     */
    public static void headSort(int[] arr) {
        initHeap(arr);
        while (arr[0] > 1) {
            //最后位置的下标
            int lastIndex = arr[0];
            //交换元素
            int temp = arr[1];
            arr[1] = arr[lastIndex];
            arr[lastIndex] = temp;
            //减少一个位置
            arr[0] = arr[0] - 1;
            sink(arr, 1, arr[0]);
        }
    }

    /**
     * 初始化堆
     *
     * @param arr
     */
    private static void initHeap(int[] arr) {
        int beginNodeIndex = (arr.length - 1) / 2;
        for (int i = beginNodeIndex; i > 0; i--) {
            sink(arr, i, arr.length - 1);
        }
    }

    /**
     * 下沉（递归版）
     *
     * @param arr
     * @param index
     * @param endPosition
     */
    private static void sink(int[] arr, int index, int endPosition) {
        //越界
        if (2 * index + 1 > endPosition) {
            return;
        }
        //找到两个子节点比较大的那个的下标
        int maxSubIndex = arr[2 * index] > arr[2 * index + 1] ? 2 * index : 2 * index + 1;
        if (arr[index] < arr[maxSubIndex]) {
            int temp = arr[index];
            arr[index] = arr[maxSubIndex];
            arr[maxSubIndex] = temp;
            //递归调用下一层
            sink(arr, maxSubIndex, endPosition);
        }
    }

    /**
     * 上浮（递归版）
     *
     * @param arr
     * @param index
     */
    private static void swim(int[] arr, int index) {
        if (index <= 1) {
            return;
        }
        if (arr[index] > arr[index / 2]) {
            //交换
            int temp = arr[index];
            arr[index] = arr[index / 2];
            arr[index / 2] = temp;
            //递归调用下一层
            swim(arr, index / 2);
        }
    }

    /**
     * 基数排序
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        Node<Integer>[] bucket = new Node[10];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new Node<>(null);
        }
        int base = 1;
        int maxNum = Integer.MIN_VALUE;
        for (int num : arr) {
            maxNum = max(maxNum, num);
        }
        while (base < maxNum) {
            for (int num : arr) {
                Node<Integer> node = new Node<>(num);
                int index = (num / base) % 10;
                Node<Integer> cur = bucket[index];
                while (cur.getNext() != null) {
                    cur = cur.getNext();
                }
                cur.setNext(node);
            }
            int index = 0;
            //遍历桶将数据拿出来
            for (int i = 0; i < bucket.length; i++) {
                Node<Integer> curNode = bucket[i].next;
                while (curNode != null && index < arr.length) {
                    arr[index++] = curNode.getData();
                    curNode = curNode.getNext();
                }
            }
            //清空桶
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new Node<>(null);
            }
            base *= 10;
        }
    }

    /**
     * 两数之和暴力版
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 二分查找两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumBinarySearch(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int foundIndex = Arrays.binarySearch(nums, target - nums[i]);
            if (foundIndex >= 0 && foundIndex != i) {
                return new int[]{i, foundIndex};
            }
        }
        return new int[0];
    }

    /**
     * 两数之和双指针
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumDoublePointer(int[] nums, int target) {
        int m = 0, n = 0, k, board = 0;
        int[] res = new int[2];
        int[] tmp1 = new int[nums.length];
        System.arraycopy(nums, 0, tmp1, 0, nums.length);
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else if (nums[i] + nums[j] == target) {
                m = i;
                n = j;
                break;
            }
        }
        for (k = 0; k < nums.length; k++) {
            if (tmp1[k] == nums[m]) {
                res[0] = k;
                break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmp1[i] == nums[n] && i != k) {
                res[1] = i;
            }
        }
        return res;
    }

}
