package facade;

import utils.MensagensDeErro;
import controller.Controller;
import core.usuario.AtributoUsuario;
import core.usuario.Usuario;
import exceptions.naoTrataveis.BadRequestException;
import exceptions.trataveis.UnauthorizedException;

public class Facade {

	private Controller controller;
	private Usuario usuarioLogado;

	public void iniciaSistema() {
		controller = new Controller();
	}

	public void fechaSistema() throws Exception {
		if (usuarioLogado != null) {
			throw new BadRequestException(MensagensDeErro.FECHA_SISTEMA_COM_USUARIO);
		}
		controller = null;
	}

	public void login(String email, String senha) throws Exception {
		if (usuarioLogado != null) {
			String mensagem = String.format(MensagensDeErro.LOGIN_USUARIO_LOGADO.getMessage(), 
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

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNasc, String imagem) throws Exception {
		controller.cadastraUsuario(nome, email, senha, dataNasc, imagem);
		return email;
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNasc) throws Exception {
		return cadastraUsuario(nome, email, senha, dataNasc, null);
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