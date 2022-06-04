package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import arbol.Arbol;
import arbol.Hoja;
import individuos.Individuo;

public class MutacionTerminal extends Mutacion {
	private final String type = "Terminal";
	
	public MutacionTerminal()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionTerminal();
		return null;
	}
	
	@Override
	protected void mutarIndividuo(Individuo ind) {		//Cambia una hoja por otra hoja
		Arbol a = (Arbol) ind.getGenes();
		Random rand = new Random();
		double prob = ((double) a.getAlturaSubArbol()) / a.getTamSubArbol();
		if(prob >= 1 || prob <= 0)
			prob = 0.5;
		while(!seleccionar(a, prob, rand));
	}

	private boolean seleccionar(Arbol a, double prob, Random rand)
	{
		List<Arbol> hijos = a.getHijos();
		if(hijos.size() == 0)
		{
			if(rand.nextDouble() < prob)
			{
				a.sustituirNodo(Hoja.generar(rand, a.getProfundidad(), a.getPadre(), a.getM6()));
				return true;
			}
		}
		else
			for(Arbol ar: hijos)
				if(seleccionar(ar, prob, rand))
					return true;
		return false;
	}
	public String toString() {
		return super.getId();
	}
}
