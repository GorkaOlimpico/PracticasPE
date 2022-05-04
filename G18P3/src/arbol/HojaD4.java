package arbol;

import java.util.List;
import java.util.Random;

public class HojaD4 extends Hoja {

	public HojaD4(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(7);
	}

	@Override
	public String toString() {
		return "D4";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaD4(profundidad, padre, new Random(), m6);
	}
}
