package algoritmoGenetico.cruce;

import java.util.List;
import java.util.Random;

import arbol.Arbol;
import individuos.Individuo;

public class CruceSubArboles extends Cruce {
	private final String type = "Sub-Arboles";
	
	public CruceSubArboles()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CrucePMX();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {		
		//Intercambia dos subarboles manteniendo el limite de profundidad de cada arbol, al ambos arboles tener el mismo limite de profundidad 
		//siempre habra algun subarbol compatible
		
		Arbol a1 = (Arbol) i1.getGenes();
		Arbol a2 = (Arbol) i2.getGenes();
		Arbol aux = a1;
		Random rand = new Random();
		boolean posible = false;
		
		for(Arbol ar: a1.getHijos())
			if(ar.getHijos().size() > 0)
				posible = true;
		
		if(posible)
		{
			posible = false;
			for(Arbol ar: a2.getHijos())
				if(ar.getHijos().size() > 0)
					posible = true;
		}
		
		if(posible)
		{
			//Seleccion subarboles
			do																				//Se selecciona un subarbol de i1
			{
				aux = seleccionar(a1, ((double) a1.getAlturaSubArbol()) / a1.getTamSubArbol(), rand);
			}while(aux == null || aux == a1);
			a1 = aux;	

			do 																				//Se selecciona un subarbol de i2 que se pueda intercambiar con la limitacion de tamaño		
			{ 																																
				aux = explorarArbol(a2, a1.getProfundidad()/*profundidad de a1*/, a1.getAlturaSubArbol()/*altura maxima de los hijos de a1*/, rand, ((double) a2.getAlturaSubArbol()) / a2.getTamSubArbol());
			}while(aux == null || aux == a2);	
			a2 = aux;
			
			//Intercambio subarboles
			a1.intercambiarNodo(a2);
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
			return null;
		}
		return null;
	}
	
	private Arbol explorarArbol(Arbol c, int prof_raiz, int altura_hijos, Random rand, double prob)
	{
		Arbol aux;
		//Si la altura de los hijos de 1 entra en 2 	Si la altura de los hijos de 2 entra en 1
		if(c.getAlturaSubArbol() <= prof_raiz && c.getProfundidad() >= altura_hijos && rand.nextDouble() < prob)
			return c;
		
		for(Arbol a: c.getHijos())
		{
			aux = explorarArbol(a, prof_raiz, altura_hijos, rand, prob);
			if(aux != null)
				return aux;
		}
		return null;
	}
	
	public String toString() {
		return super.getId();
	}
}
