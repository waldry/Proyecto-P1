package logico;

import java.util.ArrayList;
import java.util.Date;

public class Controladora {
	private static Controladora cont = null;
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	private Date fecha;
	
	public Controladora() {
		super();
		this.clientes = new ArrayList<Cliente>();
		this.facturas = new ArrayList<Factura>();
	}
	
	public Controladora getInstance() {
		if(cont==null) {
			cont = new Controladora();
		}
		return cont;
	}
	
}
