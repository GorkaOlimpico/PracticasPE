package algoritmoGenetico.cruce;

import java.util.Random;

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
		for(int i = 0; i < i1.getGenes().size(); i++)
		{
			int ini = rand.nextInt(i1.getGenes().get(i).getLongitud() - 1), fin;
			do {
				fin = rand.nextInt(i1.getGenes().get(i).getLongitud() - ini) + ini;
			}while(fin == ini);
			
			Object aux;			//Se intercambian los alelos entre ini y fin
			for(int j = ini; j <= fin; j++)
			{
				aux = i1.getGenes().get(i).getAlelo(j);
				i1.getGenes().get(i).setAlelo(j, i2.getGenes().get(i).getAlelo(j));
				i2.getGenes().get(i).setAlelo(j, aux);
			}
			
			OX(i1, i, ini, fin, i2);
			OX(i2, i, ini, fin, i1);
		}
  	}

	private void OX(Individuo i1, int i, int ini, int fin, Individuo i2)
	{
		int next = (fin + 1) % i1.getGenes().get(i).getLongitud();		
		int longitud = i1.getGenes().get(i).getLongitud() - (fin - ini);
		for(int j = 1; j < longitud; j++)
		{
			int posicion = (fin + j) % i1.getGenes().get(i).getLongitud();
			
			boolean valido = true;									//Se comprueba si el elemento en la posicion (posicion) esta entre los intercambiados
			for(int k = ini; k <= fin && valido; k++)
				if((int) i1.getGenes().get(i).getAlelo(k) == (int) i1.getGenes().get(i).getAlelo(posicion))
					valido = false;
			
			if(valido)												//Si no esta se pone en la siguiente posicion (next)
			{	
				i1.getGenes().get(i).setAlelo(next, i1.getGenes().get(i).getAlelo(posicion));
				next = (next + 1) % i1.getGenes().get(i).getLongitud();
			}
		}
		
		for(int j = ini; j <= fin; j++)
		{
			next %= i1.getGenes().get(i).getLongitud();
			
			boolean valido = true;									//Se comprueba si el elemento en la posicion (posicion) esta entre los intercambiados
			for(int k = ini; k <= fin && valido; k++)
				if((int) i1.getGenes().get(i).getAlelo(k) == (int) i2.getGenes().get(i).getAlelo(j))
					valido = false;
			
			if(valido)												//Si no esta se pone en la siguiente posicion (next)
			{	
				i1.getGenes().get(i).setAlelo(next, i2.getGenes().get(i).getAlelo(j));
				next++;
			}
		}
	}
	
	public String toString() {
		return super.getId();
	}
}
