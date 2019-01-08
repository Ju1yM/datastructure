package com.datastructure.demo.Tree;

//每次main函数都写在实现里 其实应该讲class类与main函数分开到两个文件 在写散列的时候会注意这个问题
//AVL树的基本实现 重点是balance(t)的调用与实现
// 另一个重点是insert方法与remove方法不同的方法实现的
// remove调用balance
// insert另一个直接用x.compareTo判断新插入的节点在哪里 从而判断需要左旋还是右旋
// 单旋转 如果是右旋  k2         这种情况 那么旋转之后k2的又子树仍是Z  k1的左子树仍是X  由于Y介于k1k2之间 放在k2的左子树  类似重力下垂 k2换掉了Y
//                k1    Z
//  X(X高于一层)     Y
public class AVLTree<T extends Comparable<? super T>> {
    public static class AVLNode<T> {
        T element;
        AVLNode<T> left;
        AVLNode<T> right;
        int height;

        AVLNode(T Aelement) {
            this(Aelement, null, null);
        }

        @Override
        public String toString() {
            return "AVLNode{" +
                    "element=" + element +
                    ", left=" + left +
                    ", right=" + right +
                    ", height=" + height +
                    '}';
        }

        AVLNode(T Aelement, AVLNode lc, AVLNode<T> rc) {
            element = Aelement;
            left = lc;
            right = rc;
            height = 0;
        }
    }

    private int height(AVLNode<T> t) {
        return (t == null) ? -1 : t.height;
    }

    private AVLNode<T> root;

    public AVLTree() {
        this.root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public int height() {
        return height(root);
    }

    public AVLNode<T> findMin(AVLNode<T> t) {
        if (t == null) {
            return null;
        }
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    private AVLNode<T> insert1(T x, AVLNode<T> t) {
        if (t == null) {
            return new AVLNode<>(x, null, null);
        }
        int tmp = x.compareTo(t.element);
        if (tmp < 0) {
            t.left = insert1(x, t.left);
        } else if (tmp > 0) {
            t.right = insert1(x, t.right);
        } else {
        }
        return t;
    }

    private AVLNode<T> insert(T x, AVLNode<T> t) {
        if (t == null) {
            return new AVLNode<>(x, null, null);
        }
        int tmp = x.compareTo(t.element);
        if (tmp < 0) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                //       if((height(t.left.left)>=height(t.left.right))){
                if (x.compareTo(t.left.element) < 0) {//如果比root.left的还要小，说明是插在了左边左子树 所以使用右旋
                    t = rightRotate(t);
                } else {
                    t = leftAndrightRotate(t);
                }
            }
        } else if (tmp > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.element) > 0) {
                    t = leftRotate(t);
                } else {
                    t = rightAndleftRotate(t);
                }
            }
        } else {
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AVLNode<T> remove(T x, AVLNode<T> t) {
        if (t == null) {
            return null;
        }
        int tmp = x.compareTo(t.element);
        if (tmp > 0) {
            t.right = remove(x, t.right);
        } else if (tmp < 0) {
            t.left = remove(x, t.left);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            return (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    private AVLNode<T> balance(AVLNode<T> t)
    {
        if(t==null)
        {
            return null;
        }
        if(height(t.left)-height(t.right)==2)
        {
            if(height(t.left.left)>=height(t.left.right))
            {
                t=rightRotate(t);
            }
            else
            {t=leftAndrightRotate(t);}

        }
        else if(height(t.right)-height(t.left)==2)
        {
            if(height(t.right.right)>=height(t.right.left))
            {
                t=leftRotate(t);
            }
            else {
                t=rightAndleftRotate(t);
            }

        }
        t.height=Math.max(height(t.left),height(t.right))+1;
        return t;
    }
    //针对类似     6
    //      4----null
    //   2-----null  这种 使用右旋          这里k1 k2用的不太好
    public AVLNode<T> rightRotate(AVLNode<T> k2)//顺时针右旋
    {
        AVLNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        //这里可以吧k1.height变成k2.height与height(k1.left)比较 显然。右旋之后k2就是k1的右子树
        //  k1.height= Math.max(height(k1.left),k2.height)+1;
        return k1;
    }

    public AVLNode<T> leftRotate(AVLNode<T> k2) {
        AVLNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        return k1;
    }

    //针对类似   7                                    7
    //   5------null                         6
//  null----6 这种 使用先左旋 5-6  变为    5          再右旋 7-6
    public AVLNode<T> leftAndrightRotate(AVLNode<T> k3) {
        k3.left = leftRotate(k3.left);
        return rightRotate(k3);
    }

    public AVLNode<T> rightAndleftRotate(AVLNode<T> k3) {
        k3.right = rightRotate(k3.right);
        return leftRotate(k3);
    }


    public static void main(String[] args) {
        AVLNode two = new AVLNode(4, null, null);
        AVLNode one = new AVLNode(5, two, null);
        AVLTree<Integer> tree = new AVLTree<>();
        tree.root = one;
        tree.insert(7);
        tree.insert(2);
tree.remove(7);
        System.out.println(tree.root.toString());
        tree.makeEmpty();
        //  System.out.println(tree.insert1(3,tree.root).toString());
    }
}
