package excecao;

public class ViagemNaoEncontradaException extends Exception {
    private final static long serialVersionUID = 1;

    public ViagemNaoEncontradaException() {
	super();
    }

    public ViagemNaoEncontradaException(String msg) {
	super(msg);
    }
}