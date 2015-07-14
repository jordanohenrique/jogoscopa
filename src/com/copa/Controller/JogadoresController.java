package com.copa.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.copa.DAO.JogadoresDAO;
import com.copa.Model.Jogadores;
import com.copa.jogoscopa.R;
import com.copa.utils.Utils;
import com.copa.utils.WS;

public class JogadoresController extends WS {

	private List<Jogadores> collectionJogadores;
	
	public JogadoresController(String url) {
		super(url);
	}
	
	public void updateJogadores() throws JsonMappingException, IOException, JSONException{
		collectionJogadores = new ArrayList<Jogadores>();
		Execute(0, null, 0);
		String json = getResponse();
		collectionJogadores = converteParaObjeto(json);
		JogadoresDAO.getInstance().setJogadores(collectionJogadores);
		
	}
	
	public void setJogadores() throws JsonMappingException, IOException, JSONException {
		collectionJogadores = new ArrayList<Jogadores>();
		String json;
		json = converteParaJson(JogadoresDAO.getInstance().AllJogadores());
		if (json != null) {
			Execute(1, json, 0);
		}
		else{
			;
		}
	}
    
	public List<Jogadores> converteParaObjeto(String json) throws JsonMappingException, IOException, JSONException {
		collectionJogadores = new ArrayList<Jogadores>();
		String inArray = null;
		// Paasando parametro string para ObjetoJson
		JSONObject JObjeto = new JSONObject(json);
		// Passando Array de Json para JSONArray
		JSONArray objetos = JObjeto.getJSONArray("jogadores");
		int contador = 0;

		while (contador < objetos.length()) {
			// Pega posicao do JsonArray e passa para String clienteInArray
			inArray = objetos.getJSONObject(contador).toString();
			// Objeto ObjectMapper para converter Json em um Objeto cliente
			ObjectMapper jsonMapper = new ObjectMapper();
			Jogadores jogador = jsonMapper.readValue(inArray, Jogadores.class);
			// add objeto a uma lista
			collectionJogadores.add(jogador);
			contador++;
		}
		// retorna lista de Objetos do tipo Cliente
		return collectionJogadores;
	}
	
	public String converteParaJson(List<Jogadores> list) throws JsonMappingException, IOException, JSONException {
		String json = null;
		JSONObject obj = new JSONObject();
		JSONArray JArray = new JSONArray();
		if (list.size() > 0) {
			for (Jogadores jogador : list) {
				ObjectMapper objMapper = new ObjectMapper();
				String converte = objMapper.writeValueAsString(jogador);
				JSONObject teste = new JSONObject(converte);
				JArray.put(teste);
			}
			obj.put("jogador", JArray);
			json = obj.toString();
		}
		return json;
	}
	
	public boolean DelJogador() throws Exception{
		return JogadoresDAO.getInstance().DelJogador();
	}

}
