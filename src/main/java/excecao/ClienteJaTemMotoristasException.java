package excecao;



public class ClienteJaTemMotoristasException extends RuntimeException {
    private final static long serialVersionUID = 1;

    public ClienteJaTemMotoristasException() {
	super();
    }

    public ClienteJaTemMotoristasException(String msg) {
	super(msg);
    }
}
