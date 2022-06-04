package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import arbol.Arbol;
import individuos.Individuo;

public class MutacionArbol extends Mutacion {
	private final String type = "Arbol";
	
	public MutacionArbol()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionArbol();
		return null;
	}

	protected void mutarIndividuo(Individuo ind) {		//Regenera un subarbol
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
			}while(aux == null || aux == a);
			
			aux.cambiarNodo(Arbol.generarGrow(rand, aux.getProfundidad(), aux.getPadre(), aux.getProfundidad(), aux.getM6()));
		}
	}

	private Arbol seleccionar(Arbol a, double prob, Random rand)
	{
		List<Arbol> hijos = a.getHijos();
		if(rand.nextDouble() < prob)
			return a;
		if(hijos.size() > 0)
		{
				Arbol aux;
				for(Arbol ar: hijos)
				{
					aux = seleccionar(ar, prob, rand);
					if(aux != null)
						return aux;
				}
		}
		return null;
	}
	public String toString() {
		return super.getId();
	}
}
