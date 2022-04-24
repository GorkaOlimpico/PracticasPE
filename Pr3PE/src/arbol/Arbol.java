package arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Arbol {
	protected int profundidad;			//Funciona a la inversa: la raiz tiene el valor maximo y decrece al descender
	protected int niveles_hijos;		//Numero maximo de niveles por debajo
	protected Arbol padre;
	protected List<Arbol> hijos;
	protected int tam_subarbol;
	Random rand;
	protected double prob_cruce;
	
	public Arbol(int profundidad, Arbol padre, Random rand)		
	{
		this.profundidad = profundidad;
		hijos = new ArrayList<>();
		this.padre = padre;
		tam_subarbol = 1;
		this.rand = rand;
		niveles_hijos = 0;
	}
	
	public boolean seleccionarCruce(double prob)
	{
		return prob < prob_cruce;
	}
	
	protected void sumarTamHijo(Arbol a)
	{
		tam_subarbol += a.getTamSubArbol();
		if(padre != null)
			padre.sumarTamHijo(a);
	}
	
	protected void restarTamHijo(Arbol a)
	{
		tam_subarbol -= a.getTamSubArbol();
		if(padre != null)
			padre.restarTamHijo(a);
	}
	
	
	public List<Arbol> getHijos()
	{
		return hijos;
	}
	
	public Arbol getPadre()
	{
		return padre;
	}
	
	public void setPadre(Arbol padre)
	{
		this.padre = padre;
	}
	
	public int getProfundidad()
	{
		return profundidad;
	}
	
	public void setProfundidad(int profundidad)
	{
		this.profundidad = profundidad;
		for(Arbol a: hijos)
			a.setProfundidad(profundidad - 1);
	}

	protected void actualizarProfundidad(int p)			//Compara la profundidad dada con profundidad_hijos y guarda la mayor
	{
		if(niveles_hijos <= p)
			niveles_hijos = p + 1;
	}
	
	public int getAlturaSubArbol()
	{
		return niveles_hijos;
	}
	
	public int getTamSubArbol()
	{
		return tam_subarbol;
	}
	
	public void intercambiarNodo(Arbol a)
	{
		//TODO
//		a1.getPadre().eliminarHijo(a1);													//Se resta el tamaño de hijo del del padre
//		a2.getPadre().eliminarHijo(a2);
//		
//		aux = a1.getPadre();															//Se intercambian los padres
//		a1.setPadre(a2.getPadre());
//		a2.setPadre(aux);
//		
//		a1.getPadre().getHijos().set(a1.getPadre().getHijos().indexOf(a2), a1);			//Se sustituyen en la lista de hijos de sus padres
//		a2.getPadre().getHijos().set(a2.getPadre().getHijos().indexOf(a1), a2);
//		
//		a1.getPadre().anadirHijo(a1);													//Se suma el tamaño de hijo al del padre
//		a2.getPadre().anadirHijo(a2);
//		
//		int profundidad = a1.getProfundidad();											//Se cambia la profundidad a la que estan
//		a1.setProfundidad(a2.getProfundidad());
//		a2.setProfundidad(profundidad);
	}
	
	public static Arbol generarGrow(Random rand, int profundidad, Arbol padre, int prof_generar)
	{
		if(prof_generar >= profundidad)
			return generarFull(rand, profundidad, padre);
		else if(rand.nextDouble() < 0.4)
			return Nodo.generar(rand, profundidad - 1, padre, prof_generar);
		else
			return Hoja.generar(rand, profundidad - 1, padre);
	}
	
	public static Arbol generarFull(Random rand, int profundidad, Arbol padre)
	{
		if(profundidad == 1)
			return Hoja.generar(rand, profundidad - 1, padre);
		else
			return Nodo.generar(rand, profundidad - 1, padre, 0);
	}
	
	public abstract String toString();
	
	public abstract boolean execute(List<Boolean> input);	//Input es [A0, A1, D0, D1, D2, D3]
}
