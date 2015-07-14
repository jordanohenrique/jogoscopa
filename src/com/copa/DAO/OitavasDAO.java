package com.copa.DAO;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.copa.Model.OitavasFinal;
import com.copa.Model.Selecoes;
import com.copa.jogoscopa.R;
import com.copa.utils.Utils;

public class OitavasDAO {

	private static OitavasDAO instance;
	
	public static OitavasDAO getInstance(){
		if(instance == null)
			instance = new OitavasDAO();
		
		return instance;
	}
	
	private List<OitavasFinal> CollectionOitavas;
	Cursor cursor;
	
	public List<OitavasFinal> AllOitavas() {
		CollectionOitavas = new ArrayList<OitavasFinal>();		
		try {
			cursor = Utils.db.rawQuery("Select * from Oitavas", null);
			while(cursor.moveToNext()){
				OitavasFinal oitavas = new OitavasFinal(cursor);
				CollectionOitavas.add(oitavas);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionOitavas;    
	}
	
	public void setOitavas(List<OitavasFinal> listOitavas){
		SQLiteStatement stm;
		
		try {
			for(OitavasFinal oitavas : listOitavas){
				stm = null;
					stm = Utils.db.compileStatement("Update Oitavas set oitIdSelecao = ?, oitSelNome = ?, "
							+ "oitResultado = ?, oitReferencia = ?, oitLocal = ?, oitIdGrupo = ?, "
							+ "oitPosicao = ? Where idOitavas = ?");
				
					stm.bindLong(1, oitavas.getOitIdSelecao());
					stm.bindString(2, oitavas.getOitSelNome());
					stm.bindLong(3, oitavas.getOitResultado());
					stm.bindLong(4, oitavas.getOitReferencia());
					stm.bindString(5, oitavas.getOitLocal());
					stm.bindLong(6, oitavas.getOitIdGrupo());
					stm.bindLong(7, oitavas.getOitPosicao());
					stm.bindLong(8, oitavas.getIdOitavas());	
					
					stm.execute();
					stm.close();
					
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
	}
	
	public int getCountOitavas() throws Exception{
		int _itens = 0;
		try {
			cursor = Utils.db.rawQuery("Select * from Oitavas", null);
			_itens = cursor.getCount();
		}finally{
			cursor.close();
		}
		return _itens;
		
	}
	
	public List<OitavasFinal> getAllOitavas() {
		int i=1;
		OitavasFinal oitavas;
		CollectionOitavas = new ArrayList<OitavasFinal>();		
		try {
			cursor = Utils.db.rawQuery("Select * from Oitavas order by oitReferencia", null);
			while(cursor.moveToNext()){
				oitavas = new OitavasFinal(cursor);
				CollectionOitavas.add(oitavas);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cursor.close();
		}
		return CollectionOitavas;    
	}
	
	public boolean updateOitavas(List<OitavasFinal> listOitavas){
		SQLiteStatement stm;
		boolean acao = false;
		try {
			for(OitavasFinal oitavas : listOitavas){
				cursor = Utils.db.rawQuery("Select idOitavas from Oitavas where oitIdGrupo = ? and oitPosicao = ?", 
						new String [] {String.valueOf(oitavas.getOitIdGrupo()), String.valueOf(oitavas.getOitPosicao())});
				if (cursor.moveToNext()) {
					stm = null;
					String sql = "Update Oitavas set oitSelNome = ?, oitIdSelecao = ?, oitIdGrupo = ?, oitPosicao = ? "
							+ "WHERE oitIdGrupo = ? and oitPosicao = ?";

					stm = Utils.db.compileStatement(sql);

					stm.bindString(1, oitavas.getOitSelNome());
					stm.bindLong(2, oitavas.getOitIdSelecao());
					stm.bindLong(3, oitavas.getOitIdGrupo());
					stm.bindLong(4, oitavas.getOitPosicao());
					stm.bindLong(5, oitavas.getOitIdGrupo());
					stm.bindLong(6, oitavas.getOitPosicao());

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
	
	public List<OitavasFinal> mergeDadosOitavas(){
		CollectionOitavas = getAllOitavas();
		List<OitavasFinal> listOitavas = new ArrayList<OitavasFinal>();
		int i = 0;
		while(i < CollectionOitavas.size()){
			OitavasFinal oitavasFinal = new OitavasFinal();
					oitavasFinal.setOitIdSelecao(CollectionOitavas.get(i).getOitIdSelecao());
					oitavasFinal.setOitIdGrupo(CollectionOitavas.get(i).getOitIdGrupo());
					oitavasFinal.setOitSelNome(CollectionOitavas.get(i).getOitSelNome());
					oitavasFinal.setOitResultado(CollectionOitavas.get(i).getOitResultado());
					oitavasFinal.setOitPosicao(CollectionOitavas.get(i).getOitPosicao());
					oitavasFinal.setOitReferencia(CollectionOitavas.get(i).getOitReferencia());
					oitavasFinal.setOitLocal(CollectionOitavas.get(i).getOitLocal());
					if(CollectionOitavas.get(i).getOitIdSelecao() != 0){
						oitavasFinal.setSelFlag(SelecoesDAO.getInstance().getFlag(CollectionOitavas.get(i).getOitIdSelecao()));
					}else{
						oitavasFinal.setSelFlag(R.drawable.trofeucopa);
					}
				i++;
					oitavasFinal.setOitIdSelecao1(CollectionOitavas.get(i).getOitIdSelecao());
					oitavasFinal.setOitIdGrupo1(CollectionOitavas.get(i).getOitIdGrupo());
					oitavasFinal.setOitSelNome1(CollectionOitavas.get(i).getOitSelNome());
					oitavasFinal.setOitResultado1(CollectionOitavas.get(i).getOitResultado());
					oitavasFinal.setOitPosicao1(CollectionOitavas.get(i).getOitPosicao());
					if(CollectionOitavas.get(i).getOitIdSelecao() != 0){
						oitavasFinal.setSelFlag1(SelecoesDAO.getInstance().getFlag(CollectionOitavas.get(i).getOitIdSelecao()));
					}else{
						oitavasFinal.setSelFlag1(R.drawable.trofeucopa);
					}
				i++;
				listOitavas.add(oitavasFinal);
			}
		return listOitavas;
	}

}
