package ec.com.andresguachisaca.SriConsultas;

public class Establecimiento {

	private String nombreFantasiaComercial;
	private String tipoEstablecimiento;
	private String direccionCompleta;
	private String numeroEstablecimiento;

	public Establecimiento() {
	}

	public Establecimiento(String nombreFantasiaComercial, String tipoEstablecimiento, String direccionCompleta,
			String numeroEstablecimiento) {
		this.nombreFantasiaComercial = nombreFantasiaComercial;
		this.tipoEstablecimiento = tipoEstablecimiento;
		this.direccionCompleta = direccionCompleta;
		this.numeroEstablecimiento = numeroEstablecimiento;
	}

	public String getNombreFantasiaComercial() {
		return nombreFantasiaComercial;
	}

	public void setNombreFantasiaComercial(String nombreFantasiaComercial) {
		this.nombreFantasiaComercial = nombreFantasiaComercial;
	}

	public String getTipoEstablecimiento() {
		return tipoEstablecimiento;
	}

	public void setTipoEstablecimiento(String tipoEstablecimiento) {
		this.tipoEstablecimiento = tipoEstablecimiento;
	}

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	public String getNumeroEstablecimiento() {
		return numeroEstablecimiento;
	}

	public void setNumeroEstablecimiento(String numeroEstablecimiento) {
		this.numeroEstablecimiento = numeroEstablecimiento;
	}

}
