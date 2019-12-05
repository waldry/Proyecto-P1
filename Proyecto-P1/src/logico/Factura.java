package logico;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Cliente cliente;
	private String fecha;
	private ArrayList<Plan> planes;
	private boolean vencida = false;
	private float mora;
	private float total;
	
	
	public Factura(String id, Cliente cliente, ArrayList<Plan>planCliente, String fecha, float monto, boolean vencida) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.planes = planCliente;
		this.fecha = fecha;
		this.total = monto;
		this.vencida = vencida;
		this.setMora(0);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public boolean isActiva() {
		return vencida;
	}

	public void setActiva(boolean activa) {
		this.vencida = activa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Plan> getPlanCliente() {
		return planes;
	}

	public void setPlanCliente(ArrayList<Plan> planCliente) {
		this.planes = planCliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getMonto() {
		return total;
	}

	public void setMonto(float monto) {
		this.total = monto;
	}

	public float getMora() {
		return mora;
	}

	public void setMora(float mora) {
		this.mora = mora;
	}
	
//	public float cotizar() {
//		float total = 0;
//		for (Servicio servicio : services) {
//			total += servicio.costoServicio();
//		}
//		for (Servicio servicioPlan : planCliente.getServicios()) {
//			total += servicioPlan.costoServicio();
//		}
//		return total;
//	}
}
