package algoritmoGenetico.cruce.GE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import gen.Gen;
import individuos.Individuo;

public class CruceMonopunto extends Cruce {
	private final String type = "Monopunto";
	private List<Integer> lista;
	
	public CruceMonopunto()
	{
		super.id = type;
	
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceMonopunto();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		Random rand = new Random();
		List<Gen> g1 = (List<Gen>) i1.getGenes();
		List<Gen> g2 = (List<Gen>) i2.getGenes();
		
		int aux;
		for(int i = 0; i < g1.size(); i++)
		{
			int k = rand.nextInt(g1.get(i).getLongitud());
	
			for(int j = k; j >= 0; j--)
			{
				aux = (int) g1.get(i).getAlelo(j);
				g1.get(i).setAlelo(j, g2.get(i).getAlelo(j));
				g2.get(i).setAlelo(j, aux);
			}
		}
		
		i1.setGenes(g1);
		i2.setGenes(g2);
	}
	
	public String toString() {
		return super.getId();
	}
}
