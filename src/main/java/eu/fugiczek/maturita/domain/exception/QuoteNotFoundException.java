package eu.fugiczek.maturita.domain.exception;

@SuppressWarnings("serial")
public class QuoteNotFoundException extends RuntimeException {

	public QuoteNotFoundException(int id) {
		super("Quote with ID " + id + " was not found.");
	}
	
	public QuoteNotFoundException(String message) {
        super(message);
    }

    public QuoteNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
