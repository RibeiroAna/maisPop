package core.post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.MensagensDeErro;
import exceptions.naoTrataveis.BadRequestException;

public class Mural implements Serializable {

	private static final long serialVersionUID = 6632999261405977535L;
	private List<Post> postagens = new ArrayList<Post>();

	public Mural() {
		postagens = new ArrayList<Post>();
	}

	public void adicionarPostagem(String postStr, List<String> hastags,
			List<String> audios, List<String> imagens, String data,
			String mensagem) {
		Post post = new Post(postStr, audios, imagens, hastags, data, mensagem);
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

	private AtributoPost strToAtributoPost(String atributoStr) {
		AtributoPost[] atributos = AtributoPost.values();
		for (AtributoPost atributoUsuario : atributos) {
			if (atributoUsuario.getAtributo().equals(atributoStr)) {
				return atributoUsuario;
			}
		}
		return null;
	}

	public String getPostAtributo(String atributo, int id) {
		return postagens.get(id).getAtributo(strToAtributoPost(atributo));
	}

	public String getConteudoPost(int indice, int post) {
		return postagens.get(post).getConteudoPost(indice);
	}

	public String curtir(int indexPost, int pontosPop, List<String> hastags) {
		postagens.get(indexPost).curtir(pontosPop, hastags);
		return postagens.get(indexPost).getAtributo(AtributoPost.DATA);
	}

	public String rejeitar(int indexPost, int pontosPop, List<String> hastags) {
		postagens.get(indexPost).rejeitar(pontosPop, hastags);
		return postagens.get(indexPost).getAtributo(AtributoPost.DATA);
	}

	public int getPopPost(int indexPost) {
		try {
			postagens.get(indexPost).getPopularidade();
		} catch (Exception e) {
			String msg = String.format(
					MensagensDeErro.ERROR_GET_POST_OUT_OF_BOUNDS, indexPost,
					postagens.size() + 1);
			throw new BadRequestException(msg);
		}
		return postagens.get(indexPost).getPopularidade();
	}

	public int qtdCurtidasDePost(int indexPost) {
		return postagens.get(indexPost).getNumPositivos();
	}

	public int qtdRejeicoesDePost(int indexPost) {
		return postagens.get(indexPost).getNumsNegativos();
	}

	public List<String> printHastags() {
		List<String> hastags = new ArrayList<String>();
		for (Post post : postagens) {
			hastags.add(post.getAtributo(AtributoPost.HASTAGS));
		}
		return hastags;
	}

	public int getTotalPost() {
		return postagens.size();
	}

	public List<Post> getPostsOrdemPop() {		
		return postagens;
	}

}
