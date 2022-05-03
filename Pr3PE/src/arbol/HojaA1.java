package arbol;

import java.util.List;
import java.util.Random;

public class HojaA1 extends Hoja {

	public HojaA1(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(1);
	}

	@Override
	public String toString() {
		return "A1";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaA1(profundidad, padre, new Random(), m6);
	}
}
