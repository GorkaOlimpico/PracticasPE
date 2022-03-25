package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import gen.Gen;
import individuos.Individuo;

public class MutacionBasica extends Mutacion {
	private final double prob = 0.05;
	private final String type = "Basica";
	
	public MutacionBasica()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionBasica();
		return null;
	}
	
	@Override
	protected void mutarIndividuo(Individuo ind) {
		Random rand = new Random();
		List<Gen> g = ind.getGenes();
		for(int i = 0; i < g.size(); i++)
			for(int j = 0; j < g.get(i).getLongitud(); j++)
				if(rand.nextDouble() < prob)
					g.get(i).setAlelo(j, rand.nextBoolean());
	}
}
