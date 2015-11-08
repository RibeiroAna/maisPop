package core.usuario.tipoPop;

import java.util.List;

public interface TipoPop {
	
	public String getClassePopularidade();
	
	public int getPontosPop();
	
	public List<String> getCurtirHastags();
	
	public List<String> getRejeitarHastags();
}
