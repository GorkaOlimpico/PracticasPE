package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import gen.Gen;
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
		List<Gen> genes = (List<Gen>) ind.getGenes();
		for(int i = 0; i < genes.size(); i++)
		{
			do
			{
				i1 = rand.nextInt(genes.get(i).getLongitud());
				i2 = rand.nextInt(genes.get(i).getLongitud());
			}while(i1 == i2);
			
			Object aux = genes.get(i).getAlelo(i1);
			genes.get(i).setAlelo(i1, genes.get(i).getAlelo(i2));
			genes.get(i).setAlelo(i2, aux);
		}
	}

	public String toString() {
		return super.getId();
	}
}
