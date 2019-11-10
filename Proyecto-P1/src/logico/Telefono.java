package logico;

public class Telefono extends Servicio {
	private int cantMinutos;
	private boolean ilimitado;
	
	public Telefono(String nombre, float costo, int cantMinutos, boolean ilimitado) {
		super(nombre, costo);
		this.cantMinutos = cantMinutos;
		this.ilimitado = ilimitado;
	}

	public int getCantMinutos() {
		return cantMinutos;
	}

	public void setCantMinutos(int cantMinutos) {
		this.cantMinutos = cantMinutos;
	}

	public boolean isIlimitado() {
		return ilimitado;
	}

	public void setIlimitado(boolean ilimitado) {
		this.ilimitado = ilimitado;
	}

	@Override
	public float costoServicio() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
