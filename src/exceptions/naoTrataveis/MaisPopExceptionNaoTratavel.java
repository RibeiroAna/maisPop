package exceptions.naoTrataveis;

public class MaisPopExceptionNaoTratavel extends RuntimeException {

	private static final long serialVersionUID = -6228106526536874535L;

	public MaisPopExceptionNaoTratavel(String mensagem) {
		super(mensagem);
	}

	public MaisPopExceptionNaoTratavel() {
		super();
	}
}
