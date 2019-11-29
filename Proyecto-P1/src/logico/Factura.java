package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Cliente cliente;
	private ArrayList<Servicio> services;
	private Date fecha;
	private Plan planCliente;
	private boolean activa;
	private float monto;
	
	
	public Factura(String id, Cliente cliente, Plan planCliente, Date fecha, float monto, ArrayList<Servicio> services, boolean activa) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.planCliente = planCliente;
		this.fecha = fecha;
		this.monto = monto;
		this.services = services;
		this.activa = activa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public ArrayList<Servicio> getServices() {
		return services;
	}

	public void setServices(ArrayList<Servicio> services) {
		this.services = services;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Plan getPlanCliente() {
		return planCliente;
	}

	public void setPlanCliente(Plan planCliente) {
		this.planCliente = planCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public float cotizar() {
		float total = 0;
		for (Servicio servicio : services) {
			total += servicio.costoServicio();
		}
		for (Servicio servicioPlan : planCliente.getServicios()) {
			total += servicioPlan.costoServicio();
		}
		return total;
	}
}
