package utils;

import java.util.LinkedList;
import java.util.List;

import exceptions.naoTrataveis.BadRequestException;

/**
 * Esta classe tem o principal objetivo de processar e devolver partes strings
 * específicas de strings de posts.
 * 
 * @author ana
 *
 */
public class TrataPost {

	public static String getMensagem(String post) throws Exception {
		String[] palavras = post.split(" ");
		StringBuilder mensagem = new StringBuilder("");
		for (String palavra : palavras) {
			if ((palavra.startsWith("<") || (palavra.startsWith("#")))) {
				break;
			} else {
				mensagem.append(palavra);
			}
		}

		if (mensagem.length() > 200) {
			throw new BadRequestException(MensagensDeErro.ERROR_CRIAR_POST,
					MensagensDeErro.CAUSA_POST_GRANDE);
		}
		return mensagem.toString();
	}

	public static List<String> getHastags(String post) throws Exception {
		String[] palavras = post.split(" ");
		List<String> hastags = new LinkedList<String>();
		boolean jahTeveHastag = false;

		for (String palavra : palavras) {
			if (palavra.startsWith("#")) {
				jahTeveHastag = true;
				hastags.add(palavra);
			} else {
				if ((!palavra.startsWith("<")) && jahTeveHastag) {
					String causa = String.format(
							MensagensDeErro.CAUSA_POST_HASTAGS, palavra);
					throw new BadRequestException(
							MensagensDeErro.ERROR_CRIAR_POST, causa);
				}
			}
		}
		return hastags;
	}

	public static List<String> getAudios(String post) {
		String[] palavras = post.split(" ");
		List<String> audios = new LinkedList<String>();

		for (int i = 0; i < palavras.length; i++) {
			if (palavras[i].contains("<audio>")) {
				String audio = palavras[i].split(">")[1];
				audios.add(audio.split("<")[0]);
			}
		}
		return audios;
	}

	public static List<String> getImagens(String post) {
		String[] palavras = post.split(" ");
		List<String> imagens = new LinkedList<String>();

		for (int i = 0; i < palavras.length; i++) {
			if (palavras[i].contains("<imagem>")) {
				String imagem = palavras[i].split(">")[1];
				imagens.add(imagem.split("<")[0]);
			}
		}
		return imagens;
	}
}
