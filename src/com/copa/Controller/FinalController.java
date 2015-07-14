package com.copa.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.copa.DAO.SemiFinalDAO;
import com.copa.Model.SemiFinal;
import com.copa.utils.WS;

public class FinalController extends WS {

	public FinalController(String url) {
		super(url);
	}

	private List<SemiFinal> CollectionFinal;
	
	
	public void updateFinal() throws Exception {
		CollectionFinal = new ArrayList<SemiFinal>();
		Execute(0, null, 0);
		String json = getResponse();

		if (json != null) {
			CollectionFinal = converteParaObjeto(json);
		}
		SemiFinalDAO.getInstance().updateFinal(CollectionFinal);
	}
	
	public void setSemi() throws Exception {
		CollectionFinal = new ArrayList<SemiFinal>();
		String json;

		json = converteParaJson(SemiFinalDAO.getInstance().getAllFinal());
		if (json != null) {
			Execute(1, json, 0);
		}
	}
    
    public List<SemiFinal> converteParaObjeto(String json) throws JsonMappingException, IOException, JSONException{
		CollectionFinal = new ArrayList<SemiFinal>();
		String inArray = null;

		// Paasando parametro string para ObjetoJson
		JSONObject JObjeto = new JSONObject(json);
		// Passando Array de Json para JSONArray
		JSONArray objetos = JObjeto.getJSONArray("final");
		int contador = 0;

		while (contador < objetos.length()) {
			// Pega posicao do JsonArray e passa para String clienteInArray
			inArray = objetos.getJSONObject(contador).toString();
			// Objeto ObjectMapper para converter Json em um Objeto cliente
			ObjectMapper jsonMapper = new ObjectMapper();
			SemiFinal semi = jsonMapper.readValue(inArray, SemiFinal.class);
			// add objeto a uma lista
			CollectionFinal.add(semi);
			contador++;
		}
		// retorna lista de Objetos do tipo Cliente
		return CollectionFinal;
	}
	
	public String converteParaJson(List<SemiFinal> list)throws JsonMappingException, IOException, JSONException {
		String json = null;
		JSONObject obj = new JSONObject();
		JSONArray JArray = new JSONArray();
		for (SemiFinal semi : list) {
			ObjectMapper objMapper = new ObjectMapper();
			String converte = objMapper.writeValueAsString(semi);
			JSONObject teste = new JSONObject(converte);
			JArray.put(teste);
		}
		obj.put("final", JArray);
		json = obj.toString();

		return json.toString();
	}
}
