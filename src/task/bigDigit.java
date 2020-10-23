package task;

import java.util.Arrays;
import java.util.Scanner;

import static until.FlorenceUntil.print;

/**
 * @author Florence
 */
public class bigDigit {
    public static void main(String[] args) {
        Scanner getData = getScanner();
        long startTime = System.currentTimeMillis();
        //do something
        String result = bigIntegerMultiply(getData.next(), getData.next());
        print(result);
        long endTime = System.currentTimeMillis();
        print("运行：" + (endTime - startTime) + "ms");
    }

    private static String bigIntegerMultiply(String num1, String num2) {
        //0*anything is 0
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        //get the reverse Int Arr from the String
        Integer[] numBit1 = inverseArr(charArrToNumArr(num1.toCharArray()));
        Integer[] numBit2 = inverseArr(charArrToNumArr(num2.toCharArray()));
        int[] resultArr = new int[numBit1.length + num2.length()];
        Arrays.fill(resultArr, 0);
        int carryBit = 0;
        int resultLength = resultArr.length;
        //key step
        for (int i = 0; i < numBit1.length; i++) {
            for (int j = 0; j < numBit2.length; j++) {
                resultArr[i + j] += numBit1[i] * numBit2[j];
            }
        }
        //carry bit
        for (int i = 0; i < resultLength; i++) {
            int nowNum = resultArr[i] + carryBit;
            resultArr[i] = nowNum % 10;
            carryBit = nowNum / 10;
        }
        //translate Int to String
        for (int i = 0; i < resultLength - 1; i++) {
            result.append(resultArr[i]);
        }
        //the special last step
        if (resultArr[resultLength - 1] != 0) {
            result.append(resultArr[resultArr.length - 1]);
        }
        //StringBulider to String
        return result.reverse().toString();
    }

    private static Integer[] charArrToNumArr(char[] chars) {
        Integer[] result = new Integer[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = (int) chars[i] - '0';
        }
        return result;
    }

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

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
