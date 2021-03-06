package algoritmoGenetico.cruce;

import java.util.List;
import java.util.Random;

import gen.Gen;
import individuos.Individuo;

public class CruceMonopuntoReal extends Cruce {
private final String type = "Monopunto-Real";
	
	public CruceMonopuntoReal() {
		super.id = type;
	}

	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceMonopuntoReal();
		return null;
	}
	
	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		Random rand = new Random();
		List<Gen> g1 = i1.getGenes(), g2 = i2.getGenes();
		double aux;
		for(int i = 0; i < g1.size(); i++)
		{
			for(int j = rand.nextInt(g1.get(i).getLongitud()); j < g1.get(i).getLongitud(); j++)
			{
				aux = (double) g1.get(i).getAlelo(j);
				g1.get(i).setAlelo(j, g2.get(i).getAlelo(j));
				g2.get(i).setAlelo(j, aux);
			}
		}
	}
}
