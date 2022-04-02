package algoritmoGenetico.mutacion;

import java.util.Random;

import individuos.Individuo;

public class MutacionHeuristica extends Mutacion {

	private final String type = "Heuristica";
	
	public MutacionHeuristica()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionHeuristica();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		for(int j = 0; j < ind.getGenes().size(); j++) { // para varios genes
		
			// 1. Se seleccionan 2 elementos al azar
			Random rand = new Random();
			int p1 = rand.nextInt(ind.getGenes().get(j).getLongitud());
			int p2 = rand.nextInt(ind.getGenes().get(j).getLongitud());
			while (p1 == p2) {
				p2 = rand.nextInt(ind.getGenes().get(j).getLongitud());
			}
			
			// 2. Se generan todos los posibles individuos de las permutaciones de esos 2 elementos.
			//	En este caso solo está el cambio de posiciones
			Individuo ind_aux = ind;
			Object alelo_aux = ind.getGenes().get(j).getAlelo(p2);
			ind_aux.getGenes().get(j).setAlelo(p2, ind.getGenes().get(j).getAlelo(p1)); //pongo p1 en p2
			ind_aux.getGenes().get(j).setAlelo(p1, alelo_aux); // pongo p2 en p1
			
			// 3. Se calculan los fitness y se selecciona el que tenga mejor fitness
			if(ind.getFitness() <= ind_aux.getFitness()) {
				ind = ind_aux;	// TODO cuidado con las asignaciones en java
			}			
		
		}
	}
	
	public String toString() {
		return super.getId();
	}
}
