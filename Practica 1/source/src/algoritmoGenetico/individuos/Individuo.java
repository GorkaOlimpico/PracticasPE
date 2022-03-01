package src.algoritmoGenetico.individuos;

import java.util.List;

import src.algoritmoGenetico.cruces.Cruce;
import src.algoritmoGenetico.mutacion.Mutacion;

public abstract class Individuo<T> {
	protected String type;
	
	public Individuo(){
		
	}
	
	protected Individuo(String type) {
		this.type = type;
	}
	
	protected abstract List<Individuo> parse(String[] j, int tam, double valorError);

	public String getType() {
		return type;
	}

	public abstract Mutacion[] getMutaciones();

	public abstract Cruce[] getCruces();
	
}
