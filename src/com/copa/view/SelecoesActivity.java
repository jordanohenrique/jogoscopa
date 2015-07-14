package com.copa.view;

import com.copa.Controller.JogadoresController;
import com.copa.Controller.ServidorController;
import com.copa.Model.Selecoes;
import com.copa.jogoscopa.R;
import com.copa.jogoscopa.R.id;
import com.copa.utils.Utils;
import com.copa.view.SelecoesFragment.onSelectedItem;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.*;
import android.view.Menu;
import android.view.MenuItem;

public class SelecoesActivity extends Activity implements onSelectedItem{

	boolean detailView = false;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frameactivityselecoes);

		if (savedInstanceState == null) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			SelecoesFragment frgListSelecoes = new SelecoesFragment();
			ft.add(R.id.frameSelJog, frgListSelecoes, "Selecoes");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}
		
		if(findViewById(R.id.frgJogador) != null){
			detailView= true;
			getFragmentManager().popBackStack();
			JogadorFragment frgJogadores = (JogadorFragment)getFragmentManager().findFragmentById(R.id.frgJogador);
			
			if(frgJogadores == null){
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				JogadorFragment displayJogadores = new JogadorFragment();
				ft.add(R.id.frgJogador, displayJogadores, "Fragment_Jogador1");
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	public void SelectedSelecao(Selecoes selecao, int posicao) {
		
		if (detailView) {
			JogadorFragment displayFragment = (JogadorFragment) getFragmentManager().findFragmentById(R.id.frgJogador);
			displayFragment.updateSelecaoContent(selecao, posicao);

		} else {
			JogadorFragment displayFragment = new JogadorFragment();

			displayFragment.setSelecaoContent(selecao,posicao);

			FragmentTransaction ft = getFragmentManager().beginTransaction();

			ft.add(R.id.frameSelJog, displayFragment, "Fragment_Jogador2");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.selecoes, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case id.Atualiza:
			launchDialog();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private void launchDialog(){
    	progressDialog = ProgressDialog.show(SelecoesActivity.this,null, "Verificando Servidor!!", true);
    	progressDialog.setCancelable(true);
    
    	new Thread(new Runnable() {
    		Handler handler = new Handler();
			
			@Override
			public void run() {
				try {
					if(ServidorController.getInstance(Utils.URL + "/status").statusServidor()){
						new JogadoresController(Utils.URL+ "/jogador/getall/").updateJogadores();
						handler.post(new Runnable() {
							@Override
							public void run() {
								progressDialog.setMessage("Atualizando Jogadores...");
								onStop();
							}
						});
						Thread.sleep(5000);
					}
				} catch (Exception e) {
					progressDialog.dismiss();
				}
				progressDialog.dismiss();
			}
		}).start();  
    	
    } 

}