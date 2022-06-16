package algoritmoGenetico.cruce.GE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import gen.Gen;
import individuos.Individuo;

public class CruceOPTIMO extends Cruce {
	private final String type = "OPTIMO";

	
	public CruceOPTIMO()
	{
		super.id = type;
	
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceOPTIMO();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		List<Gen> genes = (List<Gen>) i1.getGenes();
		int k = 1;
		for(Object i : genes.get(0).getAlelos()) {
			
			//System.out.println("k " + k + ":"+ i);
			k++;
		}
		//System.out.println(i1.getSolucion());
		//System.out.println();
		
		genes.get(0).setAlelo(0, 1);
		genes.get(0).setAlelo(1, 0);
		genes.get(0).setAlelo(2, 3);
		genes.get(0).setAlelo(3, 4);
		genes.get(0).setAlelo(4, 4);
		genes.get(0).setAlelo(5, 4);
		genes.get(0).setAlelo(6, 3);
		genes.get(0).setAlelo(7, 5);
		genes.get(0).setAlelo(8, 9);
		genes.get(0).setAlelo(9, 7);
		genes.get(0).setAlelo(10, 0);
		genes.get(0).setAlelo(11, 3);
		genes.get(0).setAlelo(12, 5);
		genes.get(0).setAlelo(13, 8);
		genes.get(0).setAlelo(14, 6);
		genes.get(0).setAlelo(15, 2);
		genes.get(0).setAlelo(16, 4);
		
		/* Solucion mux6
		genes.get(0).setAlelo(0, 1);
		genes.get(0).setAlelo(1, 0);
		genes.get(0).setAlelo(2, 0);
		genes.get(0).setAlelo(3, 2);
		genes.get(0).setAlelo(4, 5);
		genes.get(0).setAlelo(5, 2);
		genes.get(0).setAlelo(6, 4);
		genes.get(0).setAlelo(7, 6);
		genes.get(0).setAlelo(8, 1);
		genes.get(0).setAlelo(9, 0);
		genes.get(0).setAlelo(10, 0);
		genes.get(0).setAlelo(11, 2);
		genes.get(0).setAlelo(12, 5);
		genes.get(0).setAlelo(13, 4);
		genes.get(0).setAlelo(14, 7);
		genes.get(0).setAlelo(15, 1);
		genes.get(0).setAlelo(16, 0);
		genes.get(0).setAlelo(17, 0);
		genes.get(0).setAlelo(18, 5);
		genes.get(0).setAlelo(19, 2);
		genes.get(0).setAlelo(20, 4);
		genes.get(0).setAlelo(21, 8);
		genes.get(0).setAlelo(22, 0);
		genes.get(0).setAlelo(23, 0);
		genes.get(0).setAlelo(24, 5);
		genes.get(0).setAlelo(25, 4);
		genes.get(0).setAlelo(26, 9);
		*/
		
		k=0;
		for(Object i : genes.get(0).getAlelos()) {
			
			//System.out.println("k " + k + ":"+ i);
			k++;
		}
		//System.out.println(i1.getSolucion());
		//System.out.println();
		
		
		
		
		//Multiplexor
		// OR ( () ()
	}

	public String toString() {
		return super.getId();
	}
}
