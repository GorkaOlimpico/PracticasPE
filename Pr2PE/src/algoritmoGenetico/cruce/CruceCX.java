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
			/*
			Individuo i3 = i1;
			Individuo i4 = i2;
			
			for(int i = 0; i < i3.getGenes().get(j).getLongitud(); i++) { 
				i3.getGenes().get(j).setAlelo(i, 0);
			}
			for(int i = 0; i < i4.getGenes().get(j).getLongitud(); i++) { 
				i4.getGenes().get(j).setAlelo(i, 0);
			}
			*/
			// 2. Hago un ciclo y paro. Esto con los dos individuos

			int i = 0;
			while(!contenidoEn(i2.getGenes().get(j).getAlelo(i),l3)) { //si el i2[i] no está en i3
				for(int k = 0; k < i1.getGenes().get(j).getLongitud(); k++) { // por cada alelo de i1
					if(i2.getGenes().get(j).getAlelo(i) == i1.getGenes().get(j).getAlelo(k)) { // si ese alelo == i2[i]
						//i3.getGenes().get(j).setAlelo(k, i1.getGenes().get(j).getAlelo(k)); // lo insertamos en la lista
						Pair p = new Pair(k, i1.getGenes().get(j).getAlelo(k));
						l3.add(p);
						i = k; // nos movemos a la siguiente posicion siguiendo el ciclo
					}
				}
			}
			
			i = 0;
			while(!contenidoEn(i1.getGenes().get(j).getAlelo(i),l4)) { //si el i2[i] no está en i3
				for(int k = 0; k < i2.getGenes().get(j).getLongitud(); k++) { // por cada alelo de i1
					if(i1.getGenes().get(j).getAlelo(i) == i2.getGenes().get(j).getAlelo(k)) { // si ese alelo == i2[i]
						//i4.getGenes().get(j).setAlelo(k, i2.getGenes().get(j).getAlelo(k)); // lo insertamos en la lista
						Pair p = new Pair(k, i2.getGenes().get(j).getAlelo(k));
						l4.add(p);
						i = k; // nos movemos a la siguiente posicion siguiendo el ciclo
					}
				}
			}
			
			// 3. Apunto cuales han sido las posiciones de i1 e i2 que no se han tocado
			
			int[] posiciones;
			for(int t = 0; t <i; t++) {
				posiciones[t] = t;
			}
			for(Pair p: l3) {
				if()
			}
			
			for(int k = 0; k < i3.getGenes().get(j).getLongitud(); k++) {
				if(i3.getGenes().get(j).getAlelo(k) == (Object) 0) {
					i3.getGenes().get(j).setAlelo(k, i2.getGenes().get(j).getAlelo(k));
				}
			}
			
			for(int k = 0; k < i4.getGenes().get(j).getLongitud(); k++) {
				if(i4.getGenes().get(j).getAlelo(k) == (Object) 0) {
					i4.getGenes().get(j).setAlelo(k, i1.getGenes().get(j).getAlelo(k));
				}
			}
			
			// 4. i1 ahora es i3, i2 ahora es i4
			
			i1 = i3;
			i2 = i4;
			//TODO Ojo esto puede dar problemas por los punteros y tal
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
