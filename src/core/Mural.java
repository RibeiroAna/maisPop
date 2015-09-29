package core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import core.Post;

public class Mural implements Serializable{

	
	private static final long serialVersionUID = 6632999261405977535L;
	private List<Post> postagens;

	public Mural() {
		postagens = new LinkedList<Post>();
	}

	public void adicionarPostagem(Post post) {
		postagens.add(post);
	}

	public void deletarPostagem(Post post) {
		postagens.remove(post);
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
