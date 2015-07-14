package com.copa.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.copa.DAO.SelecoesDAO;
import com.copa.Model.Selecoes;
import com.copa.utils.WS;

public class SelecoesController extends WS{

	private List<Selecoes> collectionSelecoes;
	
	public SelecoesController(String url) {
		super(url);
	}
	
	public void updateSelecoes() throws JsonMappingException, IOException, JSONException {
		collectionSelecoes = new ArrayList<Selecoes>();
		Execute(0, null, 0);
		String json = getResponse();
		if (json != null) {
			collectionSelecoes = converteParaObjeto(json);
		}
		SelecoesDAO.getInstance().updateSelecoes(collectionSelecoes);
	}
	
	public void setSelecoes() throws JsonMappingException, IOException, JSONException {
		collectionSelecoes = new ArrayList<Selecoes>();
		String json;
		json = converteParaJson(SelecoesDAO.getInstance().AllSelecoes());
		if (json != null) {
			Execute(1, json, 0);
		}
	}
    
	public List<Selecoes> converteParaObjeto(String json) throws JsonMappingException, IOException, JSONException {
		collectionSelecoes = new ArrayList<Selecoes>();
		String inArray = null;
		// Paasando parametro string para ObjetoJson
		JSONObject JObjeto = new JSONObject(json);
		// Passando Array de Json para JSONArray
		JSONArray objetos = JObjeto.getJSONArray("selecoes");
		int contador = 0;

		while (contador < objetos.length()) {
			// Pega posicao do JsonArray e passa para String clienteInArray
			inArray = objetos.getJSONObject(contador).toString();
			// Objeto ObjectMapper para converter Json em um Objeto cliente
			ObjectMapper jsonMapper = new ObjectMapper();
			Selecoes selecao = jsonMapper.readValue(inArray, Selecoes.class);
			// add objeto a uma lista
			collectionSelecoes.add(selecao);
			contador++;
		}
		// retorna lista de Objetos
		return collectionSelecoes;
	}
	
	public String converteParaJson(List<Selecoes> list) throws JsonMappingException, IOException, JSONException {
		String json = null;
		JSONObject obj = new JSONObject();
		JSONArray JArray = new JSONArray();
		for (Selecoes selecoes : list) {
			ObjectMapper objMapper = new ObjectMapper();
			String converte = objMapper.writeValueAsString(selecoes);
			JSONObject teste = new JSONObject(converte);
			JArray.put(teste);
		}
		obj.put("selecoes", JArray);
		json = obj.toString();

		return json.toString();
	}
}
