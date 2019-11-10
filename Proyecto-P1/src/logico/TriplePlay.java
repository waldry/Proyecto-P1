package logico;

public class TriplePlay extends Servicio {
	private int anchoBandaDescarga;
	private int anchoBandaSubida;
	private int minutosTelefono;
	private int canalesTV;
	
	public TriplePlay(String nombre, float costo, int anchoBandaDescarga, int anchoBandaSubida, int minutosTelefono,
			int canalesTV) {
		super(nombre, costo);
		this.anchoBandaDescarga = anchoBandaDescarga;
		this.anchoBandaSubida = anchoBandaSubida;
		this.minutosTelefono = minutosTelefono;
		this.canalesTV = canalesTV;
	}

	@Override
	public float costoServicio() {
		// TODO Auto-generated method stub
		return 0;
	}

}
