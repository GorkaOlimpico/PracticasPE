package gramatica;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Gramatica {
	 
	 
	 
	 private  Map<String, List<List<String>>> mapa;

	 public Gramatica(String texto){
		 
		 mapa = new HashMap<String, List<List<String>>>();
		 
		 //Separamos por líneas la gramatica
		 String r[]= texto.split("\n");
		 
		 for(int i = 0; i < r.length;i++) {
			//Separamos las reglas de sus variables
			String regla[] = r[i].split("::=");
			regla[0] = regla[0].substring(0, regla[0].length() -1);
			//Separamos cada una de las variables
			String variables[] = regla[1].split("\\|");
			//Creamos la lista para las listas de reglas
			List<List<String>> list1 = new ArrayList<List<String>>();
			
			for(int j = 0; j < variables.length; j++) {
				//Hacemos un split de las reglas por los espacios
				List<String> argumentos = new ArrayList<String>();
				
				
				variables[j] = variables[j].substring(1);
				
				if(variables.length > 1) {
					String aux[] = variables[j].split(" ");
					argumentos = Arrays.asList(aux);
				}
				else {
					
					argumentos.add(variables[j]);
				}
				
				
				list1.add(argumentos);
			}
			
			
			mapa.put(regla[0], list1); // Se introduce la regla con sus argumentos
		 }
		// System.out.println("Mapa: " + mapa);
		 
	 }
	 
	 public Map<String, List<List<String>>> getMap(){
		 return mapa;
	 }
}