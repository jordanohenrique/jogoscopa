package com.copa.Controller;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;

import com.copa.utils.WS;

public class ServidorController extends WS{

	public static ServidorController instance = null;
	public static ServidorController getInstance(String url){
		if(instance == null){
			instance = new ServidorController(url);
		}
		return instance;
	}
	
	public ServidorController(String url) {
		super(url);
	}
	
	public boolean statusServidor() throws ConnectException, UnsupportedEncodingException {
		boolean status = false;
		Execute(0, null, 0);
		String json = getResponse();
		
		if (json.toString().substring(0, 4).equals("TRUE")) {
			status = true;
		}
		return status;
	}

}
