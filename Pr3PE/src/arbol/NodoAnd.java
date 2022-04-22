package arbol;

import java.util.List;
import java.util.Random;

public class NodoAnd extends Nodo {

	public NodoAnd(int profundidad, Arbol padre) {
		super(profundidad, padre);
		inicializar();
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return hijos.get(0).execute(input) && hijos.get(1).execute(input);
	}

	private void inicializar()
	{
		Random rand = new Random();
		if(profundidad == 1)
		{
			hijos.add(Hoja.generar(rand, profundidad - 1, padre));
			hijos.add(Hoja.generar(rand, profundidad - 1, padre));
		}
		else 
		{
			if(rand.nextDouble() < 0.4)
				hijos.add(Nodo.generar(rand, profundidad - 1, padre));
			else
				hijos.add(Hoja.generar(rand, profundidad - 1, padre));
			
			if(rand.nextDouble() < 0.4)
				hijos.add(Nodo.generar(rand, profundidad - 1, padre));
			else
				hijos.add(Hoja.generar(rand, profundidad - 1, padre));
		}
	}
}
