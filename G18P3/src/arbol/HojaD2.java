package arbol;

import java.util.List;
import java.util.Random;

public class HojaD2 extends Hoja {

	public HojaD2(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		if(m6)
			return input.get(4);
		else
			return input.get(5);
	}

	@Override
	public String toString() {
		return "D2";
	}
	
	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaD2(profundidad, padre, new Random(), m6);
	}
}
