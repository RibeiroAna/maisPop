package management;

import utils.MensagensDeErro;
import core.usuario.AtributoUsuario;
import core.usuario.Usuario;
import exceptions.naoTrataveis.BadRequestException;
import exceptions.trataveis.UnauthorizedException;

/**
 * @author ana
 *
 */
public class Facade {

	private Controller controller;
	private Usuario usuarioLogado;
	
	private static final String DEFAULT_PROFILE_IMAGE_PATH = "resources/default.jpg";

	public void iniciaSistema() {
		controller = new Controller();
		//TODO leitura de dados
	}

	public void fechaSistema() throws Exception {
		if (usuarioLogado != null) {
			throw new BadRequestException(MensagensDeErro.FECHA_SISTEMA_COM_USUARIO);
		}
		//TODO gravar dados
		controller = null;
	}

	public void login(String email, String senha) throws Exception {
		if (usuarioLogado != null) {
			String mensagem = String.format(MensagensDeErro.LOGIN_USUARIO_LOGADO.getMesagem(), 
					usuarioLogado.getAtributo(AtributoUsuario.NOME));
			throw new UnauthorizedException(mensagem);
		}
		usuarioLogado = controller.login(email, senha);
	}

	public void logout() throws Exception {
		if (usuarioLogado == null) {
			throw new BadRequestException(MensagensDeErro.LOGOUT_SEM_USUARIO);
		}
		usuarioLogado = null;
	}

	/**
	 * @param nome
	 * @param email
	 * @param senha
	 * @param dataNasc
	 * @param imagem
	 * @return O id único do usuário, que no caso é o email
	 * @throws Exception
	 */
	public String cadastrarUsuario(String nome, String email, String senha,
			String dataNasc, String imagem) throws Exception {
		controller.cadastrarUsuario(nome, email, senha, dataNasc, imagem);
		return email;
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNasc) throws Exception {
		return cadastrarUsuario(nome, email, senha, dataNasc, DEFAULT_PROFILE_IMAGE_PATH);
	}

	public String getInfoUsuario(String atributo) throws Exception {
		return getInfoUsuario(atributo, usuarioLogado.getEmail());
	}

	public String getInfoUsuario(String atributo, String idUsuario)
			throws Exception {
		return controller.getInfoUsuario(atributo, idUsuario);
	}

	public void removeUsuario(String email) throws Exception {
		controller.removeUsuario(email);
	}

	public void atualizaPerfil(String atributo, String valor) throws Exception {
		// sistemaPop.atualizaPerfil(atributo, valor);
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha)
			throws Exception {
		// sistemaPop.atualizaPerfil(atributo, valor, velhaSenha);
	}

	public void criaPost(String mensagem, String data) throws Exception {
		// sistemaPop.qcriaPost(mensagem, data);
	}

	public String getPost(int post) {
		// return sistemaPop.getPost(post);
		return null;
	}

	public String getPost(String atributo, int post) {
		// return sistemaPop.getPost(atributo, post);
		return null;
	}

	public String getConteudoPost(int indice, int post) throws Exception {
		return null;
	}

	public void curtir(int indexPost, String email) {
		// sistemaPop.curtir(indexPost, email);
	}

	public void rejeitar(int indexPost, String email) {
		// sistemaPop.rejeitar(indexPost, email);
	}

}