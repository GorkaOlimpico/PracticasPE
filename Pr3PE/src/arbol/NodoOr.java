package arbol;

import java.util.List;
import java.util.Random;

public class NodoOr extends Nodo {

	public NodoOr(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
		inicializar();
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return hijos.get(0).execute(input) || hijos.get(1).execute(input);
	}
	
	private void inicializar()
	{
		if(profundidad == 1)	//Si como mucho puede haber solo un elemento mas por el limite de profundidad, siempre será una hoja, esto garantiza que sea siempre ejecutable
		{
			hijos.add(Hoja.generar(rand, profundidad - 1, this));
			hijos.add(Hoja.generar(rand, profundidad - 1, this));
		}
		else 
		{
			hijos.add(Arbol.generar(rand, profundidad - 1, this));
			hijos.add(Arbol.generar(rand, profundidad - 1, this));
		}
	}
}
