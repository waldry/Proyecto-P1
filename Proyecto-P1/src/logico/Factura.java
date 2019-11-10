package logico;

import java.util.Date;

public class Factura {
	private Cliente cliente;
	private Plan planCliente;
	private Date fecha;
	private float monto;
	
	public Factura(Cliente cliente, Plan planCliente, Date fecha, float monto) {
		super();
		this.cliente = cliente;
		this.planCliente = planCliente;
		this.fecha = fecha;
		this.monto = monto;
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
}
