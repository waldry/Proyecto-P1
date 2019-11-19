package logico;

public class Cable extends Servicio {
	private int cantCanales;
	private boolean hbo = false;
	private boolean adultos = false;
	private boolean deportes = false;

	public Cable(String nombre, int cantCanales, boolean hbo, boolean adultos, boolean deportes) {
		super(nombre);
		this.cantCanales = cantCanales;
		this.hbo = hbo;
		this.adultos = adultos;
		this.deportes = deportes;
	}
	@Override
	public float costoServicio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCantCanales() {
		return cantCanales;
	}

	public void setCantCanales(int cantCanales) {
		this.cantCanales = cantCanales;
	}

	public boolean isHbo() {
		return hbo;
	}

	public void setHbo(boolean hbo) {
		this.hbo = hbo;
	}

	public boolean isAdultos() {
		return adultos;
	}

	public void setAdultos(boolean adultos) {
		this.adultos = adultos;
	}

	public boolean isDeportes() {
		return deportes;
	}

	public void setDeportes(boolean deportes) {
		this.deportes = deportes;
	}
	
}
