package arbol;

import java.util.List;
import java.util.Random;

public class HojaD6 extends Hoja {

	public HojaD6(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(9);
	}

	@Override
	public String toString() {
		return "D6";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaD3(profundidad, padre, new Random(), m6);
	}
}
