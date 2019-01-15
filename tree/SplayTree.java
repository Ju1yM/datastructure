package tree;

public class SplayTree<T extends Comparable<T>> extends AbstractTree<T> {
	private SplayNode<T> nullNode;
	
	public SplayTree() {
		//TODO
	}
	@Override
	boolean insert_p(T item) {
		// TODO
		return false;
	}

	@Override
	boolean remove_p(T item) {
		// TODO
		return false;
	}
	
	public String toString() {
		//TODO
		return super.toString();
	}

}

class SplayNode<T extends Comparable<T>> extends AbstractTreeNode<T> {

	public SplayNode(T element, ITreeNode<T> left, ITreeNode<T> right) {
		super(element, left, right);
	}
	
	public String toString() {
		//TODO
		
		return super.toString();
	}
	
}