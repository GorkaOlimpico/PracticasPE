package individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import arbol.Arbol;

public class IndividuoPG extends Individuo{

	private final static String type = "Programación Genética"; 
	private double k_bloating;
	
 	public IndividuoPG()
	{
		super.id = type;
	}
	
	public IndividuoPG(int profundidad, int tipo_generacion, int prof_generar, Random rand, boolean m6)
	{
		super(m6);
		super.id = type;
		if(tipo_generacion == 0)
			genes = Arbol.generarFull(rand, profundidad, null, prof_generar, m6);						//Full inicialization
		if(tipo_generacion == 1)
			genes = Arbol.generarGrow(rand, profundidad, null, prof_generar, m6);		//Grow inicialization (prof_generar tiene que ser > 1)
		recalcularFenotipo();
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
			boolean c = arbol.execute(combinaciones.get(i));
			if(multiplexor6)
			{
				if(c == solucionar6(combinaciones.get(i)))
					suma++;
			}
			else
				if(c == solucionar11(combinaciones.get(i)))
					suma++;
		}
		return suma;
	}
	
	@Override
	public void recalcularFenotipo() {
		valor = getValor();	
		Arbol a = (Arbol) genes;
		valor = valor + (k_bloating * a.getTamSubArbol());				//Usamos el metodo de bloating: "Penalización bien fundamentada"
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
		Random rand = new Random();
		if((String) datos[0] == id)
		{
			ind = new IndividuoPG[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoPG();
			else
			{
				int profundidad = (int) datos[1];	
				String tipo_generacion = (String) datos[2];
				boolean m6 = (boolean) datos[3];
				if(tipo_generacion == "Ramped-Half")				//Ramped and half inicialization
				{
					int aux = tam / (profundidad - 1);
					for(int j = 2; j <= profundidad; j++)			//La profundidad de la hoja es 0 y la de la raiz: profundidad - 1
					{
						for(int i = 0; i < aux / 2; i++)
							ind[i + (aux * (j - 2))] = new IndividuoPG(profundidad, 0, j, rand, m6);		//Mitad de Full

						for(int i = aux / 2; i < aux; i++)
							ind[i + (aux * (j - 2))] = new IndividuoPG(profundidad, 1, j, rand, m6);		//Mitad de Grow
					}
					for(int i = aux * (profundidad - 1); i < tam; i++)	//Si la division no es exacta se rellena con Grow
						ind[i] = new IndividuoPG(profundidad, 1, profundidad - 1, rand, m6);		
				}
				else												//Grow or Full inicialization
				{
					if(tipo_generacion == "Full")
					{
						for(int i = 0; i < tam; i++)
						{
							ind[i] = new IndividuoPG(profundidad, 0, 1, rand, m6);
						}
					}
					else
						for(int i = 0; i < tam; i++)
						{
							ind[i] = new IndividuoPG(profundidad, 1, profundidad - 1, rand, m6);
						}
				}	
			}
		}
		return ind;
	}

	@Override
	public String solutionToString() {
		String s = "El valor de la funcion es: " + getValor() + "\n";		//Durante el proceso se usa el metodo de bloating, para la solucion no
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
		setBloating(ind.getBloating());
		recalcularFenotipo();
	}
	
	@Override
	public void bloating(Individuo[] poblacion){		//Usamos el metodo de bloating: "Penalización bien fundamentada"	//TODO solucionar
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
			int y = aux.getTamSubArbol();
			tam.add(y);
			mediat += y;
		}
		mediaf = mediaf / n;
		mediat = mediat / n;
		
		double var = 0, covar = 0;
		
		for(int i = 0 ; i < n; i++)			
		{
			double rango;
			rango = Math.pow(tam.get(i) - mediat, 2);						//Se calcula la varianza (del tamaño)
			var = var + rango;
			
			rango = (fitness.get(i) - mediaf) * (tam.get(i) - mediat);		//Se calcula la covarianza	(tamaño y fitness)
			covar = covar + rango;
		}
		var = var / n;
		covar = covar / n;
		
		k = - covar / var;		//Sin el menos, el fitness no para de crecer, llegando a incluso 200 al final de la ejecucion, siendo su 
								//numero de aciertos inferior a 60 en la mayoria de los casos y las soluciones mas grandes 
		
		for(Individuo i: poblacion)
			i.setBloating(k);
	}
	
	@Override
	public void setBloating(double k){
		k_bloating = k;
		recalcularFenotipo();
	}
	
	@Override
	public double getBloating(){
		return k_bloating;
	}

	@Override
	public List<String> getSolucion() {
		// TODO Auto-generated method stub
		return null;
	}
}
