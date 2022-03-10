package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuos.Individuo;

public class SeleccionRuleta extends Seleccion {
	private final String type = "Ruleta";
	
	public SeleccionRuleta()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionRuleta();
		return null;
	}

	@Override
	protected void seleccionar(Individuo[] ind, Individuo[] aux) {
		List<Double> prob = new ArrayList<>();
		
		for(int i = 0; i < ind.length; i++)
			prob.add(ind[i].getFitness());
		
		desplazamiento(prob, aux[0].max());
		
		double sum = 0;
		for(int i = 0; i < ind.length; i++)
			sum += prob.get(i);
		
		prob.set(0, prob.get(0) / sum);
		for(int i = 1; i < ind.length; i++)
			prob.set(i, (prob.get(i) / sum) + prob.get(i - 1));
		
		Random rand = new Random();
		int j;
		double r;
		for(int i = 0; i < ind.length; i++)
		{
			j = 0;
			r = rand.nextDouble();
			while(prob.get(j) < r) {j++;}
			copiarIndividuo(ind[j], aux[i]);
		}
	}
}
