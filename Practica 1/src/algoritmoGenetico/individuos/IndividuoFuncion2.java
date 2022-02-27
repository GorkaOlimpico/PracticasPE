package src.algoritmoGenetico.individuos;

import java.util.List;

import src.algoritmoGenetico.cruces.Cruce;
import src.algoritmoGenetico.cruces.CruceGenerator;
import src.algoritmoGenetico.mutacion.Mutacion;
import src.algoritmoGenetico.mutacion.MutacionGenerator;

public class IndividuoFuncion2 extends Individuo {

	protected IndividuoFuncion2() {
		super("Funcion 2");
	}
	
	@Override
	protected List parse(String[] j, int tam, double valorError) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mutacion[] getMutaciones() {
		return MutacionGenerator.getMutacionesBin();
	}

	@Override
	public Cruce[] getCruces() {
		return CruceGenerator.getCrucesBin();
	}

}
