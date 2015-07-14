package com.copa.DAO;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.copa.Model.OitavasFinal;
import com.copa.Model.QuartasFinal;
import com.copa.Model.Selecoes;
import com.copa.utils.Utils;

public class QuartasDAO {

	private static QuartasDAO instance;
	
	public static QuartasDAO getInstance(){
		if(instance == null)
			instance = new QuartasDAO();
		
		return instance;
	}
	
	private List<QuartasFinal> CollectionQuartas;
	Cursor cursor;
	
	public List<QuartasFinal> AllQuartas() {
		CollectionQuartas = new ArrayList<QuartasFinal>();		
		try {
			cursor = Utils.db.rawQuery("Select * from Quartas", null);
			while(cursor.moveToNext()){
				QuartasFinal quartas = new QuartasFinal(cursor);
				CollectionQuartas.add(quartas);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionQuartas;    
	}
	
	public void setQuartas(List<QuartasFinal> Quartas){
		SQLiteStatement stm;
		
		try {
			for(QuartasFinal quartas : Quartas){
				stm = null;
					String sql = "Update Quartas set quaSelNome = ?, quaIdSelecao = ?, quaResultado = ? "
							+ ",quaIdGrupo = ?, quaIdPosicao = ?, quaLocal = ? WHERE idQuartas = ?";

					stm = Utils.db.compileStatement(sql);

					stm.bindString(1, quartas.getQuaSelNome());
					stm.bindLong(2, quartas.getQuaIdSelecao());
					stm.bindLong(3, quartas.getQuaResultado());
					stm.bindLong(4, quartas.getQuaIdGrupo());
					stm.bindLong(5, quartas.getQuaPosicao());
					stm.bindString(6, quartas.getQuaLocal());
					stm.bindLong(7, quartas.getQuaReferencia());

					stm.execute();
					stm.close();
				
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
	}
	
	public int getCountQuartas() throws Exception{
		int _itens = 0;
		try {
			cursor = Utils.db.rawQuery("Select * from Quartas", null);
			_itens = cursor.getCount();
		}finally{
			cursor.close();
		}
		return _itens;
		
	}
	
	public boolean updateQuartas(List<QuartasFinal> listQuartas){
		SQLiteStatement stm;
		boolean acao = false;
		try {
			for(QuartasFinal quartas : listQuartas){
				cursor = Utils.db.rawQuery("Select idQuartas from QUARTAS where quaReferencia = ?", 
						new String [] {String.valueOf(quartas.getQuaReferencia())});
				while(cursor.moveToNext()) {
					stm = null;
					String sql = "Update QUARTAS set quaSelNome = ?, quaIdSelecao = ?, quaResultado,"
							+ " quaIdGrupo = ?, quaPosicao = ?, quaLocal =? WHERE quaReferencia = ?";

					stm = Utils.db.compileStatement(sql);

					stm.bindString(1, quartas.getQuaSelNome());
					stm.bindLong(2, quartas.getQuaIdSelecao());
					stm.bindLong(3, quartas.getQuaResultado());
					stm.bindLong(4, quartas.getQuaIdGrupo());
					stm.bindLong(5, quartas.getQuaPosicao());
					stm.bindString(6, quartas.getQuaLocal());
					stm.bindLong(7, quartas.getQuaReferencia());

					stm.execute();
					stm.close();	
				}
			}
			acao = true;
		} catch (Exception e) {
			acao = false;
		}finally{
			cursor.close();
		}
		
		return acao;
	}
	
	public List<QuartasFinal> getAllQuartas() {
		CollectionQuartas = new ArrayList<QuartasFinal>();		
		try {
			cursor = Utils.db.rawQuery("Select * from QUARTAS Order By quaReferencia", null);
			while(cursor.moveToNext()){
				QuartasFinal quartas = new QuartasFinal(cursor);
				CollectionQuartas.add(quartas);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionQuartas;    
	}
	
	public String localJogo(long _id) {	
		String local = null;
		try {
			cursor = Utils.db.rawQuery("Select quaLocal from QUARTAS Where quaReferencia = " +_id, null);
			if(cursor.moveToNext()){
				local = cursor.getString(cursor.getColumnIndex("quaLocal"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return local;
	}
	
	public List<QuartasFinal> mergeDadosQuartas(){
		CollectionQuartas = getAllQuartas();
		List<QuartasFinal> listQuartasFinal = new ArrayList<QuartasFinal>();
		int i = 0;
		while(i < CollectionQuartas.size()){
			QuartasFinal quartasFinal = new QuartasFinal();
			quartasFinal.setQuaIdGrupo(CollectionQuartas.get(i).getQuaIdGrupo());
			quartasFinal.setQuaSelNome(CollectionQuartas.get(i).getQuaSelNome());
			quartasFinal.setQuaResultado(CollectionQuartas.get(i).getQuaResultado());
			quartasFinal.setQuaReferencia(CollectionQuartas.get(i).getQuaReferencia());
			quartasFinal.setQuaLocal(CollectionQuartas.get(i).getQuaLocal());
			if(CollectionQuartas.get(i).getQuaIdSelecao() != 0){
				quartasFinal.setSelFlag(SelecoesDAO.getInstance().getFlag(CollectionQuartas.get(i).getQuaIdSelecao()));
			}
			i++;
			quartasFinal.setQuaIdGrupo1(CollectionQuartas.get(i).getQuaIdGrupo());
			quartasFinal.setQuaSelNome1(CollectionQuartas.get(i).getQuaSelNome());
			quartasFinal.setQuaResultado1(CollectionQuartas.get(i).getQuaResultado());
			quartasFinal.setQuaReferencia1(CollectionQuartas.get(i).getQuaReferencia());
			if(CollectionQuartas.get(i).getQuaIdSelecao() != 0){
				quartasFinal.setSelFlag1(SelecoesDAO.getInstance().getFlag(CollectionQuartas.get(i).getQuaIdSelecao()));
			}
			i++;
			listQuartasFinal.add(quartasFinal);
			}
		return listQuartasFinal;
	}
	
}
