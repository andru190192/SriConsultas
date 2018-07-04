package ec.com.andresguachisaca.SriConsultas;

public class Persona {

	private String identificacion;
	private String nombreCompleto;

	public Persona() {
	}

	public Persona(String identificacion, String nombreCompleto) {
		this.identificacion = identificacion;
		this.nombreCompleto = nombreCompleto;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}
