package unbbayes.controller.exception;

public class InvalidFileNameException extends Exception{

	private static final long serialVersionUID = 2926132908731406659L;

	public InvalidFileNameException(){
		super(); 
	}
	
	public InvalidFileNameException(String msg){
		super(msg); 
	}
	
	public InvalidFileNameException(Throwable t){
		super(t); 
	}
	
	public InvalidFileNameException(String message , Throwable t){
		super(message,t); 
	}
}
