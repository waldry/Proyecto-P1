package logico;

import java.io.Serializable;

public class Internet extends Servicio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int anchoBandaDescarga;
	private int anchoBandaSubida;
	private String name;
	
	public Internet(String nombre, int anchoBandaDescarga, int anchoBandaSubida) {
		super(nombre);
		this.name = nombre;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
