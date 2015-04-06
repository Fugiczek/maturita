package eu.fugiczek.maturita.domain.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(int id) {
		super("User with ID " + id + " was not found.");
	}
	
	public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
    
}
