package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.naoTrataveis.BadFormatException;

public class ValidaDados {
	//TODO: singletom pesquisar patern
	/**
	 * Verifica se um nome não é vazio, se for lança uma exceção
	 * 
	 * @param nome
	 *            do usuario
	 */
	public static void validaNome(String nome, String error) {
		if (nome.trim().length() == 0) {
			throw new BadFormatException(error, MensagensDeErro.CAUSA_NOME_VAZIO);
		}
	}

	/**
	 * Dada uma data, verifica se ela está no formato correto dd/mm/aaaa
	 * 
	 * @param data
	 */
	public static void validaData(String data, String error) {
		if (data.split("/").length != 3) {
			throw new BadFormatException(error, MensagensDeErro.CAUSA_FORMATO_DATA_INVALIDA);
		}
		int dia = Integer.parseInt(data.split("/")[0]);
		int mes = Integer.parseInt(data.split("/")[1]);
		int ano = Integer.parseInt(data.split("/")[2]);
		if ((dia > 99) || (mes > 99) || (ano > 9999)) {
			throw new BadFormatException(error, MensagensDeErro.CAUSA_FORMATO_DATA_INVALIDA);
		}
		/*
		 * TODO Se e somente se houver tempo fazer isso por mês, por exemplo
		 * considerara inválida a data 30/02/2015
		 */
		if (((dia < 0) || (dia > 31) || (mes < 0) || (mes > 12) || (ano < 0) || (ano > 3000))) {
			throw new BadFormatException(error, MensagensDeErro.CAUSA_FORMATO_DATA_INEXISTENTE);
		}
	}
	
	/**
	 * Verifica se o email está no formato correto "xxxx@xxxx.xxx"
	 * @param email que será verificado
	 */
	public static void validaEmail(String email, String error) {
		  Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
		    Matcher matcher = pattern.matcher(email); 
		    if (!matcher.find()) {
		    	throw new BadFormatException(error, MensagensDeErro.CAUSA_EMAIL_ERRADO);
		    }
	}

}
