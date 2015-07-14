package com.copa.DAO;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.copa.Model.Selecoes;
import com.copa.Model.SemiFinal;
import com.copa.utils.Utils;

public class SemiFinalDAO {
	
	private static SemiFinalDAO instance;
	
	public static SemiFinalDAO getInstance(){
		if(instance == null){
			instance = new SemiFinalDAO();
		}
		return instance;
	}
	
	private List<SemiFinal> CollectionSemiFinal;
	Cursor cursor;
	
	public List<SemiFinal> getAllSemiFinal(){
		CollectionSemiFinal = new ArrayList<SemiFinal>();
		try {
			cursor = Utils.db.rawQuery("Select * from SEMIFINAL Order By semiReferencia", null);
			while(cursor.moveToNext()){
				SemiFinal semifinal = new SemiFinal(cursor);
				if(semifinal.getSemiIdSelecao() != 0){
					semifinal.setSelFlag(SelecoesDAO.getInstance().getFlag(semifinal.getSemiIdSelecao()));
				}
				CollectionSemiFinal.add(semifinal);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		
		return CollectionSemiFinal;
	}
	
	public boolean updateSemiFinal(List<SemiFinal> listSemifinal) {
		SQLiteStatement stm;
		boolean acao = false;
		try {
			for (SemiFinal semi : listSemifinal) {
				cursor = Utils.db.rawQuery("Select idSemi from SEMIFINAL where semiReferencia = ?",
								new String[] { String.valueOf(semi.getSemiReferencia()) });
				if (cursor.moveToNext()) {
					stm = null;
					String sql = "Update SEMIFINAL set semiSelNome = ?, semiIdSelecao = ?, semiResultado = ?, semiIdGrupo = ? "
							+ "WHERE semiReferencia = ?";

					stm = Utils.db.compileStatement(sql);

					stm.bindString(1, semi.getSemiSelNome());
					stm.bindLong(2, semi.getSemiIdSelecao());
					stm.bindLong(3, semi.getSemiResultado());
					stm.bindLong(4, semi.getSemiIdGrupo());
					stm.bindLong(5, semi.getSemiReferencia());

					stm.execute();
					stm.close();
				}
			}
			acao = true;
		} catch (Exception e) {
			acao = false;
		} finally {
			cursor.close();
		}

		return acao;
	}
	
	public boolean updateFinal(List<SemiFinal> listSemifinal) {
		SQLiteStatement stm;
		boolean acao = false;
		try {
			for (SemiFinal Final : listSemifinal) {
				cursor = Utils.db.rawQuery("Select idFinal from FINAL where finPosicao = ?",
						new String[] { String.valueOf(Final.getSemiReferencia()) });
				if (cursor.moveToNext()) {
					stm = null;
					String sql = "Update FINAL set finSelNome = ?, finIdSelecao = ?, finResultado = ?, finIdGrupo = ? "
							+ "WHERE finPosicao = ?";
					
					stm = Utils.db.compileStatement(sql);
					
					stm.bindString(1, Final.getSemiSelNome());
					stm.bindLong(2, Final.getSemiIdSelecao());
					stm.bindLong(3, Final.getSemiResultado());
					stm.bindLong(4, Final.getSemiIdGrupo());
					stm.bindLong(5, Final.getSemiPosicao());
					
					stm.execute();
					stm.close();
				}
			}
			acao = true;
		} catch (Exception e) {
			acao = false;
		} finally {
			cursor.close();
		}
		return acao;
	}

	public List<SemiFinal> getAllFinal(){
		CollectionSemiFinal = new ArrayList<SemiFinal>();
		try {
			cursor = Utils.db.rawQuery("Select * from FINAL Order By finPosicao", null);
			while(cursor.moveToNext()){
				SemiFinal semifinal = new SemiFinal();
				semifinal.setSemiIdSelecao(cursor.getLong(cursor.getColumnIndex("finIdSelecao")));
				semifinal.setSemiSelNome(cursor.getString(cursor.getColumnIndex("finSelNome")));
				semifinal.setSemiResultado(cursor.getInt(cursor.getColumnIndex("finResultado")));
				semifinal.setSemiPosicao(cursor.getInt(cursor.getColumnIndex("finPosicao")));
				
				CollectionSemiFinal.add(semifinal);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		
		return CollectionSemiFinal;
	}

	
	public String localJogo(long _id) {	
		String local = null;
		try {
			cursor = Utils.db.rawQuery("Select semiLocal from SEMIFINAL Where idSemi = " +_id, null);
			if(cursor.moveToNext()){
				local = cursor.getString(cursor.getColumnIndex("semiLocal"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return local;
	}
	
}
