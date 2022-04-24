package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import arbol.Arbol;
import individuos.Individuo;

public class MutacionHoist extends Mutacion {
	private final String type = "Hoist";
	
	public MutacionHoist()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionHoist();
		return null;
	}

	protected void mutarIndividuo(Individuo ind) {
		Arbol a = (Arbol) ind.getGenes();
		Arbol aux;
		Random rand = new Random();
		
		do {
			aux = seleccionar(a, 0.1, rand);
		}
		while(aux == null);
		
		aux.setPadre(null);
		ind.setGenes(aux);
	}

	private Arbol seleccionar(Arbol a, double prob, Random rand)
	{
		List<Arbol> hijos = a.getHijos();
		if(hijos.size() > 0)
		{
			if(rand.nextDouble() < prob)
				return a;
			else
			{
				Arbol aux = null;
				for(Arbol ar: hijos)
					if(aux == null)
						aux = seleccionar(ar, prob, rand);
				return aux;
			}
		}
		else
			return null;
	}

}
