package exception;

public class InvalidException extends RuntimeException {

	private String  message;
	public InvalidException(String message) {
		this.message=message;
		
	}
	public String getmessage() {
		return message;
	}

	
	
}
