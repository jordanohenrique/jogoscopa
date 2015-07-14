package com.copa.DAO;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.copa.Model.Grupo;
import com.copa.utils.Utils;

public class GrupoDAO {
	
	private static GrupoDAO instance;
	
	public static GrupoDAO getIstance(){
		if(instance == null){
			instance = new GrupoDAO();
		}
		return instance;
	}
	
	private List<Grupo> CollectionGrupos = null;
	private Cursor cursor;
	
	public List<Grupo> AllGrupos() {
		CollectionGrupos = new ArrayList<Grupo>();		
		try {
			cursor = Utils.db.rawQuery("Select * from GRUPO", null);
			while(cursor.moveToNext()){
				Grupo grupo = new Grupo(cursor);
				CollectionGrupos.add(grupo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionGrupos;    
	}
	
	public void updateGrupo(List<Grupo> grupos){
		
		for(Grupo grupo : grupos){
		
			String sql = "Update GRUPO set gruDescricao = ? WHERE idGrupo = ?";
		
			SQLiteStatement stm = Utils.db.compileStatement(sql);
		
			stm.bindString(1, grupo.getGruDescricao());
			stm.bindLong(2, grupo.getIdGrupo());
			
			stm.execute();
		}		
	}
	
	public Grupo getGrupo(long _id){
		Grupo grupo = null;		
		try {
			cursor = Utils.db.rawQuery("Select * from GRUPO where idGrupo = "+_id, null);
			if(cursor.moveToNext()){
				grupo = new Grupo(cursor);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return grupo;    
	}

}
