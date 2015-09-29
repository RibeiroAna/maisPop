package exceptions.trataveis;

public class MaisPopExceptionTratavel extends Exception{

	private static final long serialVersionUID = -3588023552428173169L;
	String error;
	String causa;
	
	public MaisPopExceptionTratavel(String error, String causa) {
		super(error + causa);
		this.error = error;
		this.causa = causa;
	}
	
	public MaisPopExceptionTratavel(String mensagem) {
		super(mensagem);
		this.error = mensagem;
		this.causa = "not specified";
	}
	
	public String getError() {
		return error;
	}
	
	public String getCausa() {
		return causa;
	}
}
