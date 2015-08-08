package core.user;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {

	private static final String DEFAULT_PROFILE_IMAGE_PATH = "imagem/perfil.png";

	private String nome;
	private String senha;
	private String email;
	private String imagemPerfilPath;
	private Date dataNascimento;
	private boolean logado;
	private Mural mural;
	/**
	 * Esta lista representa os pedidos de amizade que outros usuarios me
	 * fizeram mas eu ainda não aceitei
	 */
	private List<Usuario> pedidosDeAmizadePedente;
	private List<Usuario> amigos;

	public Usuario(String nome, String email, String senha,
			Date dataNascimento, String imagemPath) {
		if (imagemPath == null) {
			this.imagemPerfilPath = DEFAULT_PROFILE_IMAGE_PATH;
		} else {
			this.imagemPerfilPath = imagemPath;
		}
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		//Assim que o usuário faz o cadastro, ele fica logado por default
		logado = true;
		mural = new Mural();
		pedidosDeAmizadePedente = new LinkedList<Usuario>();
		amigos = new LinkedList<Usuario>();
	}
	
	public void login(String email, String senha) {
		if((email == this.email) && (senha == this.senha)) {
			logado = true;
		} else {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}
	
	public void logout() {
		logado = false;
	}
	
	public void atualizarSenha(String novaSenha, String senhaAntiga) {
		if ((logado) && (senhaAntiga == senha) && (novaSenha != null)) {
			senha = novaSenha;
		} else {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}
	
	public void atualizarNome(String novoNome) {
		if ((logado) && (novoNome != null)) {
			nome = novoNome;
		} else {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}
	
	public void atualizarEmail(String novoEmail) {
		if ((logado) && (novoEmail != null)) {
			email = novoEmail;
		} else {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}
	
	public void aceitarAmigo(Usuario novoAmigo) {
		if((logado) && (pedidosDeAmizadePedente.contains(novoAmigo))) {
			novoAmigo.amigos.add(this);
			this.amigos.add(novoAmigo);
			this.pedidosDeAmizadePedente.remove(novoAmigo);
		} else {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}
	
	public void rejeitarAmigo(Usuario novoAmigo) {
		if((logado) && (pedidosDeAmizadePedente.contains(novoAmigo))) {
			this.pedidosDeAmizadePedente.remove(novoAmigo);
		} else {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}
	
	public void adicionarAmigo(Usuario novoAmigo) {
		if(!logado) {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
		novoAmigo.pedidosDeAmizadePedente.add(this);
	}
	
	public void excluirAmigo(Usuario exAmigo) {
		if((logado) && (amigos.contains(exAmigo))) {
			amigos.remove(exAmigo);
			exAmigo.amigos.remove(amigos);
		} else {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
	}
	
	public void postar(Post post) {
		if(!logado) {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
		mural.adicionarPostagem(post);
	}
	
	public void deletarPostagem(Post post) {
		if(!logado) {
			//TODO tratar melhor as exceções com hierarquias de exception, etc
			throw new RuntimeException();
		}
		mural.deletarPostagem(post);
	}
	
	public int getPopularidade() {
		return mural.calculaPopularidade();
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
				+ ", dataNascimento=" + dataNascimento + ", logado=" + logado
				+ ", mural=" + mural + ", pedidosDeAmizadePedente="
				+ pedidosDeAmizadePedente + ", amigos=" + amigos + "]";
	}
	
}
