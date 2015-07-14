package com.copa.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.copa.Model.Grupo;

public class JSONManager {

	
	public List<Object> converteParaObjeto(String json) throws JsonGenerationException, JsonMappingException, IOException{
		List<Object> objList = new ArrayList<Object>();
		String clienteInArray = null;
		try {
			//Paasando parametro string para ObjetoJson
			JSONObject JObjeto =  new JSONObject(json);
			//Passando Array de Json para JSONArray
			JSONArray objetos = JObjeto.getJSONArray("grupo");
			int contador = 0;
			
			while (contador < objetos.length()) {
				//Pega posicao do JsonArray e passa para String clienteInArray
				clienteInArray = objetos.getJSONObject(contador).toString();
				//Objeto ObjectMapper para converter Json em um Objeto cliente
				ObjectMapper jsonMapper = new ObjectMapper();
				Grupo objeto = jsonMapper.readValue(clienteInArray, Grupo.class);
				//add objeto a uma lista
				objList.add(objeto);
				contador++;
			}
					
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//retorna lista de Objetos do tipo Cliente
		return objList;
	}
}
