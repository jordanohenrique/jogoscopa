package com.copa.Model;

import android.database.Cursor;

public class Jogadores {
	
	private long idJogador;
	private String jogNome;
	private String jogPosicao;
	private String jogStatus;
	private long selecao_id;
	
	
	public Jogadores(Cursor cursor) {
		
		this.idJogador = cursor.getLong(cursor.getColumnIndex("idJogador"));
		this.jogNome = cursor.getString(cursor.getColumnIndex("jogNome"));
		this.jogPosicao = cursor.getString(cursor.getColumnIndex("jogPosicao"));
		this.jogStatus = cursor.getString(cursor.getColumnIndex("jogStatus"));
		this.selecao_id = cursor.getLong(cursor.getColumnIndex("SelecaoId"));
	}
	
	public Jogadores() {
		// TODO Auto-generated constructor stub
	}

	public long getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(long idJogador) {
		this.idJogador = idJogador;
	}

	public String getJogNome() {
		return jogNome;
	}

	public void setJogNome(String jogNome) {
		this.jogNome = jogNome;
	}

	public String getJogPosicao() {
		return jogPosicao;
	}

	public void setJogPosicao(String jogPosicao) {
		this.jogPosicao = jogPosicao;
	}

	public long getSelecao_id() {
		return selecao_id;
	}

	public void setSelecao_id(long selecao_id) {
		this.selecao_id = selecao_id;
	}

	public String getJogStatus() {
		return jogStatus;
	}

	public void setJogStatus(String jogStatus) {
		this.jogStatus = jogStatus;
	}
	
}
