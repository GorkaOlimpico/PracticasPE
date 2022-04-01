package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;

import individuos.Individuo;
import individuos.IndividuoPr2;
import individuos.Pair;

public class CruceCX extends Cruce {
private final String type = "CX";
	
	public CruceCX()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceCX();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		for(int j = 0; j < i1.getGenes().size(); j++) { // para varios genes
			
		
			// 1. Inicializo a todo 0s los i3 e i4
			List<Pair> l3 = new ArrayList<Pair>();
			List<Pair> l4 = new ArrayList<Pair>();
			
			// 2. Hago un ciclo y paro. Esto con los dos individuos

			int i = 0;

			while(!contenidoEn(i2.getGenes().get(j).getAlelo(i),l3)) {
				Pair p = new Pair(i,i1.getGenes().get(j).getAlelo(i));
				l3.add(p);
				boolean encontrado = false;

				for(int k = 0; k < i1.getGenes().get(j).getLongitud() && !encontrado; k++) { // se busca el elemento homologo de i2 en i1 para bajarlo
					if(i1.getGenes().get(j).getAlelo(k) == i2.getGenes().get(j).getAlelo(i)) {
						i = k;
						encontrado = true;
					}
				}
				
			}
			
		
			
			i = 0;

			while(!contenidoEn(i1.getGenes().get(j).getAlelo(i),l3)) {
				Pair p = new Pair(i,i2.getGenes().get(j).getAlelo(i));
				l4.add(p);
				boolean encontrado = false;

				for(int k = 0; k < i2.getGenes().get(j).getLongitud() && !encontrado; k++) { // se busca el elemento homologo de i1 en i2 para bajarlo
					if(i2.getGenes().get(j).getAlelo(k) == i1.getGenes().get(j).getAlelo(i)) {
						i = k;
						encontrado = true;
					}
				}
				
			}
			
			
			// 3. Apunto cuales han sido las posiciones de i1 e i2 que no se han tocado
			
			List<Integer> posiciones = new ArrayList<Integer>();
			
			for(int k = 0; k < l3.size(); k++) {
				boolean aparece = false;
				for(Pair p: l3) {
					if(k == (int) p.getFirst()) {
						aparece = true;
					}
				}
				if(!aparece) {
					posiciones.add(k);
				}
			}

			
			// 4. Intercambio los alelos de las posiciones correspondientes
			for(int pos: posiciones) {
				i1.getGenes().get(j).intercambiarAlelo(pos, i2.getGenes().get(j));
			}
			
			// 5. Hago que el orden de las listas sea el nuevo orden en los individuos
			
			
			for(Pair p: l3) {
				i1.getGenes().get(j).setAlelo((int) p.getFirst(), p.getSecond());
			}
			
			for(Pair p: l4) {
				i2.getGenes().get(j).setAlelo((int) p.getFirst(), p.getSecond());
			}
			System.out.println("final");
		}
	}
	
	public boolean contenidoEn(Object o, List<Pair> lista) {
		boolean contenido = false;
		for(Pair p : lista) {
			if(p.getSecond() == o) {
				contenido = true;
			}
		}
		
		return contenido;
	}

	public String toString() {
		return "CX";
	}
}
