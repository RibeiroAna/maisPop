package core.usuario;

import java.util.LinkedList;
import java.util.List;

import core.Mural;
import core.Post;
import utils.MensagensDeErro;
import utils.ValidaDados;
import exceptions.trataveis.UnauthorizedException;

public class Usuario {

	private String nome;
	private String senha;
	private String email;
	private String imagemPerfilPath;
	private String dataNascimento;
	private Mural mural;

	/**
	 * Esta lista representa os pedidos de amizade que outros usuarios fazem ao
	 * atual usuário mas que ainda não foram aceitos.
	 */
	private List<Usuario> pedidosDeAmizadePedente;
	private List<Usuario> amigos;

	public Usuario(String nome, String email, String senha,
			String dataNascimento, String imagemPath) {
		
		validaDados(nome, email, dataNascimento);
		
		this.imagemPerfilPath = imagemPath;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		mural = new Mural();
		pedidosDeAmizadePedente = new LinkedList<Usuario>();
		amigos = new LinkedList<Usuario>();
	}

	/**
	 * Verifica se os dados do usuario estão no formato correto
	 * @param nome
	 * @param email
	 * @param dataNasc
	 */
	private void validaDados(String nome, String email, String dataNasc) {
		// Validando o nome
		ValidaDados.validaNome(nome);
		// validando a data
		ValidaDados.validaData(dataNasc);
		// Validando o email
		ValidaDados.validaEmail(email);
	}

	public void login(String email, String senha) throws UnauthorizedException {
		if (!senha.equals(this.senha)) {
			throw new UnauthorizedException(MensagensDeErro.LOGIN_SENHA_ERRADA);
		}
	}

	public void atualizarSenha(String novaSenha, String senhaAntiga) {
		if ((senhaAntiga == senha) && (novaSenha != null)) {
			senha = novaSenha;
		} else {
			// TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}

	public void atualizarNome(String novoNome) {
		if ((novoNome != null)) {
			nome = novoNome;
		} else {
			// TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}

	public void atualizarEmail(String novoEmail) {
		if ((novoEmail != null)) {
			email = novoEmail;
		} else {
			// TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}

	public void aceitarAmigo(Usuario novoAmigo) {
		if (pedidosDeAmizadePedente.contains(novoAmigo)) {
			novoAmigo.amigos.add(this);
			this.amigos.add(novoAmigo);
			this.pedidosDeAmizadePedente.remove(novoAmigo);
		} else {
			// TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}

	public void rejeitarAmigo(Usuario novoAmigo) {
		if ((pedidosDeAmizadePedente.contains(novoAmigo))) {
			this.pedidosDeAmizadePedente.remove(novoAmigo);
		} else {
			// TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}

	public void adicionarAmigo(Usuario novoAmigo) {
		novoAmigo.pedidosDeAmizadePedente.add(this);
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

	public void postar(Post post) {
		mural.adicionarPostagem(post);
	}

	public void deletarPostagem(Post post) {
		mural.deletarPostagem(post);
	}

	public int getPopularidade() {
		return mural.calculaPopularidade();
	}

	public void curtirPost(Post post) {
		post.curtir();
	}

	public void reijeitarPost(Post post) {
		post.rejeitar();
	}

	private String getDataFormatada() {
		String dia = dataNascimento.split("/")[0];
		String mes = dataNascimento.split("/")[1];
		String ano = dataNascimento.split("/")[2];
		return ano + "-" + mes + "-" + dia;
	}

	public String getAtributo(AtributoUsuario atributo) throws Exception {
		switch (atributo) {
		case NOME:
			return nome;
		case DATA_NASC:
			return getDataFormatada();
		case SENHA:
			throw new UnauthorizedException(
					MensagensDeErro.GET_DADOS_SENHA_PROTEGIDA);
		case FOTO:
			return imagemPerfilPath;
		}
		return null;
	}

	public String getEmail() {
		return email;
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

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", senha=" + senha + ", email="
				+ email + ", imagemPerfilPath=" + imagemPerfilPath
				+ ", dataNascimento=" + dataNascimento + ", mural=" + mural
				+ ", pedidosDeAmizadePedente=" + pedidosDeAmizadePedente
				+ ", amigos=" + amigos + "]";
	}
}
