package ec.com.andresguachisaca.SriConsultas;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class App {

	private static final String FILEINPUT = "entrada.txt";
	private static final String FILEINPUTCEDULAS = "entradaCedulas.txt";
	private static final String URL = "https://declaraciones.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/obtenerPorNumerosRuc?=&ruc=";
	private static final String URLESTABLECIMIENTOS = "https://declaraciones.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/Establecimiento/consultarPorNumeroRuc?numeroRuc=";
	private static final String URL_REGISTROCIVIL_SRI = "https://declaraciones.sri.gob.ec/sri-registro-civil-servicio-internet/rest/DatosRegistroCivil/obtenerPorNumeroIdentificacion?numeroIdentificacion=";

	// private static final String URLEXISTERUC =
	// "https://declaraciones.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/existePorNumeroRuc?numeroRuc=0791793041001";

	public static void main(String[] args) {
		try {
			escribir("", true);
			List<String> listRuc = getListEntrada(FILEINPUT);
			List<Contribuyente> listContribuyente = new ArrayList<Contribuyente>();
			listRuc.forEach(ruc -> {
				if (ruc.length() == 13) {
					Response response;
					try {
						response = getResponse(URL + ruc);
						if (response.code() == 200) {
							String stringJson = response.body().string();
							Contribuyente contribuyente = convertStringJsonToContribuyente(stringJson).get(0);
							response = getResponse(URLESTABLECIMIENTOS + ruc);
							contribuyente
									.setEstablecimiento(convertStringJsonToEstablecimiento(response.body().string()));
							listContribuyente.add(contribuyente);
						} else {
							escribir("El ruc " + ruc + " no existe", false);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					escribir("El ruc " + ruc + " no existe", false);
				}
			});
			Desktop.getDesktop().open(new File(exportarExcelContribuyentes(listContribuyente)));
			Desktop.getDesktop().open(new File(exportarExcelPersonas(getNombresPorCedula())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Persona> getNombresPorCedula() throws FileNotFoundException, IOException {
		List<String> listCedulas = getListEntrada(FILEINPUTCEDULAS);
		List<Persona> listPersona = new ArrayList<Persona>();
		listCedulas.forEach(cedula -> {
			if (cedula.length() == 10) {
				Response response;
				try {
					response = getResponse(URL_REGISTROCIVIL_SRI + cedula);
					if (response.code() == 200) {
						String stringJson = response.body().string();
						System.out.println("persona " + stringJson);
						Persona persona = convertStringJsonToPersona(stringJson);
						listPersona.add(persona);
					} else {
						escribir("La cedula " + cedula + " no existe", false);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				escribir("La cedula " + cedula + " no existe", false);
			}
		});
		return listPersona;
	}

	public static Response getResponse(String url) throws Exception {
		Response response = null;
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).get().addHeader("cache-control", "no-cache").build();
		response = client.newCall(request).execute();
		return response;
	}

	public static List<Contribuyente> convertStringJsonToContribuyente(String data) {
		Gson gson = new Gson();
		List<Contribuyente> list = gson.fromJson(data, new TypeToken<List<Contribuyente>>() {
		}.getType());
		return list;
	}

	public static Persona convertStringJsonToPersona(String jsonInString) {
		Gson gson = new Gson();
		Persona persona = gson.fromJson(jsonInString, Persona.class);
		return persona;
	}

	public static Establecimiento convertStringJsonToEstablecimiento(String data) {
		Gson gson = new Gson();
		List<Establecimiento> list = gson.fromJson(data, new TypeToken<List<Establecimiento>>() {
		}.getType());
		return list.get(0);
	}

	public static List<String> getListEntrada(String archivo) throws FileNotFoundException, IOException {
		String cadena;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		List<String> listRuc = new ArrayList<String>();
		while ((cadena = b.readLine()) != null) {
			listRuc.add(cadena);
		}
		b.close();
		return listRuc;
	}

	public static void escribir(String data, boolean limpiar) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("errores.txt", !limpiar);
			pw = new PrintWriter(fichero);
			pw.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static String exportarExcelContribuyentes(List<Contribuyente> listContribuyente) {
		List<String> listaCabecera = new ArrayList<String>();
		List<String> listaCuerpo = new ArrayList<String>();

		listaCabecera.add("SListado de Ruc");
		listaCabecera.add("S");
		listaCuerpo.add("SRuc" + "¬" + "SRazon Social" + "¬" + "SNombre Comercial" + "¬" + "SClase" + "¬"
				+ "SContabilidad" + "¬" + "STipo" + "¬" + "SDireccion" + "¬" + "SActividad");

		for (Contribuyente per : listContribuyente) {
			listaCuerpo.add((per.getNumeroRuc() == null ? "S" : "S" + per.getNumeroRuc().toString()) + "¬"
					+ (per.getRazonSocial() == null ? "S" : "S" + per.getRazonSocial().toString()) + "¬"
					+ (per.getNombreComercial() == null ? "S" : "S" + per.getNombreComercial().toString()) + "¬"
					+ (per.getClaseContribuyente() == null ? "S" : "S" + per.getClaseContribuyente().toString()) + "¬"
					+ (per.getObligado() == null ? "S" : "S" + per.getObligado().toString()) + "¬"
					+ (per.getSubtipoContribuyente() == null ? "S" : "S" + per.getSubtipoContribuyente().toString())
					+ "¬"
					+ (per.getEstablecimiento().getDireccionCompleta() == null ? "S"
							: "S" + per.getEstablecimiento().getDireccionCompleta().toString())
					+ "¬" + (per.getActividadContribuyente() == null ? "S"
							: "S" + per.getActividadContribuyente().toString()));
		}
		return UtilsExcel.crearExcel(listaCabecera, listaCuerpo, "OroCodigo Cia. Ltda.", "Contribuyentes");
	}

	public static String exportarExcelPersonas(List<Persona> listPersona) {
		List<String> listaCabecera = new ArrayList<String>();
		List<String> listaCuerpo = new ArrayList<String>();

		listaCabecera.add("SListado de Cedulas");
		listaCabecera.add("S");
		listaCuerpo.add("SIdentificacion" + "¬" + "SNombres");

		for (Persona per : listPersona) {
			listaCuerpo.add((per.getIdentificacion() == null ? "S" : "S" + per.getIdentificacion().toString()) + "¬"
					+ "¬" + (per.getNombreCompleto() == null ? "S" : "S" + per.getNombreCompleto().toString()));
		}
		return UtilsExcel.crearExcel(listaCabecera, listaCuerpo, "OroCodigo Cia. Ltda.", "Personas");
	}
}
