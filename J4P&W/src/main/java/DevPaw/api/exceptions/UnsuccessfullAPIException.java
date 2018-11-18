package DevPaw.api.exceptions;

public class UnsuccessfullAPIException extends Exception {
	private static final long serialVersionUID = -1124662544175605507L;
	public UnsuccessfullAPIException(String failure) {
		super(failure);
	}

}
