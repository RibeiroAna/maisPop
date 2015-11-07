package utils;

public class MensagensDeErro {	
	public static final String ERROR_LOGIN = "Nao foi possivel realizar login. ";
	public static final String ERROR_LOGOUT = "Nao eh possivel realizar logout. ";
	public static final String ERROR_FECHA_SISTEMA = "Nao foi possivel fechar o sistema. ";
	public static final String ERROR_CADASTRO = "Erro no cadastro de Usuarios. "; 
	public static final String ERROR_ATUALIZA = "Erro na atualizacao de perfil. ";
	public static final String ERROR_ATUALIZA_DESLOGADO = "Nao eh possivel atualizar um perfil. ";
	public static final String ERROR_CRIAR_POST = "Nao eh possivel criar o post. ";
	public static final String ERROR_GET_POST = "Requisicao invalida. O indice deve ser maior ou igual a zero.";
	
	public static final String CAUSA_USUARIO_NAO_CADASTRADO = "Um usuarix com email %s nao esta cadastradx.";
	public static final String CAUSA_USUARIO_SENHA_ERRADA = "Senha invalida.";
	public static final String CAUSA_USUARIO_SENHA_PROTEGIDA = "A senha dx usuarix eh protegida.";
	public static final String CAUSA_USUARIO_LOGADO = "Um usuarix ja esta logadx: %s.";
	public static final String CAUSA_USUARIO_DESLOGADO = "Nenhum usuarix esta logadx no +pop.";
	public static final String CAUSA_USUARIO_AINDA_LOGADO = "Um usuarix ainda esta logadx."; 
	
	public static final String CAUSA_NOME_VAZIO = "Nome dx usuarix nao pode ser vazio.";
	public static final String CAUSA_SENHA_INCORRETA = "A senha fornecida esta incorreta.";
	public static final String CAUSA_EMAIL_ERRADO = "Formato de e-mail esta invalido.";
	public static final String CAUSA_FORMATO_DATA_INVALIDA = "Formato de data esta invalida.";
	public static final String CAUSA_FORMATO_DATA_INEXISTENTE = "Data nao existe.";
	
	public static final String CAUSA_POST_GRANDE = "O limite maximo da mensagem sao 200 caracteres.";
	public static final String CAUSA_POST_HASTAGS = "As hashtags devem comecar com '#'. Erro na hashtag: '%s'.";
}
