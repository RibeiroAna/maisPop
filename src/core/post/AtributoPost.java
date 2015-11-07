package core.post;

public enum AtributoPost {
	
	MENSAGEM("Mensagem"), HASTAGS("Hashtags"), DATA("Data");
	
	private final String atributo;
	
	AtributoPost(String atributo) {
		this.atributo = atributo;
	}

	public String getAtributo() {
		return atributo;
	}
}
