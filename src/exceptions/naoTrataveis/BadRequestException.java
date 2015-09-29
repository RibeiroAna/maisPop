package exceptions.naoTrataveis;

public class BadRequestException extends MaisPopExceptionNaoTratavel {
	
	private static final long serialVersionUID = 1473684668098955315L;

	public BadRequestException() {
		super("");
	}

	public BadRequestException(String error) {
		super(error);
	}

	
	public BadRequestException(String error, String causa) {
		super(error, causa);
	}

}
