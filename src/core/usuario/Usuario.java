package core.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.MensagensDeErro;
import utils.MensagensDeNotificacao;
import utils.ValidaDados;
import core.post.Mural;
import core.usuario.tipoPop.CelebridadePop;
import core.usuario.tipoPop.IconePop;
import core.usuario.tipoPop.Normal;
import core.usuario.tipoPop.TipoPop;
import exceptions.naoTrataveis.BadFormatException;
import exceptions.naoTrataveis.BadRequestException;
import exceptions.trataveis.UnauthorizedException;

public class Usuario implements Serializable {

	private static final long serialVersionUID = -5997328240008153153L;
	private String nome;
	private String senha;
	private String email;
	private String imagemPerfilPath;
	private String dataNascimento;
	private Mural mural;

	private int pops;
	private List<String> notificacoes;

	private TipoPop tipoPop;

	/**
	 * Esta lista representa os pedidos de amizade que outros usuarios fazem ao
	 * atual usuário mas que ainda não foram aceitos.
	 */
	private List<Usuario> pedidosDeAmizadePedente;
	private List<Usuario> amigos;

	public Usuario(String nome, String email, String senha,
			String dataNascimento, String imagemPath) {
		validaDados(nome, email, dataNascimento);
		this.pops = 0;
		this.imagemPerfilPath = imagemPath;
		this.tipoPop = new Normal();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		mural = new Mural();
		pedidosDeAmizadePedente = new ArrayList<Usuario>();
		amigos = new ArrayList<Usuario>();
		notificacoes = new ArrayList<String>();
	}

	/**
	 * Verifica se os dados do usuario estão no formato correto
	 * 
	 * @param nome
	 * @param email
	 * @param dataNasc
	 */
	private void validaDados(String nome, String email, String dataNasc) {
		// Validando o nome
		ValidaDados.validaNome(nome, MensagensDeErro.ERROR_CADASTRO);
		// validando a data
		ValidaDados.validaData(dataNasc, MensagensDeErro.ERROR_CADASTRO);
		// Validando o email
		ValidaDados.validaEmail(email, MensagensDeErro.ERROR_CADASTRO);
	}

	public TipoPop getTipoPop() {
		return tipoPop;
	}

	public void login(String email, String senha) throws UnauthorizedException {
		if (!senha.equals(this.senha)) {
			throw new UnauthorizedException(MensagensDeErro.ERROR_LOGIN,
					MensagensDeErro.CAUSA_USUARIO_SENHA_ERRADA);
		}
	}

	public void atualizarSenha(String novaSenha, String senhaAntiga) {
		if ((senhaAntiga == senha) && (novaSenha != null)) {
			senha = novaSenha;
		} else {
			throw new BadRequestException(MensagensDeErro.ERROR_ATUALIZA,
					MensagensDeErro.CAUSA_SENHA_INCORRETA);
		}
	}

	public void aceitarAmigo(Usuario novoAmigo) {
		if (pedidosDeAmizadePedente.contains(novoAmigo)) {
			novoAmigo.amigos.add(this);
			this.amigos.add(novoAmigo);
			this.pedidosDeAmizadePedente.remove(novoAmigo);

			String msgNot = String.format(
					MensagensDeNotificacao.NOTIFICACAO_AMIZADE_ACEITACAO,
					this.nome);
			novoAmigo.notificacoes.add(msgNot);
		} else {
			String msg = String.format(
					MensagensDeErro.ERROR_AMIZADE_NAO_PEDIDA, novoAmigo.nome);
			throw new BadRequestException(msg);
		}
	}

	public void rejeitarAmigo(Usuario novoAmigo) {
		if ((pedidosDeAmizadePedente.contains(novoAmigo))) {
			this.pedidosDeAmizadePedente.remove(novoAmigo);
			String msgNot = String.format(
					MensagensDeNotificacao.NOTIFICACAO_AMIZADE_RECUSA,
					this.nome);
			novoAmigo.notificacoes.add(msgNot);
		} else {
			String msg = String.format(
					MensagensDeErro.ERROR_AMIZADE_NAO_PEDIDA, novoAmigo.nome);
			throw new BadRequestException(msg);
		}
	}

	public void adicionarAmigo(Usuario novoAmigo) {
		novoAmigo.pedidosDeAmizadePedente.add(this);
		String msg = String.format(
				MensagensDeNotificacao.NOTIFICACAO_AMIZADE_PEDIDO, this.nome);
		novoAmigo.notificacoes.add(msg);
	}

	public void excluirAmigo(Usuario exAmigo) {
		if (amigos.contains(exAmigo)) {
			amigos.remove(exAmigo);
			exAmigo.amigos.remove(amigos);
		} else {
			// TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}

	public void postar(String post, List<String> hastags, List<String> audios,
			List<String> imagens, String data, String mensagem) {
		mural.adicionarPostagem(post, hastags, audios, imagens, data, mensagem);
	}

	/*
	 * public void deletarPostagem() { mural.deletarPostagem(post); }
	 * 
	 * public int getPopularidade() { return mural.calculaPopularidade(); }
	 * 
	 * public void curtirPost(Post post) { post.curtir(); }
	 * 
	 * public void reijeitarPost(Post post) { post.rejeitar(); }
	 */

	private String getDataFormatada() {
		String dia = dataNascimento.split("/")[0];
		String mes = dataNascimento.split("/")[1];
		String ano = dataNascimento.split("/")[2];
		return ano + "-" + mes + "-" + dia;
	}

	public String getAtributo(AtributoUsuario atributo)
			throws UnauthorizedException {
		switch (atributo) {
		case NOME:
			return nome;
		case DATA_NASC:
			return getDataFormatada();
		case SENHA:
			throw new UnauthorizedException(
					MensagensDeErro.CAUSA_USUARIO_SENHA_PROTEGIDA);
		case FOTO:
			return imagemPerfilPath;
		default:
			break;
		}
		return null;
	}

	public void setAtributo(String atributo, String valor)
			throws BadFormatException {
		switch (atributo) {
		case "Nome":
			ValidaDados.validaNome(valor, MensagensDeErro.ERROR_ATUALIZA);
			nome = valor;
			break;
		case "Data de Nascimento":
			ValidaDados.validaData(valor, MensagensDeErro.ERROR_ATUALIZA);
			dataNascimento = valor;
			break;
		case "Foto":
			imagemPerfilPath = valor;
			break;
		case "E-mail":
			ValidaDados.validaEmail(valor, MensagensDeErro.ERROR_ATUALIZA);
			email = valor;
			break;
		case "Senha":
			senha = valor;
			break;
		default:
			break;
		}
	}

	public void setAtributo(String atributo, String valor, String velhaSenha)
			throws Exception {
		if (velhaSenha.equals(this.senha)) {
			this.setAtributo(atributo, valor);
		} else {
			throw new UnauthorizedException(MensagensDeErro.ERROR_ATUALIZA,
					MensagensDeErro.CAUSA_SENHA_INCORRETA);
		}
	}

	public String getEmail() {
		return email;
	}

	public int getNotificacoes() {
		return notificacoes.size();
	}

	public String getNextNotificacao() {
		if (notificacoes.size() == 0) {
			throw new BadRequestException(MensagensDeErro.ERROR_GET_NOTIFICACAO);
		}
		String mensagem = notificacoes.get(0);
		notificacoes.remove(mensagem);
		return mensagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public String getPostByID(int id) {
		return mural.getPostByID(id);
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", senha=" + senha + ", email="
				+ email + ", imagemPerfilPath=" + imagemPerfilPath
				+ ", dataNascimento=" + dataNascimento + ", mural=" + mural
				+ ", pedidosDeAmizadePedente=" + pedidosDeAmizadePedente
				+ ", amigos=" + amigos + "]";
	}

	public String getPostAtributo(String atributo, int post) {
		return mural.getPostAtributo(atributo, post);
	}

	public String getConteudoPost(int indice, int post) {
		return mural.getConteudoPost(indice, post);
	}

	public int getQtdAmigos() {
		return amigos.size();
	}

	public void curtir(int indexPost, String nome, int pontosPop,
			List<String> hastags) {
		String data = mural.curtir(indexPost, pontosPop, hastags);
		String msg = String.format(
				MensagensDeNotificacao.NOTIFICACAO_CURTIR_POST, nome, data);
		notificacoes.add(msg);
	}

	public void removeAmigo(Usuario usuario) {
		if (amigos.contains(usuario)) {
			amigos.remove(usuario);
			String msg = String.format(
					MensagensDeNotificacao.NOTIFICACAO_AMIZADE_REMOVIDA, nome);
			usuario.notificacoes.add(msg);
			usuario.amigos.remove(this);
		} else {
			throw new BadRequestException();
		}
	}
	
	public int getPop() {
		this.pops += mural.calculaPopularidade();
		return pops;
	}

	public void adicionaPops(int pops) {
		this.pops += pops;
		if (this.pops < 500) {
			tipoPop = new Normal();
		} else if ((this.pops >= 500) && (this.pops <= 1000)) {
			tipoPop = new CelebridadePop();
		} else if (this.pops > 1000) {
			tipoPop = new IconePop();
		}
	}

	public String getPopularidade() {
		return tipoPop.getClassePopularidade();
	}

	public void rejeitar(int indexPost, String atributo, int pontosPop,
			List<String> hastags) {
		String data = mural.rejeitar(indexPost, pontosPop, hastags);
		String msg = String.format(
				MensagensDeNotificacao.NOTIFICACAO_REJEITAR_POST, nome, data);
		notificacoes.add(msg);

	}

	public int getPopPost(int post) {
		return mural.getPopPost(post);
	}

	public int qtdCurtidasDePost(int post) {
		return mural.qtdCurtidasDePost(post);
	}

	public int qtdRejeicoesDePost(int post) {
		return mural.qtdRejeicoesDePost(post);
	}

}
