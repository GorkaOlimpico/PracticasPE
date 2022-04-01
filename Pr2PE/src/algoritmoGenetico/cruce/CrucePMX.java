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
			//p2 = p1 +2;
			
			
			// 2. Se intercambian las mitades
			for(int i = p1+1; i <= p2; i++)
				i1.getGenes().get(j).intercambiarAlelo(i, i2.getGenes().get(j));
			
			// 3. Se recorren i1 e i2 para incluir el resto de números. Si el elemento ya está incluido entonces ponen su homólogo.
			// Puedo apoyarme en 2 listas para no alterar a los individuos en el proceso y así evitar errores.
			List<Pair> l1 = new ArrayList<Pair>();

			for(int i = 0; i<i1.getGenes().get(j).getLongitud(); i++) {
				for(int k = i+1; k < i1.getGenes().get(j).getLongitud(); k++) {
					if(i1.getGenes().get(j).getAlelo(i) == i1.getGenes().get(j).getAlelo(k)) { // Si ya está en la lista:
						if(i > p1 && i <= p2){//si el elem i pertece a la mitad intercambiada se intercambia el otro
							//i1.getGenes().get(j).setAlelo(k, i2.getGenes().get(j).getAlelo(i)); // Se cambia por su homólogo
							Pair p = new Pair(k, i2.getGenes().get(j).getAlelo(i));
							l1.add(p);
						}
						else {
							//i1.getGenes().get(j).setAlelo(i, i2.getGenes().get(j).getAlelo(k));
							Pair p = new Pair(i, i2.getGenes().get(j).getAlelo(k));
							l1.add(p);
						}
					}
				}
			}
			
			for(int i = 0; i<i2.getGenes().get(j).getLongitud(); i++) {
				for(int k = i+1; k < i2.getGenes().get(j).getLongitud(); k++) {
					if(i2.getGenes().get(j).getAlelo(i) == i2.getGenes().get(j).getAlelo(k)) { // Si ya está en la lista:
						if(i > p1 && i <= p2)//si el elem i pertece a la mitad intercambiada se intercambia el otro
							i2.getGenes().get(j).setAlelo(k, i1.getGenes().get(j).getAlelo(i)); // Se cambia por su homólogo
						else 
							i2.getGenes().get(j).setAlelo(i, i1.getGenes().get(j).getAlelo(k));
					}
				}
			}
			// Recorro la lista y sustituyo los elementos correspondientes
			for(Pair p: l1) {
				i1.getGenes().get(j).setAlelo((int) p.getFirst(), p.getSecond());
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

	
	public String toString() {
		return "PMX";
	}
}
