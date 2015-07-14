package com.copa.view;

import java.util.ArrayList;
import java.util.List;

import com.copa.Controller.ResultadosController;
import com.copa.Controller.SelecoesController;
import com.copa.Controller.ServidorController;
import com.copa.DAO.SelecoesDAO;
import com.copa.Model.Resultados;
import com.copa.Model.Selecoes;
import com.copa.jogoscopa.R;
import com.copa.jogoscopa.R.id;
import com.copa.utils.Utils;
import com.copa.utils.VerificaResultados;

import android.os.Bundle;
import android.os.Handler;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class RodadasActivity extends TabActivity {

	ImageView image1, image2, image3,image4, image5, image6, image7, image8, image9, image10, image11, image12;
	TextView selecao1,selecao2,selecao3,selecao4,selecao5,selecao6,selecao7,selecao8,selecao9,selecao10,selecao11,selecao12;
	TextView jogo1,jogo2,jogo3,jogo4,jogo5,jogo6;
	EditText edtselecao1,edtselecao2,edtselecao3,edtselecao4,edtselecao5,edtselecao6,edtselecao7,edtselecao8,edtselecao9,edtselecao10,edtselecao11,edtselecao12;
	Bundle bundle2;
	String grupo, selecao, idimage;
	TabHost tabHost;
	int[] bandeiras;
	String[] selecoes;
	Resultados resultados;
	List<Resultados> ResultCollection;
	List<Selecoes> SelecCollection;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityrodadas);
		
		castObjetos();
		
		Intent intent = getIntent();
        bundle2 = intent.getExtras();
        if(bundle2.getString("grupo") != ""){
        	grupo = (String.valueOf(bundle2.get("grupo")));        	
        	loadRodadas(grupo);
        }
 
		
		TabSpec rodada1 = getTabHost().newTabSpec("tag1");
		rodada1.setContent(R.id.tab1);
		rodada1.setIndicator("1º Rodada", getResources().getDrawable(R.drawable.ic_menu_name));
        getTabHost().addTab(rodada1);
        
        TabSpec rodada2 = getTabHost().newTabSpec("tag2");
        rodada2.setContent(R.id.tab2);
        rodada2.setIndicator("2º Rodada", getResources().getDrawable(R.drawable.ic_menu_name));
        getTabHost().addTab(rodada2);
        
        TabSpec rodada3 = getTabHost().newTabSpec("tag3");
        rodada3.setContent(R.id.tab3);
        rodada3.setIndicator("3º Rodada", getResources().getDrawable(R.drawable.ic_menu_name));
        getTabHost().addTab(rodada3);
     
        getTabHost().setCurrentTab(0);

	}
	
	public void castObjetos(){
		
		selecao1 = (TextView) findViewById(R.id.selecao1);
		selecao2 = (TextView) findViewById(R.id.selecao2);
		selecao3 = (TextView) findViewById(R.id.selecao3);
		selecao4 = (TextView) findViewById(R.id.selecao4);
		selecao5 = (TextView) findViewById(R.id.selecao5);
		selecao6 = (TextView) findViewById(R.id.selecao6);
		selecao7 = (TextView) findViewById(R.id.selecao7);
		selecao8 = (TextView) findViewById(R.id.selecao8);
		selecao9 = (TextView) findViewById(R.id.selecao9);
		selecao10 = (TextView) findViewById(R.id.selecao10);
		selecao11 = (TextView) findViewById(R.id.selecao11);
		selecao12 = (TextView) findViewById(R.id.selecao12);
		
		//EditText
		edtselecao1 = (EditText) findViewById(R.id.edtselecao1);
		edtselecao2 = (EditText) findViewById(R.id.edtselecao2);
		edtselecao3 = (EditText) findViewById(R.id.edtselecao3);
		edtselecao4 = (EditText) findViewById(R.id.edtselecao4);
		edtselecao5 = (EditText) findViewById(R.id.edtselecao5);
		edtselecao6 = (EditText) findViewById(R.id.edtselecao6);
		edtselecao7 = (EditText) findViewById(R.id.edtselecao7);
		edtselecao8 = (EditText) findViewById(R.id.edtselecao8);
		edtselecao9 = (EditText) findViewById(R.id.edtselecao9);
		edtselecao10 = (EditText) findViewById(R.id.edtselecao10);
		edtselecao11 = (EditText) findViewById(R.id.edtselecao11);
		edtselecao12 = (EditText) findViewById(R.id.edtselecao12);
		
		//Textview
		jogo1 = (TextView) findViewById(R.id.localjogo);
		jogo2 = (TextView) findViewById(R.id.localjogo2);
		jogo3 = (TextView) findViewById(R.id.localjogo3);
		jogo4 = (TextView) findViewById(R.id.localjogo4);
		jogo5 = (TextView) findViewById(R.id.localjogo5);
		jogo6 = (TextView) findViewById(R.id.localjogo6);
		
		//Imagens Flags
		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);
		image3 = (ImageView) findViewById(R.id.image3);
		image4 = (ImageView) findViewById(R.id.image4);
		image5 = (ImageView) findViewById(R.id.image5);
		image6 = (ImageView) findViewById(R.id.image6);
		image7 = (ImageView) findViewById(R.id.image7);
		image8 = (ImageView) findViewById(R.id.image8);
		image9 = (ImageView) findViewById(R.id.image9);
		image10 = (ImageView) findViewById(R.id.image10);
		image11 = (ImageView) findViewById(R.id.image11);
		image12 = (ImageView) findViewById(R.id.image12);
	
	}
	
	public void addResultado(){
		boolean rs= false;
		try {
			VerificaResultados ValResultados = new VerificaResultados();
			List<Selecoes> ListAuxSelecoes = new ArrayList<Selecoes>();
			if(Integer.parseInt(edtselecao1.getText().toString()) >= 0 || Integer.parseInt(edtselecao2.getText().toString()) >= 0){
				ListAuxSelecoes = ValResultados.verificaResult(SelecCollection.get(0).getIdSelecao(), SelecCollection.get(1).getIdSelecao(),
										 Integer.parseInt(edtselecao1.getText().toString()), Integer.parseInt(edtselecao2.getText().toString()));
				Utils.db.execSQL("Update RESULTADO set jgResultado1 = "+edtselecao1.getText()+", jgResultado2 = "+edtselecao2.getText() +""
						+ " Where jgSelecao1 = '"+ SelecCollection.get(0).getSelNome().toString() +"' and jgSelecao2 = '"+ SelecCollection.get(1).getSelNome().toString()+"';");
			
				atualizaResultSelecoes(ListAuxSelecoes);
				rs=true;
			}
			if(Integer.parseInt(edtselecao3.getText().toString()) >= 0 || Integer.parseInt(edtselecao4.getText().toString()) >= 0){
				ListAuxSelecoes = new ArrayList<Selecoes>();
				ValResultados = new VerificaResultados();
				ListAuxSelecoes = ValResultados.verificaResult(SelecCollection.get(2).getIdSelecao(), SelecCollection.get(3).getIdSelecao(),
					 Integer.parseInt(edtselecao3.getText().toString()), Integer.parseInt(edtselecao4.getText().toString()));
				Utils.db.execSQL("Update RESULTADO set jgResultado1 = "+edtselecao3.getText()+", jgResultado2 = "+edtselecao4.getText() +""
						+ " Where jgSelecao1 = '"+ SelecCollection.get(2).getSelNome().toString() +"' and jgSelecao2 = '"+ SelecCollection.get(3).getSelNome().toString()+"';");
			
				atualizaResultSelecoes(ListAuxSelecoes);
				rs=true;
			}	
			if(Integer.parseInt(edtselecao5.getText().toString()) >= 0 || Integer.parseInt(edtselecao6.getText().toString()) >= 0){
				ListAuxSelecoes = ValResultados.verificaResult(SelecCollection.get(0).getIdSelecao(), SelecCollection.get(2).getIdSelecao(),
					 Integer.parseInt(edtselecao5.getText().toString()), Integer.parseInt(edtselecao6.getText().toString()));
				Utils.db.execSQL("Update RESULTADO set jgResultado1 = "+edtselecao5.getText()+", jgResultado2 = "+edtselecao6.getText() +""
						+ " Where jgSelecao1 = '"+ SelecCollection.get(0).getSelNome().toString() +"' and jgSelecao2 = '"+ SelecCollection.get(2).getSelNome().toString()+"';");
			
				atualizaResultSelecoes(ListAuxSelecoes);
				rs=true;
			}
			if(Integer.parseInt(edtselecao7.getText().toString()) >= 0 || Integer.parseInt(edtselecao8.getText().toString()) >= 0){
				ListAuxSelecoes = ValResultados.verificaResult(SelecCollection.get(1).getIdSelecao(), SelecCollection.get(3).getIdSelecao(),
					 Integer.parseInt(edtselecao7.getText().toString()), Integer.parseInt(edtselecao8.getText().toString()));
				Utils.db.execSQL("Update RESULTADO set jgResultado1 = "+edtselecao7.getText()+", jgResultado2 = "+edtselecao8.getText() +""
						+ " Where jgSelecao1 = '"+ SelecCollection.get(1).getSelNome().toString() +"' and jgSelecao2 = '"+ SelecCollection.get(3).getSelNome().toString()+"';");
		
				atualizaResultSelecoes(ListAuxSelecoes);
				rs=true;
			}
			if(Integer.parseInt(edtselecao9.getText().toString()) >= 0 || Integer.parseInt(edtselecao10.getText().toString()) >= 0){
				ListAuxSelecoes = ValResultados.verificaResult(SelecCollection.get(0).getIdSelecao(), SelecCollection.get(3).getIdSelecao(),
					 Integer.parseInt(edtselecao9.getText().toString()), Integer.parseInt(edtselecao10.getText().toString()));
				Utils.db.execSQL("Update RESULTADO set jgResultado1 = "+edtselecao9.getText()+", jgResultado2 = "+edtselecao10.getText() +""
						+ " Where jgSelecao1 = '"+ SelecCollection.get(0).getSelNome().toString() +"' and jgSelecao2 = '"+ SelecCollection.get(3).getSelNome().toString()+"';");
			
				atualizaResultSelecoes(ListAuxSelecoes);
			
				rs=true;
			}
			if(Integer.parseInt(edtselecao11.getText().toString()) > 0 || Integer.parseInt(edtselecao12.getText().toString()) > 0){
				ListAuxSelecoes = ValResultados.verificaResult(SelecCollection.get(1).getIdSelecao(), SelecCollection.get(2).getIdSelecao(),
					 Integer.parseInt(edtselecao11.getText().toString()), Integer.parseInt(edtselecao12.getText().toString()));
				Utils.db.execSQL("Update RESULTADO set jgResultado1 = "+edtselecao11.getText()+", jgResultado2 = "+edtselecao12.getText() +""
						+ " Where jgSelecao1 = '"+ SelecCollection.get(1).getSelNome().toString() +"' and jgSelecao2 = '"+ SelecCollection.get(2).getSelNome().toString()+"';");
			
				atualizaResultSelecoes(ListAuxSelecoes);
				rs=true;
			}
			if(rs)
				Toast.makeText(getApplicationContext(), "Os placares foram atualizados!", Toast.LENGTH_SHORT).show();
		
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Falha na atualização!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public boolean setResultados(){
		Cursor cursor=null;
		try {
			ResultCollection = new ArrayList<Resultados>();
			if(grupo.trim().length() > 1){
				String sql = "Select * from RESULTADO WHERE GrupoId in(Select idGrupo from GRUPO WHERE gruDescricao = '"+grupo.toString()+"');";
				
				cursor = Utils.db.rawQuery(sql, null);
				while(cursor.moveToNext()){
					resultados = new Resultados(cursor);
					
					ResultCollection.add(resultados);
				}				
				
				edtselecao1.setText(String.valueOf(ResultCollection.get(0).getJgResultado1()));
				edtselecao2.setText(String.valueOf(ResultCollection.get(0).getJgResultado2()));
				edtselecao3.setText(String.valueOf(ResultCollection.get(1).getJgResultado1()));
				edtselecao4.setText(String.valueOf(ResultCollection.get(1).getJgResultado2()));
				edtselecao5.setText(String.valueOf(ResultCollection.get(2).getJgResultado1()));
				edtselecao6.setText(String.valueOf(ResultCollection.get(2).getJgResultado2()));
				edtselecao7.setText(String.valueOf(ResultCollection.get(3).getJgResultado1()));
				edtselecao8.setText(String.valueOf(ResultCollection.get(3).getJgResultado2()));
				edtselecao9.setText(String.valueOf(ResultCollection.get(4).getJgResultado1()));
				edtselecao10.setText(String.valueOf(ResultCollection.get(4).getJgResultado2()));
				edtselecao11.setText(String.valueOf(ResultCollection.get(5).getJgResultado1()));
				edtselecao12.setText(String.valueOf(ResultCollection.get(5).getJgResultado2()));
				
				//Local Jogo
				jogo1.setText(String.valueOf(ResultCollection.get(0).getJgLocal()));
				jogo2.setText(String.valueOf(ResultCollection.get(1).getJgLocal()));
				jogo3.setText(String.valueOf(ResultCollection.get(2).getJgLocal()));
				jogo4.setText(String.valueOf(ResultCollection.get(3).getJgLocal()));
				jogo5.setText(String.valueOf(ResultCollection.get(4).getJgLocal()));
				jogo6.setText(String.valueOf(ResultCollection.get(5).getJgLocal()));
				
			}
		} catch (Exception e) {
		
		}finally{
			cursor.close();
		}
		
		return true;
	}
	
	private void loadRodadas(String grupo){
		
		SelecCollection = SelecoesDAO.getInstance().loadChild(grupo);
		
    	selecao1.setText(SelecCollection.get(0).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao2.setText(SelecCollection.get(1).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao3.setText(SelecCollection.get(2).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao4.setText(SelecCollection.get(3).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao5.setText(SelecCollection.get(0).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao6.setText(SelecCollection.get(2).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao7.setText(SelecCollection.get(1).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao8.setText(SelecCollection.get(3).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao9.setText(SelecCollection.get(0).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao10.setText(SelecCollection.get(3).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao11.setText(SelecCollection.get(1).getSelNome().toString().toUpperCase().substring(0, 3));
    	selecao12.setText(SelecCollection.get(2).getSelNome().toString().toUpperCase().substring(0, 3));
    	
    	//imagens flags
    	image1.setImageResource(SelecCollection.get(0).getSelFlag());
    	image2.setImageResource(SelecCollection.get(1).getSelFlag());
    	image3.setImageResource(SelecCollection.get(2).getSelFlag());
    	image4.setImageResource(SelecCollection.get(3).getSelFlag());
    	image5.setImageResource(SelecCollection.get(0).getSelFlag());
    	image6.setImageResource(SelecCollection.get(2).getSelFlag());
    	image7.setImageResource(SelecCollection.get(1).getSelFlag());
    	image8.setImageResource(SelecCollection.get(3).getSelFlag());
    	image9.setImageResource(SelecCollection.get(0).getSelFlag());
    	image10.setImageResource(SelecCollection.get(3).getSelFlag());
    	image11.setImageResource(SelecCollection.get(1).getSelFlag());
    	image12.setImageResource(SelecCollection.get(2).getSelFlag());
    	
    	setResultados();
		
	}
	
	public void clickAlertdialog(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(RodadasActivity.this);
        builder.setMessage("Salvar resultados?");
        builder.setCancelable(false);
        builder.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        addResultado();
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.rodadas, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case id.Salvar:
			clickAlertdialog();
			break;
		case id.Atualiza:
				launchDialog();

			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void launchDialog(){
    	progressDialog = ProgressDialog.show(RodadasActivity.this,null, "Verificando Servidor!!", true);
    	progressDialog.setCancelable(true);
    
    	new Thread(new Runnable() {
    		Handler handler = new Handler();
			
			@Override
			public void run() {
				try {
					if (ServidorController.getInstance(Utils.URL + "/status").statusServidor()) {

						new ResultadosController(Utils.URL+ "/resultado/getResultado/").updateResulGrupo(grupo);
						handler.post(new Runnable() {
							@Override
							public void run() {
								progressDialog.setMessage("Atualizando Resultados...");
								onStop();
							}
						});

					}else{
						progressDialog.dismiss();
					}
				} catch (Exception e) {
					progressDialog.dismiss();
				}
				progressDialog.dismiss();
			}
		}).start();    	
    }  
	
	private void atualizaResultSelecoes(List<Selecoes> ListAuxSelecoes){
		
		if(ListAuxSelecoes.size()>1){
			//if((ListAuxSelecoes.get(0).getIdSelecao() + ListAuxSelecoes.get(1).getIdSelecao() + SelecCollection.get(0).getGrupoId()) == Integer.parseInt(ResultCollection.get(0).getJgReferencia())){
				Utils.db.execSQL("Update SELECAO set selPontos ="+ListAuxSelecoes.get(0).getSelPontos()+", selNumJogos = "+ListAuxSelecoes.get(0).getSelNumJogos()+", selVitorias = "+ListAuxSelecoes.get(0).getSelVitorias() +", "
					+ "selEmpates = "+ListAuxSelecoes.get(0).getSelEmpates() +", selDerrotas = "+ListAuxSelecoes.get(0).getSelDerrotas() +", selSaldoGols = "+ListAuxSelecoes.get(0).getSelSaldoGols() +", "
					+ "selGolsCont = "+ListAuxSelecoes.get(0).getSelGolsCont() +", selGolsPro = "+ListAuxSelecoes.get(0).getSelGolsPro() +""
					+ " Where idSelecao = "+ ListAuxSelecoes.get(0).getIdSelecao()+";");
				Utils.db.execSQL("Update SELECAO set selPontos ="+ListAuxSelecoes.get(1).getSelPontos()+", selNumJogos = "+ListAuxSelecoes.get(1).getSelNumJogos()+", selVitorias = "+ListAuxSelecoes.get(1).getSelVitorias() +", "
					+ "selEmpates = "+ListAuxSelecoes.get(1).getSelEmpates() +", selDerrotas = "+ListAuxSelecoes.get(1).getSelDerrotas() +", selSaldoGols = "+ListAuxSelecoes.get(1).getSelSaldoGols() +", "
					+ "selGolsCont = "+ListAuxSelecoes.get(1).getSelGolsCont() +", selGolsPro = "+ListAuxSelecoes.get(1).getSelGolsPro() +""
					+ " Where idSelecao = "+ ListAuxSelecoes.get(1).getIdSelecao()+";");
				
				ListAuxSelecoes.clear();
		}
	}
	
	@Override
	protected void onStop() {
		setResultados();
		super.onStop();
	}

}
