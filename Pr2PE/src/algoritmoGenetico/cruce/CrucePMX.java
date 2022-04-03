package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuos.Individuo;
import individuos.IndividuoPr2;
import individuos.Pair;

public class CrucePMX extends Cruce {
private final String type = "PMX";
	
	public CrucePMX()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CrucePMX();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		for(int j = 0; j < i1.getGenes().size(); j++) { // para varios genes
			
			// 1. Se seleccionan 2 puntos de corte al azar. Max(i1.long-2) y no puede caer en el mismo sitio. Minimo 0, es decir corte entre elemento 0 y 1
			Random rand = new Random();
			int p1 = rand.nextInt(i1.getGenes().get(j).getLongitud()-1);
			int p2 = rand.nextInt(i1.getGenes().get(j).getLongitud()-1);
			while (p1 == p2) {
				p2 = rand.nextInt(i1.getGenes().get(j).getLongitud()-1);
			}
			if(p1 > p2) {
				int aux = p2;
				p2 = p1;
				p1 = aux;
			}
			
			List<Pair> l3 = new ArrayList<Pair>();
			List<Pair> l4 = new ArrayList<Pair>();
			
			int long_mitad = p2 - p1;
			
			// 2. Se ponen la zona intermedia en l3 y l4
			for(int i = p1 +1; i <= p1 + long_mitad; i++) {
				Pair p = new Pair(i,i2.getGenes().get(j).getAlelo(i));
				l3.add(p);
				
				Pair pp = new Pair(i,i1.getGenes().get(j).getAlelo(i));
				l4.add(pp);
			}
			
			// 3. Se ponen los demás valores de los individuos originales y si entran en conflicto se pone su homólogo
			
			for(int i = 0; i< i1.getGenes().get(j).getLongitud(); i++) {
				if(i <= p1 || i > p2) { // fuera de la mitad ya intercambiada
					if(!contenidoEn(i1.getGenes().get(j).getAlelo(i), l3)) {
						Pair p = new Pair(i,i1.getGenes().get(j).getAlelo(i));
						l3.add(p);
					}
					else {
						int pos = buscaPos(i1.getGenes().get(j).getAlelo(i), l3);
						if(!contenidoEn(i1.getGenes().get(j).getAlelo(pos), l3)) {
							Pair p = new Pair(i, i1.getGenes().get(j).getAlelo(pos));
							l3.add(p);
						}
						
					}
				}
			}
			
			for(int i = 0; i< i2.getGenes().get(j).getLongitud(); i++) {
				if(i <= p1 || i > p2) { // fuera de la mitad ya intercambiada
					if(!contenidoEn(i2.getGenes().get(j).getAlelo(i), l4)) {
						Pair p = new Pair(i,i2.getGenes().get(j).getAlelo(i));
						l4.add(p);
					}
					else {
						int pos = buscaPos(i2.getGenes().get(j).getAlelo(i), l4);
						if(!contenidoEn(i2.getGenes().get(j).getAlelo(pos), l4)) {
							Pair p = new Pair(i, i2.getGenes().get(j).getAlelo(pos));
							l4.add(p);
						}
						
					}
				}
			}
			
			// Adaptación para elementos repetidos:
			rellenaHuecos(i1, l3);
			rellenaHuecos(i2, l4);
			
			
			// 4. Se pasa de las listas a los individuos
			for(Pair p: l3) {
				i1.getGenes().get(j).setAlelo((int) p.getFirst(), p.getSecond());
			}
			
			for(Pair p: l4) {
				i2.getGenes().get(j).setAlelo((int) p.getFirst(), p.getSecond());
			}
			
			
		}
	}
	public void rellenaHuecos(Individuo ind, List<Pair> lista) {
		for(int i = 0; i < ind.getGenes().get(0).getLongitud(); i++) {
			if(!contenidoEn(i, lista)) {
				int hueco = primerHueco(ind, lista);
				Pair p = new Pair(hueco, i);
				lista.add(p);
			}
		}
	}
	
	public int primerHueco(Individuo ind, List<Pair> lista) {
		List<Integer> posiciones = new ArrayList<Integer>();
		for(int i = 0; i < ind.getGenes().get(0).getLongitud(); i++) {
			posiciones.add(i);
		}
		
		for(Pair p : lista) {
			if(posiciones.contains(p.getFirst())) {
				posiciones.remove((Object) p.getFirst());
			}
		}
		return posiciones.get(0);
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

	public int buscaPos(Object o, List<Pair> lista) {
		int pos = -1;
		for(int i = 0; i < lista.size(); i++) {
			if(o == lista.get(i).getSecond()) {
				pos = (int) lista.get(i).getFirst();
				break;
			}
		}
		
		return pos;
	}
	public String toString() {
		return super.getId();
	}
}
