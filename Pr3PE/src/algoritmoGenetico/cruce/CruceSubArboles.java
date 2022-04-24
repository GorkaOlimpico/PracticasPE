package algoritmoGenetico.cruce;

import java.util.Random;

import arbol.Arbol;
import individuos.Individuo;

public class CruceSubArboles extends Cruce {
	private final String type = "Sub-Arboles";
	private final double prob = 0.5;
	
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
		Arbol a1 = (Arbol) i1.getGenes();
		Arbol a2 = (Arbol) i2.getGenes();
		Arbol aux = a1;
		Random rand = new Random();
		
		//Seleccion subarboles
		do																				//Se selecciona un subarbol de i1
		{
			aux = explorarArbol(a1, 0, a1.getProfundidad() - 1, rand, a1.getAlturaSubArbol() / (double) a1.getProfundidad());	
		}while(aux == null || aux == a1);
		a1 = aux;	
		
		do 																				//Se selecciona un subarbol de i2 que se pueda intercambiar con la limitacion de tamaño		
		{ 																																
			aux = explorarArbol(a2, a1.getProfundidad(), a1.getTamSubArbol(), rand, a2.getAlturaSubArbol() / (double) a2.getProfundidad());
		}while(aux == null || aux == a2);									
		a2 = aux;
		
		
		//Intercambio subarboles
		a1.intercambiarNodo(a2);
	}
	
	private Arbol explorarArbol(Arbol c, int prof_raiz, int altura_hijos, Random rand, double prob)
	{
		Arbol aux = null;
		//Si la altura de los hijos de 1 entra en 2 	Si la altura de los hijos de 2 entra en 1
		if(c.getAlturaSubArbol() <= prof_raiz && c.getProfundidad() < altura_hijos && rand.nextDouble() < prob && c.seleccionarCruce(rand.nextDouble()))
			aux = c;
		if(aux == null)
		{
			for(Arbol a: c.getHijos())
			{
				if(aux == null)
					aux = explorarArbol(a, prof_raiz, altura_hijos, rand, prob);
			}
		}
		return aux;
	}

}
