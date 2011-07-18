package unbbayes.prs.exception;

public class InvalidParentException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidParentException(String msg) {
		super(msg);
	}
	
	public InvalidParentException(String msg, Throwable t) {
		super(msg,t);
	}

}
