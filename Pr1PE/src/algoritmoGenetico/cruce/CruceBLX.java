package algoritmoGenetico.cruce;

import java.util.Random;

import individuos.Individuo;

public class CruceBLX extends Cruce {
	private final String type = "BLX";
	private final double alfa = 0.6;
	
	public CruceBLX() {
		super.id = type;
	}

	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceBLX();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		double max, min, I;
		Random rand = new Random();
		for(int i = 0; i < i1.getGenes().size(); i++)
		{
			max = (double) i1.getGenes().get(i).getAlelo(0);
			min = (double) i2.getGenes().get(i).getAlelo(0);
			if(min > max)
			{
				double aux = min;
				min = max;
				max = aux;
			}
			I = max - min;
			min = min - I * alfa;
			max = max + I * alfa;
			I = max - min;
			i1.getGenes().get(i).setAlelo(0, rand.nextDouble() * I + min);
			i2.getGenes().get(i).setAlelo(0, rand.nextDouble() * I + min);
		}
		i1.recalcularFenotipo();
		i2.recalcularFenotipo();
	}

}
