package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import arbol.Arbol;
import arbol.Hoja;
import individuos.Individuo;

public class MutacionContraccion extends Mutacion {
	private final String type = "Contraccion";
	
	public MutacionContraccion()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionContraccion();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		Arbol a = (Arbol) ind.getGenes();
		Random rand = new Random();
		seleccionar(a, 2 / a.getTamSubArbol(), rand);
	}
	
	private boolean seleccionar(Arbol a, double prob, Random rand)
	{
		List<Arbol> hijos = a.getHijos();
		if(hijos.size() > 0)
			if(rand.nextDouble() < prob)
			{
				a.cambiarNodo(Hoja.generar(rand, a.getProfundidad(), a.getPadre()));
				return true;
			}
			else
				return false;
		else
		{
			for(Arbol ar: hijos)
				if(seleccionar(ar, prob, rand))
					return true;
			return false;
		}
	}
}
