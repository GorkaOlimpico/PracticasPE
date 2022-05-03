package arbol;

import java.util.Random;


public abstract class Hoja extends Arbol {
	public Hoja(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
		if(padre != null) padre.sumarTamHijo(this);
		prob_cruce = 0.1;
	}
	
	public static Arbol generar(Random rand, int profundidad, Arbol padre)
	{
		double prob = rand.nextDouble();
		if(prob < 1.0/6.0)
			return new HojaA0(profundidad, padre, rand);
		if(prob < 2.0/6.0)
			return new HojaA1(profundidad, padre, rand);
		if(prob < 3.0/6.0)
			return new HojaD0(profundidad, padre, rand);
		if(prob < 4.0/6.0)
			return new HojaD1(profundidad, padre, rand);
		if(prob < 5.0/6.0)
			return new HojaD2(profundidad, padre, rand);
		return new HojaD3(profundidad, padre, rand);
	}
}
