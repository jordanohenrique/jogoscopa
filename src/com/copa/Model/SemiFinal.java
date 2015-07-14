package com.copa.Model;

import android.database.Cursor;

public class SemiFinal {

	private long idSemi;
	private long semiIdSelecao;
	private String semiSelNome = "";
	private int semiResultado = 0;
	private int semiReferencia;
	private String semiLocal = "";
	private long semiIdGrupo;
	private long semiPosicao = 0;
	private int selFlag;
	
	public SemiFinal(Cursor cursor) {
		
		this.idSemi = cursor.getLong(cursor.getColumnIndex("idSemi"));
		this.semiIdSelecao = cursor.getLong(cursor.getColumnIndex("semiIdSelecao"));
		this.semiSelNome = cursor.getString(cursor.getColumnIndex("semiSleNome"));
		this.semiResultado = cursor.getInt(cursor.getColumnIndex("semiResultado"));
		this.semiReferencia = cursor.getInt(cursor.getColumnIndex("semiReferencia"));
		this.semiLocal = cursor.getString(cursor.getColumnIndex("semiLocal"));
		this.semiIdGrupo = cursor.getLong(cursor.getColumnIndex("semiIdGrupo"));
		this.semiPosicao = cursor.getLong(cursor.getColumnIndex("semiPosicao"));
	}
	
	public SemiFinal() {
		// TODO Auto-generated constructor stub
	}

	public long getIdSemi() {
		return idSemi;
	}

	public void setIdSemi(long idSemi) {
		this.idSemi = idSemi;
	}

	public long getSemiIdSelecao() {
		return semiIdSelecao;
	}

	public void setSemiIdSelecao(long semiIdSelecao) {
		this.semiIdSelecao = semiIdSelecao;
	}

	public String getSemiSelNome() {
		return semiSelNome;
	}

	public void setSemiSelNome(String semiSelNome) {
		this.semiSelNome = semiSelNome;
	}

	public int getSemiResultado() {
		return semiResultado;
	}

	public void setSemiResultado(int semiSelResultado) {
		this.semiResultado = semiSelResultado;
	}

	public int getSemiReferencia() {
		return semiReferencia;
	}

	public void setSemiReferencia(int semiReferencia) {
		this.semiReferencia = semiReferencia;
	}

	public String getSemiLocal() {
		return semiLocal;
	}

	public void setSemiLocal(String semiLocal) {
		this.semiLocal = semiLocal;
	}

	public long getSemiIdGrupo() {
		return semiIdGrupo;
	}

	public void setSemiIdGrupo(long semiIdGrupo) {
		this.semiIdGrupo = semiIdGrupo;
	}

	public long getSemiPosicao() {
		return semiPosicao;
	}

	public void setSemiPosicao(long semiPosicao) {
		this.semiPosicao = semiPosicao;
	}

	public int getSelFlag() {
		return selFlag;
	}

	public void setSelFlag(int selFlag) {
		this.selFlag = selFlag;
	}
	
}
