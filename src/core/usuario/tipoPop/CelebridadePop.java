package core.usuario.tipoPop;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CelebridadePop implements TipoPop, Serializable{

	private static final long serialVersionUID = 1L;
	private static final int PONTOS = 25;
	private static final String CLASSE_POP = "Celebridade Pop";
	
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
		return new LinkedList<String>();
	}

	@Override
	public List<String> getRejeitarHastags() {
		return new LinkedList<String>();
	}

}
