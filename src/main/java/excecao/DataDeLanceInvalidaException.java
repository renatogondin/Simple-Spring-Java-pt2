package excecao;

public class DataDeLanceInvalidaException extends Exception {
    private final static long serialVersionUID = 1;

    public DataDeLanceInvalidaException() {
	super();
    }

    public DataDeLanceInvalidaException(String msg) {
	super(msg);
    }
}