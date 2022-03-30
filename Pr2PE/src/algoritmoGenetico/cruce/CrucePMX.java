package algoritmoGenetico.cruce;

import java.util.List;
import java.util.Random;

import individuos.Individuo;

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
			
			// 1. Se seleccionan 2 puntos de corte al azar. Max(i1.long-2) y no puede caer en el mismo sitio
			Random rand = new Random();
			int p1 = rand.nextInt(i1.getGenes().get(j).getLongitud()-1);
			int p2 = rand.nextInt(i1.getGenes().get(j).getLongitud()-1);
			while (p1 == p2) {
				p2 = rand.nextInt(i1.getGenes().get(j).getLongitud()-1);
			}
			
			// 2. Inicializo i3 e i4
			Individuo i3 = i1;
			Individuo i4 = i2;
			
			for(int i = 0; i < i3.getGenes().get(j).getLongitud(); i++) { 
				i3.getGenes().get(j).setAlelo(i, 0);
			}
			for(int i = 0; i < i4.getGenes().get(j).getLongitud(); i++) { 
				i4.getGenes().get(j).setAlelo(i, 0);
			}
			// hago que p1 sea el número menor 
			if(p2 < p1) {
				int aux = p2;
				p2 = p1;
				p1 = aux;
			}
			
			// 3. Se intercambian las zonas intermedias (usando i3 e i4)
			int long_mitad = p2-p1;
			for(int i = p1;i<long_mitad; i++) {
				i3.getGenes().get(j).setAlelo(i, i2.getGenes().get(j).getAlelo(i));
				i4.getGenes().get(j).setAlelo(i, i1.getGenes().get(j).getAlelo(i));
			}
			
			// 4. Se ponen los valores que no entren en conflicto. Si entran en conflicto se pone su homólogo
			
			//Esto para i3
			for(int i = p2;i<i1.getGenes().get(j).getLongitud(); i++) {
				if(!contenidoEn(i1.getGenes().get(j).getAlelo(i), i3.getGenes().get(j).getAlelos())) { // Si no hay conflicto
					i3.getGenes().get(j).setAlelo(i, i1.getGenes().get(j).getAlelo(i));
				}
				else {
					int pos = buscaPos(i1.getGenes().get(j).getAlelo(i), i3.getGenes().get(j).getAlelos());
					i3.getGenes().get(j).setAlelo(i, i4.getGenes().get(j).getAlelo(pos));
				}
			}
			
			//Esto para i4
			for(int i = p2;i<i2.getGenes().get(j).getLongitud(); i++) {
				if(!contenidoEn(i2.getGenes().get(j).getAlelo(i), i4.getGenes().get(j).getAlelos())) { // Si no hay conflicto
					i4.getGenes().get(j).setAlelo(i, i2.getGenes().get(j).getAlelo(i));
				}
				else {
					int pos = buscaPos(i2.getGenes().get(j).getAlelo(i), i4.getGenes().get(j).getAlelos());
					i4.getGenes().get(j).setAlelo(i, i3.getGenes().get(j).getAlelo(pos));
				}
			}
			
		}
	}
	
	public boolean contenidoEn(Object o, List<Object> lista) {
		boolean contenido = false;
		for(Object objeto : lista) {
			if(o == objeto) {
				contenido = true;
			}
		}
		
		return contenido;
	}

	public int buscaPos(Object o, List<Object> lista) {
		int pos = -1;
		for(int i = 0; i< lista.size(); i++) {
			if(lista.get(i) == o) {
				pos = i;
			}
		}
		
		return pos;
	}
}
