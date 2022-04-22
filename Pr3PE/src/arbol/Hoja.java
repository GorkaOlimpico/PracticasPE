package arbol;

import java.util.Random;


public abstract class Hoja extends Arbol {
	public Hoja(int profundidad, Arbol padre) {
		super(profundidad, padre);
	}
	
	public static Hoja generar(Random rand, int profundidad, Arbol padre)
	{
		double prob = rand.nextDouble();
		if(prob < 1/6)
			return new HojaA0(profundidad, padre);
		if(prob < 1/6)
			return new HojaA0(profundidad, padre);
		if(prob < 1/6)
			return new HojaA0(profundidad, padre);
		if(prob < 1/6)
			return new HojaA0(profundidad, padre);
		if(prob < 1/6)
			return new HojaA0(profundidad, padre);
		if(prob < 1/6)
			return new HojaA0(profundidad, padre);
		return null;
	}
}
