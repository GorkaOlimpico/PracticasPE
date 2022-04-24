package arbol;

import java.util.List;
import java.util.Random;

public class HojaA1 extends Hoja {

	public HojaA1(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(1);
	}

	@Override
	public String toString() {
		return "A1";
	}

}
