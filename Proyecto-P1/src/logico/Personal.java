package logico;

import java.util.Date;

public class Personal {
	private String nombre;
	private String apellido;
	private String oficina;
	private String tipo;
	private String pass;
	private Date lastLogin;
	
	public Personal(String nombre, String apellido, String oficina, String tipo, String pass) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.oficina = oficina;
		this.tipo = tipo;
		this.pass = pass;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
