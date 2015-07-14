package com.copa.DAO;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.copa.Model.Estadio;
import com.copa.Model.Grupo;
import com.copa.Model.Parametros;
import com.copa.Model.Resultados;
import com.copa.Model.Selecoes;
import com.copa.utils.Utils;

public class DadosBD {
	
	private static DadosBD instance;
	
	public static DadosBD getInstance(){
		if(instance == null){
			instance = new DadosBD();
		}
		return instance;
	}
	
	List<Selecoes> seleCollection;
	List<Resultados> listResultados;
	Cursor cursor;
	
	public void setParametros(){
		try {
			cursor = Utils.db.rawQuery("Select * from Parametros",  null);
			if(cursor.moveToNext()){
				new Parametros(cursor);
			}
		} catch (Exception e) {
			
		}finally{
			cursor.close();
		}
	}
	
	public boolean limpaBancoDados(){
		boolean acao = false;
		
		try {
			Utils.db.execSQL("Update RESULTADO set jgResultado1 = 0, jgResultado2 = 0;");
			Utils.db.execSQL("Update SELECAO set selPontos = 0, selVitorias = 0, selDerrotas = 0, selSaldoGols = 0, selNumJogos = 0, "
							+"selEmpates = 0, selGolsPro = 0, selGolsCont = 0;");
			Utils.db.execSQL("Update OITAVAS set oitIdSelecao = 0, oitSelNome = "+null+", oitResultado = 0;");
			Utils.db.execSQL("Update QUARTAS set quaIdSelecao = 0, quaSelNome = "+null+", quaResultado = 0;");
			Utils.db.execSQL("Update SEMIFINAL set semiIdSelecao = 0, semiSelNome = "+null+", semiResultado = 0;");
			Utils.db.execSQL("Update FINAL set finIdSelecao = 0, finSelNome = "+null+", finResultado = 0;");
			Utils.db.execSQL("Update PARAMETROS set parQuartas = 'F', parOitavas = 'F', parSemi = 'F', parClassif = 'F';");
			acao = true;
		} catch (Exception e) {
			acao = false;
		}
		return acao;
	}
	
	public List<Estadio> getEstadios(){
		List<Estadio> listEstadio = new ArrayList<Estadio>();		
		try {
			cursor = Utils.db.rawQuery("Select * from ESTADIO", null);
			while(cursor.moveToNext()){
				Estadio estadio = new Estadio(cursor);
				listEstadio.add(estadio);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return listEstadio;    
	}
	
	public Estadio getEstadios(long _id){
		Estadio Estadio = null;		
		try {
			cursor = Utils.db.rawQuery("Select * from ESTADIO where idEstadio = " + _id, null);
			if(cursor.moveToNext()){
				Estadio = new Estadio(cursor);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return Estadio;    
	}
}
