package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gen.Gen;
import individuos.Individuo;

public class CruceCO extends Cruce {
	private final String type = "CO";
	private List<Integer> lista;
	
	public CruceCO()
	{
		super.id = type;
	
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceCO();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		// 1. Codifica. Son listas de posiciones relativas
		List<Integer> cod1 = codifica(i1);
		List<Integer> cod2 = codifica(i2);
		
		// 2. Cruce Monopunto entre codificados
		cruceMonopunto(cod1, cod2);
		
		
		// 3. Decodifica los 2 individuos
		decodifica(i1, cod1);
		decodifica(i2, cod2);
		
	}
	
	public List<Integer> codifica(Individuo i1) {
		List<Integer> cod = new ArrayList<Integer>();
		for(int j = 0; j < i1.getGenes().size(); j++) { // para varios genes
			
			int n = i1.getGenes().get(j).getLongitud();
			lista = new ArrayList<Integer>();
			for(int i=0; i<n; i++) {
				lista.add(i);
			}
			
			
			
			for(int k=0; k<n; k++) { // recorro la List de alelos(vuelos) y apunto su posición relativa de lista
				int pos = 0;
				for(int i= 0; i<lista.size(); i++) {
					if(i1.getGenes().get(j).getAlelo(k)==lista.get(i)) {
						pos = i;
						lista.remove(i);
					}
				}
				cod.add(pos);
			}
			
			
		}
		return cod;
	}
	
	public void cruceMonopunto(List<Integer> cod1, List<Integer> cod2) {
		Random rand = new Random();
		int aux;
		for(int i = 0; i < cod1.size(); i++) {
			for(int j = rand.nextInt(cod1.size()); j < cod1.size(); j++) {
				aux = cod1.get(j);
				cod1.remove(j);
				cod1.add(j, cod2.get(j));
				cod2.remove(j);
				cod2.add(j, aux);
			}
		}
		/*
		Random rand = new Random();
		List<Gen> g1 = i1.getGenes(), g2 = i2.getGenes();
		double aux;
		for(int i = 0; i < g1.size(); i++)
		{
			for(int j = rand.nextInt(g1.get(i).getLongitud()); j < g1.get(i).getLongitud(); j++)
			{
				aux = (double) g1.get(i).getAlelo(j);
				g1.get(i).setAlelo(j, g2.get(i).getAlelo(j));
				g2.get(i).setAlelo(j, aux);
			}
		}
		*/
	}
	
	public void decodifica(Individuo ind, List<Integer> cod) {
		lista = new ArrayList<Integer>();
		for(int i=0; i<ind.getGenes().get(0).getLongitud(); i++) {
			lista.add(i);
		}
		for(int i = 0; i<cod.size(); i++) {
			ind.getGenes().get(0).setAlelo(i, lista.get(cod.get(i)));
			int index = cod.get(i);
			lista.remove(index);
		}
	}
	public String toString() {
		return "CO";
	}

}
