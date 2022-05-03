package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import arbol.Arbol;
import arbol.Nodo;
import individuos.Individuo;

public class MutacionExpansion extends Mutacion {
	private final String type = "Expansion";
	
	public MutacionExpansion()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionExpansion();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		Arbol a = (Arbol) ind.getGenes();
		Random rand = new Random();
		while(!seleccionar(a, 2.0 / a.getTamSubArbol(), rand));
	}
	
	private boolean seleccionar(Arbol a, double prob, Random rand)
	{
		List<Arbol> hijos = a.getHijos();
		if(hijos.size() == 0)
		{
			if(rand.nextDouble() < prob)
			{
				a.cambiarNodo(Arbol.generarGrow(rand, a.getProfundidad(), a.getPadre(), 1, a.getM6()));
				return true;
			}
		}
		else
		{
			for(Arbol ar: hijos)
				if(seleccionar(ar, prob, rand))
					return true;
		}
		return false;
	}
}
