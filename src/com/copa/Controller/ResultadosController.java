package com.copa.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

import com.copa.DAO.ResultadosDAO;
import com.copa.Model.Resultados;
import com.copa.utils.Utils;
import com.copa.utils.WS;

public class ResultadosController extends WS {

	private List<Resultados> collectionResultados;
	
	public ResultadosController(String url) {
		super(url);
	}
	
	public void updateResultados() throws JsonMappingException, IOException, JSONException {
		collectionResultados = new ArrayList<Resultados>();
		Execute(0, null, 0);
		String json = getResponse();
		if (json != null) {
			collectionResultados = converteParaObjeto(json);
		}
		ResultadosDAO.getInstance().updateResultados(collectionResultados);
	}
	
	public void updateResulGrupo(String grupo) throws JsonMappingException, IOException, JSONException {
		collectionResultados = new ArrayList<Resultados>();
		long _id = getGrupo(grupo);
		Execute(4, null, _id);
		String json = getResponse();
		if (json != null) {
			collectionResultados = converteParaObjeto(json);
		}
		ResultadosDAO.getInstance().updateResultados(collectionResultados);
	}
	
	public void setResultados() throws Exception {
		collectionResultados = new ArrayList<Resultados>();
		String json;
		json = converteParaJson(ResultadosDAO.getInstance().AllResultados());
		if (json != null) {
			Execute(1, json, 0);
		}
	}	
    
	public List<Resultados> converteParaObjeto(String json) throws JsonMappingException, IOException, JSONException {
		collectionResultados = new ArrayList<Resultados>();
		String inArray = null;
		// Paasando parametro string para ObjetoJson
		JSONObject JObjeto = new JSONObject(json);
		// Passando Array de Json para JSONArray
		JSONArray objetos = JObjeto.getJSONArray("resultados");
		int contador = 0;

		while (contador < objetos.length()) {
			// Pega posicao do JsonArray e passa para String clienteInArray
			inArray = objetos.getJSONObject(contador).toString();
			// Objeto ObjectMapper para converter Json em um Objeto cliente
			ObjectMapper jsonMapper = new ObjectMapper();
			Resultados resultado = jsonMapper.readValue(inArray,
					Resultados.class);
			// add objeto a uma lista
			collectionResultados.add(resultado);
			contador++;
		}
		// retorna lista de Objetos do tipo Cliente
		return collectionResultados;
	}
	
	public String converteParaJson(List<Resultados> list) throws JsonMappingException, IOException, JSONException {
		String json = null;
		JSONObject obj = new JSONObject();
		JSONArray JArray = new JSONArray();
		for (Resultados resultado : list) {
			ObjectMapper objMapper = new ObjectMapper();
			String converte = objMapper.writeValueAsString(resultado);
			JSONObject teste = new JSONObject(converte);
			JArray.put(teste);
		}
		obj.put("resultado", JArray);
		json = obj.toString();

		return json;
	}
	
	 private long getGrupo(String grupo) {
		 Cursor cursor = null;
		 long _id = -1;
	        try {
	        	 cursor = Utils.db.rawQuery("Select * from Grupo where gruDescricao = ?", new String[] {grupo});
	             if(cursor.moveToNext()){
	            	_id = cursor.getLong(cursor.getColumnIndex("idGrupo"));
	            	
	             }
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				cursor.close();
			}
	        return _id;
	    }

}
