package algoritmoGenetico.cruce;

import java.util.List;
import java.util.Random;

import gen.Gen;
import individuos.Individuo;

public class CruceUniforme extends Cruce {
	private final double prob = 0.5;
	
	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		Random rand = new Random();
		List<Gen> g1 = i1.getGenes(), g2 = i2.getGenes();
		boolean aux;
		for(int i = 0; i < g1.size(); i++)
		{
			for(int j = 0; j < g1.get(i).getLongitud(); j++)
			{
				if(rand.nextDouble() < prob)
				{
					aux = (boolean) g1.get(i).getAlelo(j);
					g1.get(i).setAlelo(j, g2.get(i).getAlelo(j));
					g2.get(i).setAlelo(j, aux);
				}
			}
		}
	}

}
