package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuos.Individuo;

public class SeleccionTorneoProbabilistico extends Seleccion {
	private final String type = "Torneo Probabilistico";
	private final double p = 0.75;
	
	public SeleccionTorneoProbabilistico()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionTorneoProbabilistico();
		return null;
	}
	
	@Override
	protected void seleccionar(Individuo[] ind, Individuo[] aux) {
		List<Double> prob = new ArrayList<>();
		
		for(int i = 0; i < ind.length; i++)
			prob.add(ind[i].getFitness());
		
		desplazamiento(prob, aux[0].max(), ind);
		
		double sum = 0;
		for(int i = 0; i < ind.length; i++)
			sum += prob.get(i);
		
		for(int i = 0; i < ind.length; i++)
			prob.set(i, (prob.get(i) / sum));
		
		Random rand = new Random();
		for(int i = 0; i < ind.length; i++)
		{
			int i1 = rand.nextInt(ind.length), i2 = rand.nextInt(ind.length), i3 = rand.nextInt(ind.length), j;
			if(rand.nextDouble() < p)
				if(prob.get(i1) > prob.get(i2))
					if(prob.get(i1) > prob.get(i3))
						j = i1;
					else
						j = i3;
				else
					if(prob.get(i2) > prob.get(i3))
						j = i2;
					else
						j = i3;
			else
				if(prob.get(i1) < prob.get(i2))
					if(prob.get(i1) < prob.get(i3))
						j = i1;
					else
						j = i3;
				else
					if(prob.get(i2) < prob.get(i3))
						j = i2;
					else
						j = i3;
			aux[i].copiarIndividuo(ind[j]);
		}
	}
}
