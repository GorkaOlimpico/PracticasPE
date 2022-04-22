package arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Arbol {
	protected int profundidad;
	protected Arbol padre;
	protected List<Arbol> hijos;
	
	public Arbol(int profundidad, Arbol padre)
	{
		this.profundidad = profundidad;
		hijos = new ArrayList<>();
		this.padre = padre;
	}
	
	public abstract boolean execute(List<Boolean> input);
}
