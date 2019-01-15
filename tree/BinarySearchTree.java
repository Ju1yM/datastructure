package tree;

public class BinarySearchTree<T extends Comparable<T>> extends AbstractTree<T> {
	private BSTNode<T> nullNode;
	
	public BinarySearchTree() {
		super();
		nullNode = new BSTNode<T>(null);
		nullNode.setLeft(nullNode);
		nullNode.setRight(nullNode);
		
		this.setRoot(nullNode);
		this.setNullNode(nullNode);
	}
	@Override
	boolean insert_p(T item) {
		BSTNode<T> root = this.getRoot();
		if(root == nullNode) {
			root = new BSTNode<T>(item, nullNode, nullNode);
			this.setRoot(root);
			return true;
		} else {
			BSTNode<T> node = new BSTNode<T>(item, nullNode, nullNode);
			int cmp = item.compareTo(root.getElement());
			while(cmp != 0) {
				if(cmp < 0) {
					if(root.getLeft() != nullNode) {
						root = root.getLeft();
						cmp = item.compareTo(root.getElement());
					} else {
						root.setLeft(node);
						return true;
					}
				} else {
					if(root.getRight() != nullNode) {
						root = root.getRight();
						cmp = item.compareTo(root.getElement());
					} else {
						root.setRight(node);
						return true;
					}
				}
			}
			
			return false;
		}
	}

	@Override
	boolean remove_p(T item) {
		//TODO
		return false;
	}
	@Override
	public String toString() {
		// TODO
		return super.toString();
	}
	
	

}

class BSTNode<T extends Comparable<T>> extends AbstractTreeNode<T> {
	public BSTNode(T element, BSTNode<T> left, BSTNode<T> right) {
		super(element, left, right);
	}
	
	public BSTNode(T element) {
		this(element, null, null);
	}

	@Override
	public String toString() {
		//TODO
		return super.toString();
	}
	
	
	
}