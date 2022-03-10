package algoritmoGenetico;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.seleccion.Seleccion;

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
	private double prob_elite;
	
	private int num_variables;
	
	
	private double[] mejoresGeneracion;
	private double[] mejoresGlobales;
	private double[] mediaGeneracion;
	
	private Seleccion seleccion;
	private Cruce cruce;
	private Mutacion mutacion;
	
	//falta el porcentaje de elitismo
	// la condicion de finalizacion depende de la funcion usada? Deber�a estar aqui?
	
	public AlgoritmoGenetico() {//esto es de prueba
		tam_pob = 100;
		num_max_gen = 10;
		prob_cruce = 10.3;
		prob_mutacion = 0.6;
		error_val = 0.01;
		prob_elite= 11.1;
		
		num_variables = 2; // est� bien as�?
		
	}
	
	
	public Individuo[] creaPoblacion(int problema) {
		//TODO
		//problema {0,1,2,3,4} siendo 4 = Funcion 4 Real
		
		Individuo [] poblacion = null;
		
		// if problema == 1...
		// poblacion = new IndividuoFuncion1...
		
		return poblacion;
	}
	
	
	public void run() {
		//TODO
		// 1. Generar poblacion inicial
		
		
		// 2. Evaluar p(t)
		
		// 3. While (no ha llegado al numero m�ximo de generaciones
		//				&& no se ha cumplido la condici�n de terminar){
		// 		t++
		//		p(t) = Seleccion(p(t-1))
		// 		Reproduccion(p(t))
		// 		Mutacion(p(t))
		//		Evaluar(p(t))
		// 		Guarda el mejor Individuo
		
		// 		MainFrame.generaGrafica(mejoresGlobales, mejoresGeneracion, mediaGeneracion);
		
		//		generacionActual++;
		
		// 4. MainFrame.setSol(generaSolucion());
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
	public double getProbElite() {
		return prob_elite;
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
	public void setProbElite(double probElite) {
		prob_elite = probElite;
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
	
	public String generaSolucion() {
		String sol = "";
		
		int x = 0;
		for(double fenotipo:elMejor.getFenotipo()) {
			sol += "X" + x + " = " + fenotipo + ", ";
		}

		sol += "Valor de la funci�n: " + elMejor.getFitness();
		
		return sol;
	}
	
	public void setVariables(int num) {
		num_variables = num;
	}
	
}
