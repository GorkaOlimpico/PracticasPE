package algoritmoGenetico.cruce;

import java.util.List;
import java.util.Random;

import gen.Gen;
import individuos.Individuo;

public class CruceUniforme extends Cruce {
	private final double prob = 0.5;
	private final String type = "Uniforme";
	
	public CruceUniforme() {
		super.id = type;
	}
	
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

	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceUniforme();
		return null;
	}
}
