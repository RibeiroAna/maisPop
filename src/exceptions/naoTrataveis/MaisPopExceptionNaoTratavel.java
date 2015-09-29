package exceptions.naoTrataveis;

public class MaisPopExceptionNaoTratavel extends RuntimeException {

	private static final long serialVersionUID = -6228106526536874535L;
	String error;
	String causa;
	
	public MaisPopExceptionNaoTratavel(String error, String causa) {
		super(error + causa);
		this.error = error;
		this.causa = causa;
	}
	
	public MaisPopExceptionNaoTratavel(String mensagem) {
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
