package management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.MensagensDeErro;
import core.post.TrendingTop;
import core.usuario.AtributoUsuario;
import core.usuario.Usuario;
import exceptions.naoTrataveis.BadFormatException;
import exceptions.naoTrataveis.BadRequestException;
import exceptions.naoTrataveis.NotFoundException;
import exceptions.trataveis.UnauthorizedException;

public class Controller implements Serializable {

	private static final long serialVersionUID = 3227105040361512397L;
	private List<Usuario> usuarios;
	private List<TrendingTop> trendingTopics;
	private Usuario usuarioLogado;

	public Controller() {
		usuarios = new ArrayList<Usuario>();
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
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
			String dataNasc, String imagem) {
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

	private void atualizaHastags() {
		List<List<String>> hastags = getHastags();
		trendingTopics = new ArrayList<TrendingTop>();

		for (List<String> hastagsUser : hastags) {
			for (String hastagUser : hastagsUser) {
				String[] hastagArray = hastagUser.split(",");
				for (int i = 0; i < hastagArray.length; i++) {
					if (hastagArray[i].isEmpty()) 
						break;
					TrendingTop tt = new TrendingTop(hastagArray[i], 1);
					if (trendingTopics.contains(tt)) {
						int indexOfTT = trendingTopics.indexOf(tt);
						trendingTopics.get(indexOfTT).votar();
					} else {
						trendingTopics.add(tt);
					}
				}
			}
		}
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

	public void curtir(String email, int indexPost) throws NotFoundException,
			UnauthorizedException {
		Usuario usuario = getUsuarioByEmail(email);
		List<String> curtirHastags = usuarioLogado.getTipoPop()
				.getCurtirHastags();
		usuario.curtir(indexPost, usuarioLogado
				.getAtributo(AtributoUsuario.NOME), usuarioLogado.getTipoPop()
				.getPontosPop(), curtirHastags);
	}

	public void removeAmigo(String email) {
		Usuario usuario = getUsuarioByEmail(email);
		usuarioLogado.removeAmigo(usuario);
	}

	public void adicionaPops(int pops) {
		usuarioLogado.adicionaPops(pops);
	}

	public String getPopularidade() {
		return usuarioLogado.getPopularidade();
	}

	public void rejeitarPost(String email, int indexPost)
			throws NotFoundException, UnauthorizedException {
		Usuario usuario = getUsuarioByEmail(email);
		List<String> rejeitarHastags = usuarioLogado.getTipoPop()
				.getRejeitarHastags();
		usuario.rejeitar(indexPost, usuarioLogado
				.getAtributo(AtributoUsuario.NOME), usuarioLogado.getTipoPop()
				.getPontosPop(), rejeitarHastags);
	}

	public int getPopPost(int post) {
		return usuarioLogado.getPopPost(post);
	}

	public int qtdCurtidasDePost(int post) {
		return usuarioLogado.qtdCurtidasDePost(post);
	}

	public int qtdRejeicoesDePost(int post) {
		return usuarioLogado.qtdRejeicoesDePost(post);
	}

	public int getPopsUsuario(String email) throws BadRequestException {
		if (isUsuarioLogado()) {
			throw new BadRequestException(MensagensDeErro.ERROR_CONSULTA_POPS,
					MensagensDeErro.CAUSA_USUARIO_AINDA_LOGADO);
		}
		return getUsuarioByEmail(email).getPop();
	}

	public int getPopsUsuario() {
		return usuarioLogado.getPop();
	}

	public String getTrendingTopics() {
		atualizaHastags();
		Collections.sort(trendingTopics);
		StringBuilder top3 = new StringBuilder("Trending Topics: ");
		
		for (int i = 0; i < 3; i ++) {
			top3.append(String.format(" (%d) ", 1 + i));
			top3.append(trendingTopics.get(i).toString());
		}
		
		return top3.toString();
	}

	private List<List<String>> getHastags() {
		List<List<String>> hastags = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			hastags.add(usuario.getHastags());
		}
		return hastags;
	}

	public String atualizaRanking() {
		Collections.sort(usuarios);
		StringBuilder populares = new StringBuilder("Mais Populares: ");
		populares.append("(1) " + usuarios.get(usuarios.size() - 1) +  "; ");
		populares.append("(2) " + usuarios.get(usuarios.size() - 2) +  "; ");
		populares.append("(3) " + usuarios.get(usuarios.size() - 3) +  "; ");
		
		populares.append("| Menos Populares: ");
		populares.append("(1) " + usuarios.get(0) +  "; ");
		populares.append("(2) " + usuarios.get(1) +  "; ");
		populares.append("(3) " + usuarios.get(2) +  ";");

		return populares.toString();
	}
}
