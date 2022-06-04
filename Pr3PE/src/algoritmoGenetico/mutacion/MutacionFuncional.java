package algoritmoGenetico.mutacion;

import java.util.List;
import java.util.Random;

import arbol.Arbol;
import arbol.Nodo;
import individuos.Individuo;

public class MutacionFuncional extends Mutacion {
	private final String type = "Funcional";
	
	public MutacionFuncional()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionFuncional();
		return null;
	}

	protected void mutarIndividuo(Individuo ind) {		//Cambia un nodo por otro con el mismo numero de hijos, manteniendo los hijos originales
		Arbol a = (Arbol) ind.getGenes();
		
		Random rand = new Random();

		if(a.getAlturaSubArbol() > 1)
		{
			
			double prob = ((double) a.getAlturaSubArbol()) / a.getTamSubArbol();
			if(prob >= 1 || prob <= 0)
				prob = 0.5;
			
			Arbol aux;
			do																				
			{
				aux = seleccionar(a, prob, rand);
			}while(aux == null || aux == a);
			
			Arbol n;
			do {
				n = Nodo.generar(rand, 2, null, 1, 1, aux.getM6());
			}while(n.getHijos().size() != aux.getHijos().size());
			aux.sustituirNodo(n);
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
