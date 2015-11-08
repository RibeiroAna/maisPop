package management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.MensagensDeErro;
import core.usuario.AtributoUsuario;
import core.usuario.Usuario;
import exceptions.naoTrataveis.BadFormatException;
import exceptions.naoTrataveis.BadRequestException;
import exceptions.naoTrataveis.NotFoundException;
import exceptions.trataveis.UnauthorizedException;

public class Controller implements Serializable {

	private static final long serialVersionUID = 3227105040361512397L;
	List<Usuario> usuarios;
	private Usuario usuarioLogado;

	public Controller() {
		usuarios = new ArrayList<Usuario>();
	}

	public void atualizaPerfil(String atributo, String valor)
			throws BadRequestException, BadFormatException {
		if (usuarioLogado == null) {
			throw new BadRequestException(
					MensagensDeErro.ERROR_ATUALIZA_DESLOGADO,
					MensagensDeErro.CAUSA_USUARIO_DESLOGADO);
		}
		usuarioLogado.setAtributo(atributo, valor);
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha)
			throws Exception {
		usuarioLogado.setAtributo(atributo, valor, velhaSenha);
	}

	private Usuario getUsuarioByEmail(String email) throws NotFoundException {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		String mensagem = String.format(
				MensagensDeErro.CAUSA_USUARIO_NAO_CADASTRADO, email);
		throw new NotFoundException(mensagem);
	}

	public void login(String email, String senha) throws Exception {
		Usuario usuario;
		try {
			usuario = getUsuarioByEmail(email);
		} catch (NotFoundException e) {
			String mensagem = String.format(
					MensagensDeErro.CAUSA_USUARIO_NAO_CADASTRADO, email);

			throw new NotFoundException(MensagensDeErro.ERROR_LOGIN, mensagem);
		}
		usuario.login(email, senha);

		if (usuarioLogado != null) {
			String mensagem = String.format(
					MensagensDeErro.CAUSA_USUARIO_LOGADO,
					usuarioLogado.getAtributo(AtributoUsuario.NOME));
			throw new UnauthorizedException(MensagensDeErro.ERROR_LOGIN,
					mensagem);
		}
		usuarioLogado = usuario;
	}

	public void logout() throws Exception {
		if (usuarioLogado == null) {
			throw new BadRequestException(MensagensDeErro.ERROR_LOGOUT,
					MensagensDeErro.CAUSA_USUARIO_DESLOGADO);
		}
		usuarioLogado = null;
	}

	public void cadastrarUsuario(String nome, String email, String senha,
			String dataNasc, String imagem) throws Exception {
		Usuario usuario = new Usuario(nome, email, senha, dataNasc, imagem);
		usuarios.add(usuario);
	}

	/**
	 * @param atributoStr
	 *            é a string que o usuário digita como atributo
	 * @return um atributo no formato de atributo de usuário
	 */
	private AtributoUsuario strToAtributoUsuario(String atributoStr) {
		AtributoUsuario[] atributos = AtributoUsuario.values();
		for (AtributoUsuario atributoUsuario : atributos) {
			if (atributoUsuario.getAtributo().equals(atributoStr)) {
				return atributoUsuario;
			}
		}
		return null;
	}

	public String getInfoUsuario(String atributo, String email)
			throws Exception {
		Usuario usuario = getUsuarioByEmail(email);
		return usuario.getAtributo(strToAtributoUsuario(atributo));
	}

	public String getInfoUsuario(String atributo) throws Exception {
		return getInfoUsuario(atributo, usuarioLogado.getEmail());
	}

	public void removeUsuario(String email) throws Exception {
		Usuario usuario = getUsuarioByEmail(email);
		usuarios.remove(usuario);
	}

	public boolean isUsuarioLogado() {
		if (usuarioLogado == null) {
			return false;
		}
		return true;
	}

	public void criaPost(String post, List<String> hastags,
			List<String> audios, List<String> imagens, String data,
			String mensagem) {
		usuarioLogado.postar(post, hastags, audios, imagens, data, mensagem);
	}

	public String getPostByID(int id) {
		return usuarioLogado.getPostByID(id);
	}

	public String getPost(String atributo, int post) {
		return usuarioLogado.getPostAtributo(atributo, post);
	}

	public String getConteudoPost(int indice, int post) {
		return usuarioLogado.getConteudoPost(indice, post);
	}

	public void adicionaAmigo(String emailUsuario) throws NotFoundException {
		usuarioLogado.adicionarAmigo(getUsuarioByEmail(emailUsuario));
	}

	public int getNotificacoes() {
		return usuarioLogado.getNotificacoes();
	}

	public String getNextNotificacao() {
		return usuarioLogado.getNextNotificacao();
	}

	public void rejeitaAmizade(String email) {
		Usuario novoAmigo = getUsuarioByEmail(email);
		usuarioLogado.rejeitarAmigo(novoAmigo);
	}

	public int getQtdAmigos() {
		return usuarioLogado.getQtdAmigos();
	}
	
	public void aceitaAmizade(String email) {
		Usuario novoAmigo = getUsuarioByEmail(email);
		usuarioLogado.aceitarAmigo(novoAmigo);
	}

	public void curtir(String email, int indexPost) throws Exception {
		Usuario usuario = getUsuarioByEmail(email);
		usuario.curtir(indexPost, usuarioLogado.getAtributo(AtributoUsuario.NOME));
	}

	public void removeAmigo(String email) {
		Usuario usuario = getUsuarioByEmail(email);
		usuarioLogado.removeAmigo(usuario);
	}
}
