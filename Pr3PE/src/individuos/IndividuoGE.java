package individuos;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.Gen;
import gen.GenPr3;
import gramatica.Gramatica;



public class IndividuoGE extends Individuo {

	private final static String type = "Gram�tica Evolutiva"; 
	private List<String> solucion;
	private Gramatica gramatica;
	private int max_wraps;
	private int wraps;
	private int pos;
	private int longitud;

	
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
		this.genes = genes;
		recalcularFenotipo();
	

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
            total += "\n";
        }
		//System.out.println("Leido de archivo: \n" + total);
		return total;
	}
	
	public void traduceALista() {
		// Esta funci�n hace que el array num�rico pase a ser la lista con la que podemos operar
		List<Gen> genes = (List<Gen>) this.genes;
		System.out.println("GEN: "+ genes.get(0).getAlelos());
		addElemLista("S");
		System.out.println("Lista: "+ solucion);
		pos = 0;
		wraps = 0;
		
	}
	
	public void addElemLista(String key) {
		List<String> terminales = Arrays.asList("A0", "A1", "D0", "D1", "D2", "D3");
		
		
		switch (key) {
			case "AND": {
				solucion.add(key);
			}
			break;
			case "OR": {
				solucion.add(key);
			}
			break;
			case "NOT": {
				solucion.add(key);
			}
			break;
			case "IF": {
				solucion.add(key);
			}
			break;
			case "(": {
				solucion.add(key);
			}
			break;
			case ")": {
				solucion.add(key);
			}			
			break;
			case "A0": {
				solucion.add(key);
			}			
			break;
			case "A1": {
				solucion.add(key);
			}			
			break;
			case "D0": {
				solucion.add(key);
			}			
			break;
			case "D1": {
				solucion.add(key);
			}			
			break;
			case "D2": {
				solucion.add(key);
			}			
			break;
			case "D3": {
				solucion.add(key);
			}			
			break;
			
			default:{ // Si es un elemento key como por ejemplo expr
				
				// 0. Si es el �ltimo wrap entonces tiene que ser un terminal
				if (wraps == max_wraps -1) {
					Random rand = new Random();
					int prob = rand.nextInt(5);
					key = terminales.get(prob);
					addElemLista(key);
					System.out.println("Se lleg� a max_wraps -1");
					break;
				}
				
				
				// 1. Busco en la tabla Hash. Obtengo su lista de opciones
			
				List<List<String>> opciones = gramatica.getMap().get(key);
				int num_opciones = opciones.size();
				
				// 2. Elijo una de las opciones disponibles seg�n el n�mero que toca del individuo
				int numero = 0;
				if(num_opciones > 1) {
					List<Gen> genes = (List<Gen>) this.genes;
					numero = ((int) genes.get(0).getAlelo(pos)) % num_opciones;
					pos++;
					if(pos == longitud) { // m�ximo indice
						pos = 0;
						wraps++;
						if(wraps > max_wraps) {
							System.out.println("ERROR: se supera el max_wraps");
						}
					}
				}
				
				List<String> elegida = opciones.get(numero);
				
				// 3. A�ado todos los argumentos a la lista
				for(String argumento : elegida) {
					addElemLista(argumento);
				}
				
			}
			break;
			
		}
		
		
			
	}

	@Override
	public boolean max() {
		return false;
	}

	
	
	@Override
	public double getValor() {

		int aciertos = 0;
		
		// 1. Se genera la List<String> a partir del n�mero
		traduceALista();
				
		// 2. Por cada entrada[6] eval�o la List<String> y eval�o su resultado correcto del MX-6
		for(List<Boolean> entrada : entradas) {
			Boolean [] aux =  new Boolean[entrada.size()];
			entrada.toArray(aux);
			//4. Comparo los resultados. Si son iguales entonces sumo 1 a aciertos
			if(evaluaElemento(entrada, solucion) == multiplexor6(aux)) {
				aciertos++;
			}	
		}
		return aciertos;
	}
	
	public boolean evaluaElemento(List<Boolean> entrada, List<String> elemento) {
		boolean resultado = false;
		
		String primero = buscaPrimero(elemento);
		System.out.println("Primero: " + primero);
		switch (primero) {
			case "IF": {
				
			}
			break;
			case "AND": {
				
			}
			break;
			case "OR": {
				
			}
			break;
			case "NOT": {
				resultado = !(evaluaElemento(entrada, siguienteElemento()));
			}
			break;
			case "A0": {
				resultado = entrada.get(0);
			}
			break;
			case "A1": {
				resultado = entrada.get(1);
			}
			break;
			case "D0": {
				resultado = entrada.get(2);
			}
			break;
			case "D1": {
				resultado = entrada.get(3);
			}
			break;
			case "D2": {
				resultado = entrada.get(4);
			}
			break;
			case "D3": {
				resultado = entrada.get(5);
			}
			break;
				
			
		
		}
		
		
		return resultado;
	}
	
	public String buscaPrimero(List<String> elemento) {
		String primero = "";
		// Devuelve la primera operaci�n o terminal.
		// Ejemplo: ((IF (A0) (A1) (D1)) AND D3). Esta funci�n devuelve "AND" porque es la operaci�n que se quiere realizar
		
		int pos =  0;
		// 1.1 Si llega un par�ntesis se busca el siguiente elemento despu�s de que se hayan cerrado todos los par�ntesis que lleguen nuevos
		if(elemento.get(pos).equals("(")) {
			int pIntermedios = 0; // indica si existen par�ntesis intermedios sin cerrar
			pos++;
			
			while(pos < elemento.size()) {
				if (elemento.get(pos).equals("(")) {
					pIntermedios++;
				}
				else if (elemento.get(pos).equals(")")) {
					pIntermedios--;
				}
				else {
					if (pIntermedios == 0) { 
						primero = elemento.get(pos);
						pos = elemento.size();
					}
				}
				pos++;
			}			
		}
		else {	// 1.2 Si llega un terminal entonces devuelve el terminal
			primero = elemento.get(pos);
		}

		
		return primero;
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
		int longitud = Integer.parseInt((String) datos[3]);
	
		int n_wraps =  Integer.parseInt((String) datos[2]);									
		String nombreArchivo = (String) datos[1];
		
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
		System.out.println("Individuo: "+ solucion);
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

	public boolean multiplexor6(Boolean entrada[]) {
		
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
