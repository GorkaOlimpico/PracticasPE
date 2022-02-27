package src.algoritmoGenetico.individuos;

import java.util.List;

import src.algoritmoGenetico.cruces.Cruce;
import src.algoritmoGenetico.cruces.CruceGenerator;
import src.algoritmoGenetico.mutacion.Mutacion;
import src.algoritmoGenetico.mutacion.MutacionGenerator;

public class IndividuoFuncion4Real extends Individuo {

	protected IndividuoFuncion4Real() {
		super("Funcion 4 Real");
	}
	
	@Override
	protected List parse(String[] j, int tam, double valorError) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mutacion[] getMutaciones() {
		return MutacionGenerator.getMutacionesReal();
	}

	@Override
	public Cruce[] getCruces() {
		return CruceGenerator.getCrucesReal();
	}

}
