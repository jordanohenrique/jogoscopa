package com.copa.DAO;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.copa.Model.Selecoes;
import com.copa.utils.Utils;

public class SelecoesDAO {

	private static SelecoesDAO instance;
	
	public static SelecoesDAO getInstance(){
		
		if(instance == null){
			instance = new SelecoesDAO();
		}		
		return instance;	
	}
	
	private List<Selecoes> CollectionSelecoes;
	private Cursor cursor;
	
	public List<Selecoes> loadChild(String grupo) {
		CollectionSelecoes = new ArrayList<Selecoes>();
        try {
        	cursor = Utils.db.rawQuery("Select * from Selecao Where GrupoId in (Select idGrupo from Grupo where gruDescricao = '"+grupo+"');",  null);
        	while(cursor.moveToNext()){
        		Selecoes selecoes = new Selecoes();
        		
        		selecoes.setIdSelecao(cursor.getLong(cursor.getColumnIndex("idSelecao")));
        		selecoes.setSelNome(cursor.getString(cursor.getColumnIndex("selNome")));
        		selecoes.setSelFlag(cursor.getInt(cursor.getColumnIndex("selFlagID")));
        	
        		CollectionSelecoes.add(selecoes);
        	}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			cursor.close();
		}
		return CollectionSelecoes;    
    }
	
	public List<Selecoes> AllSelecoes() {
		CollectionSelecoes = new ArrayList<Selecoes>();		
		try {
			cursor = Utils.db.rawQuery("Select * from SELECAO order by selNome", null);
			while(cursor.moveToNext()){
				Selecoes selecao = new Selecoes(cursor);
				CollectionSelecoes.add(selecao);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionSelecoes;    
	}
	
	public void updateSelecoes(List<Selecoes> Selecoes){
		
		for(Selecoes selecao : Selecoes){
		
			String sql = "Update SELECAO set selNome = ?, selPontos = ?, selVitorias = ?, selSaldoGols = ?, selNumJogos = ?, selEmpates = ?, selDerrotas = ?,"
						+" selGolsPro = ?, selGolsCont = ?, selFlagID = ?, GrupoId =? WHERE idSelecao = ?";
		
			SQLiteStatement stm = Utils.db.compileStatement(sql);
		
			stm.bindString(1, selecao.getSelNome());
			stm.bindLong(2, selecao.getSelPontos());
			stm.bindLong(3, selecao.getSelVitorias());
			stm.bindLong(4, selecao.getSelSaldoGols());
			stm.bindLong(5, selecao.getSelNumJogos());
			stm.bindLong(6, selecao.getSelEmpates());
			stm.bindLong(7, selecao.getSelDerrotas());
			stm.bindLong(8, selecao.getSelGolsPro());
			stm.bindLong(9, selecao.getSelGolsCont());
			stm.bindLong(10, selecao.getSelFlag());
			stm.bindLong(11, selecao.getGrupoId());
			stm.bindLong(12, selecao.getIdSelecao());
			
			stm.execute();
		}		
	}
	
	public List<Selecoes> createSelecoesList() throws Exception{
		CollectionSelecoes = new ArrayList<Selecoes>();
		try {
			cursor = Utils.db.rawQuery("SELECT * FROM SELECAO ORDER BY selNome", null);
			while(cursor.moveToNext()){
				Selecoes selecao = new Selecoes();
				selecao.setIdSelecao(cursor.getInt(cursor.getColumnIndex("idSelecao")));
				selecao.setSelNome(cursor.getString(cursor.getColumnIndex("selNome")));
				selecao.setSelFlag(cursor.getInt(cursor.getColumnIndex("selFlagID")));
				
				CollectionSelecoes.add(selecao);
			}
		}finally{
			cursor.close();
		}
		
		return CollectionSelecoes;
	}
	
	public List<Selecoes> getSelecoesByID(long _idSelecao1, long _idSelecao2){
		CollectionSelecoes = new ArrayList<Selecoes>();
		try {
			cursor = Utils.db.rawQuery("SELECT * FROM SELECAO WHERE idSelecao = " + _idSelecao1 + " or idSelecao = "+ _idSelecao2 , null);
			while(cursor.moveToNext()){
				Selecoes selecao = new Selecoes(cursor);
				CollectionSelecoes.add(selecao);
			}
		}finally{
			cursor.close();
		}
		
		return CollectionSelecoes;
	}
	
	public List<Selecoes> Selecoes() {
		CollectionSelecoes = new ArrayList<Selecoes>();		
		try {
			cursor = Utils.db.rawQuery("Select * from SELECAO order by selNome", null);
			while(cursor.moveToNext()){
				Selecoes selecao = new Selecoes();
				
				 selecao.setIdSelecao(cursor.getLong(cursor.getColumnIndex("idSelecao")));
				 selecao.setSelNome(cursor.getString(cursor.getColumnIndex("selNome")));
				
				 CollectionSelecoes.add(selecao);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionSelecoes;    
	}
	
	public List<Selecoes> getSelecoesByGrupo(String grupo) {
		CollectionSelecoes = new ArrayList<Selecoes>();		
		try {
			cursor = Utils.db.rawQuery("Select * from Selecao s Where GrupoId in (Select idGrupo from Grupo where gruDescricao = '"+grupo+"')"
        			+ " order by s.selPontos desc, s.selSaldoGols desc, s.selGolsPro desc;",  null);
			while(cursor.moveToNext()){
				Selecoes selecao = new Selecoes(cursor);
				
				CollectionSelecoes.add(selecao);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionSelecoes;    
	}
	
	public Selecoes loadSelecao(long idSelecao) {
		
		Selecoes selecao = null;
		
		try {
			cursor = Utils.db.rawQuery("Select * from Selecao Where idSelecao =" +idSelecao+";",  null);
			if(cursor.moveToNext()){
				selecao = new Selecoes(cursor);
			}
			
		} catch (Exception e) {
			
		}finally{
			cursor.close();
		}
		return selecao;    
	}
	
	public int getFlag(long idSelecao) {
		
		Selecoes selecao = null;
		
		try {
			cursor = Utils.db.rawQuery("Select selFlagID from Selecao Where idSelecao =" +idSelecao+";",  null);
			if(cursor.moveToNext()){
				selecao = new Selecoes();
				selecao.setSelFlag(cursor.getInt(cursor.getColumnIndex("selFlagID")));
			}
			
		} catch (Exception e) {
			
		}finally{
			cursor.close();
		}
		return selecao.getSelFlag();    
	}
}
