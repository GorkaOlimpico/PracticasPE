package arbol;

import java.util.List;
import java.util.Random;

public class NodoIf extends Nodo {

	public NodoIf(int profundidad, Arbol padre, Random rand, int prof_generar, int tipo_generacion) {
		super(profundidad, padre, rand);
		inicializar(prof_generar, tipo_generacion);
		if(padre != null) padre.sumarTamHijo(this);
	}

	private void inicializar(int prof_generar, int tipo_generacion)
	{
		if(tipo_generacion == 0)
		{
			hijos.add(Arbol.generarFull(rand, profundidad - 1, this, prof_generar));
			hijos.add(Arbol.generarFull(rand, profundidad - 1, this, prof_generar));
			hijos.add(Arbol.generarFull(rand, profundidad - 1, this, prof_generar));
		}
		else
		{
			hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
			hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
			hijos.add(Arbol.generarGrow(rand, profundidad - 1, this, prof_generar));
		}
	}
	
	@Override
	public boolean execute(List<Boolean> input) {
		if(hijos.get(0).execute(input))
			return hijos.get(1).execute(input);
		else
			return hijos.get(2).execute(input);
	}
	
	@Override
	public String toString() {
		return "IF(" + hijos.get(0).toString()  + ", " + hijos.get(1).toString() + ", " + hijos.get(2).toString() + ")";
	}
	
	@Override
	public Arbol clonar(Arbol padre) {
		Nodo n = new NodoIf(2, padre, rand, 1, 1);
		for(int i = 0; i < hijos.size(); i++)
			n.getHijos().get(i).cambiarNodo(hijos.get(i).clonar(n));
		n.setProfundidad(profundidad);
		return n;
	}
}
