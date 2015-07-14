package com.copa.view;

import java.util.ArrayList;
import java.util.List;

import com.copa.Controller.OitavasController;
import com.copa.Controller.ResultadosController;
import com.copa.Controller.ServidorController;
import com.copa.DAO.OitavasDAO;
import com.copa.DAO.QuartasDAO;
import com.copa.Model.OitavasFinal;
import com.copa.Model.Parametros;
import com.copa.Model.QuartasFinal;
import com.copa.fragments.AdapterOitavas;
import com.copa.jogoscopa.R;
import com.copa.jogoscopa.R.id;
import com.copa.utils.Utils;
import com.copa.utils.VerificaResultados;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class OitavasActivity extends Activity{

	private List<OitavasFinal> CollectionOitavas = new ArrayList<OitavasFinal>();
	private ListView listView;
	private Bundle bundle;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitylistoitavas);

		listView = (ListView) findViewById(R.id.listOitavas);
		agrupaJogos();
			
		AdapterOitavas adapterOitavas = new AdapterOitavas(this, CollectionOitavas);
		listView.setAdapter(adapterOitavas);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				bundle = new Bundle();

				if(CollectionOitavas.get(position).getOitSelNome() != null){
					Intent intent = new Intent(OitavasActivity.this, OitavasDialog.class);
					bundle.putString("selecao1", CollectionOitavas.get(position).getOitSelNome().toString());
					bundle.putString("selecao2", CollectionOitavas.get(position).getOitSelNome1().toString());
					bundle.putInt("resultado1", CollectionOitavas.get(position).getOitResultado());
					bundle.putInt("resultado2", CollectionOitavas.get(position).getOitResultado1());
					intent.putExtras(bundle);
					startActivityForResult(intent, 1);
				}else{
					Toast.makeText(getApplicationContext(), "Os resultados não poderam ser alterados"
							+ "  antes de fechar a fase de classificação!! Continue acompanhando os resultados.", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode ==1){
			if((Integer.parseInt(data.getStringExtra("resultado1")) > 0) || (Integer.parseInt(data.getStringExtra("resultado2")) > 0)){
				Utils.db.execSQL("Update Oitavas set oitResultado = "+Integer.parseInt(data.getStringExtra("resultado1").toString())+" Where oitSelNome = '"+data.getStringExtra("selecao1").toString()+"';");
				Utils.db.execSQL("Update Oitavas set oitResultado = "+Integer.parseInt(data.getStringExtra("resultado2").toString())+" Where oitSelNome = '"+data.getStringExtra("selecao2").toString()+"';");
				Toast.makeText(getApplicationContext(), "Resultado gravado!", Toast.LENGTH_SHORT).show();
				AdapterOitavas adapterOitavas = new AdapterOitavas(this, agrupaJogos());
				listView.setAdapter(adapterOitavas);

			}
		}
	}
	
	@Override
	protected void onStop() {
		agrupaJogos();
		AdapterOitavas adapterOitavas = new AdapterOitavas(this, CollectionOitavas);
		listView.setAdapter(adapterOitavas);
		super.onStop();
	}
	@Override
	protected void onResume() {
		super.onResume();
		agrupaJogos();
		AdapterOitavas adapterOitavas = new AdapterOitavas(this, CollectionOitavas);
		listView.setAdapter(adapterOitavas);
	}
	
	private List<OitavasFinal> agrupaJogos(){
		CollectionOitavas = OitavasDAO.getInstance().mergeDadosOitavas();
		return CollectionOitavas;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.oitavas, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Bundle bundle = new Bundle();
		switch (item.getItemId()) {
		case id.Next:
			if(fechaOitavas()){
				Intent intent = new Intent(OitavasActivity.this, QuartasActivity.class);
				bundle.putString("A", "TRUE");
				intent.putExtras(bundle);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}else{
				Intent intent = new Intent(OitavasActivity.this, QuartasActivity.class);
				bundle.putString("A", "FALSE");
				intent.putExtras(bundle);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}
			break;
		case id.Atualizar:
			if(Parametros.getParClassif().equalsIgnoreCase("T"))
				launchDialog();
			else
				Toast.makeText(getApplicationContext(), "Não há atualização disponível.", 2000).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private boolean fechaOitavas(){
		boolean acao = false;
		List<QuartasFinal> listQuartasFinal = new ArrayList<QuartasFinal>();
		VerificaResultados veificaResultado = new VerificaResultados();
		try {
			listQuartasFinal = veificaResultado.fechaResultadosOitavas();
			if(listQuartasFinal.size() >= 8){
				acao = QuartasDAO.getInstance().updateQuartas(listQuartasFinal);
				if(acao){
					Utils.db.execSQL("Update Parametros set parOitavas = 'T'");
					Toast.makeText(getApplicationContext(), "Oitavas de Final Colcuida!!", Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(getApplicationContext(), "Oitavas não foi finalizada! Verifique todos resultados. ", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Oitavas não foi finalizada! Verifique todos resultados. ", Toast.LENGTH_LONG).show();
		}
		return acao;
	}
	
    private void launchDialog(){
    	progressDialog = ProgressDialog.show(OitavasActivity.this,null, "Verificando Servidor!!", true);
    	progressDialog.setCancelable(true);
    
    	new Thread(new Runnable() {
    		Handler handler = new Handler();
			
			@Override
			public void run() {
				try {
					if (ServidorController.getInstance(Utils.URL + "/status").statusServidor()) {

						if(Parametros.getParClassif().equalsIgnoreCase("T")){
							new OitavasController(Utils.URL+ "/oitavas/getall/").updateOitavas();	
							handler.post(new Runnable() {
								@Override
								public void run() {
									progressDialog.setMessage("Atualizando Oitavas de Final...");
								}
							});
							onStop();
						}
					}else{
						Toast.makeText(getApplicationContext(), "Servidor Indisponível!", Toast.LENGTH_SHORT).show();
						progressDialog.dismiss();
					}
				}catch (Exception e) {
					progressDialog.dismiss();
					Toast.makeText(getApplicationContext(), "Falha na sincronização! Selecione o grupo. "+e, Toast.LENGTH_SHORT).show();
				}
				progressDialog.dismiss();
			}
		}).start();    	
    }
    
}
