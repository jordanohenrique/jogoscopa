package com.copa.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.copa.DAO.QuartasDAO;
import com.copa.Model.QuartasFinal;
import com.copa.utils.WS;

public class QuartasController extends WS{
	
	private List<QuartasFinal> collectionQuartas;

	public QuartasController(String url) {
		super(url);
	}
	
	public void updateQuartas() throws JsonMappingException, IOException, JSONException {
		collectionQuartas = new ArrayList<QuartasFinal>();
		Execute(0, null, 0);
		String json = getResponse();

		if (json != null) {
			collectionQuartas = converteParaObjeto(json);
		}
		QuartasDAO.getInstance().setQuartas(collectionQuartas);
	}
	
	public void setQuartas() throws JsonMappingException, IOException, JSONException {
		collectionQuartas = new ArrayList<QuartasFinal>();
		String json;
		json = converteParaJson(QuartasDAO.getInstance().AllQuartas());
		if (json != null) {
			Execute(1, json, 0);
		}
	}
    
	public List<QuartasFinal> converteParaObjeto(String json) throws JsonMappingException, IOException, JSONException {
		collectionQuartas = new ArrayList<QuartasFinal>();
		String inArray = null;
		// Paasando parametro string para ObjetoJson
		JSONObject JObjeto = new JSONObject(json);
		// Passando Array de Json para JSONArray
		JSONArray objetos = JObjeto.getJSONArray("quartasfinal");
		int contador = 0;

		while (contador < objetos.length()) {
			// Pega posicao do JsonArray e passa para String clienteInArray
			inArray = objetos.getJSONObject(contador).toString();
			// Objeto ObjectMapper para converter Json em um Objeto cliente
			ObjectMapper jsonMapper = new ObjectMapper();
			QuartasFinal quartas = jsonMapper.readValue(inArray,
					QuartasFinal.class);
			// add objeto a uma lista
			collectionQuartas.add(quartas);
			contador++;
		}
		// retorna lista de Objetos do tipo Cliente
		return collectionQuartas;
	}
	
	public String converteParaJson(List<QuartasFinal> list) throws JsonMappingException, IOException, JSONException {
		String json = null;
		JSONObject obj = new JSONObject();
		JSONArray JArray = new JSONArray();
		if (list.size() > 0) {
			for (QuartasFinal quartas : list) {
				ObjectMapper objMapper = new ObjectMapper();
				String converte = objMapper.writeValueAsString(quartas);
				JSONObject teste = new JSONObject(converte);
				JArray.put(teste);
			}
			obj.put("quartas", JArray);
			json = obj.toString();
		}
		return json;
	}

}
