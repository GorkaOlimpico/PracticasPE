package arbol;

import java.util.List;
import java.util.Random;

public class HojaA2 extends Hoja {

	public HojaA2(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(2);
	}

	@Override
	public String toString() {
		return "A2";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaA2(profundidad, padre, new Random(), m6);
	}

}
