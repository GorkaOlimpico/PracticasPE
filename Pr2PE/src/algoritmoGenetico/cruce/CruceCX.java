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
			
			// 2. Hago un ciclo guardando los valores correspondientes en la lista. 
			int i = 0;
			boolean terminado = false;
			while(!terminado){
				Pair p = new Pair(i,i1.getGenes().get(j).getAlelo(i));
				l3.add(p);
				i = buscaPos(i2.getGenes().get(j).getAlelo(i), i1.getGenes().get(j).getAlelos());
				if(i==0) terminado=true;
			}
			
			i=0;
			terminado = false;
			while(!terminado){
				Pair p = new Pair(i,i2.getGenes().get(j).getAlelo(i));
				l4.add(p);
				i = buscaPos(i1.getGenes().get(j).getAlelo(i), i2.getGenes().get(j).getAlelos());
				if(i==0) terminado=true;
			}
			
			// 3. Guardo el resto de elementos de las listas de la forma: 
			//		elementos i1 => l4
			//		elementos i2 => l3
	
			for(int k = 0; k < i1.getGenes().get(j).getLongitud(); k++) {
				if(!contenidoEn(i1.getGenes().get(j).getAlelo(k),l4)) {
					Pair p = new Pair(k,i1.getGenes().get(j).getAlelo(k));
					l4.add(p);
				}
			}
			
			for(int k = 0; k < i2.getGenes().get(j).getLongitud(); k++) {
				if(!contenidoEn(i2.getGenes().get(j).getAlelo(k),l3)) {
					Pair p = new Pair(k,i2.getGenes().get(j).getAlelo(k));
					l3.add(p);
				}
			}
			
			// 4. Paso de lista a individuo
			for(Pair p: l3) {
				i1.getGenes().get(j).setAlelo((int) p.getFirst(), p.getSecond());
			}
			
			for(Pair p: l4) {
				i2.getGenes().get(j).setAlelo((int) p.getFirst(), p.getSecond());
			}
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
	public int buscaPos(Object o, List<Object> lista) {
		int pos = -1;
		for(int i = 0; i< lista.size(); i++) {
			if(o == lista.get(i)) {
				pos = i;
			}
		}
		
		return pos;
	}

	public String toString() {
		return super.getId();
	}
}
