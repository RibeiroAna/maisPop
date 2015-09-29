package core.usuario;

public enum AtributoUsuario {

	NOME("Nome"), DATA_NASC("Data de Nascimento"), SENHA("Senha"),
	FOTO("Foto"), EMAIL("E-mail");

	private final String atributo;

	AtributoUsuario(String atributo) {
		this.atributo = atributo;
	}

	public String getAtributo() {
		return atributo;
	}
}
