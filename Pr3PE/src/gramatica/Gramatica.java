package gramatica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gramatica {
	 
	 
	 /*
	 	S = funcion
	 	(A) funcion = IF | AND | OR | NOT
	 	(B) exp = funcion | A0 | A1 | D0 | D1 | D2 | D3
	 	(C) IF = if exp exp exp
	 	(D) NOT = not exp
	 	(E) AND = and exp exp
	 	(F) OR = or exp exp
	 	
	 	Regla A opciones = 4
	 	Regla B opciones = 7
	 	
	 	
	 */
	 private int wraps;
	 private int maxWraps;
	 private Map<String, List<List<String>>> mapa;

	 public Gramatica(int maxWraps, String texto){
		 this.wraps = 0;
		 this.maxWraps = maxWraps;
		 mapa = new HashMap<String, List<List<String>>>();
		 
		 //Separamos por líneas la gramatica
		 String r[]= texto.split("\n");
		 
		 for(int i = 0; i < r.length;i++) {
			//Separamos las reglas de sus variables
			String regla[] = r[i].split("::=");
			//Separamos cada una de las variables
			String variables[] = regla[1].split("\\|");
			//Creamos la lista para las listas de reglas
			List<List<String>> list1 = new ArrayList<List<String>>();
			
			for(int j = 0; j < variables.length; j++) {
				//Hacemos un split de las reglas por los espacios
				String aux[] = variables[j].split(" ");
				List<String> argumentos = Arrays.asList(aux);
				list1.add(argumentos);
			}
			
			
			mapa.put(regla[0], list1); // Se introduce la regla con sus argumentos
		 }

	 }
	 
}