package algoritmoGenetico.mutacion.PG;

import java.util.List;
import java.util.Random;

import algoritmoGenetico.mutacion.Mutacion;
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
	protected void mutarIndividuo(Individuo ind) {		//Convierte un nodo en una hoja
		Arbol a = (Arbol) ind.getGenes();
		Random rand = new Random();
		
		double prob = ((double) a.getAlturaSubArbol()) / a.getTamSubArbol();
		if(prob >= 1 || prob <= 0)
			prob = 0.5;
		if(a.getAlturaSubArbol() > 1)
		{
			Arbol aux;
			do {
				aux = seleccionar(a, prob, rand);
			}
			while(aux == null || aux == a);
			aux.cambiarNodo(Hoja.generar(rand, aux.getProfundidad(), aux.getPadre(), aux.getM6()));
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
