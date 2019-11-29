package logico;

import java.io.Serializable;

public abstract class Servicio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	
	public Servicio(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public abstract float costoServicio();
}
