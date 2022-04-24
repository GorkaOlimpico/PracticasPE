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
		while(!seleccionar(a, 0.1, rand)){}
	}
	
	private boolean seleccionar(Arbol a, double prob, Random rand)
	{
		List<Arbol> hijos = a.getHijos();
		if(hijos.size() == 0)
			if(rand.nextDouble() < prob)
			{
				a.cambiarNodo(Nodo.generar(rand, a.getProfundidad(), a.getPadre(), 1));
				return true;
			}
			else
				return false;
		else
		{
			boolean aux = false;
			for(Arbol ar: hijos)
				if(!aux)
					aux = seleccionar(ar, prob, rand);
			return aux;
		}
	}
}
