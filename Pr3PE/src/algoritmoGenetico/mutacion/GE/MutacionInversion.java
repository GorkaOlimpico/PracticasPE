package algoritmoGenetico.mutacion.GE;

import java.util.List;
import java.util.Random;

import algoritmoGenetico.mutacion.Mutacion;
import gen.Gen;
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
		List<Gen> genes = (List<Gen>) ind.getGenes();
		for(int i = 0; i < genes.size(); i++)
		{
			int ini = rand.nextInt(genes.get(i).getLongitud()), fin = rand.nextInt(genes.get(i).getLongitud() - ini) + ini;
			Object aux;
			for(int j = 0; j < (fin - ini) / 2; j++)
			{
				aux = genes.get(i).getAlelo(ini + j);
				genes.get(i).setAlelo(ini + j, genes.get(i).getAlelo(fin - j));
				genes.get(i).setAlelo(fin - j, aux);
			}
		}
	}

	public String toString() {
		return super.getId();
	}
}
