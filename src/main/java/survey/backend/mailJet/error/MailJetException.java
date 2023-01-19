package survey.backend.mailJet.error;

public class MailJetException extends Exception {

    private static final long serialVersionUID = 1L;

    public MailJetException(String message) {
        super(message);
    }
    public MailJetException(String message, Exception cause) {
        super(message, cause);
    }
}
