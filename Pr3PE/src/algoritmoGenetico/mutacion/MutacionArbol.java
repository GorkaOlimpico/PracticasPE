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

	protected void mutarIndividuo(Individuo ind) {
		Arbol a = (Arbol) ind.getGenes();
		Arbol aux;
		Random rand = new Random();
		
		do {
			aux = seleccionar(a, 1 / a.getTamSubArbol(), rand);
		}
		while(aux == null || aux == a);
		
		aux.getPadre().getHijos().set(aux.getPadre().getHijos().indexOf(aux), Arbol.generarGrow(rand, aux.getProfundidad(), aux.getPadre(), 1));
	}

	private Arbol seleccionar(Arbol a, double prob, Random rand)
	{
		List<Arbol> hijos = a.getHijos();
		if(hijos.size() > 0)
		{
			if(a.getPadre() != null && rand.nextDouble() < prob)
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
