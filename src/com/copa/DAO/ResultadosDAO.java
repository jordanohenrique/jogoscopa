package com.copa.DAO;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.copa.Model.Resultados;
import com.copa.utils.Utils;

public class ResultadosDAO {
	
	private static ResultadosDAO instance;
	
	public static ResultadosDAO getInstance(){
		if(instance == null){
			instance = new ResultadosDAO();
		}
		return instance;
	}
	
	private List<Resultados> CollectionResultados;
	private Cursor cursor;
	
	public List<Resultados> AllResultados() {
		CollectionResultados = new ArrayList<Resultados>();		
		try {
			cursor = Utils.db.rawQuery("Select * from RESULTADO", null);
			while(cursor.moveToNext()){
				Resultados resultados = new Resultados(cursor);
				CollectionResultados.add(resultados);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionResultados;    
	}
	
	public void updateResultados(List<Resultados> Resultados){
		
		for(Resultados resultado : Resultados){
		
			String sql = "Update RESULTADO set jgIdSelecao1 = ?, jgIdSelecao2 = ?, jgSelecao1 = ?, jgSelecao2 = ?,"
					+ " jgReferencia = ?, jgData = ?, jgResultado1 = ?, jgResultado2 = ?, jgSituacao = ?, GrupoId = ? WHERE idJogos = ?";
		
			SQLiteStatement stm = Utils.db.compileStatement(sql);
		
			stm.bindLong(1, resultado.getJgIdSelecao1());
			stm.bindLong(2, resultado.getJgIdSelecao2());
			stm.bindString(3, resultado.getJgSelecao1());
			stm.bindString(4, resultado.getJgSelecao2());
			stm.bindString(5, resultado.getJgReferencia());
			stm.bindString(6, resultado.getJgData());
			stm.bindLong(7, resultado.getJgResultado1());
			stm.bindLong(8, resultado.getJgResultado2());
			stm.bindString(9, resultado.getJgSituacao());
			stm.bindLong(10, resultado.getGrupoId());
			stm.bindLong(11, resultado.getIdJogos());
			
			stm.execute();
		}		
	}
	
	public List<Resultados> loadResultadosByGrupo(long _idGrupo) {
		
		CollectionResultados = new ArrayList<Resultados>();
		
		try {
			cursor = Utils.db.rawQuery("Select * from RESULTADO Where GrupoId =" +_idGrupo+";",  null);
			while(cursor.moveToNext()){
				Resultados resultado = new Resultados(cursor);
				
				CollectionResultados.add(resultado);
			}
			
		} catch (Exception e) {
			
		}finally{
			cursor.close();
		}
		return CollectionResultados;    
	}
	
public Resultados loadResultado(long idSelecao1 , long idSelecao2) {
		
		Resultados resultado = null;
		
		try {
			cursor = Utils.db.rawQuery("Select * from RESULTADO Where jgIdSelecao1 =" +idSelecao1+" and jgIdSelecao2 = "+idSelecao2+";",  null);
			if(cursor.moveToNext()){
				resultado = new Resultados(cursor);
			}
			
		} catch (Exception e) {
			
		}finally{
		}
		return resultado;    
	}

	public List<Resultados> loadResultados(long idSelecao) {
		
		CollectionResultados = new ArrayList<Resultados>();
		
		try {
			cursor = Utils.db.rawQuery("Select * from RESULTADO Where jgIdSelecao1 =" +idSelecao+" or jgIdSelecao2 = "+idSelecao+";",  null);
			while(cursor.moveToNext()){
				Resultados resultado = new Resultados(cursor);
				
				CollectionResultados.add(resultado);
			}	
		} catch (Exception e) {
			
		}finally{
		}
		return CollectionResultados;    
	}
}
