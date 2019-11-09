package logico;

public class Personal {
	private String nombre;
	private String apellido;
	private String oficina;
	private String tipo;
	
	public Personal(String nombre, String apellido, String oficina, String tipo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.oficina = oficina;
		this.tipo = tipo;
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
	
}
