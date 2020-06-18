package com.test.it.algorithms;

/**
 * @Author: theonecai
 * @Date: Create in 2020/6/17 15:26
 * @Description:
 */
public class LinkedHeap<T extends Comparable<? super T>> {

    /**
     * 基于链表
     */
    private Node<T> head;

    /**
     * 尾节点
     */
    private Node<T> tail;
    /**
     * 当前元素个数
     */
    private int size;

    /**
     * 是否最大堆
     */
    boolean maxHeap;

    /**
     * 根据index查找时的路径
     */
    private int[] indexPath;

    public LinkedHeap() {
        this(true);
    }

    public LinkedHeap(boolean maxHeap) {
        this.maxHeap = maxHeap;
        this.indexPath = new int[32];
    }

    public void push(T data) {
        Node<T> node = new Node<>(data, size + 1);
        if (head == null) {
            head = node;
            tail = node;
            ++size;
            return;
        }

        Node<T> parent = findParentForPush();
        node.parent = parent;
        if (parent.left == null) {
            parent.left = node;
        } else if (parent.right == null) {
            parent.right = node;
        } else {
            throw new RuntimeException("父节点找错了");
        }

        ++size;
        tail = node;

        fixUp(node);
    }

    private Node<T> findParentForPush() {
        int parentIndex = (size + 1) / 2;
        return findByIndex(head, parentIndex);
    }

    private Node<T> findByIndex2(Node<T> node, int parentIndex) {
        if (node == null) {
            return null;
        }
        if (node.index == parentIndex) {
            return node;
        }
        if (node.index > parentIndex) {
            return null;
        }
        Node<T> n = findByIndex(node.left, parentIndex);
        if (n != null) {
            return n;
        }
        return findByIndex(node.right, parentIndex);
    }

    private Node<T> findByIndex(Node<T> node, int parentIndex) {
        if (node.index == parentIndex) {
            return node;
        }

        int i;
        int len = indexPath.length;
        int level = parentIndex >> 1;
        for (i =  0; i < len; i++) {
            indexPath[i] =level;
            if (indexPath[i] == 1) {
                break;
            }
            level >>= 1;
        }
        Node<T> current = node;
        for (int j = i; j >= 0; j--) {
            if (current.index == indexPath[j]) {
                if (current.left == null) {
                    return null;
                }
                if (j > 0 && current.left.index == indexPath[j - 1]) {
                    current = current.left;
                    continue;
                }
                if (j > 0 && current.right.index == indexPath[j - 1]) {
                    current = current.right;
                }
            } else {
                return null;
            }
        }
        if (current.index == parentIndex) {
            return current;
        }
        if (current.left != null && current.left.index == parentIndex) {
            return current.left;
        }
        if (current.right != null && current.right.index == parentIndex) {
            return current.right;
        }
        return current;
    }

    /**
     * 取出头的值返回，设置头的新值为尾节点的值，删除尾节点，设置tail, 然后fixDown(head)
     * @return
     */
    public T poll() {
        if (size == 0) {
            return null;
        }
        T val = head.val;
        if (size == 1) {
            --size;
            head = null;
            tail = null;
            return val;
        }

        head.val = tail.val;
        Node<T> tailNode = tail;
        Node<T> tailParent = tail.parent;
        tailNode.parent = null;
        tailNode.left = null;
        tailNode.right = null;
        --size;

        if (tailParent != null) {
            if (tailParent.right == tailNode) {
                tailParent.right = null;
                tail = tailParent.left;
            }
            if (tailParent.left == tailNode) {
                tailParent.left = null;
                tail = findByIndex(head, size);
            }
        }
        tailNode = null;

        fixDown(head);

        return val;
    }

    public void fixDown(Node<T> node) {
        if (node.left == null) {
            return;
        }
        Node<T> left, right, down;
        Node<T> current = node;
        while (current != null) {
            left = current.left;
            if (left == null) {
                break;
            }
            down = left;
            right = current.right;
            if (maxHeap) {
                if (right != null && compare(right.val, left.val) > 0) {
                    down = right;
                }
                if (compare(current.val, down.val) < 0) {
                    swap(current, down);
                } else {
                    break;
                }
            } else {
                if (right != null && compare(right.val, left.val) < 0) {
                    down = right;
                }
                if (compare(current.val, down.val) > 0) {
                    swap(current, down);
                }  else {
                    break;
                }
            }
            current = down;
        }
    }

    public void fixUp(Node<T> node) {
        Node<T> parent = node.parent;
        Node<T> current = node;
        while (parent != null) {
            if (maxHeap) {
                if (compare(current.val, parent.val) > 0) {
                    swap(current, parent);
                } else {
                    break;
                }
            } else {
                if (compare(current.val, parent.val) < 0) {
                    swap(current, parent);
                } else {
                    break;
                }
            }
            current = parent;
            parent = parent.parent;
        }
    }

    private int compare(Object left, Object right) {
        return ((Comparable)left).compareTo(right);
    }

    /**
     * 只交换值
     * @param node
     * @param node2
     */
    private void swap(Node<T> node, Node<T> node2) {
        T tmp = node.val;
        node.val = node2.val;
        node2.val = tmp;
    }

    static class Node<T> {
        private T val;
        private Node<T> left, right, parent;
        private int index;

        public Node(T val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    private static void printHeap(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        printHeap(head.left);
        printHeap(head.right);
    }

    public static void main(String[] args) {
        LinkedHeap<Integer> maxHeap = new LinkedHeap<>();
        int len = 1000000;
        int[] a = ArrayUtil.randArray(len);
        long start = System.currentTimeMillis();
        System.out.println("len = " + len);
        for (int i = 0; i < len; i++) {
            maxHeap.push(a[i]);
        }

//        printHeap(maxHeap.head);
//        System.out.println();

        int pre = Integer.MAX_VALUE;
        int current;
        for (int i = 0; i < len; i++) {
            current = maxHeap.poll();
            if (pre < current) {
                System.out.println("maxHeap: pre = " + pre + ", current = " + current);
                return;
            }
            pre = current;
        }

        LinkedHeap<Integer> minHeap = new LinkedHeap<>(false);
        for (int i = 0; i < len; i++) {
            minHeap.push(a[i]);
        }

//        printHeap(minHeap.head);
//        System.out.println();

        pre = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            current = minHeap.poll();
            if (pre > current) {
                System.out.println("minHeap: pre = " + pre + ", current = " + current);
                return;
            }
            pre = current;
        }
        System.out.println("done,costtime=" + (System.currentTimeMillis() - start));
    }
}
