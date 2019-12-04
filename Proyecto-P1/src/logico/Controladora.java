package logico;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Controladora implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Controladora cont = null;
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	private ArrayList<Personal> empleados;
	private ArrayList<Plan> planes;
	private ArrayList<Contrato> contratos;
	private Personal loggedUser;
	private Date fecha;
	private static int cantPlan;
	
	private Controladora() {
		super();
		this.clientes = new ArrayList<Cliente>();
		this.facturas = new ArrayList<Factura>();
		this.planes = new ArrayList<Plan>();
		this.empleados = new ArrayList<Personal>();
	}
	
	public static Controladora getInstance() {
		if(cont==null) {
			cont = new Controladora();
		}
		return cont;
	}
	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
	public static Controladora getCont() {
		return cont;
	}

	public static void setCont(Controladora cont) {
		Controladora.cont = cont;
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
	
	public ArrayList<Plan> getPlanes() {
		return planes;
	}

	public void setPlanes(ArrayList<Plan> planes) {
		this.planes = planes;
	}

	public void registrarCliente(Cliente client) {
		clientes.add(client);
	}
	public void agregarFactura(Factura fact) {
		facturas.add(fact);
	}
	public void agregarContrato(Contrato contrat) {
		contratos.add(contrat);
	}
	public void registrarUsuario(Personal user) {
		empleados.add(user);
	}
	public void insertarPlan(Plan plan) {
//		planes.get(cantPlan) = plan;
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
	public Plan findPlanByName(String name) {
		Plan service = null;
		boolean found = false;
		int i = 0;
		while (!found && i<planes.size()) {
			if (planes.get(i).getNombre().equals(name)) {
				service = planes.get(i);
				found = true;
			}
		}
		return service;
	}
	public Personal findPersonalByUsername(String username) {
		Personal user = null;
		boolean found = false;
		int i = 0;
		while(!found && i<empleados.size()) {
			if(empleados.get(i).getUsername().equals(username)) {
				user = empleados.get(i);
				found = true;
			}
			i++;
		}
		return user;
	}
	private int indexBycodigo(String codigo) {
		boolean encontrado = false;
		int i = 0;
		int index = -1;
		while (!encontrado && i< planes.size()) {
			if(planes.get(i).getNombre().equals(codigo)) {
				encontrado = true;
				index = i;
				
			}
			i++;
		}
		return index;
	}

	public void eliminarPlan(String identify) {
		int index = -1;
		int i = 0;
		Object[] misPlanes = planes.toArray();
		if (indexBycodigo(identify)>-1) {
			index = indexBycodigo(identify);
			i = index;
				while (i<misPlanes.length-1) {
					misPlanes[i] = misPlanes[i+1];
					i++;
				}
				
		}
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
			if(empleado.getUsername().equals(username) && empleado.getPass().equals(pass)) {
				empleado = loggedUser;
				login = true;
			}
		}
		return login;
	}

	public void agregarPlan(Plan aux) {
		planes.add(aux);
	}
	public void eliminarPlan(Plan aux) {
		planes.remove(aux);
	}
	
	public void eliminarCliente(Cliente cliente) {
		clientes.remove(cliente);
	}
	public void eliminarUsuario(Personal user) {
		empleados.remove(user);
	}

	public ArrayList<Personal> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Personal> empleados) {
		this.empleados = empleados;
	}
	
	public String fechaActual() {
		String formatFecha = "dd/MM/yyyy";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatFecha);
		LocalDateTime ahora = LocalDateTime.now();
		return formato.format(ahora);
	}

	public Personal getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Personal loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void deletePlan(String nombre) {
		for (Plan planToModify : planes) {
			if (planToModify.getNombre().equalsIgnoreCase(nombre)) {
				planes.remove(planToModify);
			}
		}
	}
	
}
