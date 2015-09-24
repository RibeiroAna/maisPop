package controller;

import java.util.ArrayList;
import java.util.List;

import utils.MensagensDeErro;
import core.usuario.AtributoUsuario;
import core.usuario.Usuario;
import exceptions.naoTrataveis.NotFoundException;
import exceptions.trataveis.UnauthorizedException;

public class Controller {

	List<Usuario> usuarios;

	public Controller() {
		usuarios = new ArrayList<Usuario>();
	}

	public void adicionarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	private Usuario getUsuarioByEmail(String email) {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		String mensagem = String.format(
				MensagensDeErro.GET_DADOS_EMAIL_NOT_FOUND.getMessage(), email);
		throw new NotFoundException(mensagem);
	}

	public Usuario login(String email, String senha)
			throws UnauthorizedException {
		Usuario usuario;
		try {
			usuario = getUsuarioByEmail(email);
		} catch (NotFoundException e) {
			String mensagem = String.format
					(MensagensDeErro.LOGIN_ERROR_NOT_FOUND.getMessage(), email);
			throw new NotFoundException(mensagem);
		}
		usuario.login(email, senha);
		return usuario;
	}

	public void cadastraUsuario(String nome, String email, String senha,
			String dataNasc, String imagem) {
		Usuario usuario = new Usuario(nome, email, senha, dataNasc, imagem);
		usuarios.add(usuario);
	}

	public AtributoUsuario strToAtributo(String atributoStr) {
		AtributoUsuario[] atributos = AtributoUsuario.values();
		for (AtributoUsuario atributoUsuario : atributos) {
			if (atributoUsuario.getAtributo().equals(atributoStr)) {
				return atributoUsuario;
			}
		}
		return null;
	}

	public String getInfoUsuario(String atributo, String email) throws Exception {
		Usuario usuario = getUsuarioByEmail(email);
		return usuario.getAtributo(strToAtributo(atributo));
	}

	public void removeUsuario(String email) {
		Usuario usuario = getUsuarioByEmail(email);
		usuarios.remove(usuario);
	}
}
