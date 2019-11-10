package logico;

import java.util.ArrayList;
import java.util.Date;

public class HistorialPago {
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	private Date fecha;
	private static HistorialPago historiaPago = null;
	
	private HistorialPago(ArrayList<Factura> facturas, ArrayList<Cliente> clientes, Date fecha) {
		super();
		this.facturas = facturas;
		this.clientes = clientes;
		this.fecha = fecha;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public HistorialPago getInstance() {
		if(historiaPago==null) {
			historiaPago = new HistorialPago(null,null,null);
		}
		return historiaPago;
	}
}
