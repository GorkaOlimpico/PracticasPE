package arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Arbol {
	protected int profundidad;			//Funciona a la inversa: la raiz tiene el valor maximo y decrece al descender
	protected int niveles_hijos;		//Numero maximo de niveles por debajo (nodo incluido)
	protected Arbol padre;
	protected List<Arbol> hijos;
	protected int tam_subarbol;
	Random rand;
	boolean m6;
	
	public Arbol(int profundidad, Arbol padre, Random rand, boolean m6)		
	{
		this.profundidad = profundidad;
		hijos = new ArrayList<>();
		this.padre = padre;
		tam_subarbol = 1;
		this.rand = rand;
		niveles_hijos = 0;	//This no esta incluido
		this.m6 = m6;
	}
	
	public void sumarTamHijo(Arbol a)
	{
		tam_subarbol += a.getTamSubArbol();
		actualizarAlturaSubarbol(a.getAlturaSubArbol());
		if(padre != null)
			padre.sumarTamHijo(this);
	}
	
	public void restarTamHijo(Arbol a)
	{
		if(padre != null)
			padre.restarTamHijo(this);
		
		tam_subarbol -= a.getTamSubArbol();
		if(a.getAlturaSubArbol() == niveles_hijos - 1)
		{
			int max = 0;
			for(Arbol h: hijos)
				if(h != a && h.getAlturaSubArbol() > max)
					max = h.getAlturaSubArbol();
			niveles_hijos = max + 1;
		}
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

	protected void actualizarAlturaSubarbol(int p)			//Compara la profundidad dada con niveles_hijos y guarda la mayor
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
	
	public void intercambiarNodo(Arbol a)		//No pueden ser la raiz del arbol
	{											//This y a se intercambian
		padre.restarTamHijo(this);													//Se resta el tamaño de hijo del del padre
		a.getPadre().restarTamHijo(a);
		
		Arbol aux = padre;															//Se intercambian los padres
		padre = a.getPadre();
		a.setPadre(aux);
		
		padre.getHijos().set(padre.getHijos().indexOf(a), this);					//Se sustituyen en la lista de hijos de sus padres
		a.getPadre().getHijos().set(a.getPadre().getHijos().indexOf(this), a);
		
		padre.sumarTamHijo(this);													//Se suma el tamaño de hijo al del padre
		a.getPadre().sumarTamHijo(a);
		
		
		int prof_aux = profundidad;													//Se cambia la profundidad a la que estan
		profundidad = a.getProfundidad();
		a.setProfundidad(prof_aux);
		
		actualizarProfundidadHijos();												//Se actualiza la profundidad para sus hijos
		a.actualizarProfundidadHijos();
	}
	
	public void cambiarNodo(Arbol a)		//This no puede ser una raiz
	{										//This es reemplazado por a
		padre.restarTamHijo(this);													//Se resta el tamaño de hijo del del padre
		
		a.setPadre(padre);
		
		padre.getHijos().set(padre.getHijos().indexOf(this), a);					//Se sustituyen en la lista de hijos de sus padres
		
		padre.sumarTamHijo(a);														//Se suma el tamaño de hijo al del padre
		
		a.setProfundidad(profundidad);												//Se actualiza la profundidad
		a.actualizarProfundidadHijos();
	}
	
	public static Arbol generarGrow(Random rand, int profundidad, Arbol padre, int prof_generar, boolean m6)
	{
		if(profundidad == 1)
			return Hoja.generar(rand, profundidad - 1, padre, m6);
		
		if(prof_generar < profundidad)						//Si si no se ha llegado a la profundidad establecida se pasa usa el metodo Full
			 return Nodo.generar(rand, profundidad - 1, padre, prof_generar, 1, m6);
				
		if(rand.nextDouble() < 0.5)							//Se elige uno aleatorio entre todos lo elementos
			return Nodo.generar(rand, profundidad - 1, padre, prof_generar, 1, m6);
		
		return Hoja.generar(rand, profundidad - 1, padre, m6);
	}
	
	public static Arbol generarFull(Random rand, int profundidad, Arbol padre, int prof_generar, boolean m6)
	{
		if(profundidad == 1)
			return Hoja.generar(rand, profundidad - 1, padre, m6);
		
		if(prof_generar < profundidad)
			return Nodo.generar(rand, profundidad - 1, padre, 1, 0, m6);
		else
			return Hoja.generar(rand, profundidad - 1, padre, m6);
	}
	
	public abstract String toString();
	
	public abstract Arbol clonar(Arbol padre);
	
	public abstract boolean execute(List<Boolean> input);	//Input es [A0, A1, D0, D1, D2, D3]

	public void actualizarProfundidadHijos() {
		for(Arbol a: hijos)
		{
			a.setProfundidad(profundidad - 1);
			a.actualizarProfundidadHijos();
		}
	}

	public boolean getM6() {
		return m6;
	}
}
