package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import gen.Gen;
import individuos.Individuo;

public class MutacionBasica extends Mutacion {
	private final double prob = 0.05;
	
	@Override
	protected void mutarIndividuo(Individuo ind) {
		Random rand = new Random();
		List<Gen> g = ind.getGenes();
		boolean cambiar = false;
		for(int i = 0; i < g.size(); i++)
		{
			for(int j = 0; j < g.get(i).getLongitud(); j++)
			{
				if(rand.nextDouble() < prob)
				{
					g.get(i).setAlelo(j, rand.nextBoolean());
					cambiar = true;
				}
			}
		}
		if(cambiar)
			ind.recalcularFenotipo();
	}

}
