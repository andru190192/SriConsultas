package ec.com.andresguachisaca.SriConsultas;

public class InformacionFechasContribuyente {
	private String fechaInicioActividades;
	private String fechaCese;
	private String fechaReinicioActividades;
	private String fechaActualizacion;

	public InformacionFechasContribuyente() {
	}

	public InformacionFechasContribuyente(String fechaInicioActividades, String fechaCese,
			String fechaReinicioActividades, String fechaActualizacion) {
		this.fechaInicioActividades = fechaInicioActividades;
		this.fechaCese = fechaCese;
		this.fechaReinicioActividades = fechaReinicioActividades;
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getFechaInicioActividades() {
		return fechaInicioActividades;
	}

	public void setFechaInicioActividades(String fechaInicioActividades) {
		this.fechaInicioActividades = fechaInicioActividades;
	}

	public String getFechaCese() {
		return fechaCese;
	}

	public void setFechaCese(String fechaCese) {
		this.fechaCese = fechaCese;
	}

	public String getFechaReinicioActividades() {
		return fechaReinicioActividades;
	}

	public void setFechaReinicioActividades(String fechaReinicioActividades) {
		this.fechaReinicioActividades = fechaReinicioActividades;
	}

	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}
