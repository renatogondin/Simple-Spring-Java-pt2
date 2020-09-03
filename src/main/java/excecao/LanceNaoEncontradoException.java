package excecao;

public class LanceNaoEncontradoException extends Exception {
    private final static long serialVersionUID = 1;

    public LanceNaoEncontradoException() {
	super();
    }

    public LanceNaoEncontradoException(String msg) {
	super(msg);
    }
}