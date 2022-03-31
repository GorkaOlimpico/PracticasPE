package algoritmoGenetico.mutacion;

import java.util.Random;

import individuos.Individuo;

public class MutacionInsercion extends Mutacion {

	private final String type = "Insercion";
	
	public MutacionInsercion()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionInsercion();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		for(int j = 0; j < ind.getGenes().size(); j++) { // para varios genes
			
			// 1. Se selecciona 1 alelo al azar
			Random rand = new Random();
			int a1 = rand.nextInt(ind.getGenes().get(j).getLongitud());
			
			// 2. Se selecciona 1 posicion al azar
			int p1 = rand.nextInt(ind.getGenes().get(j).getLongitud());
			
			while (p1 == a1) {
				p1 = rand.nextInt(ind.getGenes().get(j).getLongitud());
			}
			
			// 3. Se inserta el alelo en la posici�n y desplaza al resto hacia la derecha o izquierda
			Object alelo_aux = ind.getGenes().get(j).getAlelo(p1);
			ind.getGenes().get(j).setAlelo(p1, ind.getGenes().get(j).getAlelo(a1)); //pongo el alelo en la posicion p1
			if(a1 > p1) {
				p1++;
			}
			else p1--;
			
			Object alelo_aux2;
			while(p1 != a1){// hasta que el �ltimo elemento a cambiar sea a1
				alelo_aux2 = ind.getGenes().get(j).getAlelo(p1);
				ind.getGenes().get(j).setAlelo(p1, alelo_aux);
				
				alelo_aux = alelo_aux2;
				if(a1 > p1) {
					p1++;
				}
				else p1--;
			}
			
			// la �ltima posici�n
			ind.getGenes().get(j).setAlelo(p1, alelo_aux);
		}
	}
	
	public String toString() {
		return "Inserci�n";
	}

}
