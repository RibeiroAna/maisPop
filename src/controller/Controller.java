package controller;

import java.util.ArrayList;
import java.util.List;

import core.Usuario;

public class Controller {
	
	List<Usuario>usuarios;
	
	public Controller() {
		usuarios = new ArrayList<Usuario>();
	}
	
	public void adicionarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public Usuario buscarUsuario(String email) {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail() == email) {
				return usuario;
			}
		}
		//TODO melhorar as exceções
		throw new RuntimeException();
	}

	public void login(String email, String senha) {
		// TODO Auto-generated method stub
		
	}

	public void logout() {
		// TODO Auto-generated method stub
		
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNasc, String imagem) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInfoUsuario(String atributo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInfoUsuario(String atributo, String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeUsuario(String email) {
		// TODO Auto-generated method stub
		
	}
}
