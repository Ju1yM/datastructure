package tree;

public interface ITreeNode<T> {
	public <E extends ITreeNode<T>> E getLeft();
	public <E extends ITreeNode<T>> void setLeft(E left);
	public <E extends ITreeNode<T>> E getRight();
	public <E extends ITreeNode<T>> void setRight(E right);
	public T getElement();
	public void setElement(T item);
}
