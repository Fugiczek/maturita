package eu.fugiczek.maturita.domain.exception;

@SuppressWarnings("serial")
public class BlogPostNotFoundException extends RuntimeException {

	public BlogPostNotFoundException(int id) {
		super("BlogPost with ID " + id + " was not found.");
	}
	
	public BlogPostNotFoundException(String message) {
        super(message);
    }

    public BlogPostNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
