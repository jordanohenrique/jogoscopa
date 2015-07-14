package com.copa.view;

import java.util.ArrayList;
import java.util.List;

import com.copa.Controller.FinalController;
import com.copa.Controller.QuartasController;
import com.copa.Controller.ServidorController;
import com.copa.DAO.GrupoDAO;
import com.copa.DAO.OitavasDAO;
import com.copa.DAO.QuartasDAO;
import com.copa.DAO.SemiFinalDAO;
import com.copa.Model.OitavasFinal;
import com.copa.Model.Parametros;
import com.copa.Model.QuartasFinal;
import com.copa.Model.SemiFinal;
import com.copa.fragments.AdapterQuartas;
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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class QuartasActivity extends Activity{

	private List<QuartasFinal> CollectionQuartas = new ArrayList<QuartasFinal>();
	private ListView listView;
	private Bundle bundle;
	private ProgressDialog progressDialog;
	boolean acao = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityquartas);
		
		Intent intent = getIntent();
		Bundle bundlequa = intent.getExtras();
		if(bundlequa.getString("A").equalsIgnoreCase("TRUE")){
			acao = true;
		}
		
		listView = (ListView) findViewById(R.id.listQuartas);
		 agrupaJogos();
			
		AdapterQuartas adapterQuartas = new AdapterQuartas(QuartasActivity.this, CollectionQuartas);
		listView.setAdapter(adapterQuartas);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				bundle = new Bundle();
				Intent intent = new Intent(QuartasActivity.this, OitavasDialog.class);
				if(CollectionQuartas.get(position).getQuaSelNome() != null){
					bundle.putString("selecao1", CollectionQuartas.get(position).getQuaSelNome().toString());
					bundle.putString("selecao2", CollectionQuartas.get(position).getQuaSelNome1().toString());
					bundle.putInt("resultado1", CollectionQuartas.get(position).getQuaResultado());
					bundle.putInt("resultado2", CollectionQuartas.get(position).getQuaResultado1());
					intent.putExtras(bundle);
					startActivityForResult(intent, 1);
				}else{
					Toast.makeText(getApplicationContext(), "Os resultados não podem ser alterados"
							+ "  antes de fechar Oitavas de Final!! Continue acompanhando os resultados.", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode ==1){
			if((Integer.parseInt(data.getStringExtra("resultado1")) > 0) || (Integer.parseInt(data.getStringExtra("resultado2")) > 0)){
				Utils.db.execSQL("Update Quartas set quaResultado = "+Integer.parseInt(data.getStringExtra("resultado1").toString())+" Where quaSelNome = '"+data.getStringExtra("selecao1").toString()+"';");
				Utils.db.execSQL("Update Quartas set quaResultado = "+Integer.parseInt(data.getStringExtra("resultado2").toString())+" Where quaSelNome = '"+data.getStringExtra("selecao2").toString()+"';");
				Toast.makeText(getApplicationContext(), "Resultado gravado!", Toast.LENGTH_SHORT).show();
				AdapterQuartas adapterQuartas = new AdapterQuartas(QuartasActivity.this, agrupaJogos());
				listView.setAdapter(adapterQuartas);
			}
		}
	}
	
	private List<QuartasFinal> agrupaJogos(){

		if(acao){
			CollectionQuartas = QuartasDAO.getInstance().mergeDadosQuartas();
			
		}else{
			List<OitavasFinal> listOitavas = OitavasDAO.getInstance().mergeDadosOitavas();
			int sel1 = 0, sel2 = 1;
			
			while(sel1 < listOitavas.size()){
				QuartasFinal quartasFinal = new QuartasFinal();
				quartasFinal.setQuaSelNome(listOitavas.get(sel1).getOitPosicao()+"º  "+(String)GrupoDAO.getIstance().getGrupo(listOitavas.get(sel1).getOitIdGrupo()).toString().substring(6,7)+"  ou  "
						+ listOitavas.get(sel1).getOitPosicao1()+"º  "+(String)GrupoDAO.getIstance().getGrupo(listOitavas.get(sel1).getOitIdGrupo1()).toString().substring(6,7));
				
				quartasFinal.setQuaSelNome1(listOitavas.get(sel2).getOitPosicao()+"º  "+(String)GrupoDAO.getIstance().getGrupo(listOitavas.get(sel2).getOitIdGrupo()).toString().substring(6,7)+"  ou  "
						+ listOitavas.get(sel2).getOitPosicao1()+"º  "+(String)GrupoDAO.getIstance().getGrupo(listOitavas.get(sel2).getOitIdGrupo1()).toString().substring(6,7));
				
				quartasFinal.setQuaResultado(0);
				quartasFinal.setQuaResultado1(0);
				quartasFinal.setQuaLocal(QuartasDAO.getInstance().localJogo(listOitavas.get(sel1).getOitReferencia()));
				quartasFinal.setSelFlag(R.drawable.trofeucopa);
				quartasFinal.setSelFlag1(R.drawable.trofeucopa);
				
				CollectionQuartas.add(quartasFinal);
				
				sel1 = sel1 + 2;
				sel2 = sel2 + 2;
			}
		}
		return CollectionQuartas;
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
			if(fechaQuartas()){
				Intent intent = new Intent(QuartasActivity.this, SemiFinalActivity.class);
				bundle.putString("A", "TRUE");
				intent.putExtras(bundle);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}else{
				Intent intent = new Intent(QuartasActivity.this, SemiFinalActivity.class);
				bundle.putString("A", "FALSE");
				intent.putExtras(bundle);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}
			break;
		case id.Atualizar:
			if(Parametros.getParOitavas().equalsIgnoreCase("T"))
				launchDialog();
			else
				Toast.makeText(getApplicationContext(), "Não há atualização disponível.", 2000).show();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private boolean fechaQuartas(){
		boolean acao = false;
		List<SemiFinal> listSemiFinal = new ArrayList<SemiFinal>();
		VerificaResultados veificaResultado = new VerificaResultados();
		try {
			listSemiFinal = veificaResultado.fechaResultadosQuartas();
			if(listSemiFinal.size() >= 8){
				acao = SemiFinalDAO.getInstance().updateSemiFinal(listSemiFinal);
				if(acao){
					Utils.db.execSQL("Update Parametros set parQuartas = 'T'");
					Toast.makeText(getApplicationContext(), "Quartas de Final Colcuida!!", Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(getApplicationContext(), "Quartas de Final não foi finalizada! Verifique todos resultados. ", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Quartas de Final não foi finalizada! Verifique todos resultados. ", Toast.LENGTH_LONG).show();
		}
		return acao;
	}
	
	
	private void launchDialog(){
    	progressDialog = ProgressDialog.show(QuartasActivity.this,null, "Verificando Servidor!!", true);
    	progressDialog.setCancelable(true);
    
    	new Thread(new Runnable() {
    		Handler handler = new Handler();
			
			@Override
			public void run() {
				try {
					if (ServidorController.getInstance(Utils.URL + "/status").statusServidor()) {

						if(Parametros.getParClassif().equalsIgnoreCase("T")){
							new QuartasController(Utils.URL+ "/quartas/getall/").updateQuartas();
							handler.post(new Runnable() {
								@Override
								public void run() {
									progressDialog.setMessage("Atualizando Quartas de Final...");
									onStop();
								}
							});
							Thread.sleep(10000);
							onStop();
						}
					}else{
						Toast.makeText(getApplicationContext(), "Servidor Indisponível!", Toast.LENGTH_SHORT).show();
						progressDialog.dismiss();
					}
				}catch (Exception e) {
					progressDialog.dismiss();
					Toast.makeText(getApplicationContext(), "Falha na sincronização!. "+e, Toast.LENGTH_SHORT).show();
				}
				progressDialog.dismiss();
			}
		}).start();    	
    }
	
	@Override
	protected void onStop() {
		 agrupaJogos();
		AdapterQuartas adapterQuartas = new AdapterQuartas(QuartasActivity.this, CollectionQuartas);
		listView.setAdapter(adapterQuartas);
		super.onStop();
	}
}
