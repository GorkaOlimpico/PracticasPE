package arbol;

import java.util.List;
import java.util.Random;

public class HojaD5 extends Hoja {

	public HojaD5(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(8);
	}

	@Override
	public String toString() {
		return "D5";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaD3(profundidad, padre, new Random(), m6);
	}
}
