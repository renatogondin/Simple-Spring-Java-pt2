package excecao;

public class MotoristaNaoEncontradoException extends Exception {
    private final static long serialVersionUID = 1;

    public MotoristaNaoEncontradoException() {
	super();
    }

    public MotoristaNaoEncontradoException(String msg) {
	super(msg);
    }
}