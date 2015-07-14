package com.copa.Model;

import android.database.Cursor;


public class Estadio {

	private long idEstadio;
	private String estNome;
	private String estApelido;
	private String estCidade;
	private String estEndereco;
	private String estCapacidade;
	private String estVagas;
	private int estImagem;
	
	
	public Estadio(Cursor cursor) throws Exception{
		
		this.idEstadio = cursor.getLong(cursor.getColumnIndex("idEstadio"));
		this.estNome = cursor.getString(cursor.getColumnIndex("estNome"));
		this.estApelido = cursor.getString(cursor.getColumnIndex("estApelido"));
		this.estCidade = cursor.getString(cursor.getColumnIndex("estCidade"));
		this.estEndereco = cursor.getString(cursor.getColumnIndex("estEndereco"));
		this.estCapacidade = cursor.getString(cursor.getColumnIndex("estCapacidade"));
		this.estVagas = cursor.getString(cursor.getColumnIndex("estVagas"));
		this.estImagem = cursor.getInt(cursor.getColumnIndex("estImagem"));
	}
	
	public Estadio() {
		
	}


	public long getIdEstadio() {
		return idEstadio;
	}

	public void setIdEstadio(long idEstadio) {
		this.idEstadio = idEstadio;
	}

	public String getEstNome() {
		return estNome;
	}

	public void setEstNome(String estNome) {
		this.estNome = estNome;
	}

	public String getEstApelido() {
		return estApelido;
	}

	public void setEstApelido(String estApelido) {
		this.estApelido = estApelido;
	}

	public String getEstCidade() {
		return estCidade;
	}

	public void setEstCidade(String estCidade) {
		this.estCidade = estCidade;
	}

	public String getEstEndereco() {
		return estEndereco;
	}

	public void setEstEndereco(String estEndereco) {
		this.estEndereco = estEndereco;
	}

	public String getEstCapacidade() {
		return estCapacidade;
	}

	public void setEstCapacidade(String estCapacidade) {
		this.estCapacidade = estCapacidade;
	}

	public String getEstVagas() {
		return estVagas;
	}

	public void setEstVagas(String estVagas) {
		this.estVagas = estVagas;
	}

	public int getEstImagem() {
		return estImagem;
	}

	public void setEstImagem(int estImagem) {
		this.estImagem = estImagem;
	}
	
}
