package arbol;

import java.util.List;
import java.util.Random;

public class HojaD1 extends Hoja {

	public HojaD1(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		if(m6)
			return input.get(3);
		else
			return input.get(4);
	}

	@Override
	public String toString() {
		return "D1";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaD1(profundidad, padre, new Random(), m6);
	}
}
