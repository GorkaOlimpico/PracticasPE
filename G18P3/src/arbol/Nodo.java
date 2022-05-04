package arbol;

import java.util.Random;

public abstract class Nodo extends Arbol {

	public Nodo(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	public static Arbol generar(Random rand, int profundidad, Arbol padre, int prof_generar, int tipo_generacion, boolean m6)
	{
		double prob = rand.nextDouble();
		if(prob < 1.0 / 4.0)
			return new NodoIf(profundidad, padre, rand, prof_generar, tipo_generacion, m6);
		if(prob < 2.0 / 4.0)
			return new NodoAnd(profundidad, padre, rand, prof_generar, tipo_generacion, m6);
		if(prob < 3.0 / 4.0)
			return new NodoNot(profundidad, padre, rand, prof_generar, tipo_generacion, m6);
		return new NodoOr(profundidad, padre, rand, prof_generar, tipo_generacion, m6);
	}
}
