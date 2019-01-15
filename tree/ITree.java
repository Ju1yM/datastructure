package tree;

import java.util.Iterator;

public interface ITree<T extends Comparable<T>> {
	
	public boolean contains(T item);
	
	public T findMin();
	public T findMax();
	
	public void insert(T item);
	public void insert(T [] items);
	public void insert(ITree<T> itemsTree);
	
	public void remove(T item);
	public void remove(T [] items);
	public void remove(ITree<T> itemTree);
	
	public int size();
	
	public Iterator<T> iterator_preorder();
	public Iterator<T> iterator_inorder();
	public Iterator<T> iterator_postorder();
	public Iterator<T> iterator_levelorder();
	
	public boolean isEmpty();
	public void empty();
}
