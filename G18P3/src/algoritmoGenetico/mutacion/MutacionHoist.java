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

	protected void mutarIndividuo(Individuo ind) {		//Convierte a un subarbol en la raiz
		Arbol a = (Arbol) ind.getGenes();
		Random rand = new Random();

		boolean posible = false;
		for(Arbol ar: a.getHijos())
			if(ar.getHijos().size() > 0)
				posible = true;
		
		if(posible)
		{
			Arbol aux;
			do																				
			{
				aux = seleccionar(a, ((double) a.getAlturaSubArbol()) / a.getTamSubArbol(), rand);
			}while(aux == null || aux == a);
			
			aux.setProfundidad(a.getProfundidad());
			aux.actualizarProfundidadHijos();
			aux.setPadre(null);
			ind.setGenes(aux);
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
