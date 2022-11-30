import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author JGomez
 *
 */
public class MorseCodeConverter {

	public MorseCodeConverter() {

	}

	/**
	 * returns a string with all the data in the tree in LNR order with an space in between them.
	 * @return
	 */
	public static String printTree() {
		MorseCodeTree tree = new MorseCodeTree();
		String treeString = "";
		ArrayList<String> treeList = tree.toArrayList();
		for (int i = 0; i < treeList.size(); i++) {
			treeString += treeList.get(i);
			if (i != treeList.size() - 1) {
				treeString += " ";
			}
		}
		return treeString;
	}

	/**
	 * Converts Morse code into English.
	 * @param code
	 * @return
	 */
	public static String convertToEnglish(String code) {
		MorseCodeTree tree = new MorseCodeTree();
		String english = "";
		String[] words = code.split("/");
		String[] letters;
		for (int i = 0; i < words.length; i++) {
			letters = words[i].split(" ");
			for (int k = 0; k < letters.length; k++) {
				english += tree.fetch(letters[k]);
			}
			if (i != words.length - 1) {
				english += " ";
			}
		}
		return english;
	}

	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘).
	 * @param codeFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		String code = "";
		Scanner scanner = new Scanner(codeFile);
		while (scanner.hasNext()) {
			code += (scanner.nextLine());
		}
		scanner.close();
		return convertToEnglish(code);
	}
}