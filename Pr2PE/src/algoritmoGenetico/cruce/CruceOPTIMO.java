package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gen.Gen;
import individuos.Individuo;

public class CruceOPTIMO extends Cruce {
	private final String type = "OPTIMO";

	
	public CruceOPTIMO()
	{
		super.id = type;
	
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceOPTIMO();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		i1.getGenes().get(0).setAlelo(0, 7);
		i1.getGenes().get(0).setAlelo(1, 11);
		i1.getGenes().get(0).setAlelo(2, 10);
		i1.getGenes().get(0).setAlelo(3, 3);
		i1.getGenes().get(0).setAlelo(4, 2);
		i1.getGenes().get(0).setAlelo(5, 9);
		i1.getGenes().get(0).setAlelo(6, 4);
		i1.getGenes().get(0).setAlelo(7, 5);
		i1.getGenes().get(0).setAlelo(8, 6);
		i1.getGenes().get(0).setAlelo(9, 8);
		i1.getGenes().get(0).setAlelo(10, 0);
		i1.getGenes().get(0).setAlelo(11, 1);
		
	}

	public String toString() {
		return super.getId();
	}
}
