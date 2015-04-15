package eu.fugiczek.maturita.domain.exception;

@SuppressWarnings("serial")
public class BlogPostNotCommentableException extends RuntimeException {

	public BlogPostNotCommentableException(int id) {
		super("BlogPost with ID " + id + " is not commentable.");
	}
	
	public BlogPostNotCommentableException(String message) {
        super(message);
    }

    public BlogPostNotCommentableException(String message, Throwable ex) {
        super(message, ex);
    }
}
