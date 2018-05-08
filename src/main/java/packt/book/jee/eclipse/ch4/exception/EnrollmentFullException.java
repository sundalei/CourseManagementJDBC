package packt.book.jee.eclipse.ch4.exception;

public class EnrollmentFullException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public EnrollmentFullException() {
		
	}
	
	public EnrollmentFullException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
