package com.copa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class WS {

	private static final int GET=0, POST=1, PUT=2, DELETE=3, GETID=4;
    
    private String url;
    
    private int responseCode;
    private String message;

    private String response;

    public String getResponse() {
        return response;
    }

    public String getErrorMessage() {
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }
    
    public WS(String url) {
        this.url = url;
    }
    
    public void Execute(int method, String dados, long id) throws ConnectException, UnsupportedEncodingException{
     
       switch (method) {
         case GET:
             HttpGet httpGet = new HttpGet(url);
             executeRequest(httpGet, this.url);
             break;
         case POST:
             HttpPost httpPost = new HttpPost(url);
             StringEntity pesInsere = new StringEntity(dados.toString(),"UTF-8");
             pesInsere.setContentType("application/json");
             httpPost.setEntity(pesInsere);
             executeRequest(httpPost, this.url);
             break;
         case PUT:
             HttpPut httpPut = new HttpPut(url);
             StringEntity pesAtualiza = new StringEntity(dados.toString(),"UTF-8");
             pesAtualiza.setContentType("application/json");
             httpPut.setEntity(pesAtualiza);
             executeRequest(httpPut, this.url);
             break;
         case DELETE:
         	HttpDelete httpDelete = new HttpDelete(url+id);
         	executeRequest(httpDelete, this.url);
             break;
         case GETID:
             HttpGet httpGetId = new HttpGet(url+id);
             executeRequest(httpGetId, this.url);
             break;
         default:
             throw new IllegalArgumentException("Unknown Request.");
         }   
    	
    }
    
    private void executeRequest(HttpUriRequest request, String url)
    {
        HttpClient client = new DefaultHttpClient();
        
        HttpResponse httpResponse;

        try {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {

                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);

                // Closing the input stream will trigger connection release
                instream.close();
            }

        } catch (ClientProtocolException e)  {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        } catch (IOException e) {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
    }
    
    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
	
}
