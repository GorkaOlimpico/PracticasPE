package arbol;

import java.util.List;
import java.util.Random;

public class NodoNot extends Nodo {

	public NodoNot(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
		inicializar();
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return !hijos.get(0).execute(input);
	}

	
	private void inicializar()
	{
		if(profundidad == 1)	//Si como mucho puede haber solo un elemento mas por el limite de profundidad, siempre será una hoja, esto garantiza que sea siempre ejecutable
			hijos.add(Hoja.generar(rand, profundidad - 1, this));
		else 
			hijos.add(Arbol.generar(rand, profundidad - 1, this));
	}
}
