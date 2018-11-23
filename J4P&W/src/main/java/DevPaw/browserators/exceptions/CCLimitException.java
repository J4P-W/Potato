package DevPaw.browserators.exceptions;

public class CCLimitException extends Exception {
	private static final long serialVersionUID = 1L;
	public CCLimitException(String error) {
		super(error);
	}
}
