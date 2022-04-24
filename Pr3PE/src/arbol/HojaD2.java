package arbol;

import java.util.List;
import java.util.Random;

public class HojaD2 extends Hoja {

	public HojaD2(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(4);
	}

	@Override
	public String toString() {
		return "D2";
	}
}
