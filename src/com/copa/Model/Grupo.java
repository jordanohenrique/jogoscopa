package com.copa.Model;

import java.util.List;

import android.database.Cursor;

public class Grupo {

	private long idGrupo;
	private String gruDescricao;
	
	public Grupo(Cursor cursor) {
		this.idGrupo = cursor.getLong(cursor.getColumnIndex("idGrupo"));
		this.gruDescricao = cursor.getString(cursor.getColumnIndex("gruDescricao"));		
	}
	
	public Grupo() {
		//construtor padrao
	}
	
	public long getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getGruDescricao() {
		return gruDescricao;
	}
	public void setGruDescricao(String gruDescricao) {
		this.gruDescricao = gruDescricao;
	}

	@Override
	public String toString() {
		return getGruDescricao();
	}
	
}
