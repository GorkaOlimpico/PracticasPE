package arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Arbol {
	protected int profundidad;
	protected Arbol padre;
	protected List<Arbol> hijos;
	protected int tam_subarbol;
	Random rand;
	
	public Arbol(int profundidad, Arbol padre, Random rand)
	{
		this.profundidad = profundidad;
		hijos = new ArrayList<>();
		this.padre = padre;
		tam_subarbol = 1;
		this.rand = rand;
	}
	
	public void anadirHijo(int tam)
	{
		tam_subarbol += tam;
	}
	
	public void eliminarHijo(int tam)
	{
		tam_subarbol -= tam;
	}
	
	public List<Arbol> getHijos()
	{
		return hijos;
	}
	
	public Arbol getPadre()
	{
		return padre;
	}
	
	public int getProfundidad()
	{
		return profundidad;
	}
	
	public int getTamSubArbol()
	{
		return tam_subarbol;
	}
	
	public static Arbol generar(Random rand, int profundidad, Arbol padre)
	{
		if(rand.nextDouble() < 0.4)
			return Nodo.generar(rand, profundidad - 1, padre);
		else
			return Hoja.generar(rand, profundidad - 1, padre);
	}
	
	public abstract boolean execute(List<Boolean> input);	//Input es [A0, A1, D0, D1, D2, D3]
}
