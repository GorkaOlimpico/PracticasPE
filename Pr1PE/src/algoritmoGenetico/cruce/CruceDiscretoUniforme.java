package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuos.Individuo;

public class CruceDiscretoUniforme extends Cruce {
	private final String type = "Discreto Uniforme";
	
	public CruceDiscretoUniforme() {
		super.id = type;
	}

	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceDiscretoUniforme();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		Random rand = new Random();
		List<Double> prob = new ArrayList<>();
		for(int i = 0; i < i1.getGenes().size(); i++)
			prob.add(rand.nextDouble());
		
		double p = rand.nextDouble();
		Object aux;
		for(int i = 0; i < prob.size(); i++)
		{
			aux = i1.getGenes().get(i).getAlelo(0);
			i1.getGenes().get(i).setAlelo(0, i1.getGenes().get(i).getAlelo(0));
			i2.getGenes().get(i).setAlelo(0, aux);
		}
	}

}
