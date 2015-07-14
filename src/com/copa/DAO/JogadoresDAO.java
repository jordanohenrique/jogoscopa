package com.copa.DAO;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.copa.Model.Jogadores;
import com.copa.utils.Utils;


public class JogadoresDAO {
	String [] jogPosicao = {"GL","ZA","LA","MC","AT","TE"};
	private static JogadoresDAO instance;
	
	public static JogadoresDAO getInstance(){
		if(instance == null)
			instance = new JogadoresDAO();
		return instance;
	}
	
	private List<Jogadores> CollectionJogadores;
	Cursor cursor;
	
	public List<Jogadores> AllJogadores() {
		CollectionJogadores = new ArrayList<Jogadores>();		
		try {
			cursor = Utils.db.rawQuery("Select * from JOGADOR", null);
			while(cursor.moveToNext()){
				Jogadores jogador = new Jogadores(cursor);
				CollectionJogadores.add(jogador);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionJogadores;    
	}
	
	public void setJogadores(List<Jogadores> listJogadores){
		SQLiteStatement stm;
		
		try {
			for(Jogadores jogador : listJogadores){
				cursor = Utils.db.rawQuery("Select idJogador from Jogador where idJogador = ?", new String [] {String.valueOf(jogador.getIdJogador())});
				if (cursor.moveToNext()) {
					stm = null;
					String sql = "Update Jogador set jogNome = ?, jogPosicao = ?, SelecaoId = ?, jogStatus = ? WHERE idJogador = ?";

					stm = Utils.db.compileStatement(sql);

					stm.bindString(1, jogador.getJogNome());
					stm.bindLong(2, jogador.getSelecao_id());
					stm.bindString(3, jogador.getJogPosicao());
					stm.bindString(4, jogador.getJogStatus());
					stm.bindLong(5, jogador.getIdJogador());

					stm.execute();
					stm.close();
					
				}else{
					stm = null;
					String sql = "INSERT INTO Jogador(idJogador, jogNome, SelecaoId, jogPosicao, jogStatus) VALUES(?,?,?,?,?)";

					stm = Utils.db.compileStatement(sql);

					stm.bindLong(1, jogador.getIdJogador());
					stm.bindString(2, jogador.getJogNome());
					stm.bindLong(3, jogador.getSelecao_id());
					stm.bindString(4, jogador.getJogPosicao());
					stm.bindString(5, jogador.getJogStatus());

					stm.execute();
					stm.close();
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
	}
	
	public void setJogadores(Jogadores jogador){
		SQLiteStatement stm;
		
		try {
			cursor = Utils.db.rawQuery("Select idJogador from Jogador where idJogador = ?", 
					new String[] { String.valueOf(jogador.getIdJogador()) });
			if (cursor.moveToNext()) {
				stm = null;
				String sql = "Update Jogador set jogNome = ?, jogPosicao = ?, SelecaoId = ?, jogStatus = ? WHERE idJogador = ?";

				stm = Utils.db.compileStatement(sql);

				stm.bindString(1, jogador.getJogNome());
				stm.bindString(2, jogador.getJogPosicao());
				stm.bindLong(3, jogador.getSelecao_id());
				stm.bindString(4, jogador.getJogStatus());
				stm.bindLong(5, jogador.getIdJogador());

				stm.execute();
				stm.close();

			} else {
				stm = null;
				String sql = "INSERT INTO Jogador(jogNome, SelecaoId, jogPosicao, jogStatus) VALUES(?,?,?,?)";

				stm = Utils.db.compileStatement(sql);

				stm.bindString(1, jogador.getJogNome());
				stm.bindLong(2, jogador.getSelecao_id());
				stm.bindString(3, jogador.getJogPosicao());
				stm.bindString(4, jogador.getJogStatus());

				stm.execute();
				stm.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
	}
	
	public boolean DelJogador(){
		boolean confirmacao = false;
		cursor = Utils.db.rawQuery("Select idJogador from Jogador", null);
		if(cursor.moveToNext()){
			int ret = Utils.db.delete("DADOS_PESSOA", "jogStatus = ?",new String [] {Utils.DELETE});
			if(ret > 0)
				confirmacao = true;
		}
		return confirmacao;
	}
	
	
	public List<Jogadores> createListJogadores(long _id) throws Exception{
		CollectionJogadores = new ArrayList<Jogadores>();
		Jogadores jogador;
		try {
			if(_id > 0){
				cursor = Utils.db.rawQuery("SELECT * FROM JOGADOR where SelecaoId = "+_id, null);
				if (cursor.getCount()>0) {
					while (cursor.moveToNext()) {
						jogador = new Jogadores(cursor);

						CollectionJogadores.add(jogador);
					}
				}else{
					jogador = new Jogadores();
					jogador.setJogNome("Não Localizado...");
					jogador.setJogPosicao("NE");
					jogador.setJogStatus("F");
					CollectionJogadores.add(jogador);
				}
			}
		}finally{
			cursor.close();
		}
		return CollectionJogadores;
	}
	
	public Jogadores getJogador(long _id) {
		Jogadores jogador = null;
		try {
			cursor = Utils.db.rawQuery("Select * from JOGADOR where idJogador = " + _id, null);
			if(cursor.moveToNext()){
				jogador = new Jogadores(cursor);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return jogador;    
	}
	
	public List<Jogadores> ordenaJogadores(long _idselecao)throws Exception{
		List<Jogadores> listJogadores = new ArrayList<Jogadores>();
		List<Jogadores> listJogAuxiliar = new ArrayList<Jogadores>();
		CollectionJogadores = createListJogadores(_idselecao);

		for(int i=0; i < jogPosicao.length; i++){
			for(Jogadores jogador : CollectionJogadores){
				if(jogador.getJogPosicao().equals(jogPosicao[i]) && jogador.getJogStatus().equalsIgnoreCase("T")){
					listJogadores.add(jogador);
				}else if(jogador.getJogPosicao().equals(jogPosicao[i]) && jogador.getJogStatus().equalsIgnoreCase("F")){
					listJogAuxiliar.add(jogador);
				}
			}
		}
		for(Jogadores jogadoraux : listJogAuxiliar){
			listJogadores.add(jogadoraux);
		}
		return listJogadores;
	}
}
