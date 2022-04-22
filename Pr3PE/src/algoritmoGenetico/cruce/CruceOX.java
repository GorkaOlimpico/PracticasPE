package algoritmoGenetico.cruce;

import java.util.List;
import java.util.Random;

import gen.Gen;
import individuos.Individuo;

public class CruceOX extends Cruce {
	private final String type = "OX";		//Hago yo el cruce OX que es igual que el OX-PP, haz tu el PMX en su lugar
	
	public CruceOX()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceOX();
		return null;
	}

	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		Random rand = new Random();
		List<Gen> genes = (List<Gen>) i1.getGenes();
		List<Gen> genes2 = (List<Gen>) i2.getGenes();
		for(int i = 0; i < genes.size(); i++)
		{
			int ini = rand.nextInt(genes.get(i).getLongitud() - 1), fin;
			do {
				fin = rand.nextInt(genes.get(i).getLongitud() - ini) + ini;
			}while(fin == ini);
			
			Object aux;			//Se intercambian los alelos entre ini y fin
			for(int j = ini; j <= fin; j++)
			{
				aux = genes.get(i).getAlelo(j);
				genes.get(i).setAlelo(j, genes2.get(i).getAlelo(j));
				genes2.get(i).setAlelo(j, aux);
			}
			
			OX(i1, i, ini, fin, i2);
			OX(i2, i, ini, fin, i1);
		}
  	}

	private void OX(Individuo i1, int i, int ini, int fin, Individuo i2)
	{
		List<Gen> genes = (List<Gen>) i1.getGenes();
		List<Gen> genes2 = (List<Gen>) i2.getGenes();
		int next = (fin + 1) % genes.get(i).getLongitud();		
		int longitud = genes.get(i).getLongitud() - (fin - ini);
		for(int j = 1; j < longitud; j++)
		{
			int posicion = (fin + j) % genes.get(i).getLongitud();
			
			boolean valido = true;									//Se comprueba si el elemento en la posicion (posicion) esta entre los intercambiados
			for(int k = ini; k <= fin && valido; k++)
				if((int) genes.get(i).getAlelo(k) == (int) genes.get(i).getAlelo(posicion))
					valido = false;
			
			if(valido)												//Si no esta se pone en la siguiente posicion (next)
			{	
				genes.get(i).setAlelo(next, genes.get(i).getAlelo(posicion));
				next = (next + 1) % genes.get(i).getLongitud();
			}
		}
		
		for(int j = ini; j <= fin; j++)
		{
			next %= genes.get(i).getLongitud();
			
			boolean valido = true;									//Se comprueba si el elemento en la posicion (posicion) esta entre los intercambiados
			for(int k = ini; k <= fin && valido; k++)
				if((int) genes.get(i).getAlelo(k) == (int) genes2.get(i).getAlelo(j))
					valido = false;
			
			if(valido)												//Si no esta se pone en la siguiente posicion (next)
			{	
				genes.get(i).setAlelo(next, genes2.get(i).getAlelo(j));
				next++;
			}
		}
	}
	
	public String toString() {
		return super.getId();
	}
}
