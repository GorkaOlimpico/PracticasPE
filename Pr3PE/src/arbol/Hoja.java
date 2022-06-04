package arbol;

import java.util.Random;

public abstract class Hoja extends Arbol {
	
	public Hoja(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
		if(padre != null) padre.actualizarPadre(this, getTamSubArbol());
	}
	
	public static Arbol generar(Random rand, int profundidad, Arbol padre, boolean m6)
	{
		double prob = rand.nextDouble();
		if(m6)
		{
			if(prob < 1.0/6.0)
				return new HojaA0(profundidad, padre, rand, m6);
			if(prob < 2.0/6.0)
				return new HojaA1(profundidad, padre, rand, m6);
			if(prob < 3.0/6.0)
				return new HojaD0(profundidad, padre, rand, m6);
			if(prob < 4.0/6.0)
				return new HojaD1(profundidad, padre, rand, m6);
			if(prob < 5.0/6.0)
				return new HojaD2(profundidad, padre, rand, m6);
			return new HojaD3(profundidad, padre, rand, m6);
		}
		else
		{
			if(prob < 1.0/11.0)
				return new HojaA0(profundidad, padre, rand, m6);
			if(prob < 2.0/11.0)
				return new HojaA1(profundidad, padre, rand, m6);
			if(prob < 3.0/11.0)
				return new HojaA2(profundidad, padre, rand, m6);
			if(prob < 4.0/11.0)
				return new HojaD0(profundidad, padre, rand, m6);
			if(prob < 5.0/11.0)
				return new HojaD1(profundidad, padre, rand, m6);
			if(prob < 6.0/11.0)
				return new HojaD2(profundidad, padre, rand, m6);
			if(prob < 7.0/11.0)
				return new HojaD3(profundidad, padre, rand, m6);
			if(prob < 8.0/11.0)
				return new HojaD4(profundidad, padre, rand, m6);
			if(prob < 9.0/11.0)
				return new HojaD5(profundidad, padre, rand, m6);
			if(prob < 10.0/11.0)
				return new HojaD6(profundidad, padre, rand, m6);
			return new HojaD7(profundidad, padre, rand, m6);
		}
	}
}
