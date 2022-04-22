package arbol;

import java.util.List;

public class HojaD0 extends Hoja {

	public HojaD0(int profundidad, Arbol padre) {
		super(profundidad, padre);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(2);
	}

}
