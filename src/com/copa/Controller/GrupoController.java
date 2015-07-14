package com.copa.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.copa.DAO.GrupoDAO;
import com.copa.Model.Grupo;
import com.copa.utils.WS;

public class GrupoController extends WS {

	private List<Grupo> collectionGrupo;
	
	public GrupoController(String url) {
		super(url);	
	}
	
	public void updateGrupos() throws Exception {
		collectionGrupo = new ArrayList<Grupo>();
		Execute(0, null, 0);
		String json = getResponse();

		if (json != null) {
			collectionGrupo = converteParaObjeto(json);
		}
		GrupoDAO.getIstance().updateGrupo(collectionGrupo);
	}
	
	public void getGrupos() throws Exception {
		collectionGrupo = new ArrayList<Grupo>();
		String json;

		json = converteParaJson(GrupoDAO.getIstance().AllGrupos());
		if (json != null) {
			Execute(1, json, 0);
		}
	}
    
    public List<Grupo> converteParaObjeto(String json) throws JsonMappingException, IOException, JSONException{
		collectionGrupo = new ArrayList<Grupo>();
		String inArray = null;

		// Paasando parametro string para ObjetoJson
		JSONObject JObjeto = new JSONObject(json);
		// Passando Array de Json para JSONArray
		JSONArray objetos = JObjeto.getJSONArray("grupo");
		int contador = 0;

		while (contador < objetos.length()) {
			// Pega posicao do JsonArray e passa para String clienteInArray
			inArray = objetos.getJSONObject(contador).toString();
			// Objeto ObjectMapper para converter Json em um Objeto cliente
			ObjectMapper jsonMapper = new ObjectMapper();
			Grupo grupo = jsonMapper.readValue(inArray, Grupo.class);
			// add objeto a uma lista
			collectionGrupo.add(grupo);
			contador++;
		}
		// retorna lista de Objetos do tipo Cliente
		return collectionGrupo;
	}
	
	public String converteParaJson(List<Grupo> list)throws JsonMappingException, IOException, JSONException {
		String json = null;
		JSONObject obj = new JSONObject();
		JSONArray JArray = new JSONArray();
		for (Grupo grupo : list) {
			ObjectMapper objMapper = new ObjectMapper();
			String converte = objMapper.writeValueAsString(grupo);
			JSONObject teste = new JSONObject(converte);
			JArray.put(teste);
		}
		obj.put("grupo", JArray);
		json = obj.toString();

		return json.toString();
	}

}
