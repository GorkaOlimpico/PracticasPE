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

	protected void mutarIndividuo(Individuo ind) {
		Arbol a = (Arbol) ind.getGenes();
		Arbol aux;
		Random rand = new Random();
		
		do {
			aux = seleccionar(a, 1 / a.getTamSubArbol(), rand);
		}
		while(aux == null);
		
		Arbol n;
		do {
			n = Nodo.generar(rand, 2, aux.getPadre(), 1);
		}while(n.getHijos().size() != aux.getHijos().size());
		n.setProfundidad(aux.getProfundidad());
		for(int i = 0; i < n.getHijos().size(); i++)				//Se sustituyen los hijos de el nuevo nodo por los originales
			n.getHijos().get(i).cambiarNodo(aux.getHijos().get(i));
		aux.cambiarNodo(n);											//Se sustituye el nuevo nodo
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
				return null;
			}
		}
		else
			return null;
	}
}
