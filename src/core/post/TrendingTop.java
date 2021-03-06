package core.post;

import java.io.Serializable;


public class TrendingTop implements Comparable<TrendingTop>, Serializable {

	private static final long serialVersionUID = -807303160722835920L;
	private String tt;
	private Integer aparicoes;
	
	public TrendingTop(String tt, int aparicoes) {
		this.tt = tt;
		this.aparicoes = new Integer(aparicoes);
	}
	
	public void votar() {
		aparicoes++;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tt == null) ? 0 : tt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrendingTop other = (TrendingTop) obj;
		if (tt == null) {
			if (other.tt != null)
				return false;
		} else if (!tt.equals(other.tt))
			return false;
		return true;
	}

	@Override
	public int compareTo(TrendingTop o) {
		if (this.aparicoes.compareTo(o.aparicoes) == 0) {
			return this.tt.toUpperCase().compareTo(o.tt.toUpperCase());
		} else {
			return this.aparicoes.compareTo(o.aparicoes) * (-1);
		}
	}
	
	@Override
	public String toString() {
		return tt + ": " + aparicoes.toString() + ";";
	}
	

}
