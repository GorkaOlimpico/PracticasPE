package algoritmoGenetico.mutacion;

import java.util.Random;

import individuos.Individuo;

public class MutacionUniforme extends Mutacion {
	private final double prob = 0.4;
	private final String type = "Uniforme";
	
	public MutacionUniforme()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionUniforme();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		Random rand = new Random();
		for(int i = 0; i < ind.getGenes().size(); i++)
		{
			if(rand.nextDouble() < prob)
				ind.getGenes().get(i).setAlelo(0, rand.nextDouble() * (ind.getMax(i) - ind.getMin(i)) + ind.getMin(i));
		}
	}
}
