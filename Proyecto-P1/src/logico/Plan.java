package logico;

import java.util.ArrayList;

public class Plan {
	private String nombre;
	private ArrayList<Servicio> servicios;
	
	public Plan(String nombre, ArrayList<Servicio> servicios) {
		super();
		this.nombre = nombre;
		this.servicios = servicios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
}
