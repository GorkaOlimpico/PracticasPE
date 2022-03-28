package individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenPr2;

public class IndividuoPr2 extends Individuo {
	private int n;	//n� vuelos
	private int m;	//n� pistas
	private final static String type = "Practica 2"; 
	private List<List<Pair<Integer, Double>>> solucion; //Pista, (vuelo, TLA)
	private List<Pair<Integer, String>> vuelos;			//Tipo de vuelo: W = 0, G = 1, P = 2; Nombre
	private List<List<Double>> TEL;						// m x n
	private double[][] tEspera;							// m x m
	
	public IndividuoPr2(List<Pair<Integer, String>> vuelos, List<List<Double>> TEL, double[][] tEspera) {
		super.id = type;
		n = TEL.get(0).size();
		m = TEL.size();
		this.vuelos = vuelos;
		this.TEL = TEL;
		this.tEspera = tEspera;
		Random rand = new Random();
		GenPr2 aux = new GenPr2(n);
		aux.initializeGen(rand);
		valor = getValor();
		solucion = new ArrayList<>();
		for(int i = 0; i < m; i++)
			solucion.add(new ArrayList<>());
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
	public double getValor() {
		asignarPista();
		double suma = 0;
		int min;
		for(int i = 0; i < m; i++)
		{
			for(int j = 0; j < solucion.get(i).size();j++)
			{
				min = 0;									//Minimo TEL para el avion -solucion.get(i).get(j).getFirst()-
				for(int k = 1; k < m; k++)	
					if(TEL.get(k).get(solucion.get(i).get(j).getFirst()) < TEL.get(min).get(solucion.get(i).get(j).getFirst()))
						min = k;
				suma += Math.pow(solucion.get(i).get(j).getSecond() - TEL.get(i).get(solucion.get(min).get(solucion.get(i).get(j).getFirst()).getFirst()), 2);
			}
		}
		return suma;
	}
	
	private void asignarPista()
	{
		double min, aux;
		int pos;
		for(int i = 0; i < genes.get(0).getLongitud(); i++)
		{
			min = Double.MAX_VALUE;
			pos = 0;
			for(int j = 0; j < TEL.size(); j++)
			{
				aux = 0;
				if(solucion.get(j).size() > 0)
					aux += tEspera[vuelos.get(solucion.get(j).get(solucion.get(j).size() - 1).getFirst()).getFirst()][vuelos.get((int) genes.get(0).getAlelo(i)).getFirst()];
				aux += TEL.get(j).get(i);
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
				List<Pair<Integer, String>> vuelos = (List<Pair<Integer, String>>) datos[1];	//TODO comprobas por si acaso porque sale warning
				List<List<Double>> TEL = (List<List<Double>>) datos[2];									
				double[][] tEspera = (double[][]) datos[3];
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoPr2(vuelos, TEL, tEspera);
			}
		}
		return ind;
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
