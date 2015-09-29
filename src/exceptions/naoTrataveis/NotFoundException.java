package exceptions.naoTrataveis;

public class NotFoundException extends MaisPopExceptionNaoTratavel {
	
	private static final long serialVersionUID = 1473684668098975313L;

	public NotFoundException() {
		super("");
	}

	public NotFoundException(String error) {
		super(error);
	}
	
	public NotFoundException(String error, String causa) {
		super(error, causa);
	}
	
}
