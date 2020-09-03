package excecao;




public class MotoristaJaTemCarrosException extends RuntimeException {
    private final static long serialVersionUID = 1;

    public MotoristaJaTemCarrosException() {
	super();
    }

    public MotoristaJaTemCarrosException(String msg) {
	super(msg);
    }
}
