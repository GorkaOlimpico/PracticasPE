package arbol;

import java.util.Random;

public abstract class Nodo extends Arbol {

	public Nodo(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
		padre.sumarTamHijo(this);
		prob_cruce = 0.9;
	}

	public static Arbol generar(Random rand, int profundidad, Arbol padre, int prof_generar)
	{
		double prob = rand.nextDouble();
		if(prob < 1/4)
			return new NodoIf(profundidad, padre, rand, prof_generar);
		if(prob < 2/4)
			return new NodoAnd(profundidad, padre, rand, prof_generar);
		if(prob < 3/4)
			return new NodoNot(profundidad, padre, rand, prof_generar);
		return new NodoOr(profundidad, padre, rand, prof_generar);
	}
}
