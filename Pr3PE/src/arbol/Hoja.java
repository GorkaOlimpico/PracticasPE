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
		if(prob < 2/6)
			return new HojaA1(profundidad, padre);
		if(prob < 3/6)
			return new HojaD0(profundidad, padre);
		if(prob < 4/6)
			return new HojaD1(profundidad, padre);
		if(prob < 5/6)
			return new HojaD2(profundidad, padre);
		return new HojaD3(profundidad, padre);
	}
}
