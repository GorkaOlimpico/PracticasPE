package individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import arbol.Nodo;
import arbol.Arbol;

public class IndividuoPG extends Individuo{

	private final static String type = "Programación Genética"; 
	private double k_bloating;
	
 	public IndividuoPG()
	{
		super.id = type;
	}
	
	public IndividuoPG(int profundidad, int tipo_generacion, int prof_generar)
	{
		super.id = type;
		if(tipo_generacion == 0)
			genes = Arbol.generarFull(new Random(), profundidad, null);						//Full inicialization
		if(tipo_generacion == 1)
			genes = Arbol.generarGrow(new Random(), profundidad, null, prof_generar);		//Grow inicialization (prof_generar tiene que ser >= 1)
	}
	
	@Override
	public boolean max() {
		return true;
	}

	@Override
	public double getValor() {
		int suma = 0;
		Arbol arbol = (Arbol) genes;
		List<List<Boolean>> combinaciones = entradas;
		for(int i = 0; i < combinaciones.size(); i++)
		{
			if(arbol.execute(combinaciones.get(i)) == Individuo.solucion[i])
				suma++;
		}
		return suma + (k_bloating * arbol.getTamSubArbol());				//Usamos el metodo de bloating: "Penalización bien fundamentada"
	}

	@Override
	public double getFenotipo(int i) {return 0;}

	@Override
	public Cruce[] getCruces() {
		return Cruce.getCrucesPG();
	}

	@Override
	public Mutacion[] getMutaciones() {
		return Mutacion.getMutacionesPG();
	}

	@Override
	protected Individuo[] parse(int tam, Object[] datos) {
		IndividuoPG[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoPG[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoPG();
			else
			{
				int profundidad = (int) datos[1];	
				profundidad--;
				String tipo_generacion = (String) datos[2];
				if(tipo_generacion == "Ramped-Half")				//Ramped and half inicialization
				{
					int aux = tam / profundidad;
					for(int j = 1; j <= profundidad; j++)			//La profundidad de la hoja es 0 y la de la raiz profundidad
					{
						for(int i = 0; i < aux / 2; i++)
							ind[i + (aux * j)] = new IndividuoPG(profundidad, 0, j);		//Mitad de Full
						for(int i = (aux / 2); i < tam; i++)
							ind[i + (aux * j)] = new IndividuoPG(profundidad, 1, j);		//Mitad de Grow
					}
					for(int i = aux * (profundidad - 1); i < tam; i++)	//Si la division no es exacta se rellena con Grow de tamaño maximo
						ind[i] = new IndividuoPG(profundidad, 1, 1);			
				}
				else				//Grow or Full inicialization
				{
					int tipo = 0;
					if(tipo_generacion == "Grow")
						tipo = 1;
					for(int i = 0; i < tam; i++)
						ind[i] = new IndividuoPG(profundidad, tipo, 1);
				}	
			}
		}
		return ind;
	}

	@Override
	public String solutionToString() {
		String s = "El valor de la funcion es: " + super.getFitness() + "\n";
		s += "Solucion: " + genToString();
		return s;
	}

	@Override
	public String genToString() {
		Arbol a = (Arbol) genes;
		return a.toString();
	}

	@Override
	public void copiarIndividuo(Individuo ind) {
		Arbol a = (Arbol) ind.getGenes();
		genes = a.clonar(null);
	}
	
	@Override
	public void bloating(Individuo[] poblacion){		//Usamos el metodo de bloating: "Penalización bien fundamentada"
		double k = 0;
		List<Double> fitness = new ArrayList<>();
		double mediaf = 0, mediat = 0;
		List<Integer> tam = new ArrayList<>();
		Arbol aux;
		int n = poblacion.length;
		
		for(Individuo i: poblacion)			//Se calculan los fitness y tamaño de los arboles y sus medias
		{
			double x = i.getFitness();
			fitness.add(x);
			mediaf += x;
			
			aux = (Arbol) i.getGenes();
			x = aux.getTamSubArbol();
			tam.add(aux.getTamSubArbol());
			mediat += x;
		}
		mediaf = mediaf / n;
		mediat = mediat / n;
		
		double var = 0, covar = 0;
		
		for(int i = 0 ; i < n; i++)			
		{
			double rango;
			rango = Math.pow(tam.get(i) - mediaf, 2);						//Se calcula la varianza
			var = var + rango;
			
			rango = (fitness.get(i) - mediaf) * (tam.get(i) - mediat);		//Se calcula la covarianza
			covar = covar + rango;
		}
		var = var / n;
		covar = covar / n;
		
		k = covar / var;
		for(Individuo i: poblacion)
			i.setBloating(k);
	}
	
	@Override
	public void setBloating(double k){
		k_bloating = k;
	}
}
