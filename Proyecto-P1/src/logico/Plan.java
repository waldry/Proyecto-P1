package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Plan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private int anchoBandaDescarga;
	private int anchoBandaSubida;
	private int cantMinutos;
	private int cantCanales;
	private boolean ilimitado = false;
	private boolean voicemail = false;
	private boolean doblelinea = false;
	private boolean hbo = false;
	private boolean adultos = false;
	private boolean deportes = false;
	private float costo;
	

	public Plan(String id, String nombre, int anchoBandaDescarga, int anchoBandaSubida, int cantMinutos, int cantCanales,
			boolean ilimitado, boolean voicemail, boolean doblelinea, boolean hbo, boolean adultos, boolean deportes,float costo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anchoBandaDescarga = anchoBandaDescarga;
		this.anchoBandaSubida = anchoBandaSubida;
		this.cantMinutos = cantMinutos;
		this.cantCanales = cantCanales;
		this.ilimitado = ilimitado;
		this.voicemail = voicemail;
		this.doblelinea = doblelinea;
		this.hbo = hbo;
		this.adultos = adultos;
		this.deportes = deportes;
		this.costo = costo;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public int getCantMinutos() {
		return cantMinutos;
	}

	public void setCantMinutos(int cantMinutos) {
		this.cantMinutos = cantMinutos;
	}

	public int getCantCanales() {
		return cantCanales;
	}

	public void setCantCanales(int cantCanales) {
		this.cantCanales = cantCanales;
	}

	public boolean isIlimitado() {
		return ilimitado;
	}

	public void setIlimitado(boolean ilimitado) {
		this.ilimitado = ilimitado;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
