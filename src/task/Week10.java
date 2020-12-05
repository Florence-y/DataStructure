package task;

import implDataStruce.FlorenceQueue;
import implDataStruce.FlorenceStack;
import implDataStruce.HuffmanTreeNode;
import implDataStruce.TreeNode;

import java.util.*;

/**
 * @author Florence
 */
public class Week10 {
    public static void main(String[] args) {
//        TreeNode<Integer> treeNode1 = new TreeNode<>(1);
//        TreeNode<Integer> treeNode2 = new TreeNode<>(2);
//        TreeNode<Integer> treeNode3 = new TreeNode<>(3);
//        TreeNode<Integer> treeNode4 = new TreeNode<>(4);
//        TreeNode<Integer> treeNode5 = new TreeNode<>(5);
//        treeNode1.setLeft(treeNode2);
//        treeNode2.setLeft(treeNode3);
//        treeNode3.setLeft(treeNode4);
////        treeNode4.setLeft(treeNode5);
//        makeUnSymmetryTreeToSymmetryByAddNode(treeNode1);
//        System.out.println("****************************");
////        TreeNode<Integer> inverseZigZag = inverseZigZagTree(treeNode1);
//        System.out.println("****************************");
        selectSort(new Integer[]{3, 2, 5, 4, 6, 7});
    }

    /**
     * 反转二叉树  递归的方式首先是交换当前节点的左右子树，然后当前节点的左右子树也要反转
     *
     * @param treeNode
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> inverseBinaryTree(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return null;
        }
        //下面三句为交换当前的左右节点
        TreeNode<T> temp = treeNode.getLeft();
        treeNode.setLeft(treeNode.getRight());
        treeNode.setRight(temp);
        //反转左子树
        inverseBinaryTree(treeNode.getLeft());
        //反转右子树
        inverseBinaryTree(treeNode.getRight());
        return treeNode;
    }

    /**
     * 方向 true为left，false为right
     *
     * @param treeNode 根节点
     * @param <T>      data类型
     * @return
     */
    public static <T> TreeNode<T> inverseZigZagTree(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return null;
        }
        boolean firstDirection = treeNode.getLeft() != null;
        FlorenceStack<TreeNode<T>> stack = new FlorenceStack<>();
        TreeNode<T> curNode = treeNode;
        int length = 0;
        //先全部压进去
        while (curNode != null) {
            if (curNode.getLeft() == null && curNode.getRight() == null) {
                stack.push(curNode);
                break;
            }
            //当前节点不为空，则将当前节点压入栈中
            stack.push(new TreeNode<>(curNode.getData()));
            if (curNode.getLeft() != null) {
                curNode = curNode.getLeft();
            } else if (curNode.getRight() != null) {
                curNode = curNode.getRight();
            }
            length++;
        }
        //用来判断反转节点的第一个子节点是左子树还是右子树
        boolean flag = (length % 2 == 0) != firstDirection;
        TreeNode newRootTreeNode = new TreeNode(stack.pop().getData());
        curNode = newRootTreeNode;
        //弹出反转zigzag树
        while (!stack.isEmpty()) {
            T data = stack.pop().getData();
            TreeNode<T> temp = new TreeNode<>(data);
            //子树为左节点
            if (flag) {
                curNode.setLeft(temp);
            }
            //子树为右节点
            else {
                curNode.setRight(temp);
            }
            //反转方向
            curNode = temp;
            flag = !flag;
        }
        //返回结果
        return newRootTreeNode;
    }

    /**
     * 将不对称的二叉树转化为对称的二叉树
     *
     * @param treeNode 树的根节点
     * @param <T>      data域类型
     */
    public static <T> void makeUnSymmetryTreeToSymmetryByAddNode(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }
        ArrayList<TreeNode<T>> list = new ArrayList<>();
        list.add(null);
        list.add(treeNode);
        int maxNum = 1;
        while (!list.isEmpty()) {
            if (list.get(1) == null) {
                break;
            }
            int left = 1, right = maxNum;
            //下一层的最大上界与最小下界
            int nextLeft = 1, nextRight = maxNum * 2;
            ArrayList<TreeNode<T>> newList = new ArrayList<>();
            //由于arraylist的限制，无法在没有元素的时候生命为空，只能采取这种蠢办法
            for (int i = 0; i <= maxNum * 2; i++) {
                newList.add(null);
            }
            //两边分别遍历(压节点的时候记得先进左，在进右)
            while (left <= right) {
                TreeNode<T> leftNode = list.get(left);
                TreeNode<T> rightNode = list.get(right);
                if (leftNode == null || rightNode == null) {
                    break;
                }
                //如果左右都不为空，两边同时入队
                if (leftNode.getLeft() != null && rightNode.getRight() != null) {
                    newList.remove(nextLeft);
                    newList.add(nextLeft++, leftNode.getLeft());
                    newList.remove(nextRight);
                    newList.add(nextRight--, rightNode.getRight());
                }
                if (leftNode.getRight() != null && rightNode.getLeft() != null) {
                    newList.remove(nextLeft);
                    newList.add(nextLeft++, leftNode.getRight());
                    newList.remove(nextRight);
                    newList.add(nextRight--, rightNode.getLeft());
                }
                //如果左边的节点的左子树为空，但是右边的不为空，则加入
                if (leftNode.getLeft() == null && rightNode.getRight() != null) {
                    TreeNode<T> leftSubLeftNode = new TreeNode<>(null);
                    leftNode.setLeft(leftSubLeftNode);
                    //添加到最左边和最右边
                    newList.remove(nextLeft);
                    newList.add(nextLeft++, leftSubLeftNode);
                    newList.remove(nextRight);
                    newList.add(nextRight--, rightNode.getRight());
                }
                //跟上面的情况相反的
                else if (leftNode.getLeft() != null && rightNode.getRight() == null) {
                    TreeNode<T> rightSubRightNode = new TreeNode<>(null);
                    rightNode.setRight(rightSubRightNode);
                    //同上
                    newList.remove(nextRight);
                    newList.add(nextRight--, rightSubRightNode);
                    newList.remove(nextLeft);
                    newList.add(nextLeft++, leftNode.getLeft());
                }
                //记住这里跟上面前往不能用if else连起来，因为这已经是两个不同的类型的，他们并不是互斥发生的
                //判断左子树的右子树和右子树的左子树
                if (leftNode.getRight() == null && rightNode.getLeft() != null) {
                    TreeNode<T> leftSubRightNode = new TreeNode<>(null);
                    leftNode.setRight(leftSubRightNode);
                    //添加到里面一点
                    newList.remove(nextLeft);
                    newList.add(nextLeft++, leftSubRightNode);
                    newList.remove(nextRight);
                    newList.add(nextRight--, rightNode.getLeft());
                } else if (leftNode.getRight() != null && rightNode.getLeft() == null) {
                    TreeNode<T> rightSubLeftNode = new TreeNode<>(null);
                    rightNode.setLeft(rightSubLeftNode);
                    newList.remove(nextLeft);
                    newList.add(nextLeft++, leftNode.getRight());
                    newList.remove(nextRight);
                    newList.add(nextRight--, rightSubLeftNode);
                }
                //另外一种情况就是两边都为空了，不进队，也就是不处理
                left++;
                right--;
                maxNum *= 2;
            }
            list = newList;
        }
    }

    /**
     * 将二叉树的叶子节点从左到右连起来
     *
     * @param root 根节点
     * @param <T>  data域类型
     */
    public static <T> void linkLeafNode(BTreeNode<T> root) {
        FlorenceQueue<BTreeNode<T>> leafNodes = new FlorenceQueue<>();
        //从左到右获取所有叶子节点
        foreOrderFindLeafNode(leafNodes, root);
        //获取当前第一个子节点
        BTreeNode<T> cur = leafNodes.deQueue();
        //如果队列不为空
        while (!leafNodes.isEmpty()) {
            //先将队列的元素出队一个
            BTreeNode<T> temp = leafNodes.deQueue();
            //将当前指针的下一个叶子节点设置为temp
            cur.setLink(temp);
            //滚动迭代
            cur = temp;
        }
    }

    /**
     * 利用前序遍历获取所有叶子节点
     *
     * @param leafNodes 给的队列
     * @param root      根节点
     * @param <T>       data类型
     */
    private static <T> void foreOrderFindLeafNode(FlorenceQueue<BTreeNode<T>> leafNodes, BTreeNode<T> root) {
        if (root == null) {
            return;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            leafNodes.enQueue(root);
        }
        foreOrderFindLeafNode(leafNodes, root.getLeft());
        foreOrderFindLeafNode(leafNodes, root.getRight());
    }

    /**
     * 构造哈夫曼树
     *
     * @param weights
     * @param <T>
     */
    private static <T> HuffmanTreeNode initHuffManTree(ArrayList<Integer> weights) {
        HuffmanTreeNode root = new HuffmanTreeNode(Integer.MAX_VALUE);
        Map<Integer, HuffmanTreeNode> mapTree = new HashMap<>(weights.size());
        //初始化每一个权值代表的树（一开始都是一个节点）
        for (Integer weight : weights) {
            HuffmanTreeNode huffmanTreeNode = new HuffmanTreeNode(weight);
            huffmanTreeNode.setRoot(root);
            mapTree.put(weight, huffmanTreeNode);
        }
        int size = weights.size();
        //长度是多少，必定是长度-1次循环，因为每次减一个数，（但是我不要到最后一次，因为我想获取他的根节点，如果放在循环里面代码会要特判，所以我减少一次，让）
        for (int i = 0; i < size - 1; i++) {
            //先排序，为了获取最小的两个值
            Collections.sort(weights);
            //获取当前两个的最小权值
            int valueLeft = weights.get(0);
            //获取当前两个的最小权值
            int valueRight = weights.get(1);
            //获得新权值
            int newValue = valueLeft + valueRight;
            //获取当前这个权值代表的左子树
            HuffmanTreeNode huffmanTreeNodeLeft = mapTree.get(valueLeft);
            //获取当前这个权值代表的右子树
            HuffmanTreeNode huffmanTreeNodeRight = mapTree.get(valueRight);
            //构建一个新的子树
            HuffmanTreeNode newHuffManTreeNodeRoot = new HuffmanTreeNode(newValue);
            //设置他的左子树与右子树(左小右大)
            newHuffManTreeNodeRoot.setLeft(huffmanTreeNodeLeft);
            newHuffManTreeNodeRoot.setRight(huffmanTreeNodeRight);
            //加入map中和list中
            mapTree.put(newValue, newHuffManTreeNodeRoot);
            weights.add(newValue);
            //删除一些之前的垃圾数据
            weights.remove(0);
            weights.remove(1);
            mapTree.remove(valueLeft);
            mapTree.remove(valueRight);
        }
        Collections.sort(weights);
        //设置根节点的左子树为比较小的
        root.setLeft(mapTree.get(weights.get(0)));
        root.setRight(mapTree.get(weights.get(1)));
        return root;
    }

    private static Map<String, String> encodeByHuffManTree(Map<String, Integer> keyAndWeight) {
        Map<String, String> res = new HashMap<>(keyAndWeight.size());
        //反转获得value-key的map
        Map<Integer, String> integerStringMap = inverseMap(keyAndWeight);
        //所有的权值
        Collection<Integer> values = keyAndWeight.values();
        //获取权值的list
        ArrayList<Integer> weights = new ArrayList<>(values);
        //然后进行建立HuffManTree
        HuffmanTreeNode root = initHuffManTree(weights);
        //前序遍历获取编码
        foreOrderGetCode(integerStringMap, res, "", root);
        return res;
    }

    private static void foreOrderGetCode(Map<Integer, String> findKeyByValueMap, Map<String, String> res, String s, HuffmanTreeNode root) {
        if (root == null) {
            return;
        }
        //如果是叶子节点，将字符的编码添加到map中
        if (root.getLeft() == null && root.getRight() == null) {
            String beEnCodedWord = findKeyByValueMap.get(root.getData());
            res.put(beEnCodedWord, s);
            return;
        }
        //往左走
        foreOrderGetCode(findKeyByValueMap, res, s + "0", root.getLeft());
        //往右走
        foreOrderGetCode(findKeyByValueMap, res, s + "1", root.getRight());
    }

    /**
     * 反转map键值对(一定要是双射)
     *
     * @param keyAndValue 键值对
     * @param <K>         键具体类型
     * @param <V>         值具体类型
     * @return
     */
    private static <K, V> Map<V, K> inverseMap(Map<K, V> keyAndValue) {
        Map<V, K> resMap = new HashMap<>(keyAndValue.size());
        Set<Map.Entry<K, V>> entries = keyAndValue.entrySet();
        for (Map.Entry<K, V> entry : entries) {
            resMap.put(entry.getValue(), entry.getKey());
        }
        return resMap;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable> void insertSort(T[] arr) {
        int i, j;
        for (i = 1; i < arr.length; i++) {
            T temp = arr[i];
            //如果没找到，就将前面的一个元素往后走一个位置
            for (j = i - 1; j >= 0 && arr[j].compareTo(temp) > 0; j--) {
                arr[j + 1] = arr[j];
            }
            //找到后插入到相应的位置
            arr[j + 1] = temp;
        }
    }

    /**
     * 树 ，森林，二叉树的转换
     */

    /**
     * 选择排序
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable> void selectSort(T[] arr) {
        int index;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            //找到最小的值
            for (int j = i + 1; j < arr.length; j++) {
                //当前这个值大于遍历的值
                if (arr[index].compareTo(arr[j]) > 0) {
                    index = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    /**
     * B树节点内部类
     *
     * @param <T> data类型
     */
    static class BTreeNode<T> {
        BTreeNode<T> left = null;
        BTreeNode<T> right = null;
        BTreeNode<T> link = null;
        T data = null;

        BTreeNode(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public BTreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(BTreeNode<T> left) {
            this.left = left;
        }

        public BTreeNode<T> getRight() {
            return right;
        }

        public void setRight(BTreeNode<T> right) {
            this.right = right;
        }

        public BTreeNode<T> getLink() {
            return link;
        }

        public void setLink(BTreeNode<T> link) {
            this.link = link;
        }
    }
}
