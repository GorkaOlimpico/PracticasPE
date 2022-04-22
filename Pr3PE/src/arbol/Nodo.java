package arbol;

import java.util.Random;

public abstract class Nodo extends Arbol {

	public Nodo(int profundidad, Arbol padre) {
		super(profundidad, padre);
	}

	public static Nodo generar(Random rand, int profundidad, Arbol padre)
	{
		double prob = rand.nextDouble();
		if(prob < 1/4)
			return new NodoIf(profundidad, padre);
		if(prob < 2/4)
			return new NodoAnd(profundidad, padre);
		if(prob < 3/4)
			return new NodoNot(profundidad, padre);
		return new NodoOr(profundidad, padre);
	}
}
