package arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Arbol {
	protected int profundidad;			//Funciona a la inversa: la raiz tiene el valor maximo y decrece al descender
	protected int niveles_hijos;		//Numero maximo de niveles por debajo (nodo no incluido)
	protected Arbol padre;
	protected List<Arbol> hijos;
	protected int tam_subarbol;
	Random rand;
	boolean m6;							//m6 = true -> multiplexor 6 entradas
										//m6 = false -> multiplexor 11 entradas
	public Arbol(int profundidad, Arbol padre, Random rand, boolean m6)		
	{
		this.profundidad = profundidad;
		hijos = new ArrayList<>();
		this.padre = padre;
		tam_subarbol = 1;	//This incluido
		this.rand = rand;
		niveles_hijos = 0;	//This no esta incluido
		this.m6 = m6;
	}
	
	protected void sumarTamHijo(Arbol a, int tam_a)
	{
		actualizarPadre(a, tam_a);
		if(padre != null)
			padre.sumarTamHijo(this, tam_a);
	}
	
	protected void actualizarPadre(Arbol a, int tam_a)
	{
		tam_subarbol += tam_a;
		if(niveles_hijos <= a.getAlturaSubArbol())
			niveles_hijos = a.getAlturaSubArbol() + 1;
	}
	
	
	protected void restarTamHijo(Arbol a, int tam_a)
	{
		if(padre != null)
			padre.restarTamHijo(this, tam_a);
		
		tam_subarbol -= tam_a;
		if(a.getAlturaSubArbol() == niveles_hijos - 1)
		{
			int max = -1;
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

	
	public int getAlturaSubArbol()
	{
		return niveles_hijos;
	}
	
	protected void setAlturaSubArbol(int n)
	{
		niveles_hijos = n;
	}
	
	public int getTamSubArbol()
	{
		return tam_subarbol;
	}
	
	protected void setTamSubArbol(int t)
	{
		tam_subarbol = t;
	}
	
	public void intercambiarNodo(Arbol a)		//No pueden ser la raiz del arbol
	{											//This y a se intercambian
		padre.restarTamHijo(this, getTamSubArbol());								//Se resta el tamaño de hijo del del padre
		a.getPadre().restarTamHijo(a, a.getTamSubArbol());
		
		Arbol aux = padre;															//Se intercambian los padres
		padre = a.getPadre();
		a.setPadre(aux);
		
		padre.getHijos().set(padre.getHijos().indexOf(a), this);					//Se sustituyen en la lista de hijos de sus padres
		a.getPadre().getHijos().set(a.getPadre().getHijos().indexOf(this), a);
		
		padre.sumarTamHijo(this, getTamSubArbol());									//Se suma el tamaño de hijo al del padre
		a.getPadre().sumarTamHijo(a, a.getTamSubArbol());
		
		
		int prof_aux = profundidad;													//Se cambia la profundidad a la que estan
		profundidad = a.getProfundidad();
		a.setProfundidad(prof_aux);
		
		actualizarProfundidadHijos();												//Se actualiza la profundidad para sus hijos
		a.actualizarProfundidadHijos();
	}
	
	public void cambiarNodo(Arbol a)		//This no puede ser una raiz
	{										//El subarbol del que this es la raiz es reemplazado por a (a mantiene su subarbol original)
		padre.restarTamHijo(this, getTamSubArbol());								//Se resta el tamaño de hijo del del padre
		
		a.setPadre(padre);
		
		padre.getHijos().set(padre.getHijos().indexOf(this), a);					//Se sustituyen en la lista de hijos de sus padres
		
		padre.sumarTamHijo(a, a.getTamSubArbol());									//Se suma el tamaño de hijo al del padre
		
		a.setProfundidad(profundidad);												//Se actualiza la profundidad
		a.actualizarProfundidadHijos();
	}
	
	public void sustituirNodo(Arbol a)	//Sustituye unicamente el nodo this por a, los hijos siguen siendo los mismos
	{
		if(a.getHijos().size() == getHijos().size() && padre != null)
		{
			for(int i = 0; i < getHijos().size(); i++)
			{
				a.getHijos().set(i, getHijos().get(i));
				a.getHijos().get(i).setPadre(a);
			}
			a.setPadre(padre);
			padre.getHijos().set(padre.getHijos().indexOf(this), a);
			a.setProfundidad(profundidad);
			a.setAlturaSubArbol(niveles_hijos);
			a.setTamSubArbol(tam_subarbol);
		}
	}
	
	public static Arbol generarGrow(Random rand, int profundidad, Arbol padre, int prof_generar, boolean m6)
	{
		if(profundidad <= 1)
			return Hoja.generar(rand, profundidad - 1, padre, m6);
		
		if(prof_generar < profundidad || rand.nextDouble() < 0.5)						
			 return Nodo.generar(rand, profundidad - 1, padre, prof_generar, 1, m6);
				
		return Hoja.generar(rand, profundidad - 1, padre, m6);
	}
	
	public static Arbol generarFull(Random rand, int profundidad, Arbol padre, int prof_generar, boolean m6)
	{
		if(profundidad <= 1)
			return Hoja.generar(rand, profundidad - 1, padre, m6);
		
		if(prof_generar < profundidad)
			return Nodo.generar(rand, profundidad - 1, padre, prof_generar, 1, m6);

		return Hoja.generar(rand, profundidad - 1, padre, m6);
	}
	
	public abstract String toString();
	
	public abstract Arbol clonar(Arbol padre);
	
	public abstract boolean execute(List<Boolean> input);	//Input es [A0, A1, D0, D1, D2, D3] en M6 y [A0, A1, A2, D0, D1, D2, D3, D4, D5, D6, D7] en M8

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
