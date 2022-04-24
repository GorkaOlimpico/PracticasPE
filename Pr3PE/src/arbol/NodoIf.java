package arbol;

import java.util.List;
import java.util.Random;

public class NodoIf extends Nodo {

	public NodoIf(int profundidad, Arbol padre, Random rand, int prof_generar) {
		super(profundidad, padre, rand);
		inicializar(prof_generar);
		actualizarProfundidad(niveles_hijos);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		if(hijos.get(0).execute(input))
			return hijos.get(1).execute(input);
		else
			return hijos.get(2).execute(input);
	}
	
	private void inicializar(int prof_generar)
	{
		hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
		hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
		hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
	}
	
	@Override
	public String toString() {
		return "IF(" + hijos.get(0).toString()  + ", " + hijos.get(1).toString() + ", " + hijos.get(2).toString() + ")";
	}
}
