package core.post;

import java.io.Serializable;
import java.util.List;

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
	
	private int votosPositivos;
	private int votosNegativos;

	public Post(String texto, List<String> audioPath, List<String> imagemPath,
			List<String> hastags, String data, String mensagem) {
		this.texto = texto;
		this.imagemPath = imagemPath;
		this.audioPath = audioPath;
		this.dataDeCriacao = data;
		this.mensagem = mensagem;
		this.hastags = hastags;
		this.votosNegativos = 0;
		this.votosPositivos = 0;
		mountConteudoPost();
	}
	
	private void mountConteudoPost() {
		conteudoPost.add(mensagem);
		conteudoPost.addAll(imagemPath);
		conteudoPost.addAll(audioPath);
	}

	public void curtir() {
		votosPositivos++;
	}

	public void rejeitar() {
		votosNegativos++;
	}

	public int getPopularidade() {
		return votosPositivos - votosNegativos;
	}

	public String getMensagem() {
		return mensagem;
	}

	@Override
	public String toString() {
		return "Post [texto=" + texto + ", imagemPath=" + imagemPath
				+ ", audioPath=" + audioPath + ", hastags=" + hastags
				+ ", votosPositivos=" + votosPositivos + ", votosNegativos="
				+ votosNegativos + ", dataDeCriacao=" + dataDeCriacao + "]";
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
	
 /*	public String getConteudoPost(int conteudo) {
		try {
			return conteudoPost.get(conteudo);
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			if (conteudo < 0) {
				throw new BadRequestException();
			}
		}
		return null;
	}*/
	
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
