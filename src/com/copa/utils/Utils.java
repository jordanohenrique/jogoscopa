package com.copa.utils;

import android.database.sqlite.SQLiteDatabase;

public class Utils {
	
	public static String ATIVO = "A";
	public static String INATIVO = "I";
	public static String DELETE = "D";
	
	public static String ABERTO = "A";
	public static String FECHADO = "F";
	
	public static String GL ="GL";
	public static String ZA ="ZA";
	public static String LA ="LA";
	public static String MC ="MC";
	public static String AT ="AT";	
	public static String TE ="TE";	
	
	public static String URL = "http://192.168.1.5:8080/RestfullTabelaJogos";
	
	public static SQLiteDatabase db;
	
	public static String SQL;

	public static String getSQL() {
		return SQL;
	}

	public static void setSQL(String setSQL) {
		Utils.SQL = setSQL;
	}

}
