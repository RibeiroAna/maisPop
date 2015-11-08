package core.usuario.tipoPop;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class IconePop implements TipoPop, Serializable {

	private static final long serialVersionUID = -3726103859643855448L;
	private static final int PONTOS = 50;
	private static final String CLASSE_POP = "Icone Pop";

	@Override
	public String getClassePopularidade() {
		return CLASSE_POP;
	}

	@Override
	public int getPontosPop() {
		return PONTOS;
	}

	@Override
	public List<String> getCurtirHastags() {
		List<String> hastags = new LinkedList<String>();
		hastags.add("#epicwin");
		return hastags;
	}

	@Override
	public List<String> getRejeitarHastags() {
		List<String> hastags = new LinkedList<String>();
		hastags.add("#epicfail");
		return hastags;
	}

}
