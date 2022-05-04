package arbol;

import java.util.List;
import java.util.Random;

public class NodoOr extends Nodo {

	public NodoOr(int profundidad, Arbol padre, Random rand, int prof_generar, int tipo_generacion, boolean m6) {
		super(profundidad, padre, rand, m6);
		inicializar(prof_generar, tipo_generacion);
		if(padre != null) padre.sumarTamHijo(this);
	}

	private void inicializar(int prof_generar, int tipo_generacion)
	{
		if(tipo_generacion == 0)
		{
			hijos.add(Arbol.generarFull(rand, profundidad, this, prof_generar, m6));
			hijos.add(Arbol.generarFull(rand, profundidad, this, prof_generar, m6));
		}
		else
		{
			hijos.add(Arbol.generarGrow(rand, profundidad, this, prof_generar, m6));
			hijos.add(Arbol.generarGrow(rand, profundidad, this, prof_generar, m6));
		}
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return hijos.get(0).execute(input) || hijos.get(1).execute(input);
	}
	
	@Override
	public String toString() {
		return "OR(" + hijos.get(0).toString() + ", " + hijos.get(1).toString() + ")";
	}
	
	@Override
	public Arbol clonar(Arbol padre) {
		Nodo n = new NodoOr(profundidad, padre, rand, 1, 1, m6);
		n.setAlturaSubArbol(niveles_hijos);
		n.setTamSubArbol(tam_subarbol);
		for(int i = 0; i < hijos.size(); i++)
			n.getHijos().set(i, hijos.get(i).clonar(n));
		return n;
	}
}
