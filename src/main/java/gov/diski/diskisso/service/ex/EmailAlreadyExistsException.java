package gov.diski.diskisso.service.ex;

public class EmailAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistsException(String message){
		super(message);
	}
	
	public EmailAlreadyExistsException(){
		super("Email is already exist..");
	}

}
