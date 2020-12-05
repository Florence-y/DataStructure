package task;

import implDataStruce.FlorenceQueue;
import implDataStruce.FlorenceStack;
import implDataStruce.TreeNode;

import java.util.*;

/**
 * @author Florence
 */
public class Week7 {
    private static final boolean WASTE_ZOOM_WAY = true;
    private static final boolean HARD_GET_SON_WAY = false;
    private static ArrayList<Double> poissonDistribute = new ArrayList<>();
    private static ArrayList<Double> exponentDistribute = new ArrayList<>();
    private static int MAX_POSSIBLE = 1000;

    static {
        for (int i = 0; i < MAX_POSSIBLE; i++) {
            poissonDistribute.add(getPoissonNum(i));
            exponentDistribute.add(getExponent(i));
        }
    }

    public static void main(String[] args) throws Exception {
//        cashierDeskEmulation();
//        Map<String, String> map = recognizeTheCLanguageCode("#include<stdio.h>\n" +
//                "int main()\n" +
//                "{\n" +
//                "    int a,b;\n" +
//                "    int c;\n" +
//                "    c=a+b;\n" +
//                "    if (a>0)\n" +
//                "    {\n" +
//                "    }\n" +
//                "    else\n" +
//                "    {\n" +
//                "    }\n" +
//                "    return 0;\n" +
//                "}\n" +
//                "\n");
//        for (Map.Entry<String,String> entry:map.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
//        TreeNodeLessZoom<Object>[] treeNodeLessZoomArr = getTreeNodeArr(10);
//        List<Integer> sonNodeByClass = getSonNodeByClass(treeNodeLessZoomArr[1], treeNodeLessZoomArr);
//        System.out.println(sonNodeByClass);
//        System.out.println(Arrays.toString(buildBinaryTree(new Integer[]{2,3,4,5,7,9,10,6,8,11})));
//        List<Integer> list = new LinkedList<>();
//        for (int i=0;i<10;i++){
//            list.add(1);
//            list.add(2);
//            list.remove(0);
//            list.remove(1);
//        }
        showTree(buildBinaryTree(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8}));
    }

    /**
     * 将乱序利用两个栈回复成顺序
     *
     * @param disOrderStr 乱序字符串
     * @return 恢复完的字符串
     * @throws Exception 字符串异常
     */
    public static String recoverOrder(String disOrderStr) throws Exception {
        if (disOrderStr.trim().isEmpty()) {
            throw new Exception("参数异常");
        }
        char[] toCharArray = disOrderStr.toCharArray();
        FlorenceStack<Character> ascStack = new FlorenceStack<>();
        FlorenceStack<Character> descStack = new FlorenceStack<>();
        for (char c : toCharArray) {
            //一开始或者符合顺序直接入栈
            if (ascStack.size() == 0 || c < ascStack.top()) {
                ascStack.push(c);
            } else {
                //找到那个字符应在在的位置然后入栈
                while (!ascStack.isEmpty() && c > ascStack.top()) {
                    descStack.push(ascStack.pop());
                }
                ascStack.push(c);
                //将那些之前出栈的元素入栈
                while (!descStack.isEmpty()) {
                    ascStack.push(descStack.pop());
                }
            }
        }
        return ascStack.toString();
    }

    /**
     * 收银台仿真
     *
     * @throws Exception
     */
    public static void cashierDeskEmulation() throws Exception {
        FlorenceQueue<Consumer> queue = new FlorenceQueue<>();
        long consumerNumber = 1;
        do {
            //泊松分布生成概率*当前的x（也就是单位时间来x人乘于泊松分布的概率当作单位时间来的人）
            long poissonNum = getNewConsumerCount();
            consumerNumber = addNewConsumer(queue, poissonNum, consumerNumber);
            if (!queue.isEmpty()) {
                //获取正在服务的人
                Consumer front = queue.front();
                //如果服务时间已经为0，出队列;
                if (front.getRemindTime() == 0) {
                    System.out.println(queue.deQueue());
                }
                front.setRemindTime(front.getRemindTime() - 1);
            }
        } while (!queue.isEmpty() && queue.size() <= 200);
    }

    public static Map<String, String> recognizeTheCLanguageCode(String CLanguageCode) {
        Map<String, String> resMap = new HashMap<>(20);
        List<String[]> list = new ArrayList<>();
        for (String str : CLanguageCode.split("\n")) {
            list.add(str.trim().split(" "));
        }
        List<String> cDataTypeKeyWordList = Arrays.asList("char", "int", "float", "double", "short", "long", "signed", "unsigned");
        List<String> cOperateKeyWordList = Arrays.asList("return", "continue", "break", "goto", "if", "else", "switch", "case", "default", "for", "do", "while");
        for (String[] strings : list) {
            String temp = strings[0];
            for (String s : cDataTypeKeyWordList) {
                if (temp.equals(s)) {
                    for (int i = 1; i < strings.length; i++) {
                        for (String str : strings[i].split(",")) {
                            if (!" ".equals(str)) {
                                resMap.put(str.replace(";", ""), "变量名");
                            }
                        }
                    }
                    break;
                }
            }
            for (String s : strings) {
                for (String operate : cOperateKeyWordList) {
                    if (s.contains(operate)) {
                        resMap.put(operate, "操作符");
                    }
                }
            }
        }
        return resMap;
    }

    public static int getParentNodeByArrWay(int curNodeIndex) {
        return curNodeIndex / 2;
    }

    public static int getSonNodeByArrWay(int curNodeIndex, String leftOrRight) {
        int gap = "left".equals(leftOrRight) ? 0 : 1;
        return curNodeIndex * 2 + 1;
    }

    public static <T> int getParentNodeByClass(TreeNodeLessZoom<T> node) {
        return node.parent;
    }

    /**
     * 用节省空间的方式来存储树，然后要获取他的子节点
     *
     * @param node
     * @param treeNodeLessZooms
     * @param <T>
     * @return
     */
    public static <T> List<Integer> getSonNodeByClass(TreeNodeLessZoom<T> node, TreeNodeLessZoom[] treeNodeLessZooms) {
        int selfIndex = -1;
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < treeNodeLessZooms.length; i++) {
            if (node == treeNodeLessZooms[i]) {
                selfIndex = i;
                break;
            }
        }
        for (int i = 1; i < treeNodeLessZooms.length; i++) {
            if (selfIndex == treeNodeLessZooms[i].parent) {
                list.add(i);
            }
        }
        return list;
    }

    public static <T> TreeNode<T>[] buildBinaryTree(T[] wantToBuildArr) throws Exception {
        LinkedList<TreeNode<T>> list = new LinkedList<>();
        if (wantToBuildArr == null) {
            System.out.println("Arr is null");
            return null;
        }
        if (wantToBuildArr.length == 0) {
            System.out.println("length is 0");
            return null;
        }
        FlorenceQueue<T> florenceQueue = new FlorenceQueue<>();
        florenceQueue.enQueue(wantToBuildArr[0]);
        int count = 1;
        while (!florenceQueue.isEmpty()) {
            //根据循环添加某一层的元素，记得最后一层可能不是满的
            for (int i = 0; i < count && !florenceQueue.isEmpty(); i++) {
                list.add(new TreeNode<>(florenceQueue.deQueue()));
            }
            for (int i = count; i < count * 2 && i < wantToBuildArr.length; i++) {
                florenceQueue.enQueue(wantToBuildArr[i]);
            }
            count *= 2;
        }
        return getResultBinaryTreeArr(list);
    }

    private static <T> TreeNode<T>[] getResultBinaryTreeArr(LinkedList<TreeNode<T>> list) {
        TreeNode<T>[] treeNodes = new TreeNode[list.size() + 1];
        for (int i = 1; i < treeNodes.length; i++) {
            treeNodes[i] = list.get(i - 1);
        }
        return treeNodes;
    }

    public static <T> void showTree(TreeNode<T>[] treeNodes) {
        int length = treeNodes.length - 1;
        int rowCount = (int) (isPowerOfTwo(length) ? log2(length) : Math.ceil(log2(length)));
        int bottomLength = (int) Math.pow(2, rowCount - 1);
        FlorenceQueue<TreeNode<T>> queue = new FlorenceQueue<>();
        queue.enQueue(treeNodes[1]);
        int count = 1;
        while (!queue.isEmpty()) {
            String blankAndData = getBlank(bottomLength / 2 - ((count + count - 1) / 2));
            for (int i = 0; i < count && !queue.isEmpty(); i++) {
                blankAndData += queue.deQueue().getData() + " ";
                int index = (count + i) * 2;
                //左节点入队列
                if (index <= length) {
                    queue.enQueue(treeNodes[index]);
                }
                //右节点入队列
                if (index + 1 <= length) {
                    queue.enQueue(treeNodes[index + 1]);
                }
            }
            //输出这一层的内容
            System.out.println(blankAndData);
            count *= 2;
        }
    }

    private static String getBlank(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public static double log2(double N) {
        //Math.log的底为e
        return Math.log(N) / Math.log(2);
    }

    /**
     * 根据泊松分布给的个数进行人数的添加
     *
     * @param queue   收银台队列
     * @param poisson 泊松分布的
     * @param y
     * @return
     */
    private static long addNewConsumer(FlorenceQueue<Consumer> queue, long poisson, long y) {
        for (int i = 1; i <= poisson; i++) {
            queue.enQueue(new Consumer(getConsumerStayTime(), (y + i) + "号顾客"));
        }
        return y + poisson;
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 根据泊松分布获取新来的消费者的数量
     *
     * @return
     */
    private static long getNewConsumerCount() {
        double random = Math.random();
        double sum = 0;
        for (int i = 0; i < poissonDistribute.size(); i++) {
            Double temp = poissonDistribute.get(i);
            if (random >= sum && random <= sum + temp) {
                return i;
            }
            sum += temp;
        }
        //一定要在前面的概率取到
        return -666666666;
    }

    /**
     * 根据指数分布获取的新消费者待的时间
     *
     * @return
     */
    private static int getConsumerStayTime() {
        double random = Math.random();
        double sum = 0;
        for (int i = 0; i < exponentDistribute.size(); i++) {
            Double temp = poissonDistribute.get(i);
            if (random > sum && random < sum + temp) {
                return i;
            }
            sum += temp;
        }
        //一定要在前面的概率取到
        return -666666666;
    }

    /**
     * 获取泊松分布函数
     *
     * @param k
     * @return
     */
    public static double getPoissonNum(long k) {
        return Math.pow(3, k) / getFactorial(k) * Math.pow(Math.E, -3);
    }

    /**
     * 指数分布
     *
     * @param k 参数k
     * @return
     */
    public static double getExponent(long k) {
        if (k <= 0) {
            return 0;
        }
        return (2 * Math.pow(Math.E, -2 * k));
    }

    /**
     * 获取阶乘
     *
     * @param n 要获取的阶乘数目
     * @return 得到的阶乘数
     */
    public static double getFactorial(long n) {
        double sum = 1;
        for (int i = 2; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }

    private static <T> TreeNodeLessZoom<T>[] getTreeNodeArr(int length) {
        TreeNodeLessZoom<T>[] treeNodeLessZooms = new TreeNodeLessZoom[length + 1];
        for (int i = 1; i <= length; i++) {
            treeNodeLessZooms[i] = new TreeNodeLessZoom<>(i / 2);
        }
        return treeNodeLessZooms;
    }

    static class Consumer {
        String consumerName;
        long remindTime = 0;
        long stayTime;

        Consumer(long stay, String name) {
            remindTime = stay;
            consumerName = name;
            stayTime = stay;
        }

        public long getRemindTime() {
            return remindTime;
        }

        public void setRemindTime(long remindTime) {
            this.remindTime = remindTime;
        }

        public String getName() {
            return consumerName;
        }

        public void setName(String name) {
            this.consumerName = name;
        }

        @Override
        public String toString() {
            return consumerName + "：服务时长" + stayTime + "小时";
        }
    }

    static class TreeNodeLessZoom<T> {
        T data;
        int parent = 0;

        TreeNodeLessZoom(int initParent) {
            parent = initParent;
        }
    }
}
