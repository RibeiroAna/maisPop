package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mural implements Serializable{

	
	private static final long serialVersionUID = 6632999261405977535L;
	private List<Post> postagens  = new ArrayList<Post>();

	public Mural() {
		postagens = new ArrayList<Post>();
	}
	
	public void adicionarPostagem(String postStr, List<String> hastags,
			List<String> audios, List<String> imagens, String data, String mensagem) {
		Post post = new Post(postStr, hastags, audios, imagens, data, mensagem);
		postagens.add(post);
	}

	public void deletarPostagem(Post post) {
		postagens.remove(post);
	}
	
	public String getPostByID(int id) {
		return postagens.get(id).getMensagem();
	}

	public int calculaPopularidade() {
		int popularidade = 0;
		for (Post post : postagens) {
			popularidade += post.getPopularidade();
		}
		return popularidade;
	}

	@Override
	public String toString() {
		return "Mural [postagens=" + postagens + "]";
	}

}
