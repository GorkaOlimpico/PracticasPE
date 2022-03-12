package algoritmoGenetico;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.seleccion.Seleccion;
import gui.MainFrame;
import individuos.Individuo;


public class AlgoritmoGenetico {

	private Individuo[] poblacion;
	private int tam_pob;
	private int num_max_gen;
	private Individuo elMejor;
	private double prob_cruce;
	private double prob_mutacion;
	private double error_val;
	private double elite;
	
	private String problema;
	private int num_variables;
	
	
	private double[] mejoresGeneracion;
	private double[] mejoresGlobales;
	private double[] mediaGeneracion;
	private int generacionActual;
	
	private Seleccion seleccion;
	private Cruce cruce;
	private Mutacion mutacion;
		
	public AlgoritmoGenetico() {// Aquí valores por defecto
		tam_pob = 100;
		num_max_gen = 100;
		prob_cruce = 60;
		prob_mutacion = 5;
		error_val = 0.001;
		elite= 2;
		num_variables = 2; 
		
		generacionActual = 0;
	}
	
	
	public Individuo[] creaPoblacion(String problema, int tam) {		
		this.problema = problema;
		return Individuo.seleccionarIndividuo(tam, new String[]{problema, Double.toString(error_val), Integer.toString(num_variables)});
	}
	
	
	private void ordenarPoblacion()
	{
		List<Double> fitness = new ArrayList<>();
		for(int i = 0; i < poblacion.length; i++)
			fitness.add(poblacion[i].getFitness());
		
		for(int i = 0; i < fitness.size() - 1; i++)
		{
			int max = i;
			for(int j = i + 1; j < fitness.size(); j++)
			{
				if(poblacion[0].max())
				{
					if(fitness.get(j) > fitness.get(max))
						max = j;
				}
				else
				{
					if(fitness.get(j) < fitness.get(max))
						max = j;
				}
			}
			double aux1 = fitness.get(i);
			fitness.set(i, fitness.get(max));
			fitness.set(max, aux1);
			
			Individuo aux2 = poblacion[i];
			poblacion[i] = poblacion[max];
			poblacion[max] = aux2;
		}
	}
	
	public void run() {
		// Inicializo elMejor con el primer individuo de la poblacion
		poblacion = creaPoblacion(problema, tam_pob);
		ordenarPoblacion();
		elMejor = creaPoblacion(problema,(int) 1)[0];
		copiarIndividuo(poblacion[0], elMejor);
		generacionActual = 0;
		mejoresGeneracion = new double[num_max_gen + 1]; //Hay num_max_gen generaciones + la generacion 0 (la creada de forma aleatoria)
		mejoresGlobales = new double[num_max_gen + 1];
		mediaGeneracion = new double[num_max_gen + 1];
		// 1. Evaluar p(t)
		// 		Guarda el mejor Individuo, mejoresGlobales, mejoresGeneracion, mediaGeneracion
		evaluarPoblacion();
		
		Individuo[] elites = creaPoblacion(problema, (int) (tam_pob * elite / 100));
		Individuo[] poblacionAux = creaPoblacion(problema, tam_pob);
		// 2. While (no ha llegado al numero máximo de generaciones
		//				&& no se ha cumplido la condición de terminar){
		
		/*for(Individuo pob:poblacion) {
			System.out.println(pob.getFitness());
		}*/
	
		while(generacionActual < num_max_gen) {
			// cumplir la condicion de terminar quiere decir llegar al máximo de la funcion en cada problema?
		//		ordena pob

			generacionActual++;
			
			sacarElites(elites);
			
			seleccion.select(poblacion, poblacionAux);
			
		// 		Reproduccion(p(t))
			cruce.cruzar(poblacion, prob_cruce / 100);
			

		// 		Mutacion(p(t))
			
			mutacion.mutar(poblacion, prob_mutacion / 100);
			
		//		Evaluar(p(t))
		// 		Guarda el mejor Individuo, mejoresGlobales, mejoresGeneracion, mediaGeneracion
			
			ordenarPoblacion();
			meterElites(elites);

			
			evaluarPoblacion();
			
		}
		
		MainFrame.imprimeGrafica(mejoresGlobales, mejoresGeneracion, mediaGeneracion);
		
		MainFrame.setSolucion(generaSolucion());
	}
	
	private void sacarElites(Individuo[] e) //Guarda los mejores antes del proceso de seleccion, cruce y mutacion
	{
		for(int i = 0; i < e.length; i++)
		{
			copiarIndividuo(poblacion[i], e[i]);
		}
	}
	
	private void meterElites(Individuo[] e) //Sustituye los mejores de la generacion anterior por los peores de la actual
	{
		for(int i = 0; i < e.length; i++)
		{
			copiarIndividuo(e[i], poblacion[poblacion.length - i - 1]);
		}
		ordenarPoblacion();
	}
	
	private void copiarIndividuo(Individuo i1, Individuo i2) //Copia los datos de i1 a i2
	{
		for(int i = 0; i < i1.getGenes().size(); i++)
		{
			i1.getGenes().get(i).copiarGen(i2.getGenes().get(i));
		}
	}
	
	public int getTamPoblacion() {
		return tam_pob;
	}
	public int getMaxGen() {
		return num_max_gen;
	}
	public double getErrorVal() {
		return error_val;
	}
	public double getProbCruce() {
		return prob_cruce;
	}
	public double getProbMutacion() {
		return prob_mutacion;
	}
	public double getElite() {
		return elite;
	}
	public Seleccion getSeleccion() {
		return seleccion;
	}
	public Cruce getCruce() {
		return cruce;
	}
	public Mutacion getMutacion() {
		return mutacion;
	}
	
	public void setTamPoblacion(int tamPob) {
		tam_pob = tamPob;
	}
	public void setMaxGen(int maxGen) {
		num_max_gen = maxGen;
	}
	public void setErrorVal(double errorVal) {
		error_val = errorVal;
	}
	public void setProbCruce(double probCruce) {
		prob_cruce = probCruce;
	}
	public void setProbMutacion(double probMut) {
		prob_mutacion = probMut;
	}
	public void setElite(double elite) {
		this.elite = elite;
	}
	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}
	public void setCruce(Cruce cruce) {
		this.cruce = cruce;
	}
	public void setMutacion(Mutacion mutacion) {
		this.mutacion = mutacion;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
	
	public String generaSolucion() {
		String sol = "";
		
		int x = 0;
		for(double fenotipo:elMejor.getFenotipo()) {
			sol += "X" + x + " = " + fenotipo + ", ";
		}

		sol += "Valor de la función: " + elMejor.getFitness();
		
		
		
		return sol;
	}
	
	public void setVariables(int num) {
		num_variables = num;
	}
	
	public void evaluarPoblacion() 
	{
		double media = 0;
		double fitness;
		Individuo elMejorGeneracion = creaPoblacion(problema,(int) 1)[0];
		copiarIndividuo(poblacion[0], elMejorGeneracion);
		
		for(Individuo ind:poblacion) 
		{
			fitness = ind.getFitness();
			media += fitness;
			
			if(elMejor.max())//si el problema es F1
			{
				if(fitness > elMejorGeneracion.getFitness()) 
					copiarIndividuo(ind, elMejorGeneracion);	
			}
			else
			{
				if(fitness < elMejorGeneracion.getFitness()) 
					copiarIndividuo(ind, elMejorGeneracion);	
			}
		}
		
		mejoresGeneracion[generacionActual] = elMejorGeneracion.getFitness();
		
		//El propio generacionActual hace de indice para los arrays
		
		if(elMejor.max())//si el problema es F1
		{
			if(elMejorGeneracion.getFitness() > elMejor.getFitness()) 
			{
				elMejor = elMejorGeneracion;

				mejoresGlobales[generacionActual] = elMejor.getFitness();
			}
			else //si no se mejora se mantiene el actual (al ser >= en el caso de generacionActual = 0 entra en el if ya que elMejor = poblacion[0] = elMejorGeneracion)
				mejoresGlobales[generacionActual] = elMejor.getFitness();
		}
		else
		{
			if(elMejorGeneracion.getFitness() < elMejor.getFitness()) 
			{
				elMejor = elMejorGeneracion;
				
				mejoresGlobales[generacionActual] = elMejor.getFitness();
			}
			else
				mejoresGlobales[generacionActual] = elMejor.getFitness();
				
			//la funcion 2 da indexoutofbounds: index = -1, eso significa que ha entrado al else, lo que deberia ser imposible porque al ser
			//la poblacion inicial (si no no podria ser index = -1): elMejor == elMejorGeneracion[generacionActual]
		}
		media = media / poblacion.length;
		mediaGeneracion[generacionActual] = media;
		
	}
}
