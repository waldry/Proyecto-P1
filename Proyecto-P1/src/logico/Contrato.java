package logico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Contrato {
	private int id;
	private String user;
	private ArrayList<Plan> planes;
	private Cliente client;
	private String fechaApertura;
	private float mora;
	private float total;
	
	public Contrato(int id, String user, ArrayList<Plan> planes, Cliente client, String fechaApertura, float mora,
			float total) {
		super();
		this.id = id;
		this.user = user;
		this.planes = planes;
		this.client = client;
		this.fechaApertura = fechaApertura;
		this.mora = mora;
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public float getMora() {
		return mora;
	}
	public void setMora(float mora) {
		this.mora = mora;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
