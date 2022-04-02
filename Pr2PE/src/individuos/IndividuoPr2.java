package individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
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
		//valor = getValor();
		valor = getFitness2();
		
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
		asignarPista();
		double suma = 0;
		int min;
		for(int i = 0; i < m; i++)
		{
			for(int j = 0; j < solucion.get(i).size();j++)
			{
				min = 0;									//Minimo TEL para el avion -solucion.get(i).get(j).getFirst()-
				for(int k = 0; k < m; k++)	
					if(TEL.get(k).get(solucion.get(i).get(j).getFirst()) < TEL.get(min).get(solucion.get(i).get(j).getFirst()))
						min = k;


				suma += Math.pow(solucion.get(i).get(j).getSecond() - TEL.get(min).get(solucion.get(i).get(j).getFirst()), 2);
			}
		}
		return suma;
	}
	
	private void asignarPista()
	{
		solucion = new ArrayList<>();
		for(int i = 0; i < m; i++)
			solucion.add(new ArrayList<>());
		double min, aux;
		int pos;
		for(int i = 0; i < genes.get(0).getLongitud(); i++)
		{
			min = Double.MAX_VALUE;
			pos = 0;
			for(int j = 0; j < TEL.size(); j++)
			{
				aux = TEL.get(j).get(i);
				if(solucion.get(j).size() != 0) {
					double calculo = tEspera.get(vuelos.get(solucion.get(j).get(solucion.get(j).size() - 1).getFirst()).getFirst()).get(vuelos.get((int) genes.get(0).getAlelo(i)).getFirst()) + solucion.get(j).get(solucion.get(j).size() - 1).getSecond();
					if(aux < calculo) {
						aux = calculo;
					}
				}
					
				if(aux < min)
				{
					min = aux;
					pos = j;
				}
			}
			Pair<Integer, Double> par = new Pair<Integer, Double>((int) genes.get(0).getAlelo(i), min);
			solucion.get(pos).add(par);
		}
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
		
		List<Pair<Integer, String>> vuelos = (List<Pair<Integer, String>>) datos[1];	//TODO comprobas por si acaso porque sale warning
		List<List<Double>> TEL = (List<List<Double>>) datos[2];									
		List<List<Double>> tEspera = (List<List<Double>>) datos[3];
		
		individuo = new IndividuoPr2(vuelos, TEL, tEspera);
		
		return individuo;
	}

	@Override
	public String solutionToString() {
		String s = "|";;
		for(int i = 0; i < solucion.size(); i++)
			s += String.format("{0,9}Pista {1,2}{2,9}|", "", i + 1, "");	//26 caracteres por pista
		s += "|\n|";
		
		for(int i = 0; i < solucion.size(); i++)
			s += String.format("{0,2}Vuelo{1,3}Nombre{2,4}TLA{3,3}|,", "", "", "", "");
		s += "|\n|";
		
		int i = 0;
		boolean continuar = true;
		while(continuar)
		{
			continuar = false;
			for(int j = 0; j < solucion.size(); j++)
			{
				if(i < solucion.get(j).size())
				{
					continuar = true;
					s += String.format("{0,2}{1,3}{2,4}{3,8}{4,3}{5,4}{6,2},", "", 
							solucion.get(j).get(i).getFirst(),  "", 
							vuelos.get(solucion.get(j).get(i).getFirst()).getSecond(), "",
							solucion.get(j).get(i).getSecond(), "");
				}
				else
					s += String.format("{0,26}", "");
				s += "|\n";
			}
			i++;
		}
		//TODO comprobar que se muestra correctamente
		return s;
	}

}
