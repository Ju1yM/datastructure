package com.className.demo;


import java.nio.BufferUnderflowException;
import java.util.Comparator;

//实现一个二叉排序树树的基本操作

//                               20
//                    12--------------------23
//              4------------15         22-------null
//                      13----------17
//                null-----14

//insert和remove方法写错了 导致一直只返回一个节点 错以为每次tree.root都在变化 实际上是没有变的
// 关键在于t.left=insert(x,t.left) 而不是t=insert(x,t.left)。

// 思路1.使得一个nullNode 将root节点指向nullNode 不会产生空指针异常  (暂时没用到)
// 2.while循环代替递归
// 3.lambda表达式的使用 在合适的地方使用泛型与lambda表达式对应起来
//4.递归的方法需要有一个特殊情况下 root==null时候的返回值 ，并且无特殊情况每次也需要一个返回值return t来跳出本次递归
public class BinarySearchTree<T extends Comparable<? super T>> {
    public static class BinaryNode<T> {
        public T element;
        public BinaryNode<T> left;
        public BinaryNode<T> right;

        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        @Override
        public String toString() {
            return "BinaryNode{" +
                    "element=" + element +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
        }
    }

    private BinaryNode<T> root;
    public BinaryNode<T> nullNode = new BinaryNode<>(null, null, null);

    public BinarySearchTree() {
        root = null;
    }


    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public boolean containsCompare(T x) {
        return containsCompare(x, root);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }

    //
    public void insert(T x) {
        root = insert(x, root);
    }

    //
    public void remove(T x) {
        root = remove(x, root);
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int tmp;
        tmp = x.compareTo(t.element);

//        while (t != null) {
//            tmp = x.compareTo(t.element);
//            if (tmp > 0) {
//                t = t.right;
//
//            } else if (tmp < 0) {
//                t = t.left;
//
//            } else if (tmp == 0) {
//                return true;
//            }
//        }
//        return false;
        if (tmp > 0) {
            t = t.right;
            return contains(x, t);
        } else if (tmp < 0) {
            t = t.left;
            return contains(x, t);
        } else {
            return true;
        }
    }

    private Comparator<? super T> cmp;

    //考虑如何传入一个Compartor 1,匿名内部类 2,显式声明 3,也可以用lambda表达式
    public BinarySearchTree(Comparator<? super T> c) {
        root = null;
        cmp = c;
    }

    private int myCompare(T lc, T rc) {
        return cmp.compare(lc, rc);

    }

    private boolean containsCompare(T x, BinaryNode<T> t) {
        if (t == null)
            return false;
        int tmp = myCompare(x, t.element);
        if (tmp > 0) {
            return containsCompare(x, t.right);
        } else if (tmp < 0) {
            return containsCompare(x, t.left);
        } else {
            return true;
        }

    }


    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null)
            return null;
        while (t.left != null) {
            t = t.left;
        }
        return t;

    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t == null)
            return null;
        else if (t.right == null)
            return t;
        return findMax(t.right);

//        if (t.right != null) {
//            t = t.right;
//            findMax(t);
//        }
//        return t;
    }

    //这里显然是return t因为t每次都在不断变化 当找到contains的位置的时候就是最适合insert插入节点的位置
    //这时候不能用t.element了 因为在找到的时候t已经是null了 这时候调用t.element会报空指针

    //每次insert之后返回上一层 t.root都会发生变化 因此t.right = 上次一insert的return值  之后再return t 再返回给上一层的t.right
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null)
        {  return new BinaryNode<>(x, null, null);}

        int tmp = x.compareTo(t.element);
        if (tmp > 0) {
            t.right= insert(x, t.right);
        } else if (tmp < 0) {
            t.left= insert(x, t.left);
        } else {
        }
        return t;//考虑0->1->2->5 出栈的时候比如insert(5,null)，上一层是  2.right=new Node(5,null,null) 这时候t为2
        // return 2  那么再出栈到上一层t=1  1.right=insert(x,t.right)的insert返回值 t=2  即为 1.right=Node(2) 这时候整个树就连起来了

    }

    public BinaryNode<T> removeMin(BinaryNode<T> t)
    {
       if(t.left==null)
       {
           return t.right;
       }
       t.left=removeMin(t.left);
       return t;
    }
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return t;
        }
        int tmp = x.compareTo(t.element);
        if (tmp > 0) {
            t.right= remove(x, t.right);
        } else if (tmp < 0) {
            t.left= remove(x, t.left);
        } else if (t.left != null || t.right != null) {
            t.element = findMin(t.right).element;
            t.right= removeMin(t.right);
           // t.right = remove(t.element, t.right);
            //removeMin方法 显然最多只有右子树 这里可以直接将t节点换成t.right
        } else {
            return (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void printTree(BinaryNode t) {
    }


    public static void main(String[] args) {


        //匿名内部类
        BinarySearchTree tree1 = new BinarySearchTree(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.intValue() - o2.intValue();
            }
        });

        //显式声明
        Comparator<Integer> cmp1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.intValue() - o2.intValue();
            }
        };

        BinarySearchTree tree2 = new BinarySearchTree(cmp1);

        //lambda表达式
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>((a, b) -> {
            return a - b; });
        BinaryNode fiveleft_right = new BinaryNode(14,null,null);
        BinaryNode fourleft_right = new BinaryNode(5,null,null);
        BinaryNode fourright_left = new BinaryNode(13,null,fiveleft_right);
        BinaryNode fourright_right = new BinaryNode(17,null,null);
        BinaryNode threeleft_left = new BinaryNode(4, null, fourleft_right);
        BinaryNode threeleft_right = new BinaryNode(15, fourright_left, fourright_right);
        BinaryNode threeright_left = new BinaryNode(22, null, null);
        BinaryNode twoleft = new BinaryNode(12, threeleft_left, threeleft_right);
        BinaryNode tworight = new BinaryNode(23, threeright_left, null);


   //      tree.makeEmpty();
//        tree.root.element = 20;
//        tree.root.left = twoleft;
//        tree.root.right = tworight;


//      System.out.println(tree.containsCompare(1));
//      System.out.println(tree.contains(1));



//        System.out.println(tree.findMin());
//        System.out.println(tree.findMax());


//        //在这里insert之后 root已经变为插入的节点 只有这一个节点 而之前定义的整个树的数据结构没有插入新的节点 只是每次root发生了变化
//        //insert每次循环都会更改掉树的整体结构  insert之后就不是之前定义的tree了
      tree.insert(5);
        tree.insert(3);
        tree.insert(10);
        tree.insert(7);
        tree.insert(11);
//       System.out.println("insert over");
        tree.remove(5);
        System.out.println(tree.root);

        tree.makeEmpty();

        //执行insert remove 操作的时候会更改tree 因此需要注意此时的tree.root
   //    tree.remove(12);//跟踪整个过程 每次会跳回判断  因为用了递归 使用了栈数据结构 当然会弹出栈了！！！！！
       // System.out.println(tree.remove(12,tree.root));
//       System.out.println("remove over");
//        System.out.println(tree.root);
    //    System.out.println(tree.removeMin(tree.root));

        //这时候findmax会只访问一个节点 并且会报错nullPoint
//        System.out.println(tree.findMin());
//        System.out.println(tree.findMax());
    }
}
