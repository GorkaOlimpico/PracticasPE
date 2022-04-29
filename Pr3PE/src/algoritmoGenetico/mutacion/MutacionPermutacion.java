package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import arbol.Arbol;
import individuos.Individuo;

public class MutacionPermutacion extends Mutacion {
	private final String type = "Permutacion";
	
	public MutacionPermutacion()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionPermutacion();
		return null;
	}

	protected void mutarIndividuo(Individuo ind) {
		Arbol a = (Arbol) ind.getGenes();
		Arbol aux;
		Random rand = new Random();
		
		do {
			aux = seleccionar(a, 1 / a.getTamSubArbol(), rand);
		}
		while(aux == null || aux == a);
		
		for(int i = 0; i < aux.getHijos().size() - 1; i++)
		{
			aux.getHijos().get(i).intercambiarNodo(aux.getHijos().get(i + 1));
		}
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
				Arbol aux;
				for(Arbol ar: hijos)
				{
					aux = seleccionar(ar, prob, rand);
					if(aux != null)
						return aux;
				}
				return null;
			}
		}
		else
			return null;
	}

}
