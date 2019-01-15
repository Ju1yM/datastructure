package tree;

import java.util.Iterator;

public abstract class AbstractTree<T extends Comparable<T>> implements ITree<T> {
	private int size;
	private ITreeNode<T> root;
	
	private  ITreeNode<T> nullNode;
	
	private boolean isLazyRemove;
	
	public AbstractTree() {
		size = 0;
		this.isLazyRemove = false;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void empty() {
		root = nullNode;
		size = 0;
	}

	@SuppressWarnings("unchecked")
	protected <E extends ITreeNode<T>> E getNullNode() {
		return (E)nullNode;
	}
	
	protected void setNullNode(ITreeNode<T> nullNode) {
		this.nullNode = nullNode;
	}
	
	@SuppressWarnings("unchecked")
	protected <E extends ITreeNode<T>> E getRoot() {
		return (E)root;
	}
	
	protected void setRoot(ITreeNode<T> root) {
		this.root = root;
	}

	@Override
	public void insert(T item) {
		if(insert_p(item)) size++;
		
	}

	@Override
	public void insert(T[] items) {
		for(int i = 0, n = items.length; i < n; i++) {
			if(insert_p(items[i])) size++;
		}
	}

	@Override
	public void insert(ITree<T> itemsTree) {
		Iterator<T> iter = itemsTree.iterator_inorder();
		while(iter.hasNext()) {
			if(insert_p(iter.next())) size++;
		}
	}

	@Override
	public void remove(T item) {
		if(remove_p(item)) size--;
		
	}

	@Override
	public void remove(T[] items) {
		for(int i = 0, n = items.length; i < n; i++) {
			if(remove_p(items[i])) size--;
		}
	}

	@Override
	public void remove(ITree<T> itemTree) {
		Iterator<T> iter = itemTree.iterator_inorder();
		while(iter.hasNext()) {
			if(remove_p(iter.next())) size--;
		}
	}

	@Override
	public boolean contains(T item) {
		if(this.isLazyRemove) clear();
		
		ITreeNode<T> node = root;
		int cmp = 0;
		while(node != nullNode) {
			cmp = item.compareTo(node.getElement());
			if(cmp == 0) return true;
			if(cmp < 0) node = node.getLeft();
			else node = node.getRight();
		}
		return false;
	}

	@Override
	public T findMin() {
		//TODO
		return null;
	}

	@Override
	public T findMax() {
		//TODO
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator_preorder() {
		if(this.isLazyRemove) clear();
		
		// TODO
		return null;
	}

	@Override
	public Iterator<T> iterator_inorder() {
		if(this.isLazyRemove) clear();
		
		// TODO
		return null;
	}

	@Override
	public Iterator<T> iterator_postorder() {
		if(this.isLazyRemove) clear();
		
		// TODO
		return null;
	}

	@Override
	public Iterator<T> iterator_levelorder() {
		if(this.isLazyRemove) clear();
		
		// TODO
		return null;
	}
	
	//子类要实现的方法
	//实现插入和删除方法，插入或者删除成功返回true，失败返回false
	//这里不区分懒惰删除
	abstract boolean insert_p(T item);
	
	abstract boolean remove_p(T item);
	
	//默认不采用懒惰删除
	//这里是与懒惰删除有关的方法
	//当采用懒惰删除时，构造函数中调用这个方法设置懒惰删除为true
	protected void setLazyRemove() {
		this.isLazyRemove = true;
	}
	
	//子类重写clear方法
	//当调用clear时，将未删除的节点删除
	//这个方法在调用contains，迭代器时会被调用
	//remove方法的删除策略与这里无关
	protected void clear() {
		
	}
	
}