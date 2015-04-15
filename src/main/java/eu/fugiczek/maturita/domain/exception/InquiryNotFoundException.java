package eu.fugiczek.maturita.domain.exception;

@SuppressWarnings("serial")
public class InquiryNotFoundException extends RuntimeException {

	public InquiryNotFoundException(int id) {
		super("Inquiry with ID " + id + " was not found.");
	}
	
	public InquiryNotFoundException(String message) {
        super(message);
    }

    public InquiryNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
