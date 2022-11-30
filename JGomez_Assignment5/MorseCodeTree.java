import java.util.ArrayList;

/**
 * @author JGomez
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	public static final String DOT = ".";
	public static final String DASH = "-";
	private TreeNode<String> root;

	public MorseCodeTree() {
		buildTree();
	}

	/**
	 * sets the root of the MorseCodeTree
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}

	/**
	 * Returns a reference to the root
	 * @return root reference to root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * Adds element to the correct position in the tree based on the code This method will call the recursive method addNode
	 * 
	 */
	@Override
	public void insert(String code, String result) {
		addNode(root, code, result);
	}

	/**
	 *
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {

		if (code.length() == 1) {
			if (code.equals(DOT)) {
				if (root.left == null) {
					root.left = new TreeNode<String>(letter);
				}
				root.left.setData(letter);
			} else if (code.equals(DASH)) {
				if (root.right == null) {
					root.right = new TreeNode<String>(letter);
				}
				root.right.setData(letter);
			}
			return;
		}

		else if (code.length() > 1) {
			if (code.charAt(0) == DOT.charAt(0)) {
				if (root.left == null) {
					root.left = new TreeNode<String>("");
				}
				root = root.left;
				code = code.substring(1);
				addNode(root, code, letter);
			} else if (code.charAt(0) == DASH.charAt(0)) {
				if (root.right == null) {
					root.right = new TreeNode<String>("");
				}
				root = root.right;
				code = code.substring(1);
				addNode(root, code, letter);
			}
		}
	}

	/**
	 * Fetch the data in the tree based on the code This method will call the recursive method fetchNode
	 * @return the string (letter) that corresponds to the code
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	/**
	 * 
	 * @return the string (letter) corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {

		String letter = "";

		if (code.length() == 1) {
			if (code.equals(DOT)) {
				return letter = root.left.getData();
			} else if (code.equals(DASH)) {
				letter = root.right.getData();
				return letter;
			}
		}

		else if (code.length() > 1) {
			if (code.charAt(0) == DOT.charAt(0)) {
				root = root.left;
				code = code.substring(1);
				return fetchNode(root, code);
			} else if (code.charAt(0) == DASH.charAt(0)) {
				root = root.right;
				code = code.substring(1);
				return fetchNode(root, code);
			}
		}

		return "";

	}

	/**
	 * This operation is not supported in the MorseCodeTree
	 * @return reference to the current tree
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		System.out.println("Unsupported method...");
		return null;
	}

	/**
	 * This operation is not supported in the MorseCodeTree
	 * @return reference to the current tree
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		System.out.println("Unsupported method...");
		return null;
	}

	/**
	 *
	 */
	@Override
	public void buildTree() {
		String[] codes = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
				"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
		String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		root = new TreeNode<String>("");
		for (int i = 0; i < codes.length; i++) {
			insert(codes[i], letters[i]);
		}
	}

	/**
	 * Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> lettersList = new ArrayList<String>();
		LNRoutputTraversal(root, lettersList);
		return lettersList;
	}

	/**
	 * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {

		if (root == null) {
			return;
		}

		LNRoutputTraversal(root.left, list);
		list.add(root.getData());
		LNRoutputTraversal(root.right, list);

	}
}