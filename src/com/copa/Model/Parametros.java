package com.copa.Model;

import android.database.Cursor;

public class Parametros {

	private static long idParametro;
	private static String parURL;
	private static String parVersao = "0.0";
	private static String parVersaoBD;
	private static String parOitavas;
	private static String parQuartas;
	private static String parSemi;
	private static String parClassif;
	
	
	public Parametros(Cursor cursor) {
		
		Parametros.idParametro = cursor.getLong(cursor.getColumnIndex("idParametro"));
		Parametros.parURL = cursor.getString(cursor.getColumnIndex("parURL"));
		Parametros.parVersao = cursor.getString(cursor.getColumnIndex("parVersao"));
		Parametros.parVersaoBD = cursor.getString(cursor.getColumnIndex("parVersaoBD"));
		Parametros.parOitavas = cursor.getString(cursor.getColumnIndex("parOitavas"));
		Parametros.parQuartas = cursor.getString(cursor.getColumnIndex("parQuartas"));
		Parametros.parSemi = cursor.getString(cursor.getColumnIndex("parSemi"));
		Parametros.parClassif = cursor.getString(cursor.getColumnIndex("parClassif"));

	}
	
	public Parametros() {
		// TODO Auto-generated constructor stub
	}

	public static long getIdParametro() {
		return idParametro;
	}

	public static void setIdParametro(long idParametro) {
		Parametros.idParametro = idParametro;
	}

	public static String getParURL() {
		return parURL;
	}

	public static void setParURL(String parURL) {
		Parametros.parURL = parURL;
	}

	public static String getParVersao() {
		return parVersao;
	}

	public static void setParVersao(String parVersao) {
		Parametros.parVersao = parVersao;
	}

	public static String getParVersaoBD() {
		return parVersaoBD;
	}

	public static void setParVersaoBD(String parVersaoBD) {
		Parametros.parVersaoBD = parVersaoBD;
	}

	public static String getParOitavas() {
		return parOitavas;
	}

	public static void setParOitavas(String parOitavas) {
		Parametros.parOitavas = parOitavas;
	}

	public static String getParQuartas() {
		return parQuartas;
	}

	public static void setParQuartas(String parQuartas) {
		Parametros.parQuartas = parQuartas;
	}

	public static String getParSemi() {
		return parSemi;
	}

	public static void setParSemi(String parSemi) {
		Parametros.parSemi = parSemi;
	}

	public static String getParClassif() {
		return parClassif;
	}

	public static void setParClassif(String parClassif) {
		Parametros.parClassif = parClassif;
	}
	
}
