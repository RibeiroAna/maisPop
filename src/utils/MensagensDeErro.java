package utils;

public enum MensagensDeErro {
	
	LOGIN_ERROR_NOT_FOUND("Nao foi possivel realizar login. "
					+ "Um usuarix com email %s nao esta cadastradx."),
	LOGIN_SENHA_ERRADA ("Nao foi possivel realizar login. Senha invalida."),
	LOGIN_USUARIO_LOGADO("Nao foi possivel realizar login. Um usuarix ja esta logadx: %s."),
	
	LOGOUT_SEM_USUARIO("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop."),
	
	FECHA_SISTEMA_COM_USUARIO("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx."),
	
	CADASTRO_NOME_VAZIO("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio."),
	CADASTRO_FORMATO_EMAIL_ERRADO("Erro no cadastro de Usuarios. Formato de e-mail esta invalido."),
	CADASTRO_FORMATO_DATA_INVALIDA("Erro no cadastro de Usuarios. Formato de data esta invalida."),
	CADASTRO_FORMATO_DATA_INEXISTENTE("Erro no cadastro de Usuarios. Data nao existe."),
	
	GET_DADOS_SENHA_PROTEGIDA("A senha dx usuarix eh protegida."),
	GET_DADOS_EMAIL_NOT_FOUND("Um usuarix com email %s nao esta cadastradx.");
	
	private final String mensagem;
	
	MensagensDeErro(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMessage(){
		return mensagem;
	}

}
