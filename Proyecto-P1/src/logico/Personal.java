package logico;

import java.util.Date;

public class Personal {
	private String nombre;
	private String apellido;
	private String oficina;
	private String tipo;
	private String username;
	private String pass;
	private Date lastLogin;
	
	public Personal(String nombre, String apellido, String oficina, String tipo, String username, String pass, Date lastLogin) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.oficina = oficina;
		this.tipo = tipo;
		this.username = username;
		this.pass = pass;
		this.lastLogin = lastLogin;
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

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
