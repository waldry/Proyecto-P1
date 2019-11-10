package logico;

import java.util.ArrayList;
import java.util.Date;

public class HistorialPago {
	private static ArrayList<Factura> facturas;
	private static ArrayList<Cliente> clientes;
	private static Date fecha;
	private static HistorialPago historiaPago = null;
	
	private HistorialPago() {
		super();
	}

	public HistorialPago getInstance() {
		if(historiaPago==null) {
			historiaPago = new HistorialPago();
		}
		return historiaPago;
	}

	public static ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public static void setFacturas(ArrayList<Factura> facturas) {
		HistorialPago.facturas = facturas;
	}

	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(ArrayList<Cliente> clientes) {
		HistorialPago.clientes = clientes;
	}

	public static Date getFecha() {
		return fecha;
	}

	public static void setFecha(Date fecha) {
		HistorialPago.fecha = fecha;
	}

	public static HistorialPago getHistoriaPago() {
		return historiaPago;
	}

	public static void setHistoriaPago(HistorialPago historiaPago) {
		HistorialPago.historiaPago = historiaPago;
	}
}
