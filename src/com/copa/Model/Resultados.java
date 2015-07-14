package com.copa.Model;

import android.database.Cursor;

public class Resultados {

	
	private long idJogos;
	private long jgIdSelecao1;
	private long jgIdSelecao2;
	private String jgSelecao1;
	private String jgSelecao2;
	private String jgReferencia;
	private String jgData;
	private int jgResultado1;
	private int jgResultado2;
	private String jgSituacao = "A";
	private String jgLocal;
	private long GrupoId;
	
	
	public Resultados(Cursor cursor) {
		
		 this.idJogos = cursor.getLong(cursor.getColumnIndex("IdJogos"));
		 this.jgIdSelecao1 = cursor.getLong(cursor.getColumnIndex("jgIdSelecao1"));
		 this.jgIdSelecao2 = cursor.getLong(cursor.getColumnIndex("jgIdSelecao2"));
		 this.jgSelecao1 = cursor.getString(cursor.getColumnIndex("jgSelecao1"));
		 this.jgSelecao2 = cursor.getString(cursor.getColumnIndex("jgSelecao2"));
		 this.jgReferencia = cursor.getString(cursor.getColumnIndex("jgReferencia"));
		 this.jgData = cursor.getString(cursor.getColumnIndex("jgData"));
		 this.jgResultado1 = cursor.getInt(cursor.getColumnIndex("jgResultado1"));
		 this.jgResultado2 = cursor.getInt(cursor.getColumnIndex("jgResultado2"));
		 this.jgSituacao = cursor.getString(cursor.getColumnIndex("jgSituacao"));
		 this.jgLocal = cursor.getString(cursor.getColumnIndex("jgLocal"));
		 this.GrupoId = cursor.getLong(cursor.getColumnIndex("GrupoId"));
	}
	
	public Resultados() {

	}
	
	public long getIdJogos() {
		return idJogos;
	}
	public void setIdJogos(long idJogos) {
		this.idJogos = idJogos;
	}
	public long getJgIdSelecao1() {
		return jgIdSelecao1;
	}
	public void setJgIdSelecao1(long jgIdSelecao1) {
		this.jgIdSelecao1 = jgIdSelecao1;
	}
	public long getJgIdSelecao2() {
		return jgIdSelecao2;
	}
	public void setJgIdSelecao2(long jgIdSelecao2) {
		this.jgIdSelecao2 = jgIdSelecao2;
	}
	public String getJgSelecao1() {
		return jgSelecao1;
	}
	public void setJgSelecao1(String jgSelecao1) {
		this.jgSelecao1 = jgSelecao1;
	}
	public String getJgSelecao2() {
		return jgSelecao2;
	}
	public void setJgSelecao2(String jgSelecao2) {
		this.jgSelecao2 = jgSelecao2;
	}
	public String getJgReferencia() {
		return jgReferencia;
	}
	public void setJgReferencia(String jgReferencia) {
		this.jgReferencia = jgReferencia;
	}
	public String getJgData() {
		return jgData;
	}
	public void setJgData(String jgData) {
		this.jgData = jgData;
	}
	public int getJgResultado1() {
		return jgResultado1;
	}
	public void setJgResultado1(int jgResultado1) {
		this.jgResultado1 = jgResultado1;
	}
	public int getJgResultado2() {
		return jgResultado2;
	}
	public void setJgResultado2(int jgResultado2) {
		this.jgResultado2 = jgResultado2;
	}
	public long getGrupoId() {
		return GrupoId;
	}
	public void setGrupoId(long grupoId) {
		GrupoId = grupoId;
	}

	public String getJgSituacao() {
		return jgSituacao;
	}

	public void setJgSituacao(String jgSituacao) {
		this.jgSituacao = jgSituacao;
	}

	public String getJgLocal() {
		return jgLocal;
	}

	public void setJgLocal(String jgLocal) {
		this.jgLocal = jgLocal;
	}

}
