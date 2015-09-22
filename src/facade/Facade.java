package facade;

import core.management.MaisPopController;

public class MaisPopFacade {
	
	private MaisPopController sistemaPop;
	
	public MaisPopFacade(){
		sistemaPop = new MaisPopController ();
	}
	
	public void iniciaSistema(){
		//sistemaPop.iniciaSistema();
	}
	public void fechaSistema() throws Exception {
		//sistemaPop.fechaSistema();
	}
	
	public void login(String email, String senha) throws Exception{
		sistemaPop.login(email, senha);
	}
	
	public void logout() throws Exception{
		sistemaPop.logout();
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem) throws Exception{
		return sistemaPop.cadastraUsuario(nome, email, senha, dataNasc, imagem);
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc) throws Exception{
		return this.cadastraUsuario(nome, email, senha, dataNasc, "resources/default.jpg");
	}
	
	public String getInfoUsuario(String atributo) throws Exception{
		return sistemaPop.getInfoUsuario(atributo);
	}
	
	public String getInfoUsuario(String atributo, String usuario) throws Exception{
		return sistemaPop.getInfoUsuario(atributo, usuario);
	}
	
	public void removeUsuario(String email) throws Exception{
		sistemaPop.removeUsuario(email);
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