package exceptions.naoTrataveis;

public class BadFormatException extends MaisPopExceptionNaoTratavel {
	
	private static final long serialVersionUID = 1473684668098975315L;

	public BadFormatException() {
		super("");
	}
	
	public BadFormatException(String error, String causa) {
		super(error, causa);
	}

}
