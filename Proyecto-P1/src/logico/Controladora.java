package logico;

import java.util.ArrayList;
import java.util.Date;

public class Controladora {
	private static Controladora cont = null;
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	private ArrayList<Personal> empleados;
	private ArrayList<Plan> planes;
	private Personal loggedUser;
	private Date fecha;
	
	public Controladora() {
		super();
		this.clientes = new ArrayList<Cliente>();
		this.facturas = new ArrayList<Factura>();
	}
	
	public static Controladora getInstance() {
		if(cont==null) {
			cont = new Controladora();
		}
		return cont;
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
	
	public void registrarCliente(Cliente client) {
		clientes.add(client);
	}
	public void agregarFactura(Factura fact) {
		facturas.add(fact);
	}
	public void registrarUsuario(Personal user) {
		empleados.add(user);
	}
	
	public Cliente findClienteById(String cedulaCliente) {
		Cliente client = null;
		boolean found = false;
		int i = 0;
		while(!found && i<clientes.size()) {
			if(clientes.get(i).getCedula().equalsIgnoreCase(cedulaCliente)) {
				client = clientes.get(i);
				found = true;
			}
			i++;
		}
		return client;
	}
	
	public Factura findFacturaByCodigo(String idFactura) {
		Factura aux = null;
		boolean found = false;
		int i = 0;
		while(!found && i<facturas.size()) {
			if(facturas.get(i).getId().equalsIgnoreCase(idFactura)) {
				aux = facturas.get(i);
				found = true;
			}
			i++;
		}
		return aux;
	}
	
	public boolean confirmLogin(String username, String pass) {
		boolean login = false;
		for (Personal empleado : empleados) {
			if(empleado.getNombre().equals(username) && empleado.getPass().equals(pass)) {
				empleado = loggedUser;
				login = true;
			}
		}
		return login;
	}

	public void agregarPlan(Plan aux) {
		planes.add(aux);
	}
	
	public void eliminarCliente(Cliente cliente) {
		clientes.remove(cliente);
	}
	
}
