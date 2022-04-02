package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import individuos.Individuo;

public class SeleccionRanking extends Seleccion {
private final double beta = 1.5;
private final String type = "Ranking";
	
	public SeleccionRanking()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionRanking();
		return null;
	}

	@Override
	protected void seleccionar(Individuo[] ind, Individuo[] aux) {
		List<Double> prob = new ArrayList<>();
		
		for(int i = 0; i < ind.length; i++)
			prob.add(ind[i].getFitness());
		
		desplazamiento(prob, aux[0].max(), ind);

		prob.set(0, beta / prob.size()); 
		for(int i = 1; i < prob.size(); i++)													//TODO no estoy muy seguro de que este bien
			prob.set(i, ((beta - 2 * (beta - 1) * i / (prob.size()) - 1) / prob.size()) + prob.get(i - 1)); 	
		
		SeleccionRuleta ruleta = new SeleccionRuleta();
		ruleta.ruleta(ind,  aux,  prob);
	}

	public String toString() {
		return super.getId();
	}
}
