package excecao;



public class CarroJaTemViagensException extends RuntimeException {
    private final static long serialVersionUID = 1;

    public CarroJaTemViagensException() {
	super();
    }

    public CarroJaTemViagensException(String msg) {
	super(msg);
    }
}
