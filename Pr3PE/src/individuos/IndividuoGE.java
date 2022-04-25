package individuos;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.*;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.Gen;
import gen.GenPr3;
import gramatica.Gramatica;



public class IndividuoGE extends Individuo {

	private final static String type = "Gramática Evolutiva"; 
	private List<String> solucion;
	private Gramatica gramatica;
	private int max_wraps;
	private int wraps;
	private int pos;
	private int longitud;
	private List<List<Boolean>> entradas;
	
	public IndividuoGE(int longitud, int n_wraps, String nombreArchivo) throws FileNotFoundException {
		super.id = type;
		List<Gen> genes = new ArrayList<>();
		solucion = new ArrayList<String>();
		String texto_gramatica = archivoATexto(nombreArchivo);
		pos = 0;
		max_wraps = n_wraps;
		gramatica = new Gramatica(texto_gramatica);
		this.longitud = longitud;
		Random rand = new Random();
		GenPr3 aux = new GenPr3(longitud);
		aux.initializeGen(rand);
		genes.add(aux);
		recalcularFenotipo();
		this.genes = genes;
		
		entradas = generaEntradas();
	}
	public IndividuoGE()
	{
		super.id = type;
	}
	
	public String archivoATexto(String nombreArchivo) throws FileNotFoundException {
		String total ="";
		File doc = new File("gramatica.txt");
		Scanner obj = new Scanner(doc);
		while (obj.hasNextLine()) {
            total += obj.nextLine();
        }
		System.out.println("Leido de archivo: \n" + total);
		return total;
	}
	
	public void traduceALista() {
		// Esta función hace que el array numérico pase a ser la lista con la que podemos operar
		List<Gen> genes = (List<Gen>) this.genes;
		System.out.println("GEN: "+ genes.get(0).getAlelos());
		addElemLista("funcion");
		System.out.println("Lista: "+ solucion);
		pos = 0;
		wraps = 0;
		
	}
	
	public void addElemLista(String elemento) {
		
		switch (elemento) {
			case "funcion": {
				List<Gen> genes = (List<Gen>) this.genes;
				int numero = ((int) genes.get(0).getAlelo(pos)) % 4;
				pos++;
				if(pos == longitud) { // máximo indice
					pos = 0;
					wraps++;
					if(wraps > max_wraps) {
						System.out.println("ERROR: se supera el max_wraps");
					}
				}
				
				if(numero==0) {
					addElemLista("IF");
				}
				else if (numero==1) {
					addElemLista("AND");
				}
				else if (numero==2) {
					addElemLista("OR");
				}
				else if (numero==3) {
					addElemLista("NOT");
				}
				break;
			}
			
			case "IF":{
				solucion.add("(");
				solucion.add("IF");
				addElemLista("exp");
				addElemLista("exp");
				addElemLista("exp");
				solucion.add(")");
				break;
			}
			
			case "AND":{
				solucion.add("(");
				solucion.add("AND");
				addElemLista("exp");
				addElemLista("exp");
				solucion.add(")");
				break;
			}
			
			case "OR":{
				solucion.add("(");
				solucion.add("OR");
				addElemLista("exp");
				addElemLista("exp");
				solucion.add(")");
				break;
			}
			
			case "NOT":{
				solucion.add("(");
				solucion.add("NOT");
				addElemLista("exp");
				solucion.add(")");
				break;
			}
			
			case "exp":{ // exp = funcion | A0 | A1 | D0 | D1 | D2 | D3
				List<Gen> genes = (List<Gen>) this.genes;
				int numero = ((int) genes.get(0).getAlelo(pos)) % 7;
				pos++;
				if(pos == longitud) {
					pos = 0;
					wraps++;
					if(wraps == max_wraps -1 ) {// si solo falta 1 wrap no voy a hacer otra funcion
						Random rand = new Random();
						numero = rand.nextInt(6 + 1) + 1;
						System.out.println("Maxwraps-1, numero: "+ numero);
					}
					
				}
				switch (numero) {
					case 0: {
						addElemLista("funcion");
						break;
					}
					case 1: {
						solucion.add("A0");
						break;
					}
					case 2: {
						solucion.add("A1");
						break;
					}
					case 3: {
						solucion.add("D0");
						break;
					}
					case 4: {
						solucion.add("D1");
						break;
					}
					case 5: {
						solucion.add("D2");
						break;
					}
					case 6: {
						solucion.add("D3");
						break;
					}
				}
				break;
			}
		}
			
	}

	@Override
	public boolean max() {
		return false;
	}

	
	
	@Override
	public double getValor() {

		int aciertos = 0;
		
		// 1. Se genera la List<String> a partir del número
		traduceALista();
				
		// 2. Por cada entrada[6] evalúo la List<String> y evalúo su resultado correcto del MX-6
		for(List<Boolean> entrada : entradas) {
			
			// 4. Comparo los resultados. Si son iguales entonces sumo 1 a aciertos
			if(evaluaElemento(entrada) == multiplexor6(entrada)) {
				aciertos++;
			}
		}
		return aciertos;
	}
	
	public boolean evaluaElemento(List<List<Boolean>> entrada) {
		boolean resultado = false;
		
		
		
		return resultado;
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
		IndividuoGE[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoGE[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoGE();
			else
			{
				for(int i = 0; i < tam; i++)
					ind[i] = nuevoInd(datos);
			}
		}
		return ind;
	}
	
	public IndividuoGE nuevoInd(Object[] datos) {
		IndividuoGE individuo = new IndividuoGE();
		
		int longitud = (int) datos[1];	
		int n_wraps =  (int) datos[2];									
		String nombreArchivo = (String) datos[3];
		
		try {
			individuo = new IndividuoGE(longitud, n_wraps, nombreArchivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return individuo;
	}

	@Override
	public String solutionToString() {
		String s = "El valor de la funcion es: " + super.getFitness() + "\n";
		//TODO hay que anadir la gramatica resultante
		return s;
	}
	
	public String genToString()		
	{
		List<Gen> genes = (List<Gen>) this.genes;
		String s = "";
		for(int i = 0; i < longitud; i++)
			s += ((int) genes.get(0).getAlelo(i) + 1) + " ";
		return s + "\n";
	}

																//He creado un array estatico en Individuo con las soluciones
//	public List<boolean[]> generaEntradas(){					//He puesto la funcion en individuo, ya que es comun a ambos metodos
//		int tam_ejemplo = 6;
//		List<boolean[]> listaTotal = new ArrayList<boolean[]>();
//		
//		
//		for(int i = 0; i < 4; i++) {
//			boolean[] binario = new boolean[tam_ejemplo];
//			int num1 = i;
//			int k = 0;
//			if(num1==0) {
//				binario[k] = false;
//			}
//			while(num1 != 0) {
//				int digito = num1 % 2;
//				if(digito == 1) {
//					binario[k] = true;
//				}
//				else
//					binario[k] = false;
//				num1= num1 / 2;
//				k++;
//			}
//			
//			while(k<1) {
//				k++;
//				binario[k] = false;
//			}
//			
//			
//			for(int j = 0; j < 16; j++) {
//				int x = 2;
//				int num2 = j;
//				if(num2==0) {
//					binario[x] = false;
//				}
//				while(num2 != 0) {
//					int digito = num2 % 2;
//					if(digito == 1) {
//						binario[x] = true;
//					}
//					else
//						binario[x] = false;
//					num2 = num2/2;
//					x++;
//				}
//				
//				while(x<5) {
//					x++;
//					binario[x] = false;
//				}
//				
//				listaTotal.add(binario);
//			}			
//		}
//		return listaTotal;
//	}

	public boolean multiplexor6(boolean entrada[]) {
		
		boolean d0 = !entrada[0] && !entrada[1] && entrada[2];
		boolean d1 = !entrada[0] && entrada[1] && entrada[3];
		boolean d2 = entrada[0] && !entrada[1] && entrada[4];
		boolean d3 = entrada[0] && entrada[1] && entrada[5];
		
		return d0 || d1 || d2 || d3;
	}
	@Override
	public void copiarIndividuo(Individuo ind) {
		List<Gen> genes = (List<Gen>) this.genes, genes2 = (List<Gen>) ind.getGenes();
		for(int i = 0; i < genes.size(); i++)
			genes.get(i).copiarGen(genes2.get(i));
			
		recalcularFenotipo();
		if(valor == ind.getFitness()) {//Se ha copiado bien
			//System.out.println("Si");
		}
		else
			System.out.println("Da el mismo valor al copiarse"); 
	}
}
