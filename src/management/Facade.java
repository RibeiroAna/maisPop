package management;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import utils.MensagensDeErro;
import utils.TrataPost;
import exceptions.naoTrataveis.BadRequestException;
import exceptions.naoTrataveis.NotFoundException;
import exceptions.trataveis.UnauthorizedException;

/**
 * @author ana
 *
 */
public class Facade {

	protected Controller controller;

	private static final String DEFAULT_PROFILE_IMAGE_PATH = "resources/default.jpg";
	private static final String FILE_SYSTEM_PATH = "backupSistema/sistemaPop";

	public void iniciaSistema() {
		try {
			FileInputStream arquivoLeitura = new FileInputStream(
					FILE_SYSTEM_PATH);
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
			controller = (Controller) objLeitura.readObject();
			objLeitura.close();
			arquivoLeitura.close();
		} catch (Exception e) {
			// ignorando possíveis exceções
		}
		if (controller == null) {
			controller = new Controller();
		}
	}

	public void fechaSistema() throws BadRequestException {
		if (controller.isUsuarioLogado()) {
			throw new BadRequestException(MensagensDeErro.ERROR_FECHA_SISTEMA,
					MensagensDeErro.CAUSA_USUARIO_AINDA_LOGADO);
		}
		try {
			BufferedOutputStream arquivoGrav = new BufferedOutputStream(
					new FileOutputStream(FILE_SYSTEM_PATH));
			ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
			objGravar.writeObject(controller);
			objGravar.flush();
			objGravar.close();
			arquivoGrav.flush();
			arquivoGrav.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller = null;
	}

	public void login(String email, String senha) throws Exception {
		controller.login(email, senha);
	}

	public void logout() throws Exception {
		controller.logout();
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
	public String cadastraUsuario(String nome, String email, String senha,
			String dataNasc, String imagem) {
		controller.cadastrarUsuario(nome, email, senha, dataNasc, imagem);
		return email;
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNasc) {
		return cadastraUsuario(nome, email, senha, dataNasc,
				DEFAULT_PROFILE_IMAGE_PATH);
	}

	public String getInfoUsuario(String atributo) throws Exception {
		return controller.getInfoUsuario(atributo);
	}

	public String getInfoUsuario(String atributo, String idUsuario)
			throws Exception {
		return controller.getInfoUsuario(atributo, idUsuario);
	}

	public void removeUsuario(String email) throws Exception {
		controller.removeUsuario(email);
	}

	public void atualizaPerfil(String atributo, String valor) throws Exception {
		controller.atualizaPerfil(atributo, valor);
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha)
			throws Exception {
		controller.atualizaPerfil(atributo, valor, velhaSenha);
	}

	public void criaPost(String mensagem, String data) throws Exception {
		String post = TrataPost.getMensagem(mensagem);
		java.util.List<String> hastags = TrataPost.getHastags(mensagem);
		java.util.List<String> audios = TrataPost.getAudios(mensagem);
		java.util.List<String> imagens = TrataPost.getImagens(mensagem);
		String dataFormatada = TrataPost.formataData(data);
		String mensagemFormatada = mensagem + " (" + dataFormatada + ")";
		controller.criaPost(post, hastags, audios, imagens, dataFormatada,
				mensagemFormatada);
	}

	public String getPost(int post) {
		return controller.getPostByID(post);
	}

	public String getPost(String atributo, int post) {
		return controller.getPost(atributo, post);
	}

	public String getConteudoPost(int indice, int post) throws Exception {
		return controller.getConteudoPost(indice, post);
	}

	public void adicionaAmigo(String emailUsuario) throws NotFoundException {
		controller.adicionaAmigo(emailUsuario);
	}

	public int getNotificacoes() {
		return controller.getNotificacoes();
	}

	public String getNextNotificacao() {
		return controller.getNextNotificacao();
	}

	public void rejeitaAmizade(String email) {
		controller.rejeitaAmizade(email);
	}

	public void aceitaAmizade(String email) {
		controller.aceitaAmizade(email);
	}

	public int getQtdAmigos() {
		return controller.getQtdAmigos();
	}

	public void curtirPost(String email, int indexPost)
			throws NotFoundException, UnauthorizedException {
		controller.curtir(email, indexPost);
	}

	public void rejeitarPost(String email, int indexPost)
			throws NotFoundException, UnauthorizedException {
		controller.rejeitarPost(email, indexPost);
	}

	public void removeAmigo(String email) {
		controller.removeAmigo(email);
	}

	public void adicionaPops(int pops) {
		controller.adicionaPops(pops);
	}

	public String getPopularidade() {
		return controller.getPopularidade();
	}
	
	public int getPopsPost(int post) {
		return controller.getPopPost(post);
	}
	
	public int qtdCurtidasDePost(int post) {
		return controller.qtdCurtidasDePost(post);
	}
	
	public int qtdRejeicoesDePost(int post) {
		return controller.qtdRejeicoesDePost(post);
	}
	
	public int getPopsUsuario(String email) {
		return controller.getPopsUsuario(email);
	}
	
	public int getPopsUsuario() {
		return controller.getPopsUsuario();
	}
	
	public String atualizaRanking() {
		return controller.atualizaRanking();
	}
	
	public String atualizaTrendingTopics() {
		return controller.getTrendingTopics();
	}
	
	public int getTotalPosts() {
		return controller.getTotalPosts();
	}
	
	public String getPostFeedNoticiasMaisPopulares(int post) {
		return controller.getPostFeedNoticiasMaisPopulares(post);
	}
}