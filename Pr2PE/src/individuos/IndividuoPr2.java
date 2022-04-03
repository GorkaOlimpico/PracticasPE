package individuos;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;


import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.Gen;
import gen.GenPr2;

public class IndividuoPr2 extends Individuo {
	private int n;	//nº vuelos
	private int m;	//nº pistas
	private final static String type = "Practica 2"; 
	private List<List<Pair<Integer, Double>>> solucion; //Pista, (vuelo, TLA)
	private List<Pair<Integer, String>> vuelos;			//Tipo de vuelo: W = 0, G = 1, P = 2; Nombre
	private List<List<Double>> TEL;						// m x n
	private List<List<Double>> tEspera;							
	
	public IndividuoPr2(List<Pair<Integer, String>> vuelos, List<List<Double>> TEL, List<List<Double>> tEspera) {
		super.id = type;
		n = TEL.get(0).size();
		m = TEL.size();
		this.vuelos = vuelos;
		this.TEL = TEL;
		this.tEspera = tEspera;
		Random rand = new Random();
		GenPr2 aux = new GenPr2(n);
		aux.initializeGen(rand);
		genes.add(aux);
		recalcularFenotipo();
	}
	
	public IndividuoPr2()
	{
		super.id = type;
	}

	@Override
	public boolean max() {
		return false;
	}

	@Override
	public double getFitness2() {
		asignarPista();
		double suma = 0;
		
		// 1. Se recorre cada pista calculando los retardos de cada avion de la forma:
		// 		retardo = (TLA - menor TEL de esa pista)
		int i = 0;
		for(List<Pair<Integer, Double>> pista : solucion) {
			for(Pair<Integer, Double> avion : pista) {
				double retardo;
				
				double tel = TEL.get(i).get(avion.getFirst()); // TEL [n pista][n vuelo] 
				double tla = avion.getSecond();
				
				retardo = Math.pow((tla - tel), 2);
				suma += retardo;
			}
			i++;
		}
		
		return suma;
	}
	
	@Override
	public double getValor() {
		//asignarPista();
		asigPista2();
		double suma = 0;
		int x = 0;
		double retardo1 = 0;
		double retardo2 = 0;
		double retardo3 = 0;
		
		for(List<Pair<Integer, Double>> pista : solucion) {
			x++;
			for(Pair<Integer, Double> avion : pista) {
				double retardo = 0;

				
				double tel = TEL.get(0).get(avion.getFirst()); 
				for(int i = 1; i < m; i++)
					if(TEL.get(i).get(avion.getFirst()) < tel)
						tel = TEL.get(i).get(avion.getFirst());	//Menor TEL de ese vuelo
				double tla = avion.getSecond();
				
				retardo = Math.pow((tla - tel), 2);
				if(x==1) {
					retardo1 += retardo;
				}
				if(x==2) {
					retardo2 += retardo;
				}
				if(x==3) {
					retardo3 += retardo;
				}
				suma += retardo;

			}
			
			System.out.println("Suma hasta pista " + x + ": " + suma);
		}
		if(suma == 11.25) {
			System.out.println("Retardo1:" + retardo1);
			System.out.println("Retardo2:" + retardo2);
			System.out.println("Retardo3:" + retardo3);
			System.out.println(this.solutionToString());
		}
		return suma;
	}
	
	private void asignarPista()
	{
		solucion = new ArrayList<>();
		for(int i = 0; i < m; i++)
			solucion.add(new ArrayList<>());
		double tla, aux, calculo;
		int pos;
		
		Gen g = genes.get(0);
		for(int i = 0; i < n; i++) // por n vuelos
		{
			tla = Double.MAX_VALUE;
			pos = 0;
			for(int j = 0; j < m; j++) // por n pistas
			{
				calculo = 0;
				aux = TEL.get(j).get((int) g.getAlelo(i));	//TEL vuelo en la pista j
				if(solucion.get(j).size() > 0)
				{
					int vueloAnt = solucion.get(j).size() - 1;
					//TLA vuelo anterior + tiempo de espera correspondiente al tipo de vuelo
					calculo = solucion.get(j).get(vueloAnt).getSecond() + 
							tEspera.get(vuelos.get(solucion.get(j).get(vueloAnt).getFirst()).getFirst()).get(vuelos.get((int) g.getAlelo(i)).getFirst()); 
				}						//tipo del ultimo vuelo en la pista j									tipo del vuelo a añadir
				if(calculo > aux)	
					aux = calculo;		//El TLA de asignarse esta pista seria el maximo entre el TEL y el TLA del vuelo anterior + retraso
				if(aux < tla)			//El vuelo se asigna a la pista con menor TLA
				{
					pos = j;
					tla = aux;
				}
			}
			Pair<Integer, Double> par = new Pair<Integer, Double>((int) genes.get(0).getAlelo(i), tla);
			solucion.get(pos).add(par);
		}
	}
	
	private void asigPista2() {
		solucion = new ArrayList<>();
		for(int i = 0; i < m; i++)
			solucion.add(new ArrayList<>());
		Gen g = genes.get(0);
		

		for(int i = 0; i < n; i++) {
			
			// 1. Por cada vuelo: Lo asigno a las 3 pistas y calculo su retardo posible
			double maxRetardo = Double.MAX_VALUE;
			int mejorPista = -1;
			double tla = -1;
			
			for(int j = 0; j < m; j++) { // pistas de 0 a m
				
				
				double retardo = asignaAPista(genes.get(0).getAlelo(i), j, -1.0);
				double aux_tla = asignaAPistaTLA(genes.get(0).getAlelo(i), j);
				if(retardo < maxRetardo) { // aqui siempre escoge la primera opción que tenía menos retardo
					maxRetardo = retardo;
					mejorPista = j;
					tla = aux_tla;
				}
				if(retardo == maxRetardo) { // 50% de quedarse con el otro retardo
					Random rand = new Random();
					int prob = rand.nextInt(1);
					if(prob == 1) {
						maxRetardo = retardo;
						mejorPista = j;
						tla = aux_tla;
					}
						
				}
			}
			
			// 2. Lo asigno a la pista en la cual tiene menos retardo
			Pair<Integer, Double> par = new Pair<Integer, Double>((int) genes.get(0).getAlelo(i), tla);
			solucion.get(mejorPista).add(par);
			
		}

		
	}
	
	private double asignaAPista(Object avion, int numPista, double aux_tla) {
		double retardo = 0;
		double minTel = minTEL(avion); // este es el mínimo TEL global
		double tel = TEL.get(numPista).get((int) avion);
		double tlaAnterior = ultimoTLA(numPista);
		
		int tipoAnterior = -1;
		if(solucion.get(numPista).size() > 0) {
			int avionAnterior = solucion.get(numPista).get(solucion.get(numPista).size()-1).getFirst();
			tipoAnterior = vuelos.get(avionAnterior).getFirst();
		}
		int tipoActual = vuelos.get((int) avion).getFirst();
		
		double diferencia = diferencia(tipoActual, tipoAnterior);
		
		if(tel >= tlaAnterior + diferencia) {
			aux_tla = tel;
		}
		else {
			aux_tla = tlaAnterior + diferencia;
		}
		
		retardo = Math.pow((aux_tla - minTel), 2);
		
		return retardo;
	}
	
	public double minTEL(Object avion) {
		double minTel = Double.MAX_VALUE;
		for(List<Double> pista : TEL) {
			double tel = pista.get((int) avion);
			if(tel < minTel) {
				minTel = tel;
			}
		}
		
		return minTel;
	}
	
	public double ultimoTLA(int numPista) {
		double tla = -1;
		
		if(solucion.get(numPista).size() > 0) {//si hay algun avion en la pista
			tla = solucion.get(numPista).get(solucion.get(numPista).size() -1).getSecond(); //selecciono el último asignado
		}
		else {
			tla = 0;
		}
		
		return tla;
	}
	
	public double diferencia(int tipo1, int tipo2) {
		// W = 0, G = 1, P = 2
		double diferencia = 0;
		if(tipo2 != -1) {
			diferencia = tEspera.get(tipo2).get(tipo1);
		}
		return diferencia;
	}

	@Override
	public double getFenotipo(int i) {
		return 0;
	}

	@Override
	public Cruce[] getCruces() {
		return Cruce.getCrucesPr2();
	}

	@Override
	public Mutacion[] getMutaciones() {
		return Mutacion.getMutacionesPr2();
	}

	@Override
	protected Individuo[] parse(int tam, Object[] datos) {
		IndividuoPr2[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoPr2[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoPr2();
			else
			{
				for(int i = 0; i < tam; i++)
					ind[i] = nuevoInd(datos);
			}
		}
		return ind;
	}
	
	public IndividuoPr2 nuevoInd(Object[] datos) {
		IndividuoPr2 individuo = new IndividuoPr2();
		
		List<Pair<Integer, String>> vuelos = (List<Pair<Integer, String>>) datos[1];	
		List<List<Double>> TEL = (List<List<Double>>) datos[2];									
		List<List<Double>> tEspera = (List<List<Double>>) datos[3];
		
		individuo = new IndividuoPr2(vuelos, TEL, tEspera);
		
		return individuo;
	}

	@Override
	public String solutionToString() {
		String s = "Gen: ";
		for(int i = 0; i < n; i++)
			s += ((int) genes.get(0).getAlelo(i) + 1) + " ";
		s += "\n";
		int i = 1;
		for(List<Pair<Integer, Double>> pista : solucion) 
		{
			s += "Pista " + i + ":\n";
			for(Pair<Integer, Double> avion : pista) 
			{
				s += "Vuelo: "+ (avion.getFirst() + 1) + "\t Nombre: " + vuelos.get(avion.getFirst()).getSecond() + "\t TLA: " + avion.getSecond() + "\n";
			}
			s += "\n";
			i++;
		}
		return s;
	}
	
	public String genToString()
	{
		String s = "";
		for(int i = 0; i < n; i++)
			s += ((int) genes.get(0).getAlelo(i) + 1) + " ";
		return s + "\n";
	}

	@Override
	public List<List<Pair<Integer, Double>>> getSolucion(){
		return solucion;
	}
	
	private double asignaAPistaTLA(Object avion, int numPista) {
		double retardo = 0;
		double aux_tla = -1;
		double tel = TEL.get(numPista).get((int) avion);
		double tlaAnterior = ultimoTLA(numPista);
		
		int tipoAnterior = -1;
		if(solucion.get(numPista).size() > 0) {
			int avionAnterior = solucion.get(numPista).get(solucion.get(numPista).size()-1).getFirst();
			tipoAnterior = vuelos.get(avionAnterior).getFirst();
		}
		int tipoActual = vuelos.get((int) avion).getFirst();
		
		double diferencia = diferencia(tipoActual, tipoAnterior);
		
		if(tel >= tlaAnterior + diferencia) {
			aux_tla = tel;
		}
		else {
			aux_tla = tlaAnterior + diferencia;
		}
		
		retardo = Math.pow(((double) aux_tla - tel), 2);
		
		return aux_tla;
	}
}
