package excecao;

public class ValorDeLanceInvalidoException extends Exception {
    private final static long serialVersionUID = 1;

    public ValorDeLanceInvalidoException() {
	super();
    }

    public ValorDeLanceInvalidoException(String msg) {
	super(msg);
    }
}