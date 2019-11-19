package logico;

public class Telefono extends Servicio {
	private int cantMinutos;
	private boolean ilimitado = false;
	private boolean voicemail = false;
	private boolean doblelinea = false;
	
	public Telefono(String nombre, int cantMinutos, boolean ilimitado, boolean voicemail, boolean doblelinea) {
		super(nombre);
		this.cantMinutos = cantMinutos;
		this.ilimitado = ilimitado;
		this.voicemail = voicemail;
		this.doblelinea = doblelinea;
	}

	public int getCantMinutos() {
		return cantMinutos;
	}

	public void setCantMinutos(int cantMinutos) {
		this.cantMinutos = cantMinutos;
	}

	public boolean isVoicemail() {
		return voicemail;
	}

	public void setVoicemail(boolean voicemail) {
		this.voicemail = voicemail;
	}

	public boolean isDoblelinea() {
		return doblelinea;
	}

	public void setDoblelinea(boolean doblelinea) {
		this.doblelinea = doblelinea;
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
