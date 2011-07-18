package unbbayes.controller.exception;

public class NoObjectToBeSavedException extends Exception{

	
	private static final long serialVersionUID = 5870468413181587201L;

	public NoObjectToBeSavedException(){
		super(); 
	}
	
	public NoObjectToBeSavedException(String msg){
		super(msg); 
	}
	
	public NoObjectToBeSavedException(Throwable t) {
		super(t);
	}
	
	public NoObjectToBeSavedException(String message, Throwable t) {
		super(message,t);
	}
}
