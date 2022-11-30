
/**
 * @author JGomez
 *
 * @param <T>
 */
public class TreeNode<T> {

	protected TreeNode<T> left = null;
	protected TreeNode<T> right = null;
	private T data = null;

	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * @param dataNode the data to be stored in the TreeNode
	 */
	public TreeNode(T dataNode) {
		left = null;
		right = null;
		data = dataNode;
	}

	/**
	 * used for making deep copies
	 * @param node node to make copy of
	 */
	public TreeNode(TreeNode<T> node) {
		this.data = node.data;
		this.left = node.left;
		this.right = node.right;
	}

	/**
	 * Return the data within this TreeNode
	 * @return the data within the TreeNode
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}
}