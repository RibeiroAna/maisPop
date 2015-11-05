package core;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable{
	
	private static final long serialVersionUID = 6760403185218932353L;
	private String texto;
	private  List<String> imagemPath;
	private  List<String> audioPath;
	private String mensagem;
	private String dataDeCriacao;
	
	private List<String> hastags;
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
		System.out.println(mensagem);
		return mensagem;
	}
	
	@Override
	public String toString() {
		return "Post [texto=" + texto + ", imagemPath=" + imagemPath
				+ ", audioPath=" + audioPath + ", hastags=" + hastags
				+ ", votosPositivos=" + votosPositivos + ", votosNegativos="
				+ votosNegativos + ", dataDeCriacao=" + dataDeCriacao + "]";
	}

}
