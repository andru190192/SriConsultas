package ec.com.andresguachisaca.SriConsultas;

public class Contribuyente {

	private String numeroRuc;
	private String razonSocial;
	private String nombreComercial;
	private String estadoPersonaNatural;
	private String estadoSociedad;
	private String claseContribuyente;
	private String obligado;
	private String actividadContribuyente;
	private InformacionFechasContribuyente informacionFechasContribuyente;
	private String representanteLegal;
	private String agenteRepresentante;
	private String personaSociedad;
	private String subtipoContribuyente;
	private String motivoCancelacion;
	private String motivoSuspension;
	private Establecimiento establecimiento;

	public Contribuyente() {
	}

	public Contribuyente(String numeroRuc, String razonSocial, String nombreComercial, String estadoPersonaNatural,
			String estadoSociedad, String claseContribuyente, String obligado, String actividadContribuyente,
			InformacionFechasContribuyente informacionFechasContribuyente, String representanteLegal,
			String agenteRepresentante, String personaSociedad, String subtipoContribuyente, String motivoCancelacion,
			String motivoSuspension, Establecimiento establecimiento) {
		this.numeroRuc = numeroRuc;
		this.razonSocial = razonSocial;
		this.nombreComercial = nombreComercial;
		this.estadoPersonaNatural = estadoPersonaNatural;
		this.estadoSociedad = estadoSociedad;
		this.claseContribuyente = claseContribuyente;
		this.obligado = obligado;
		this.actividadContribuyente = actividadContribuyente;
		this.informacionFechasContribuyente = informacionFechasContribuyente;
		this.representanteLegal = representanteLegal;
		this.agenteRepresentante = agenteRepresentante;
		this.personaSociedad = personaSociedad;
		this.subtipoContribuyente = subtipoContribuyente;
		this.motivoCancelacion = motivoCancelacion;
		this.motivoSuspension = motivoSuspension;
		this.setEstablecimiento(establecimiento);
	}

	public String getNumeroRuc() {
		return numeroRuc;
	}

	public void setNumeroRuc(String numeroRuc) {
		this.numeroRuc = numeroRuc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getEstadoPersonaNatural() {
		return estadoPersonaNatural;
	}

	public void setEstadoPersonaNatural(String estadoPersonaNatural) {
		this.estadoPersonaNatural = estadoPersonaNatural;
	}

	public String getEstadoSociedad() {
		return estadoSociedad;
	}

	public void setEstadoSociedad(String estadoSociedad) {
		this.estadoSociedad = estadoSociedad;
	}

	public String getClaseContribuyente() {
		return claseContribuyente;
	}

	public void setClaseContribuyente(String claseContribuyente) {
		this.claseContribuyente = claseContribuyente;
	}

	public String getObligado() {
		return obligado.compareTo("S") == 0 ? "SI" : "NO";
	}

	public void setObligado(String obligado) {
		this.obligado = obligado;
	}

	public String getActividadContribuyente() {
		return actividadContribuyente;
	}

	public void setActividadContribuyente(String actividadContribuyente) {
		this.actividadContribuyente = actividadContribuyente;
	}

	public InformacionFechasContribuyente getInformacionFechasContribuyente() {
		return informacionFechasContribuyente;
	}

	public void setInformacionFechasContribuyente(InformacionFechasContribuyente informacionFechasContribuyente) {
		this.informacionFechasContribuyente = informacionFechasContribuyente;
	}

	public String getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getAgenteRepresentante() {
		return agenteRepresentante;
	}

	public void setAgenteRepresentante(String agenteRepresentante) {
		this.agenteRepresentante = agenteRepresentante;
	}

	public String getPersonaSociedad() {
		return personaSociedad;
	}

	public void setPersonaSociedad(String personaSociedad) {
		this.personaSociedad = personaSociedad;
	}

	public String getSubtipoContribuyente() {
		return subtipoContribuyente;
	}

	public void setSubtipoContribuyente(String subtipoContribuyente) {
		this.subtipoContribuyente = subtipoContribuyente;
	}

	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	public String getMotivoSuspension() {
		return motivoSuspension;
	}

	public void setMotivoSuspension(String motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

}
