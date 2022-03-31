package algoritmoGenetico.mutacion;

import java.util.Random;

import individuos.Individuo;

public class MutacionIntercambio extends Mutacion {

	private final String type = "Intercambio";
	
	public MutacionIntercambio()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionIntercambio();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		Random rand = new Random();
		int i1, i2;
		for(int i = 0; i < ind.getGenes().size(); i++)
		{
			do
			{
				i1 = rand.nextInt(ind.getGenes().get(i).getLongitud());
				i2 = rand.nextInt(ind.getGenes().get(i).getLongitud());
			}while(i1 == i2);
			
			Object aux = ind.getGenes().get(i).getAlelo(i1);
			ind.getGenes().get(i).setAlelo(i1, ind.getGenes().get(i).getAlelo(i2));
			ind.getGenes().get(i).setAlelo(i2, aux);
		}
	}

	public String toString() {
		return "Intercambio";
	}
}
