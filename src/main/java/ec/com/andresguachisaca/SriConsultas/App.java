package ec.com.andresguachisaca.SriConsultas;

import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class App {
	public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url("https://declaraciones.sri.gob.ec/sri-catastro-sujeto-servicio-internet/rest/ConsolidadoContribuyente/obtenerPorNumerosRuc?=&ruc=0791793041001")
				.get().addHeader("cache-control", "no-cache").build();
		try {
			Response response = client.newCall(request).execute();
			// System.out.println(response.body().string());
			String stringJson = response.body().string();
			System.out.println(stringJson.substring(1, stringJson.length() - 1));

			Gson g = new Gson();
			System.out.println(g.fromJson(stringJson.substring(1, stringJson.length() - 1), Object.class));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
