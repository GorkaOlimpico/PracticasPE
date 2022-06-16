package algoritmoGenetico.mutacion.PG;

import java.util.List;
import java.util.Random;

import algoritmoGenetico.mutacion.Mutacion;
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

	protected void mutarIndividuo(Individuo ind) {			//Cambia el orden de los hijos de un nodo
		Arbol a = (Arbol) ind.getGenes();
		Random rand = new Random();
		
		if(a.getAlturaSubArbol() > 0)
		{
			double prob = ((double) a.getAlturaSubArbol()) / a.getTamSubArbol();
			if(prob >= 1 || prob <= 0)
				prob = 0.5;
			Arbol aux;
			do																				
			{
				aux = seleccionar(a, prob, rand);
			}while(aux == null);
			
			for(int i = 0; i < aux.getHijos().size() - 1; i++)
			{
				aux.getHijos().get(i).intercambiarNodo(aux.getHijos().get(i + 1));
			}
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
			}
		}
		return null;
	}

	public String toString() {
		return super.getId();
	}
}
