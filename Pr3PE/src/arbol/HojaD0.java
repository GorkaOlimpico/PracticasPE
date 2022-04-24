package arbol;

import java.util.List;
import java.util.Random;

public class HojaD0 extends Hoja {

	public HojaD0(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(2);
	}

	@Override
	public String toString() {
		return "D0";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaD0(profundidad, padre, new Random());
	}
}
