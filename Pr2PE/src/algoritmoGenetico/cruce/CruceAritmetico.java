package algoritmoGenetico.cruce;

import individuos.Individuo;

public class CruceAritmetico extends Cruce {
	private final String type = "Aritmetico";
	private final double alfa = 0.6;
	
	public CruceAritmetico() {
		super.id = type;
	}

	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceAritmetico();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		for(int i = 0; i < i1.getGenes().size(); i++)
		{
			double h1 = ((double) i1.getGenes().get(i).getAlelo(0) + (double) i2.getGenes().get(i).getAlelo(0)) / 2;
			double h2 = ((double) i1.getGenes().get(i).getAlelo(0)) * alfa + (1 - alfa) * (double) i2.getGenes().get(i).getAlelo(0);
			i1.getGenes().get(i).setAlelo(0, h1);
			i2.getGenes().get(i).setAlelo(0, h2);
		}
	}

}
