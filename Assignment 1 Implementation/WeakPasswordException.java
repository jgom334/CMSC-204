public class WeakPasswordException extends Exception {
	public WeakPasswordException() {
		super("Password is weak but valid");
	}
}
