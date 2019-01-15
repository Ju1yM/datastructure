package tree;

public class AVLTree<T extends Comparable<T>> extends AbstractTree<T> {
    private AVLNode<T> nullNode;

    public AVLTree() {
        super();
        nullNode = new AVLNode<T>(null);
        nullNode.setLeft(nullNode);
        nullNode.setRight(nullNode);

        this.setRoot(nullNode);
        this.setNullNode(nullNode);

    }

    @Override
    boolean insert_p(T item) {
        try {
            insertNode(item, getRoot());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    boolean remove_p(T item) {
        if (contains(item)) {
            try {
                removeNode(item, getRoot());
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }

        } else {
            return false;
        }
    }

    private int height(AVLNode<T> t) {
        return t == nullNode ? -1 : t.getHeight();
    }

    private AVLNode<T> insertNode(T item, AVLNode<T> node) {

        if (this.getRoot() == nullNode) {

            node = new AVLNode<>(item, nullNode, nullNode);
            setRoot(node);
            return node;
        }
        if (node == nullNode) {
            return new AVLNode<>(item, nullNode, nullNode);
        }
        int cmp = item.compareTo(node.getElement());
        if (cmp > 0) {
            AVLNode<T> tmpnode = node.getRight();
            node.setRight(insertNode(item, tmpnode));
        } else if (cmp < 0) {
            AVLNode<T> tmpnode = node.getLeft();
            node.setLeft(insertNode(item, tmpnode));
        } else {
        }
        return balance(node);
    }

    private AVLNode<T> balance(AVLNode<T> t) {
        AVLNode<T> leftNode = t.getLeft();
        AVLNode<T> rightNode = t.getRight();
        if (height(leftNode) - height(rightNode) == 2) {
            leftNode = rightNode.getLeft();
            rightNode = rightNode.getRight();
            if (height(leftNode) >= height(rightNode)) {
                t = rightRotate(t);
            } else {
                t = left_rightRotate(t);
            }

        }
        if (height(rightNode) - height(leftNode) == 2) {
            leftNode = rightNode.getLeft();
            rightNode = rightNode.getRight();
            if (height(rightNode) >= height(leftNode)) {
                t = leftRotate(t);
            } else {
                t = right_leftRotate(t);
            }
        } else {
        }
        leftNode = t.getLeft();
        rightNode = t.getRight();
        int nowheight = Math.max(height(leftNode), height(rightNode));
        t.setHeight(nowheight + 1);
        return t;
    }

    private AVLNode<T> right_leftRotate(AVLNode<T> t) {
        t.setRight(rightRotate(t.getRight()));
        return leftRotate(t);
    }

    private AVLNode<T> leftRotate(AVLNode<T> t) {
        AVLNode<T> rightNode = t.getRight();
        AVLNode<T> tmpLeftTree = rightNode.getLeft();
        t.setRight(tmpLeftTree);
        rightNode.setLeft(t);

        t.setHeight(Math.max(height(t.getLeft()), height(t.getRight())) + 1);
        rightNode.setHeight(Math.max(height(rightNode.getRight()), t.getHeight()) + 1);
        return rightNode;
    }

    private AVLNode<T> left_rightRotate(AVLNode<T> t) {
        t.setLeft(leftRotate(t.getLeft()));
        return rightRotate(t);
    }


    private AVLNode<T> rightRotate(AVLNode<T> t) {
        AVLNode<T> leftNode = t.getLeft();
        AVLNode<T> tmpRightTree = leftNode.getRight();
        t.setLeft(tmpRightTree);
        leftNode.setRight(t);

        int tHeight = Math.max(height(t.getLeft()), height(t.getRight())) + 1;
        t.setHeight(tHeight);

        int leftHeight = Math.max(height(leftNode.getLeft()), t.getHeight()) + 1;
        leftNode.setHeight(leftHeight);
        return leftNode;
    }



    private AVLNode<T> removeNode(T item, AVLNode<T> node) {

        if (getRoot() == nullNode) {
            return nullNode;
        }
        if (node == nullNode) {
            return nullNode;
        }
        int cmp = item.compareTo(node.getElement());
        if (cmp > 0) {
            node.setRight(removeNode(item, node.getRight()));

        } else if (cmp < 0) {
            node.setLeft(removeNode(item, node.getLeft()));
        } else {
            if (node.getRight() != nullNode && node.getLeft() != nullNode) {
                T min = findMin();
                node.setElement(min);
                removeNode(min, node.getRight());
            } else {
                return node.getLeft() == nullNode ? node.getRight() : node.getLeft();
            }
        }
        return balance(node);
    }


}

class AVLNode<T extends Comparable<T>> extends AbstractTreeNode<T> {
    private int height;

    public AVLNode(T element, ITreeNode<T> left, ITreeNode<T> right) {
        super(element, left, right);
        this.height = 0;
    }

    public AVLNode(T element) {
        this(element, null, null);
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int nowheight) {
        this.height = nowheight;
    }
@Override
    public String toString() {

        return "AbstractTreeNode{" +
                "left=" + this.getLeft() +
                ", right=" + this.getRight() +
                ", element=" + this.getElement() +
                ", height=" + getHeight()+
                '}';

    }

}