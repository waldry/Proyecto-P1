package logico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Contrato {
	private String id;
	private String user;
	private ArrayList<Plan> planes;
	private Cliente client;
	private String fechaApertura;
	private float subtotal;
	private boolean activo = false;
	
	public Contrato(String id, String user, ArrayList<Plan> planes, Cliente client, String fechaApertura,
			float total, boolean activo) {
		super();
		this.id = id;
		this.user = user;
		this.planes = planes;
		this.client = client;
		this.fechaApertura = fechaApertura;
		this.subtotal = total;
		this.activo = activo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public ArrayList<Plan> getPlanes() {
		return planes;
	}
	public void setPlanes(ArrayList<Plan> planes) {
		this.planes = planes;
	}
	public Cliente getClient() {
		return client;
	}
	public void setClient(Cliente client) {
		this.client = client;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public float getTotal() {
		return subtotal;
	}
	public void setTotal(float total) {
		this.subtotal = total;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
