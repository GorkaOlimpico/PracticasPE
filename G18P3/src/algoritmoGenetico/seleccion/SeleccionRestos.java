package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import individuos.Individuo;

public class SeleccionRestos extends Seleccion {
	private final String type = "Restos";
	
	public SeleccionRestos()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionRestos();
		return null;
	}

	@Override
	protected void seleccionar(Individuo[] ind, Individuo[] aux) {
		SeleccionRuleta s = new SeleccionRuleta();
		s.seleccionar(ind, aux);
		
		List<Double> prob = new ArrayList<>();
		
		for(int i = 0; i < ind.length; i++)
			prob.add(ind[i].getFitness());
		
		desplazamiento(prob, aux[0].max(), ind);
		
		double sum = 0;
		for(int i = 0; i < ind.length; i++)
			sum += prob.get(i);
		
		for(int i = 0; i < ind.length; i++)
			prob.set(i, (prob.get(i) / sum));
		
		List<Integer> restos = new ArrayList<>();
		for(int i = 0; i < ind.length; i++)
			restos.add((int) (prob.get(i) * ind.length));
		
		int contador = 0;
		for(int i = 0; i < ind.length; i++)
			for(int j = 0; j < restos.get(i); j++)
			{
				aux[contador].copiarIndividuo(ind[i]);
				contador++;
			}
	}
	
	public String toString() {
		return super.getId();
	}
}
