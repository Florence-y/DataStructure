package task;

import implDataStruce.FlorenceStack;
import implDataStruce.Node;

import java.io.*;
import java.util.*;


/**
 * @author Florence
 */
public class Week6 {

    static Map<Integer, Boolean> isUse = new HashMap<>();
    static int count = 0;
    static BufferedWriter writer;
    static BufferedReader reader;

    static {
        try {
            writer = getOutFromFile(System.getProperty("user.dir") + "\\file", "AllPermutation.txt");
            reader = getInFromFile(System.getProperty("user.dir") + "\\file", "AllPermutation.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        josephRollExtend(getCircleRoll(100),100);
//        generatePermutation(5);
//        isMatch("{{{{{{{{}}}}}}[][][][][]]");
//          judgeAllPermutationIsFix(5);
//        System.out.println(isPalindromeByStack("aacscana"));
        long calculator = calculator("(3-2)+5+(4*3)");
        System.out.println(calculator);

    }

    /**
     * 展示我们的二维数组
     *
     * @param arr 展示的二维数组
     */
    public static void showTwoDimensionArr(char[][] arr) {
        for (char[] subArr : arr) {
            System.out.println(Arrays.toString(subArr));
        }
        System.out.println("**************************************************");
    }

    private static BufferedReader getInFromFile(String filePath, String fileName) throws FileNotFoundException {
        File file = new File(filePath + "\\" + fileName);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return null;
        }
        return new BufferedReader(new FileReader(file));
    }

    public static BufferedWriter getOutFromFile(String filePath, String fileName) throws IOException {
        File dictionary = new File(filePath);
        if (!dictionary.exists()) {
            dictionary.mkdirs();
        }
        File file = new File(filePath + "\\" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return new BufferedWriter(new FileWriter(file));
    }

    private static Node<Integer> getCircleRoll(int length) {
        Node<Integer> node = new Node(1);
        Node<Integer> first = node;
        for (int i = 2; i <= length; i++) {
            node.next = new Node(i);
            node = node.next;
        }
        node.next = first;
        return first;
    }


    /**
     * 约瑟夫环（增强版）
     *
     * @param circleRoll 循环链表
     * @param length     循环链表一开始的长度
     * @param <T>        具体的data的数据的类型
     */
    public static <T> void josephRollExtend(Node<T> circleRoll, int length) {
        int nodeValue = length;
        int curSize = length;
        Node<T> tempNode = circleRoll;
        while (curSize >= length / 3) {
            //用于计算是否遍历一遍
            int count = curSize;
            //遍历一遍（叫一些人出去）
            System.out.println("开始派人出征：");
            while (count != 0) {
                //是否死亡
                if (count % 3 == 0 && isDead()) {
                    //删除当前节点
                    System.out.println(tempNode.data + "出征");
                    deleteSelfNode(tempNode);
                    curSize--;
                } else {
                    //跳转到下一个节点
                    tempNode = tempNode.next;
                }
                //每次的计数都减少
                count--;
            }
            //每次循环完添加3到5个
            System.out.println("有另外的生存者回来：");
            for (int i = 0; i < getRandomNum(3, 5); i++) {
                insertNode(tempNode, new Node(nodeValue++));
                curSize++;
                System.out.println(nodeValue + "到来");
            }
        }
        //输出或者的人
        System.out.println("生存者：");
        for (int i = 0; i < curSize; i++) {
            System.out.print(tempNode.data + "号, ");
            tempNode = tempNode.next;
        }
    }

    private static <T> Node<T> deleteSelfNode(Node<T> node) {
        node.data = node.next.data;
        node.next = node.next.next;
        return node;
    }

    public static <T> void insertNode(Node<T> fore, Node<T> wanToInsert) {
        wanToInsert.next = fore.next;
        fore.next = wanToInsert;
    }

    private static boolean isDead() {
        return Math.random() <= 0.8;
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

    /**
     * 全排列（类链表形式）
     *
     * @param length 操作的长度
     */
    public static void permutationByList(int length) {
        Integer[] integerArr = getIntegerArr(length);
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(integerArr));
        for (Integer integer : list) {
            dfs(integer + "", getListExpectN(list, integer));
        }
    }

    private static void dfs(String str, LinkedList<Integer> list) {
        if (list.size() == 0) {
            System.out.println(str);
            return;
        }
        for (Integer integer : list) {
            dfs(str + "->" + integer, getListExpectN(list, integer));
        }
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

    /**
     * 100个节点，倒数第一插入到第一个节点后面，倒数第二插入到第三个的后面，倒数第三插入到插入到第六个
     *
     * @param node 要操作的链表
     * @param <T>  具体的data域
     */
    public static <T> void listManipulate(Node<T> node, int size) {
        Node<T> head = node;
        node = node.next;
        Node<T> inverseList = newInverseList(node).next;
        int left = 1;
        int right = size;
        int count = 2;
        while (left < right) {
            insertNode(node, new Node<>(inverseList.data));
            count++;
            node = jumpStep(node, count);
            inverseList = inverseList.next;
            right--;
            left += (count - 1);
            count++;
        }
        head = cutSize(head, size);
        showListBeforeRecursion(head);
    }

    /**
     * 括号匹配
     *
     * @param str 括号字符串
     * @return 是否匹配
     */
    public static boolean isMatch(String str) {
        char[] chars = str.toCharArray();
        FlorenceStack<Character> stack = new FlorenceStack<>();
        try {
            for (char c : chars) {
                if ('{' == c || '(' == c || '[' == c) {
                    stack.push(c);
                } else if ('}' == c) {
                    if (stack.top() != '{') {
                        return false;
                    }
                    stack.pop();
                } else if (')' == c) {
                    if (stack.top() != '(') {
                        return false;
                    }
                    stack.pop();
                } else if (']' == c) {
                    if (stack.top() != '[') {
                        return false;
                    }
                    stack.pop();
                }
            }
        } catch (Exception e) {
            return false;
        }
        return stack.isEmpty();
    }

    /**
     * 全排列，栈模拟
     *
     * @param length 长度
     */
    public static void generatePermutation(int length) throws IOException {
        FlorenceStack<Integer> stack = new FlorenceStack<>();
        for (int i = 1; i <= length; i++) {
            stack.push(i);
            isUse.put(i, true);
            recursionHelp(stack, length);
            isUse.remove(i);
            stack.pop();
        }
        writer.close();
        System.out.println("总共" + count + "种排列");
    }

    /**
     * 辅助的递归函数
     *
     * @param stack  我们模拟全排列的栈
     * @param length 排列的长度
     * @throws IOException 操作异常
     */
    private static void recursionHelp(FlorenceStack<Integer> stack, int length) throws IOException {
        if (stack.size() == length) {
            String s = showStack(stack);
            writer.write(s);
            writer.newLine();
            count++;
            return;
        }
        for (int i = 1; i <= length; i++) {
            if (isUse.get(i) == null) {
                stack.push(i);
                isUse.put(i, true);
                recursionHelp(stack, length);
                stack.pop();
                isUse.remove(i);
            }
        }
    }

    /**
     * @param template 模板串
     * @param pattern  匹配串
     * @return 是否可以从栈中来
     */
    private static String isFromStackPopAndPush(String template, String pattern) {
        int count = 1;
        StringBuilder operations = new StringBuilder();
        FlorenceStack<Character> stack = new FlorenceStack<>();
        int length = template.length();
        int tempPointer = 0;
        int patPointer = 0;
        char[] templateArr = template.toCharArray();
        char[] patternArr = pattern.toCharArray();
        while (tempPointer < length) {
            if (templateArr[tempPointer] != patternArr[patPointer]) {
                stack.push(templateArr[tempPointer]);
                operations.append(count++).append("、push (").append(templateArr[tempPointer]).append(") ");
            } else {
                operations.append(count++).append("、push (").append(templateArr[tempPointer]).append(") ").append(count++).append("、pop (").append(templateArr[tempPointer]).append(") ");
                patPointer++;
                while (!stack.isEmpty() && stack.top() == patternArr[patPointer]) {
                    Character pop = stack.pop();
                    operations.append(count++).append("、pop (").append(pop).append(") ");
                    patPointer++;
                }
            }
            tempPointer++;
        }
        boolean res = patPointer == length && stack.isEmpty();
        if (res) {
            return "{" + pattern + "}: " + operations.toString();
        } else {
            return "{" + pattern + "}: 改序列不能被模式串以栈的形式生成";
        }
    }

    /**
     * 判断全排列是否可以用栈的操作实现
     *
     * @param length 长度
     * @throws IOException
     */
    private static void judgeAllPermutationIsFix(int length) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String template = getTemplate(length);
        String temp;
        while ((temp = reader.readLine()) != null) {
            list.add(temp);
        }
        reader.close();
        writer = getOutFromFile(System.getProperty("user.dir") + "\\file", "fixPermutation.txt");
        for (String str : list) {
            String res = isFromStackPopAndPush(template, str);
            writer.write(res);
            writer.newLine();
        }
        writer.close();
    }

    /**
     * doublePointer
     *
     * @param str 等待判断的字符
     * @return 是否为回文串
     */
    public static boolean isPalindromeByPointer(String str) {
        char[] toCharArray = str.toCharArray();
        int left = 0, right = toCharArray.length - 1;
        while (left < right) {
            if (toCharArray[left] != toCharArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * stack
     *
     * @param str 给我们判断的回文串
     * @return 是否为字符串
     */
    public static boolean isPalindromeByStack(String str) {
        int i;
        FlorenceStack<Character> stack = new FlorenceStack<>();
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        int mid = length / 2;
        for (i = 0; i < mid; i++) {
            stack.push(toCharArray[i]);
        }
        if (length % 2 != 0) {
            i++;
        }
        //比较异常（基本类型没有办法跟null比较）
        while (!stack.isEmpty() && i < length - 1) {
            if (stack.pop() != toCharArray[i]) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static long calculator(String wantToCalculateStr) throws Exception {
        FlorenceStack<Integer> valStack = new FlorenceStack<>();
        FlorenceStack<Character> operateStack = new FlorenceStack<>();
        int temp = 0;
        wantToCalculateStr += '#';
        operateStack.push('#');
        char[] toCharArray = wantToCalculateStr.toCharArray();
        char[][] advantageExp = getAdvantageExpArr();
        boolean flag=true;
        for (Character c : toCharArray) {
            if (Character.isDigit(c)) {
                temp = temp * 10 + Integer.parseInt(String.valueOf(c));
            } else {
                if (!c.equals('(')&&flag) {
                    valStack.push(temp);
                }
                flag=true;
                temp = 0;
                Character top = operateStack.top();
                Character advantage = advantageExp[top][c];
                if (advantage.equals('<')) {
                    operateStack.push(c);
                } else if (advantage.equals('>')) {
                    do {
                        int afterNum = valStack.pop();
                        int beforeNum = valStack.pop();
                        char operate = operateStack.pop();
                        valStack.push(calculate(beforeNum, afterNum, operate));
                        advantage = advantageExp[operateStack.top()][c];
                    }
                    while (c != ')' && advantage.equals('>'));
                    if (!c.equals(')')) {
                        operateStack.push(c);
                    } else {
                        operateStack.pop();
                        flag=false;
                    }
                } else if (advantage.equals('=')) {
                    operateStack.pop();
                } else {
                    throw new Exception("表达式异常");
                }
            }
        }
        return valStack.pop();
    }

    private static int calculate(int beforeNum, int afterNum, char operate) throws Exception {
        if (operate == '+') {
            return beforeNum + afterNum;
        } else if (operate == '-') {
            return beforeNum - afterNum;
        } else if (operate == '*') {
            return beforeNum * afterNum;
        } else if (operate == '/') {
            return beforeNum / afterNum;
        } else {
            throw new Exception("运算符异常");
        }
    }

    private static char[][] getAdvantageExpArr() {
        char[][] chars = new char[128][128];
        char[] operate = new char[]{'+', '-', '*', '/', '(', ')', '#'};
        // + -
        for (int i = 0; i < 2; i++) {
            chars[operate[i]]['+'] = '>';
            chars[operate[i]]['-'] = '>';
            chars[operate[i]]['*'] = '<';
            chars[operate[i]]['/'] = '<';
            chars[operate[i]]['('] = '<';
            chars[operate[i]][')'] = '>';
            chars[operate[i]]['#'] = '>';
        }
        // * /
        for (int i = 2; i < 4; i++) {
            chars[operate[i]]['+'] = '>';
            chars[operate[i]]['-'] = '>';
            chars[operate[i]]['*'] = '>';
            chars[operate[i]]['/'] = '>';
            chars[operate[i]]['('] = '<';
            chars[operate[i]][')'] = '>';
            chars[operate[i]]['#'] = '>';
        }
        //'('
        chars[operate[4]]['+'] = '<';
        chars[operate[4]]['-'] = '<';
        chars[operate[4]]['*'] = '<';
        chars[operate[4]]['/'] = '<';
        chars[operate[4]]['('] = '<';
        chars[operate[4]][')'] = '=';
        chars[operate[4]]['#'] = 'e';
        //')'
        chars[operate[5]]['+'] = '>';
        chars[operate[5]]['-'] = '>';
        chars[operate[5]]['*'] = '>';
        chars[operate[5]]['/'] = '>';
        chars[operate[5]]['('] = '=';
        chars[operate[5]][')'] = '>';
        chars[operate[5]]['#'] = '>';
        //'#'
        chars[operate[6]]['+'] = '<';
        chars[operate[6]]['-'] = '<';
        chars[operate[6]]['*'] = '<';
        chars[operate[6]]['/'] = '<';
        chars[operate[6]]['('] = '<';
        chars[operate[6]][')'] = 'e';
        chars[operate[6]]['#'] = '=';
        return chars;
    }


    private static String getTemplate(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    private static <T> String showStack(FlorenceStack<T> stack) {
        StringBuilder stringBuilder = new StringBuilder();
        for (T data : stack) {
            stringBuilder.append(data);
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * 剪切size大小的数组
     *
     * @param head 头节点
     * @param size 大小
     * @param <T>  泛型
     * @return 返回切割后的头节点
     */
    private static <T> Node<T> cutSize(Node<T> head, int size) {
        Node<T> node = new Node<>();
        head = head.next;
        Node<T> newHead = node;
        int count = 0;
        while (count < size && head != null) {
            node.next = new Node<>(head.data);
            node = node.next;
            head = head.next;
            count++;
        }
        return newHead;
    }

    /**
     * 根据步数跳获取这个节点下一个节点
     *
     * @param list  当前节点
     * @param count 跳的步数
     * @param <T>   data域
     * @return 具体node
     */
    private static <T> Node<T> jumpStep(Node<T> list, int count) {
        for (int i = 0; i < count && list != null; i++) {
            list = list.next;
        }
        return list;
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


    public static <T> Node<T> newInverseList(Node<T> head) {
        Node<T> newHead = new Node<>();
        head = head.next;
        while (head != null) {
            Node<T> tempNode = new Node<>(head.data);
            tempNode.next = newHead.next;
            newHead.next = tempNode;
            head = head.next;
        }
        return newHead;
    }


    /**
     * 获取链表
     * 尾插法
     *
     * @param <T>  链表含有的参数
     * @param data 数据
     * @return 返回头节点
     */
    public static <T> Node getRightOrderList(T[] data) {
        Node<T> head = new Node<>();
        Node<T> nowNode = head;
        int count = 0;
        while (count < data.length) {
            Node<T> newNode = new Node<>(data[count]);
            nowNode.next = newNode;
            nowNode = newNode;
            count++;
        }
        return head;
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
}
