package logico;

public class Cable extends Servicio {
	private int cantCanales;

	public Cable(String nombre, float costo, int cantCanales) {
		super(nombre, costo);
		this.cantCanales = cantCanales;
	}
	
}
