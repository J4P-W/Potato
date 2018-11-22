package DevPaw.browserators.exceptions;

public class InvalidLoginException extends Exception {
	private static final long serialVersionUID = 1L;
	public InvalidLoginException(String error) {
		super(error);
	}
}
