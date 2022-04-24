package arbol;

import java.util.List;
import java.util.Random;

public class HojaA0 extends Hoja {

	public HojaA0(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(0);
	}

	@Override
	public String toString() {
		return "A0";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaA0(profundidad, padre, new Random());
	}

}
