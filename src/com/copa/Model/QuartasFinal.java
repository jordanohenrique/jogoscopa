package com.copa.Model;

import android.database.Cursor;

public class QuartasFinal {

	private long idQuartas;
	private long quaIdSelecao;
	private String quaSelNome;
	private int quaResultado;
	private int quaReferencia;
	private long quaIdGrupo;
	private long quaPosicao;
	private String quaLocal;
	private int selFlag;
	
	private long quaIdGrupo1;
	private long quaIdSelecao1;
	private String quaSelNome1;
	private int quaResultado1;
	private int quaReferencia1;
	private int selFlag1;
	
	public QuartasFinal(Cursor cursor) {
		this.idQuartas = cursor.getLong(cursor.getColumnIndex("idQuartas"));
		this.quaIdSelecao = cursor.getLong(cursor.getColumnIndex("quaIdSelecao"));
		this.quaSelNome = cursor.getString(cursor.getColumnIndex("quaSelNome"));
		this.quaReferencia = cursor.getInt(cursor.getColumnIndex("quaReferencia"));
		this.quaResultado = cursor.getInt(cursor.getColumnIndex("quaResultado"));
		this.quaLocal = cursor.getString(cursor.getColumnIndex("quaLocal"));
		this.quaIdGrupo = cursor.getLong(cursor.getColumnIndex("quaIdGrupo"));

	}
	
	public QuartasFinal() {
		//construtor padrao
	}

	public long getIdQuartas() {
		return idQuartas;
	}

	public void setIdQuartas(long idQuartas) {
		this.idQuartas = idQuartas;
	}

	public long getQuaIdSelecao() {
		return quaIdSelecao;
	}

	public void setQuaIdSelecao(long quaIdSelecao) {
		this.quaIdSelecao = quaIdSelecao;
	}

	public long getQuaIdGrupo() {
		return quaIdGrupo;
	}

	public void setQuaIdGrupo(long quaIdGrupo) {
		this.quaIdGrupo = quaIdGrupo;
	}

	public long getQuaPosicao() {
		return quaPosicao;
	}

	public void setQuaPosicao(long quaPosicao) {
		this.quaPosicao = quaPosicao;
	}

	public String getQuaSelNome() {
		return quaSelNome;
	}

	public void setQuaSelNome(String quaSelNome) {
		this.quaSelNome = quaSelNome;
	}

	public String getQuaSelNome1() {
		return quaSelNome1;
	}

	public void setQuaSelNome1(String quaSelNome1) {
		this.quaSelNome1 = quaSelNome1;
	}

	public int getQuaResultado() {
		return quaResultado;
	}

	public void setQuaResultado(int quaResultado) {
		this.quaResultado = quaResultado;
	}

	public int getQuaResultado1() {
		return quaResultado1;
	}

	public void setQuaResultado1(int quaResultado1) {
		this.quaResultado1 = quaResultado1;
	}

	public int getQuaReferencia() {
		return quaReferencia;
	}

	public void setQuaReferencia(int quaReferencia) {
		this.quaReferencia = quaReferencia;
	}

	public long getQuaIdGrupo1() {
		return quaIdGrupo1;
	}

	public void setQuaIdGrupo1(long quaIdGrupo1) {
		this.quaIdGrupo1 = quaIdGrupo1;
	}

	public long getQuaIdSelecao1() {
		return quaIdSelecao1;
	}

	public void setQuaIdSelecao1(long quaIdSelecao1) {
		this.quaIdSelecao1 = quaIdSelecao1;
	}

	public int getQuaReferencia1() {
		return quaReferencia1;
	}

	public void setQuaReferencia1(int quaReferencia1) {
		this.quaReferencia1 = quaReferencia1;
	}

	public String getQuaLocal() {
		return quaLocal;
	}

	public void setQuaLocal(String quaLocal) {
		this.quaLocal = quaLocal;
	}

	public int getSelFlag() {
		return selFlag;
	}

	public void setSelFlag(int selFlag) {
		this.selFlag = selFlag;
	}

	public int getSelFlag1() {
		return selFlag1;
	}

	public void setSelFlag1(int selFlag1) {
		this.selFlag1 = selFlag1;
	}
	
	
}
