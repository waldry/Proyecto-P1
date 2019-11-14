package logico;

public class Miscelaneos extends Servicio{
	private String detalles;

	public Miscelaneos(String nombre, float costo, String detalles) {
		super(nombre, costo);
		this.detalles = detalles;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	@Override
	public float costoServicio() {
		// TODO Auto-generated method stub
		return 0;
	}

}
