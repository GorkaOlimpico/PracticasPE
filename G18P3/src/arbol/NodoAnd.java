package arbol;

import java.util.List;
import java.util.Random;

public class NodoAnd extends Nodo {

	public NodoAnd(int profundidad, Arbol padre, Random rand, int prof_generar, int tipo_generacion, boolean m6) {
		super(profundidad, padre, rand, m6);
		inicializar(prof_generar, tipo_generacion);
		if(padre != null) padre.actualizarPadre(this);
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
		return hijos.get(0).execute(input) && hijos.get(1).execute(input);
	}

	@Override
	public String toString() {
		return "AND(" + hijos.get(0).toString() + ", " + hijos.get(1).toString() + ")";
	}
	
	@Override
	public Arbol clonar(Arbol padre) {
		Nodo n = new NodoAnd(2, padre, rand, 1, 1, m6);
		for(int i = 0; i < hijos.size(); i++)
			n.getHijos().set(i, hijos.get(i).clonar(n));
		n.setProfundidad(profundidad);
		n.setAlturaSubArbol(niveles_hijos);
		n.setTamSubArbol(tam_subarbol);
		return n;
	}
}
