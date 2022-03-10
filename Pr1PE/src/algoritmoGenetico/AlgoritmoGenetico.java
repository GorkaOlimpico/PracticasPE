package algoritmoGenetico;

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
	private int pos_mejor;
	private double prob_cruce;
	private double prob_mutacion;
	private double precision;
	private double error_val;
	private double elite;
	
	private String problema;
	private int num_variables;
	
	
	private double[] mejoresGeneracion;
	private double[] mejoresGlobales;
	private double[] mediaGeneracion;
	private int i_mejoresGen;
	private int i_mejoresGlo;
	private int generacionActual;
	
	private Seleccion seleccion;
	private Cruce cruce;
	private Mutacion mutacion;
	
	//falta el porcentaje de elitismo
	// la condicion de finalizacion depende de la funcion usada? Debería estar aqui?
	
	public AlgoritmoGenetico() {// Aquí valores por defecto
		tam_pob = 100;
		num_max_gen = 10;
		prob_cruce = 10.3;
		prob_mutacion = 0.6;
		error_val = 0.01;
		elite= 11.1;
		mejoresGeneracion = new double[num_max_gen];
		mejoresGlobales = new double[num_max_gen];
		mediaGeneracion = new double[num_max_gen];
		i_mejoresGen = 0;
		i_mejoresGlo = 0;
		generacionActual = 0;
		
		
		num_variables = 2; // está bien así?
		
	}
	
	
	public Individuo[] creaPoblacion(String problema) {		
		this.problema = problema;
		return Individuo.seleccionarIndividuo(tam_pob, new String[]{problema, Double.toString(error_val), Integer.toString(num_variables)});
	}
	
	
	public void run() {
		// Inicializo elMejor con el primer individuo de la poblacion
		elMejor = poblacion[0];
		// 1. Evaluar p(t)
		// 		Guarda el mejor Individuo, mejoresGlobales, mejoresGeneracion, mediaGeneracion
		evaluarPoblacion();
		
		
		
		// 2. While (no ha llegado al numero máximo de generaciones
		//				&& no se ha cumplido la condición de terminar){
		while((generacionActual < num_max_gen - 1)) {
			// cumplir la condicion de terminar quiere decir llegar al máximo de la funcion en cada problema?
		//		ordena pob
		
			generacionActual++;
			
			Individuo[] poblacionAux = creaPoblacion(problema); // revisar esto. Solo lo he puesto así por comodidad
			seleccion.select(poblacion, poblacionAux);
			
		// 		Reproduccion(p(t))
			cruce.cruzar(poblacion, prob_cruce);
			
		// 		Mutacion(p(t))
			mutacion.mutar(poblacion, prob_mutacion);
			
		//		Evaluar(p(t))
		// 		Guarda el mejor Individuo, mejoresGlobales, mejoresGeneracion, mediaGeneracion
			evaluarPoblacion();
			
		
		//		Actualiza la gráfica
			MainFrame.generaGrafica(mejoresGlobales, mejoresGeneracion, mediaGeneracion);
		
		// Creo que no hace falta actualizar la generacion porque en mediaGeneracion siempre se añade un elemento al final
		
			//		ordena pob
		}
		
		MainFrame.setSolucion(generaSolucion());
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
	
	public void evaluarPoblacion() {
		double media = 0;
		double fitness;
		Individuo elMejorGeneracion = poblacion[0];
		
		for(Individuo ind:poblacion) {
			fitness= ind.getFitness();
			media += fitness;
			
			if(fitness > elMejorGeneracion.getFitness()) {
				elMejorGeneracion = ind;			
			}
			
		}
		
		
		mejoresGeneracion[i_mejoresGen] = elMejorGeneracion.getFitness();
		i_mejoresGen++;
		
		if(elMejorGeneracion.getFitness() >= elMejor.getFitness()) {
			elMejor = elMejorGeneracion;
			
			mejoresGlobales[i_mejoresGlo] = elMejor.getFitness();
			i_mejoresGlo++;
		}
		media = media/poblacion.length;
		mediaGeneracion[generacionActual] = media;
		
	}
}
