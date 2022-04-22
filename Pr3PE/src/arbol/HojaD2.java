package arbol;

import java.util.List;

public class HojaD2 extends Hoja {

	public HojaD2(int profundidad, Arbol padre) {
		super(profundidad, padre);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(4);
	}
}
