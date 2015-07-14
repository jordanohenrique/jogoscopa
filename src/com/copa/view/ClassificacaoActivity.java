package com.copa.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.copa.Controller.ResultadosController;
import com.copa.Controller.SelecoesController;
import com.copa.Controller.ServidorController;
import com.copa.DAO.OitavasDAO;
import com.copa.DAO.SelecoesDAO;
import com.copa.Model.OitavasFinal;
import com.copa.Model.Selecoes;
import com.copa.fragments.ExpandClassificacao;
import com.copa.jogoscopa.R;
import com.copa.jogoscopa.R.id;
import com.copa.utils.Utils;
import com.copa.utils.VerificaResultados;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class ClassificacaoActivity extends Activity {
	
	List<Selecoes> SelecColletion = new ArrayList<Selecoes>();
	List<Map<String, String>> classificacao;
	Map<String, List<Selecoes>> selecoesCollection;
	ExpandableListView ExpandListView;
	List<Selecoes> classifList;
	List<String> groupList;
	Cursor cursor;
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitygrupos);
		carregaLista();
	}
	
	private void createGroupList() {
        groupList = new ArrayList<String>();
        try {
        	 cursor = Utils.db.rawQuery("Select * from Grupo", null);
             
             while(cursor.moveToNext()){
             	groupList.add(cursor.getString(cursor.getColumnIndex("gruDescricao")));
             }
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			cursor.close();
		}
       

    }
	
	 private void createCollection() {
	    	
	        selecoesCollection = new LinkedHashMap<String, List<Selecoes>>();
	 
	        for (String grupo : groupList) {
	            if (grupo.contains("GRUPO A")) {
	                	loadChild(grupo);
	            	} else if (grupo.contains("GRUPO B")){
	            		loadChild(grupo);
	            	}else if (grupo.contains("GRUPO C")){
	            		loadChild(grupo);
	        		}else if (grupo.contains("GRUPO D")){
	        			loadChild(grupo);
	    			}else if (grupo.contains("GRUPO E")){
	    				loadChild(grupo);
					}else if (grupo.contains("GRUPO F")){
						loadChild(grupo);
					}else if (grupo.contains("GRUPO H")){
						loadChild(grupo);
					}else{
						loadChild(grupo);
					}
	 
	            selecoesCollection.put(grupo, SelecoesDAO.getInstance().getSelecoesByGrupo(grupo));
	        }
	      }
	 
	    private void loadChild(String grupo) {
	    	
	        classifList = new ArrayList<Selecoes>();
	        classifList = SelecoesDAO.getInstance().loadChild(grupo);
	        
	    }
	    
	
	public void carregaLista(){
		createGroupList();
		createCollection();
		
		ExpandListView = (ExpandableListView) findViewById(R.id.expListSelecoes);
		final ExpandClassificacao expandAdapter = new ExpandClassificacao(this, selecoesCollection, groupList);
		ExpandListView.setAdapter(expandAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.classificacao, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case id.Classificacao:
				launchDialog();
			
			break;
		case id.Plus:
			break;
		case id.Oitavas:
			getOitavas();
			break;
		case id.FechaClassif:
			fechaClassificacao();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void getOitavas(){
		try {
			if(OitavasDAO.getInstance().getCountOitavas() >= 16){
				Intent oitavas = new Intent(ClassificacaoActivity.this, OitavasActivity.class);
				startActivity(oitavas);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
				
			}else{
				Toast.makeText(getApplicationContext(), "Fase classificatória em andamento!!", Toast.LENGTH_LONG).show();
			}				
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Falha ao carregar os dados!!", Toast.LENGTH_SHORT).show();
		}
	}
	public void fechaClassificacao(){
		List<OitavasFinal> listOitavas = new ArrayList<OitavasFinal>();
		VerificaResultados verificaResultados = new VerificaResultados();
		try {
			listOitavas = verificaResultados.fechaReultadosClassificacao();
			if(listOitavas.size() >= 16){
				boolean acao = OitavasDAO.getInstance().updateOitavas(listOitavas);
				if(acao){
					Utils.db.execSQL("Update Parametros set parClassif = 'T'");
					Toast.makeText(getApplicationContext(), "Classificação fechada com sucesso", Toast.LENGTH_SHORT).show();
				}
			}
			else{
				Toast.makeText(getApplicationContext(), "Fase de classificação não foi finalizada! Verifique todos resultados. ", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Falha ao analisar resultados!! Verifique todos resultados. ", Toast.LENGTH_LONG).show();
		}
	}	

	private void launchDialog(){
    	progressDialog = ProgressDialog.show(ClassificacaoActivity.this,null, "Verificando Servidor!!", true);
    	progressDialog.setCancelable(true);
    
    	new Thread(new Runnable() {
    		Handler handler = new Handler();
			
			@Override
			public void run() {
				try {
					if(ServidorController.getInstance(Utils.URL + "/status").statusServidor()){
						new SelecoesController(Utils.URL+ "/selecao/getall/").updateSelecoes();
						handler.post(new Runnable() {
							@Override
							public void run() {
								progressDialog.setMessage("Atualizando Classificação...");
								onStop();
							}
						});
						Thread.sleep(15000);
					}
				} catch (Exception e) {
					progressDialog.dismiss();
				}
				progressDialog.dismiss();
			}
		}).start();  
    	
    } 
	
	@Override
	protected void onStop() {
		carregaLista();
		super.onStop();
	}
}
