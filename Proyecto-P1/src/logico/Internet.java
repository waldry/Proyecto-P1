package logico;

public class Internet extends Servicio {
	private int anchoBandaDescarga;
	private int anchoBandaSubida;
	
	public Internet(String nombre, float costo, int anchoBandaDescarga, int anchoBandaSubida) {
		super(nombre, costo);
		this.anchoBandaDescarga = anchoBandaDescarga;
		this.anchoBandaSubida = anchoBandaSubida;
	}

	public int getAnchoBandaDescarga() {
		return anchoBandaDescarga;
	}

	public void setAnchoBandaDescarga(int anchoBandaDescarga) {
		this.anchoBandaDescarga = anchoBandaDescarga;
	}

	public int getAnchoBandaSubida() {
		return anchoBandaSubida;
	}

	public void setAnchoBandaSubida(int anchoBandaSubida) {
		this.anchoBandaSubida = anchoBandaSubida;
	}

	@Override
	public float costoServicio() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
