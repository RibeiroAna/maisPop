package exceptions.naoTrataveis;

import utils.MensagensDeErro;

public class BadFormatException extends MaisPopExceptionNaoTratavel {
	
	private static final long serialVersionUID = 1473684668098975315L;

	public BadFormatException() {
		super();
	}

	public BadFormatException(MensagensDeErro loginErrorNotFound) {
		super(loginErrorNotFound.getMessage());
	}

}
