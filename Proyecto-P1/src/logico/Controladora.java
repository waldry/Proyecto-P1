package logico;

import java.util.ArrayList;

public class Controladora {
	private static Controladora cont = null;
	
	private Controladora() {
		super();
	}
	
	public Controladora getInstance() {
		if(cont==null) {
			cont = new Controladora();
		}
		return cont;
	}
}
