package exceptions.trataveis;

public class UnauthorizedException extends MaisPopExceptionTratavel {

	public UnauthorizedException(String error, String causa) {
		super(error, causa);
	}
	
	public UnauthorizedException(String mensagemUnica) {
		super(mensagemUnica);
	}

	public String getMessage() {
		return super.getMessage();
	}
	
	private static final long serialVersionUID = 1L;

}
