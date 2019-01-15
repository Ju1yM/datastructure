package tree;

public class Test {

	public static void main(String[] args) {
		avltree();
	}
	
	public static void bstreetest() {
		BinarySearchTree<Integer> bs = new BinarySearchTree<>();
		bs.insert(5);
		bs.insert(4);
		bs.insert(6);
		
		System.out.println(bs.size());
		bs.empty();
		
	}

	public static void avltree(){
		AVLTree<Integer> avl = new AVLTree<>();
		avl.insert(5);
		avl.insert(4);
		avl.insert(7);
		avl.insert(6);
		avl.insert(19);
		avl.remove(19);
		//avl.printTree(avl);
		avl.empty();
	}

}
