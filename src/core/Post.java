package core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Post implements Serializable{
	
	private static final long serialVersionUID = 6760403185218932353L;
	private String texto;
	private String imagemPath;
	private String audioPath;
	private List<String> hastags;
	private int votosPositivos;
	private int votosNegativos;
	private Date dataDeCriacao;

	public Post(String texto, String imagemPath, String audioPath,
			List<String> hastags) {
		this.texto = texto;
		this.imagemPath = imagemPath;
		this.audioPath = audioPath;
		this.hastags = hastags;
		this.votosNegativos = 0;
		this.votosPositivos = 0;
		dataDeCriacao = new Date();
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

	@Override
	public String toString() {
		return "Post [texto=" + texto + ", imagemPath=" + imagemPath
				+ ", audioPath=" + audioPath + ", hastags=" + hastags
				+ ", votosPositivos=" + votosPositivos + ", votosNegativos="
				+ votosNegativos + ", dataDeCriacao=" + dataDeCriacao + "]";
	}

}
