package arbol;

import java.util.Random;

public abstract class Nodo extends Arbol {

	public Nodo(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
		padre.anadirHijo(super.tam_subarbol);
	}

	public static Arbol generar(Random rand, int profundidad, Arbol padre)
	{
		double prob = rand.nextDouble();
		if(prob < 1/4)
			return new NodoIf(profundidad, padre, rand);
		if(prob < 2/4)
			return new NodoAnd(profundidad, padre, rand);
		if(prob < 3/4)
			return new NodoNot(profundidad, padre, rand);
		return new NodoOr(profundidad, padre, rand);
	}
}
