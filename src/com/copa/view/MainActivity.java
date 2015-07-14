package com.copa.view;

import java.util.Date;

import com.copa.Controller.JogadoresController;
import com.copa.Controller.ResultadosController;
import com.copa.Controller.SelecoesController;
import com.copa.Controller.ServidorController;
import com.copa.DAO.DadosBD;
import com.copa.Model.Parametros;
import com.copa.jogoscopa.R;
import com.copa.jogoscopa.R.id;
import com.copa.utils.GeraBancoSQLite;
import com.copa.utils.Utils;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Resources.Theme;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	Button btnGrupos, btnClassificacao, btnSelecoes;
	ProgressDialog progressDialog;
	Context context;
	TextView data, versao;
	
	Time time = new Time("hh:mm:ss");
	PackageInfo info;
	Date date = new Date();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        
        Utils.db = openOrCreateDatabase("COPA", Context.MODE_PRIVATE, null);
        new GeraBancoSQLite();
        
        data = (TextView) findViewById(R.id.txtData);
        versao = (TextView) findViewById(R.id.txtVersao);
        
        data.setText("Por:JordanoSantos");
        versao.setText("Versão: "+Parametros.getParVersao().toString());
        
        btnClassificacao=(Button)findViewById(R.id.btnClassificacao);
        btnGrupos =(Button)findViewById(R.id.btnGrupos);
        btnSelecoes=(Button)findViewById(R.id.btnSelecoes);       
        
        btnGrupos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				Intent iten = new Intent(MainActivity.this, GruposActivity.class);
				startActivity(iten);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}
			
		});
        
        btnSelecoes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent Jogadores = new Intent(MainActivity.this, SelecoesActivity.class);
				startActivity(Jogadores);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}
		});
        
        btnClassificacao.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		Bundle bundle = new Bundle();
        		if(Parametros.getParClassif().equalsIgnoreCase("F")){
        			Intent classificacao = new Intent(MainActivity.this, ClassificacaoActivity.class);
        			startActivity(classificacao);
        			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        		
        		}else if(Parametros.getParOitavas().equalsIgnoreCase("F")){
        			Intent oitavas = new Intent(MainActivity.this, OitavasActivity.class);
        			startActivity(oitavas);
        			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        		
        		}else if(Parametros.getParQuartas().equalsIgnoreCase("F")){
        			Intent oitavas = new Intent(MainActivity.this, QuartasActivity.class);
    				bundle.putString("A", "TRUE");
    				oitavas.putExtras(bundle);
        			startActivity(oitavas);
        			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        		
        		}else if(Parametros.getParSemi().equalsIgnoreCase("F")){
        			Intent semi = new Intent(MainActivity.this, SemiFinalActivity.class);
    				bundle.putString("A", "TRUE");
    				semi.putExtras(bundle);
        			startActivity(semi);
        			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        		}else{
        			Intent semi = new Intent(MainActivity.this, FinalActivity.class);
    				bundle.putString("A", "TRUE");
    				semi.putExtras(bundle);
        			startActivity(semi);
        			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        		}
        		
        	}
        });
    }
    
   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case id.Limpar:
			clickAlertdialog();
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
    public void clickAlertdialog(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Excluir Resultados?");
        builder.setCancelable(false);
        builder.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       if(DadosBD.getInstance().limpaBancoDados()){
                    	   Toast.makeText(getApplicationContext(), "Os resultados foram excluídos!", Toast.LENGTH_SHORT).show();
                       }else{
                    	   
                       }
                    	   
                    }
                });
        builder.setNegativeButton("Não",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
	}
    
    private PackageInfo getVersaoNome(){
    	try {
			return getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (Exception e) {
			return null;
		}   	
    }
    
    private void launchDialogEnviar(){
    	progressDialog = ProgressDialog.show(MainActivity.this,null, "Verificando Servidor", true);
    	progressDialog.setCancelable(true);
    
    	new Thread(new Runnable() {
    		Handler handler = new Handler();
			
			@Override
			public void run() {
				try {
					
					if (ServidorController.getInstance(Utils.URL + "/servidor/status/").statusServidor()) {

							handler.post(new Runnable() {
								@Override
								public void run() {
									progressDialog.setMessage("Atualizando selecões...");
								}
							});
							new SelecoesController(Utils.URL+ "/selecao/set/").setSelecoes();
							handler.post(new Runnable() {
								@Override
								public void run() {
									progressDialog.setMessage("Atualizando Resultados...");
								}
							});
							new ResultadosController(Utils.URL+ "/resultado/set/").setResultados();
							handler.post(new Runnable() {
								@Override
								public void run() {
									progressDialog.setMessage("Atualizando Jogadores...");
								}
							});
							new JogadoresController(Utils.URL+ "/jogador/set/").setJogadores();
							
					}else{
						progressDialog.dismiss();
						Toast.makeText(getApplicationContext(), "Servidor Indisponível!", Toast.LENGTH_SHORT).show();
						
					}
				} catch (Exception e) {
					progressDialog.dismiss();
					Toast.makeText(getApplicationContext(), "Falha na sincronização! Selecione o grupo. "+e, Toast.LENGTH_SHORT).show();
				}
				progressDialog.dismiss();
			}
		}).start();    	
    }  
}
