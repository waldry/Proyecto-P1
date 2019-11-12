package logico;

public class Cable extends Servicio {
	private int cantCanales;

	public Cable(String nombre, float costo, int cantCanales) {
		super(nombre, costo);
		this.cantCanales = cantCanales;
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
	
}
