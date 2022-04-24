package arbol;

import java.util.List;
import java.util.Random;

public class NodoNot extends Nodo {

	public NodoNot(int profundidad, Arbol padre, Random rand, int prof_generar) {
		super(profundidad, padre, rand);
		inicializar(prof_generar);
		actualizarAlturaSubarbol(niveles_hijos);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return !hijos.get(0).execute(input);
	}

	
	private void inicializar(int prof_generar)
	{
		hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
	}
	
	@Override
	public String toString() {
		return "NOT(" + hijos.get(0).toString() + ")";
	}
	
	@Override
	public Arbol clonar(Arbol padre) {
		Nodo n = new NodoNot(profundidad, padre, rand, 1);
		for(int i = 0; i < hijos.size(); i++)
			n.getHijos().get(i).cambiarNodo(hijos.get(i).clonar(n));
		return n;
	}
}
