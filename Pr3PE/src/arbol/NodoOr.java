package arbol;

import java.util.List;
import java.util.Random;

public class NodoOr extends Nodo {

	public NodoOr(int profundidad, Arbol padre, Random rand, int prof_generar) {
		super(profundidad, padre, rand);
		inicializar(prof_generar);
		actualizarProfundidad(niveles_hijos);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return hijos.get(0).execute(input) || hijos.get(1).execute(input);
	}
	
	private void inicializar(int prof_generar)
	{
		hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
		hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
	}
	
	@Override
	public String toString() {
		return "OR(" + hijos.get(0).toString() + ", " + hijos.get(1).toString() + ")";
	}
}
