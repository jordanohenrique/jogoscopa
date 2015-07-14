package com.copa.Model;

import android.database.Cursor;

public class Selecoes {

	private long idSelecao;
	private String selNome;
	private int selPontos;
	private int selVitorias;
	private int selSaldoGols;
	private int selNumJogos;
	private int selEmpates;
	private int selDerrotas;
	private int selGolsPro;
	private int selGolsCont;
	private int selFlag;
	private long grupoId;
	
	public Selecoes(Cursor cursor) {
		
		 this.idSelecao = cursor.getLong(cursor.getColumnIndex("idSelecao"));
		 this.selNome = cursor.getString(cursor.getColumnIndex("selNome"));
		 this.selPontos = cursor.getInt(cursor.getColumnIndex("selPontos"));
		 this.selVitorias = cursor.getInt(cursor.getColumnIndex("selVitorias"));
		 this.selDerrotas = cursor.getInt(cursor.getColumnIndex("selDerrotas"));
		 this.selSaldoGols = cursor.getInt(cursor.getColumnIndex("selSaldoGols"));
		 this.selNumJogos = cursor.getInt(cursor.getColumnIndex("selNumJogos"));
		 this.selEmpates = cursor.getInt(cursor.getColumnIndex("selEmpates"));
		 this.selGolsPro = cursor.getInt(cursor.getColumnIndex("selGolsPro"));
		 this.selGolsCont = cursor.getInt(cursor.getColumnIndex("selGolsCont"));
		 this.selFlag = cursor.getInt(cursor.getColumnIndex("selFlagID"));
		 this.grupoId = cursor.getLong(cursor.getColumnIndex("GrupoId"));
		
	}	
	
	public Selecoes() {
		// TODO Auto-generated constructor stub
	}
	
	public long getIdSelecao() {
		return idSelecao;
	}
	public void setIdSelecao(long idSelecao) {
		this.idSelecao = idSelecao;
	}
	public String getSelNome() {
		return selNome;
	}
	public void setSelNome(String selNome) {
		this.selNome = selNome;
	}
	public int getSelPontos() {
		return selPontos;
	}
	public void setSelPontos(int selPontos) {
		this.selPontos = selPontos;
	}
	public int getSelVitorias() {
		return selVitorias;
	}
	public void setSelVitorias(int selVitorias) {
		this.selVitorias = selVitorias;
	}
	public int getSelSaldoGols() {
		return selSaldoGols;
	}
	public void setSelSaldoGols(int selSaldoGols) {
		this.selSaldoGols = selSaldoGols;
	}
	public int getSelNumJogos() {
		return selNumJogos;
	}
	public void setSelNumJogos(int selNumJogos) {
		this.selNumJogos = selNumJogos;
	}
	public int getSelEmpates() {
		return selEmpates;
	}
	public void setSelEmpates(int selEmpates) {
		this.selEmpates = selEmpates;
	}
	public int getSelDerrotas() {
		return selDerrotas;
	}
	public void setSelDerrotas(int selDerrotas) {
		this.selDerrotas = selDerrotas;
	}
	public int getSelGolsPro() {
		return selGolsPro;
	}
	public void setSelGolsPro(int selGolsPro) {
		this.selGolsPro = selGolsPro;
	}
	public int getSelGolsCont() {
		return selGolsCont;
	}
	public void setSelGolsCont(int selCont) {
		this.selGolsCont = selCont;
	}
	public int getSelFlag() {
		return selFlag;
	}
	public void setSelFlag(int selFlag) {
		this.selFlag = selFlag;
	}
	public long getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(long grupoId) {
		this.grupoId = grupoId;
	}
	
	@Override
	public String toString() {
		return selNome;
	}
}
