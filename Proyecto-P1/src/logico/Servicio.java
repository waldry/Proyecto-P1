package logico;

public abstract class Servicio {
	protected String nombre;
	protected float costo;
	
	public Servicio(String nombre, float costo) {
		super();
		this.nombre = nombre;
		this.costo = costo;
	}
	
	public abstract float costoServicio();
}
