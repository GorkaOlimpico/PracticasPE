package arbol;

import java.util.List;
import java.util.Random;

public class HojaD7 extends Hoja {

	public HojaD7(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(10);
	}

	@Override
	public String toString() {
		return "D7";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaD3(profundidad, padre, new Random(), m6);
	}
}
