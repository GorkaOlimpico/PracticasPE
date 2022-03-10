package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuos.Individuo;

public class SeleccionTruncamiento extends Seleccion {
	private final String type = "Truncamiento";
	
	public SeleccionTruncamiento()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionTruncamiento();
		return null;
	}

	@Override
	protected void seleccionar(Individuo[] ind, Individuo[] aux) {
		Random rand = new Random();
		double trunc = (rand.nextInt(40) + 10) / 100;
		
		List<Double> prob = new ArrayList<>();
		
		for(int i = 0; i < ind.length; i++)
			prob.add(ind[i].getFitness());
		
		desplazamiento(prob, aux[0].max());
		ordenar(prob, ind);
		
		int repetido = (int) (1 / trunc), limite = (int) (ind.length * trunc), contador = 0;
		for(int i = 0; i < limite; i++)
		{
			for(int j = 0; j < repetido; j++)
			{
				copiarIndividuo(ind[i], aux[contador]);
				contador++;
			}
		}
		
		for(int i = 0; contador < ind.length; i++)
		{
			copiarIndividuo(ind[i], aux[contador]);
			contador++;
		}
	}
	
	private void ordenar(List<Double> prob, Individuo[] ind)
	{
		//TODO ordenar por maximos segun prob (prob e ind)
	}
}
