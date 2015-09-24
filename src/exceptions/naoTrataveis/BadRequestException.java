package exceptions.naoTrataveis;

import utils.MensagensDeErro;

public class BadRequestException extends MaisPopExceptionNaoTratavel {
	
	private static final long serialVersionUID = 1473684668098955315L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(MensagensDeErro error) {
		super(error.getMessage());
	}

}
