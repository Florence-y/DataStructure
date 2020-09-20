package until;


import java.util.Arrays;

/**
 * @author Florence
 */
public class FlorenceUntil {
    public static void print(Object object){
        System.out.println(object);
    }

    /**
     * 冒泡排序
     * @param arrs 要排序的数组
     */
    public static void bubbleSort(int[] arrs) {
        for(int i=0;i<arrs.length;i++) {
            for(int j=0;j<arrs.length-i-1;j++) {
                if(arrs[j]>arrs[j+1]) {
                    swap(arrs,j,j+1);
                }
            }
        }
    }

    /**
     * 交换两个下标的值
     * @param sortArrays 要交换的数组地址
     * @param index1 下标一
     * @param index2 下标二
     */
    public static void swap(int[] sortArrays,int index1,int index2) {
        int temp=sortArrays[index1];
        sortArrays[index1]=sortArrays[index2];
        sortArrays[index2]=temp;
    }
    /**
     * 获取一维测试数组
     * @param n 上限
     * @return 得到的数组
     */
    public static int[] getOneDimensionNumArr(int begin,int n){
        int[] arr = new int[n];
        for (int i=0;i<n;i++){
            arr[i]=begin+i+1;
        }
        return arr;
    }

    /**
     * 获取二维数组
     * @param n 要的大小
     * @return 得到的二维数组
     */
    public static int[][] getTwoDimensionNumArr(int begin,int n){
        int[][] arr = new int[n][n];
        for (int i=0;i<n;i++){
            arr[i]=getOneDimensionNumArr(n*i+begin,n);
        }
        return arr;
    }

    /**
     * 获取三维数组 n*n*n
     * @param n 要获取的三维数组的大小
     * @return 获取到的三维数组
     */
    public static int[][][] getThreeDimensionNumArr(int n){
        int[][][] arr = new int[n][n][n];
        for (int i=0;i<n;i++){
            arr[i]=getTwoDimensionNumArr(n*n*i,n);
        }
        return arr;
    }
    /**
     * 展示我们的二维数组
     * @param arr 展示的二维数组
     */
    public static void showTwoDimensionArr(int[][] arr){
        for (int[] subArr:arr){
            System.out.println(Arrays.toString(subArr));
        }
        System.out.println("**************************************************");
    }

    /**
     * 展示三维数组
     * @param arr 要展示的三维数组
     */
    public static void showThreeDimension(int[][][] arr){
        for (int[][] subArr:arr){
            showTwoDimensionArr(subArr);
        }
    }
    public static int[][] copyArrTwoDimension(int[][] sourceArr,int[][] wantToCopyArr){
        for (int i=0;i<sourceArr.length;i++){
            System.arraycopy(sourceArr[i],0,wantToCopyArr[i],0,sourceArr[i].length);
        }
        return wantToCopyArr;
    }
    public static int[][][] copyArrThreeDimension(int [][][] sourceArr,int[][][] wantToCopyArr){
        for (int i=0;i<sourceArr.length;i++){
            copyArrTwoDimension(sourceArr[i],wantToCopyArr[i]);
        }
        return wantToCopyArr;
    }
    /**
     * 数组交换元素
     * @param arr 要交换元素的数组地址
     * @param index1 下标一
     * @param index2 下标二
     * @param <T> 泛型数组
     */
    public static <T> void swap(T[] arr,int index1,int index2){
        T temp=arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=temp;
    }
    /**
     * 倒置算法
     * @param object 具体的对象数组
     * @param <T> 对象类型
     * @return 反转后的数组地址
     */
    private static <T> T[] inverseArr(T[] object){
        int left=0,right=object.length-1;
        while(left<right){
            T temp = object[left];
            object[left]=object[right];
            object[right]=temp;
            left++;
            right--;
        }
        return object;
    }
}
