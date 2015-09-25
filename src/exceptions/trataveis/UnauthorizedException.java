package exceptions.trataveis;

import utils.MensagensDeErro;


public class UnauthorizedException extends MaisPopExceptionTratavel {
	
	public UnauthorizedException(MensagensDeErro loginSenhaErrada) {
		super(loginSenhaErrada.getMesagem());
	}
	
	public UnauthorizedException(String loginSenhaErrada) {
		super(loginSenhaErrada);
	}

	public String getMessage() {
		return super.getMessage();
	}
	
	private static final long serialVersionUID = 1L;

}
