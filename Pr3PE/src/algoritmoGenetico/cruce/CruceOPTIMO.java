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
		List<Gen> genes = (List<Gen>) i1.getGenes();
		genes.get(0).setAlelo(0, 3);
		genes.get(0).setAlelo(1, 0);
		genes.get(0).setAlelo(2, 4);
		genes.get(0).setAlelo(3, 5);
		genes.get(0).setAlelo(4, 9);
		genes.get(0).setAlelo(5, 3);
		genes.get(0).setAlelo(6, 4);
		genes.get(0).setAlelo(7, 7);
		genes.get(0).setAlelo(8, 3);
		genes.get(0).setAlelo(9, 5);
		genes.get(0).setAlelo(10, 8);
		genes.get(0).setAlelo(11, 6);
	//	System.out.println(i1.getSolucion());
		
	}

	public String toString() {
		return super.getId();
	}
}
