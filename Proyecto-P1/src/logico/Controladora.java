package logico;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

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
	private static int genCodContrato;
	private static int genCodPlan;
	private static Timer timer = new Timer();
	
	public int getGenCodPlan() {
		return genCodPlan;
	}

	public static void setGenCodPlan(int genCodPlan) {
		Controladora.genCodPlan = genCodPlan;
	}

	private Controladora() {
		super();
		this.clientes = new ArrayList<Cliente>();
		this.facturas = new ArrayList<Factura>();
		this.planes = new ArrayList<Plan>();
		this.empleados = new ArrayList<Personal>();
		this.contratos = new ArrayList<Contrato>();
		genCodPlan = 1;
		genCodContrato = 1;
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
		genCodContrato++;
	}
	public void eliminarContrato(Contrato contract) {
		contratos.remove(contract);
		genCodContrato--;
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
	public Plan findPlanByID(String id) {
		Plan service = null;
		boolean found = false;
		int i = 0;
		while (!found && i<planes.size()) {
			if (planes.get(i).getId().equalsIgnoreCase(id)) {
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
				setLoggedUser(empleado);
				login = true;
			}
		}
		return login;
	}

	public void agregarPlan(Plan aux) {
		planes.add(aux);
		genCodPlan++;
	}
	public void eliminarPlan(Plan aux) {
		planes.remove(aux);
		genCodPlan--;
	}
	
	public void eliminarCliente(Cliente cliente) {
		clientes.remove(cliente);
	}
	public int getGenCodContrato() {
		return genCodContrato;
	}

	public void setGenCodContrato(int genCodContrato) {
		Controladora.genCodContrato = genCodContrato;
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

	public void empezarFacturar() {
		TimerTask tarea = new TimerTask() {
			
			@Override
			public void run() {
				for (Contrato contract : contratos) {
					if (contract.isActivo()) {
						Factura aux = new Factura(contract.getId(), contract.getClient(),contract.getPlanes(), contract.getFechaApertura(), contract.getTotal(), contract.isActivo());
						facturas.add(aux);
					}
				}
				for (Factura item: facturas) {
					Calendar inicio = new GregorianCalendar();
					Calendar fin = new GregorianCalendar();
					Date fechaActual = new Date();
					DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					String hoy = formato.format(fechaActual);
					try {
						inicio.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(item.getFecha()));
						fin.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(hoy));
						int diffYear = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
						int diffM = diffYear * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
						float cargo = diffM*(float) (item.getMonto()*0.0314);
						item.setMonto(cargo+item.getMonto());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		};
		timer.schedule(tarea,0,2592000);
	}
	
}
