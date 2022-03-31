package algoritmoGenetico.mutacion;

import java.util.Random;

import individuos.Individuo;

public class MutacionInversion extends Mutacion {

	private final String type = "Inversion";
	
	public MutacionInversion()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionInversion();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		Random rand = new Random();
		for(int i = 0; i < ind.getGenes().size(); i++)
		{
			int ini = rand.nextInt(ind.getGenes().get(i).getLongitud()), fin = rand.nextInt(ind.getGenes().get(i).getLongitud() - ini) + ini;
			Object aux;
			for(int j = 0; j < (fin - ini) / 2; j++)
			{
				aux = ind.getGenes().get(i).getAlelo(ini + j);
				ind.getGenes().get(i).setAlelo(ini + j, ind.getGenes().get(i).getAlelo(fin - j));
				ind.getGenes().get(i).setAlelo(fin - j, aux);
			}
		}
	}

	public String toString() {
		return "Inversión";
	}
}
