package core.post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.MensagensDeErro;
import exceptions.naoTrataveis.BadRequestException;

public class Post implements Serializable {

	private static final long serialVersionUID = 6760403185218932353L;
	private String texto;
	private String mensagem;
	private String dataDeCriacao;

	private List<String> imagemPath;
	private List<String> audioPath;
	private List<String> hastags;
	private List<String> conteudoPost;

	private int pontosPositivos;
	private int pontosNegativos;
	private int numPositivos;
	private int numNegativos;

	public Post(String texto, List<String> audioPath, List<String> imagemPath,
			List<String> hastags, String data, String mensagem) {
		this.texto = texto;
		this.imagemPath = imagemPath;
		this.audioPath = audioPath;
		this.dataDeCriacao = data;
		this.mensagem = mensagem;
		this.hastags = hastags;
		this.pontosNegativos = 0;
		this.pontosPositivos = 0;
		this.numNegativos = 0;
		this.numPositivos = 0;
		mountConteudoPost();
	}
	
	public int getNumPositivos() {
		return numPositivos;
	}
	
	public int getNumsNegativos() {
		return numNegativos;
	}

	private void mountConteudoPost() {
		this.conteudoPost = new ArrayList<String>();
		conteudoPost.add(texto.substring(0, texto.length() - 1));
		for (String audio : audioPath) {
			conteudoPost.add("$arquivo_audio:" + audio);
		}
		for (String imagem : imagemPath) {
			conteudoPost.add("$arquivo_imagem:" + imagem);
		}
		this.conteudoPost.addAll(imagemPath);
	}

	public void curtir(int pontosPop, List<String> hastags) {
		pontosPositivos += pontosPop;
		numPositivos++;
		this.hastags.addAll(hastags);
	}

	public void rejeitar(int pontosPop, List<String> hastags) {
		pontosNegativos += pontosPop;
		numNegativos++;
		this.hastags.addAll(hastags);
	}

	public int getPopularidade() {
		return pontosPositivos - pontosNegativos;
	}

	public String getMensagem() {
		return mensagem;
	}

	@Override
	public String toString() {
		return "Post [texto=" + texto + ", imagemPath=" + imagemPath
				+ ", audioPath=" + audioPath + ", hastags=" + hastags
				+ ", votosPositivos=" + pontosPositivos + ", votosNegativos="
				+ pontosNegativos + ", dataDeCriacao=" + dataDeCriacao + "]";
	}

	private String getHastagsStr() {
		StringBuilder hastags = new StringBuilder();
		for (String hastag : this.hastags) {
			hastags.append(hastag);
			hastags.append(",");
		}
		return hastags.toString().substring(0, hastags.toString().length() - 1);
	}

	private String getTextoMsg() {
		String msg = texto.substring(0, texto.length() - 1);
		for (String imagem : imagemPath) {
			msg += " <imagem>" + imagem + "</imagem>";
		}
		for (String audio : audioPath) {
			msg += " <audio>" + audio + "</audio>";
		}
		return msg;
	}

	public String getConteudoPost(int conteudo) {
		try {
			return conteudoPost.get(conteudo);
		} catch (ArrayIndexOutOfBoundsException e) {
			if (conteudo < 0) {
				throw new BadRequestException(MensagensDeErro.ERROR_GET_POST);
			}
			String mensagem = String.format(
					MensagensDeErro.ERROR_GET_POST_OUT_OF_BOUNDS, conteudo,
					conteudoPost.size());
			throw new BadRequestException(mensagem);

		}
	}

	public String getAtributo(AtributoPost atributoPost) {
		switch (atributoPost) {
		case MENSAGEM:
			return getTextoMsg();
		case DATA:
			return dataDeCriacao;
		case HASTAGS:
			return getHastagsStr();
		}
		return null;
	}

}
