package com.copa.Controller;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.copa.DAO.OitavasDAO;
import com.copa.Model.OitavasFinal;
import com.copa.utils.WS;

public class OitavasController extends WS {

	private List<OitavasFinal> collectionOitavas;
	
	public OitavasController(String url) {
		super(url);
	}
	
	public void updateOitavas() throws JsonMappingException, IOException, JSONException {
		collectionOitavas = new ArrayList<OitavasFinal>();
		Execute(0, null, 0);
		String json = getResponse();
		if (json != null) {
			collectionOitavas = converteParaObjeto(json);
		}
		OitavasDAO.getInstance().setOitavas(collectionOitavas);
	}
	
	public void setOitavas() throws JsonMappingException, IOException, JSONException{
		collectionOitavas = new ArrayList<OitavasFinal>();
		String json;
		json = converteParaJson(OitavasDAO.getInstance().AllOitavas());
		if (json != null) {
			Execute(1, json, 0);
		}
	}
    
	public List<OitavasFinal> converteParaObjeto(String json) throws JsonMappingException, IOException, JSONException {
		collectionOitavas = new ArrayList<OitavasFinal>();
		String inArray = null;
		// Paasando parametro string para ObjetoJson
		JSONObject JObjeto = new JSONObject(json);
		// Passando Array de Json para JSONArray
		JSONArray objetos = JObjeto.getJSONArray("oitavasfinal");
		int contador = 0;

		while (contador < objetos.length()) {
			// Pega posicao do JsonArray e passa para String clienteInArray
			inArray = objetos.getJSONObject(contador).toString();
			// Objeto ObjectMapper para converter Json em um Objeto cliente
			ObjectMapper jsonMapper = new ObjectMapper();
			OitavasFinal oitavas = jsonMapper.readValue(inArray,
					OitavasFinal.class);
			// add objeto a uma lista
			collectionOitavas.add(oitavas);
			contador++;
		}
		// retorna lista de Objetos do tipo Cliente
		return collectionOitavas;
	}
	
	public String converteParaJson(List<OitavasFinal> list) throws JsonMappingException, IOException, JSONException {
		String json = null;
		JSONObject obj = new JSONObject();
		JSONArray JArray = new JSONArray();
		if (list.size() > 0) {
			for (OitavasFinal oitavas : list) {
				ObjectMapper objMapper = new ObjectMapper();
				String converte = objMapper.writeValueAsString(oitavas);
				JSONObject teste = new JSONObject(converte);
				JArray.put(teste);
			}
			obj.put("oitavas", JArray);
			json = obj.toString();
		}
		return json;
	}

}
