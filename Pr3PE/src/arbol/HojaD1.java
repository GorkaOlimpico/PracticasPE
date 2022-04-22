package arbol;

import java.util.List;

public class HojaD1 extends Hoja {

	public HojaD1(int profundidad, Arbol padre) {
		super(profundidad, padre);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(3);
	}

}
