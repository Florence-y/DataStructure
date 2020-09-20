package task;

import java.util.Arrays;

/**
 * @author Florence
 *
 */
public class Week1 {
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        //do something
        int[] a = new int[]{10,9,8,7,6,5,7,3,2,1};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
        long endTime=System.currentTimeMillis();
        System.out.println("运行时间："+(endTime-startTime)+"ms");
    }
    public static void bubbleSort(int[] arrs) {
        for(int i=0;i<arrs.length;i++) {
            for(int j=0;j<arrs.length-i-1;j++) {
                if(arrs[j]>arrs[j+1]) {
                    swap(arrs,j,j+1);
                }
            }
        }
    }
    public static void swap(int[] sortArrays,int index1,int index2) {
        int temp=sortArrays[index1];
        sortArrays[index1]=sortArrays[index2];
        sortArrays[index2]=temp;
    }
    public static void baiqianbaiji(int n) {
        for(int i=0;i<=n/5;i++) {
            for(int j=0;j<=n/3;j++) {
                int remindNeedChicken=n-i-j;
                if(i+j+remindNeedChicken/3==100&&remindNeedChicken%3==0) {
                    //do something
                }
            }
        }
    }


    public static int fobonaqidp(int n) {
        int res =0;
        int first=1,second=1;
        for(int i=0;i<n;i++) {
            res=first+second;
            second=res;
            first=second;
        }
        return res;
    }
    public static int fobonaqi(int n) {
        if(n==1||n==2) {
            return 1;
        }
        return fobonaqi(n-1)+fobonaqi(n-2);
    }


    public static int jiechengdp(int n) {
        int res=0;
        for(int i=1;i<=n;i++) {
            res*=i;
        }
        return res;
    }


    public static int jiecheng(int n) {
        if(n==1) {
            return n;
        }
        return n*jiecheng(n-1);
    }
}
