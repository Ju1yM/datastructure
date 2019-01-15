package tree;

public abstract class AbstractTreeNode<T extends Comparable<T>> implements ITreeNode<T> {
	private ITreeNode<T> left;
	private ITreeNode<T> right;
	private T element;

	@Override
	public String toString() {
		return "AbstractTreeNode{" +
				"left=" + left +
				", right=" + right +
				", element=" + element +
				'}';
	}

	public AbstractTreeNode(T element, ITreeNode<T> left, ITreeNode<T> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <E extends ITreeNode<T>> E getLeft() {
		return (E)left;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <E extends ITreeNode<T>> E getRight() {
		return (E)right;
	}
	
	
	
	@Override
	public <E extends ITreeNode<T>> void setLeft(E left) {
		this.left = left;
		
	}
	@Override
	public <E extends ITreeNode<T>> void setRight(E right) {
		this.right = right;
		
	}
	@Override
	public T getElement() {
		return element;
	}

	@Override
	public void setElement(T item){
		this.element=item;
	}
}
