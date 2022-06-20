package algoritmoGenetico.mutacion.GE;

import java.util.List;
import java.util.Random;

import algoritmoGenetico.mutacion.Mutacion;
import gen.Gen;
import individuos.Individuo;

public class MutacionBasica extends Mutacion {

	private final String type = "Basica";
	
	public MutacionBasica()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionBasica();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		Random rand = new Random();
		List<Gen> g =(List<Gen>) ind.getGenes();
		int posicion = rand.nextInt(g.get(0).getLongitud());

		g.get(0).setAlelo(posicion, rand.nextInt(256));
			
				
	
	}
	
	public String toString() {
		return super.getId();
	}
}
