package management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.MensagensDeErro;
import core.usuario.AtributoUsuario;
import core.usuario.Usuario;
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

	public void atualizaPerfil(String atributo, String valor, Usuario usuario) {
		usuarios.remove(usuario);
		usuario.setAtributo(strToAtributo(atributo), valor);
		usuarios.add(usuario);
	}
	
	public void atualizaPerfil(String atributo, String senhaAntiga, 
			String novaSenha, Usuario usuario) {
		usuarios.remove(usuario);
		usuario.atualizarSenha(novaSenha, senhaAntiga);
		usuarios.add(usuario);
	}


	private Usuario getUsuarioByEmail(String email) throws Exception {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		String mensagem = String.format(
				MensagensDeErro.CAUSA_USUARIO_NAO_CADASTRADO, email);
		throw new NotFoundException(mensagem);
	}

	public void login(String email, String senha)
			throws Exception {
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
			String mensagem = String.format(MensagensDeErro.CAUSA_USUARIO_LOGADO, 
					usuarioLogado.getAtributo(AtributoUsuario.NOME));
			throw new UnauthorizedException(MensagensDeErro.ERROR_LOGIN, mensagem);
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
	private AtributoUsuario strToAtributo(String atributoStr) {
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
		return usuario.getAtributo(strToAtributo(atributo));
	}
	
	public String getInfoUsuario(String atributo) throws Exception {
		return getInfoUsuario(atributo, usuarioLogado.getEmail());
	}

	public void removeUsuario(String email) throws Exception {
		Usuario usuario = getUsuarioByEmail(email);
		usuarios.remove(usuario);
	}
	
	public boolean isUsuarioLogado() {
		if(usuarioLogado.equals(null)) {
			return false;
		}
		return true;
	}
}
