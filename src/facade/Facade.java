package facade;

import controller.Controller;

public class Facade {
	
	private Controller controller;
	
	public Facade(){
		controller = new Controller ();
	}
	
	public void iniciaSistema(){
		//sistemaPop.iniciaSistema();
	}
	public void fechaSistema() throws Exception {
		//sistemaPop.fechaSistema();
	}
	
	public void login(String email, String senha) throws Exception{
		controller.login(email, senha);
	}
	
	public void logout() throws Exception{
		controller.logout();
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem) throws Exception{
		return controller.cadastraUsuario(nome, email, senha, dataNasc, imagem);
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc) throws Exception{
		return this.cadastraUsuario(nome, email, senha, dataNasc, "resources/default.jpg");
	}
	
	public String getInfoUsuario(String atributo) throws Exception{
		return controller.getInfoUsuario(atributo);
	}
	
	public String getInfoUsuario(String atributo, String usuario) throws Exception{
		return controller.getInfoUsuario(atributo, usuario);
	}
	
	public void removeUsuario(String email) throws Exception{
		controller.removeUsuario(email);
	}
	
	public void atualizaPerfil(String atributo, String valor) throws Exception{
		//sistemaPop.atualizaPerfil(atributo, valor);
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws Exception{
		//sistemaPop.atualizaPerfil(atributo, valor, velhaSenha);
	}
	
	public void criaPost(String mensagem, String data) throws Exception{
		//sistemaPop.qcriaPost(mensagem, data);
	}
	
	public String getPost(int post){
		//return sistemaPop.getPost(post);
		return null;
	}
	
	public String getPost(String atributo, int post){
		//return sistemaPop.getPost(atributo, post);
		return null;
	}
	
	public String getConteudoPost(int indice, int post) throws Exception{
		return null;
	}
	
	
	public void curtir(int indexPost, String email) {
		//sistemaPop.curtir(indexPost, email);
	}
	
	public void rejeitar(int indexPost, String email) {
		//sistemaPop.rejeitar(indexPost, email);
	}

}