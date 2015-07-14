package com.copa.Model;

import android.database.Cursor;

public class OitavasFinal {
	
	private long idOitavas;
	private long oitIdSelecao;
	private String oitSelNome = "";
	private int oitResultado = 0;
	private int oitReferencia;
	private String oitLocal = "";
	private long oitIdGrupo;
	private long oitPosicao;
	private int SelFlag;
	
	private String oitSelNome1 = "";
	private int oitResultado1 = 0;
	private long oitIdSelecao1;
	private long oitIdGrupo1;
	private long oitPosicao1;
	private int SelFlag1;
	
	public OitavasFinal(Cursor cursor) {
		
		this.idOitavas = cursor.getLong(cursor.getColumnIndex("idOitavas"));
		this.oitIdSelecao = cursor.getLong(cursor.getColumnIndex("oitIdSelecao"));
		this.oitIdGrupo = cursor.getLong(cursor.getColumnIndex("oitIdGrupo"));
		this.oitPosicao = cursor.getLong(cursor.getColumnIndex("oitPosicao"));
		this.oitSelNome = cursor.getString(cursor.getColumnIndex("oitSelNome"));
		this.oitResultado = cursor.getInt(cursor.getColumnIndex("oitResultado"));
		this.oitReferencia = cursor.getInt(cursor.getColumnIndex("oitReferencia"));
		this.oitLocal = cursor.getString(cursor.getColumnIndex("oitLocal"));
	}
	
	public OitavasFinal() {

	}

	public long getIdOitavas() {
		return idOitavas;
	}

	public void setIdOitavas(long idOitavas) {
		this.idOitavas = idOitavas;
	}

	public long getOitIdSelecao() {
		return oitIdSelecao;
	}

	public void setOitIdSelecao(long oitIdSelecao) {
		this.oitIdSelecao = oitIdSelecao;
	}

	public long getOitIdGrupo() {
		return oitIdGrupo;
	}

	public void setOitIdGrupo(long oitIdGrupo) {
		this.oitIdGrupo = oitIdGrupo;
	}

	public long getOitPosicao() {
		return oitPosicao;
	}

	public void setOitPosicao(long oitPosicao) {
		this.oitPosicao = oitPosicao;
	}

	public String getOitSelNome() {
		return oitSelNome;
	}

	public void setOitSelNome(String oitSelNome) {
		this.oitSelNome = oitSelNome;
	}

	public int getOitResultado() {
		return oitResultado;
	}

	public void setOitResultado(int oitResultado) {
		this.oitResultado = oitResultado;
	}

	public int getOitReferencia() {
		return oitReferencia;
	}

	public void setOitReferencia(int oitReferencia) {
		this.oitReferencia = oitReferencia;
	}

	public String getOitLocal() {
		return oitLocal;
	}

	public void setOitLocal(String oitLocal) {
		this.oitLocal = oitLocal;
	}

	public String getOitSelNome1() {
		return oitSelNome1;
	}

	public void setOitSelNome1(String oitSelNome1) {
		this.oitSelNome1 = oitSelNome1;
	}

	public int getOitResultado1() {
		return oitResultado1;
	}

	public void setOitResultado1(int oitSelResultado1) {
		this.oitResultado1 = oitSelResultado1;
	}

	public long getOitIdGrupo1() {
		return oitIdGrupo1;
	}

	public void setOitIdGrupo1(long oitIdGrupo1) {
		this.oitIdGrupo1 = oitIdGrupo1;
	}

	public long getOitPosicao1() {
		return oitPosicao1;
	}

	public void setOitPosicao1(long oitPosicao1) {
		this.oitPosicao1 = oitPosicao1;
	}

	public int getSelFlag() {
		return SelFlag;
	}

	public void setSelFlag(int selFlag) {
		SelFlag = selFlag;
	}
	
	public int getSelFlag1() {
		return SelFlag1;
	}

	public void setSelFlag1(int selFlag1) {
		SelFlag1 = selFlag1;
	}

	public long getOitIdSelecao1() {
		return oitIdSelecao1;
	}

	public void setOitIdSelecao1(long oitIdSelecao1) {
		this.oitIdSelecao1 = oitIdSelecao1;
	}
	
}
