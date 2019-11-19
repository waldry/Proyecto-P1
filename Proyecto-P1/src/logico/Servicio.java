package logico;

public abstract class Servicio {
	protected String nombre;
	
	public Servicio(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public abstract float costoServicio();
}
