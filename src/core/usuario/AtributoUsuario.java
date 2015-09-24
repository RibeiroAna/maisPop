package core.usuario;

public enum AtributoUsuario {

	NOME("Nome"), DATA_NASC("Data de Nascimento"), SENHA("Senha"), FOTO("Foto");

	private final String atributo;

	AtributoUsuario(String atributo) {
		this.atributo = atributo;
	}

	public String getAtributo() {
		return atributo;
	}
}
