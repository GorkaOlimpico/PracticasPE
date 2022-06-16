package algoritmoGenetico.cruce.GE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import gen.Gen;
import individuos.Individuo;

public class CruceOXPP extends Cruce {
	private final String type = "OX-PP";
	private final double prob = 0.1; //Probabilidad de elegir la posicion como punto de intercambio
	
	public CruceOXPP()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceOXPP();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		Random rand = new Random();
		List<Gen> genes = (List<Gen>) i1.getGenes();
		List<Gen> genes2 = (List<Gen>) i2.getGenes();
		for(int i = 0; i < genes.size(); i++)
		{
			List<Integer> pos = new ArrayList<>();
			do {
				Object aux;
				for(int j = 0; j < genes.get(i).getLongitud(); j++)		//Se elige que alelos se intercambian
				{
					if(rand.nextDouble() < prob)
					{
						pos.add(j);
						aux = genes.get(i).getAlelo(j);
						genes.get(i).setAlelo(j, genes2.get(i).getAlelo(j));
						genes2.get(i).setAlelo(j, aux);
					}
				}
			}while(pos.size() == 0);
			
			OXPP(i1, pos, i, i2);
			OXPP(i2, pos, i, i1);
		}
  	}

	private void OXPP(Individuo i1, List<Integer> pos, int i, Individuo i2)
	{
		List<Gen> genes = (List<Gen>) i1.getGenes();
		List<Gen> genes2 = (List<Gen>) i2.getGenes();
		int ini = pos.get(pos.size() - 1) + 1, next = ini % genes.get(i).getLongitud(), indice = 0, i_next = 0;		
		for(int j = 0; j < genes.get(i).getLongitud(); j++)
		{
			while(i_next < pos.size() && pos.get(i_next) == next)				//Si next cae sobre una posicion que ha sido intercambiada pasa a la siguiente
			{
				next = (next + 1) % genes.get(i).getLongitud();
				i_next++;
			}
			int posicion = (ini + j) % genes.get(i).getLongitud();
			boolean valido = true;												//Se comprueba si el elemento en la posicion (posicion) esta entre los intercambiados
			if(indice < pos.size() && pos.get(indice) == posicion)				//El elemento a comprobar ha sido intercambiado en la primera fase
				indice++;
			else																//El elemento a comprobar NO ha sido intercambiado en la primera fase
			{		
				for(int k = 0; k < pos.size() && valido; k++)
					if((int) genes.get(i).getAlelo(pos.get(k)) == (int) genes.get(i).getAlelo(posicion))
						valido = false;
				
				if(valido)														//Si no esta se pone en la siguiente posicion (next)
				{
					genes.get(i).setAlelo(next, genes.get(i).getAlelo(posicion));
					next = (next + 1) % genes.get(i).getLongitud();
				}
			}
		}
		for(int j = 0; j < pos.size(); j++)
		{
			while(i_next < pos.size() && pos.get(i_next) == next)				//Si next cae sobre una posicion que ha sido intercambiada pasa a la siguiente
			{
				next = (next + 1) % genes.get(i).getLongitud();
				i_next++;
			}
			boolean valido = true;
			for(int k = 0; k < pos.size() && valido; k++)
				if((int) genes.get(i).getAlelo(pos.get(k)) == (int) genes2.get(i).getAlelo(pos.get(j)))
					valido = false;
			
			if(valido)														//Si no esta se pone en la siguiente posicion (next)
			{
				genes.get(i).setAlelo(next, genes2.get(i).getAlelo(pos.get(j)));
				next = (next + 1) % genes.get(i).getLongitud();
			}
		}
	}
	
	public String toString() {
		return super.getId();
	}
}