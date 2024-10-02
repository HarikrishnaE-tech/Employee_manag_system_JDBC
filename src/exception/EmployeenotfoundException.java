package exception;

public class EmployeenotfoundException extends RuntimeException {
String message;
public EmployeenotfoundException( String message) {
	this.message=message;
	
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}






}
