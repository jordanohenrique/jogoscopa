package com.copa.view;

import java.util.ArrayList;
import java.util.List;

import com.copa.Controller.FinalController;
import com.copa.Controller.OitavasController;
import com.copa.Controller.ServidorController;
import com.copa.DAO.SemiFinalDAO;
import com.copa.Model.Parametros;
import com.copa.Model.SemiFinal;
import com.copa.jogoscopa.R;
import com.copa.jogoscopa.R.id;
import com.copa.utils.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FinalActivity extends Activity{
	
	List<SemiFinal> CollectionFinal = new ArrayList<SemiFinal>();
	EditText edtFinalResultado1, edtFinalResultado2;
	TextView txtFinalResultado1, txtFinalResultado2;
	ImageView imgFinalSelecao1, imgFinalSelecao2;
	ProgressDialog progressDialog;
	boolean acao = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityfinal);
		
		txtFinalResultado1 = (TextView) findViewById(R.id.txtFinalSelecao1);
		txtFinalResultado2 = (TextView) findViewById(R.id.txtFinalSelecao2);
		edtFinalResultado1 = (EditText) findViewById(R.id.edtFinalPlacar1);
		edtFinalResultado2 = (EditText) findViewById(R.id.edtFinalPlacar2);
		imgFinalSelecao1 = (ImageView) findViewById(R.id.imgFinalSelecao1);
		imgFinalSelecao2 = (ImageView) findViewById(R.id.imgFinalSelecao2);
		

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if(bundle.getString("A").equalsIgnoreCase("TRUE")){
			acao = true;
		}
		getFinal();
	}
	
	private void getFinal(){
		CollectionFinal = SemiFinalDAO.getInstance().getAllFinal();
		if(acao){
			txtFinalResultado1.setText(CollectionFinal.get(0).getSemiSelNome().toString());
			txtFinalResultado2.setText(CollectionFinal.get(1).getSemiSelNome().toString());
			edtFinalResultado1.setText(CollectionFinal.get(0).getSemiResultado());
			edtFinalResultado2.setText(CollectionFinal.get(1).getSemiResultado());
			imgFinalSelecao1.setImageResource(CollectionFinal.get(0).getSelFlag());
			imgFinalSelecao2.setImageResource(CollectionFinal.get(1).getSelFlag());
			
		}else{
			txtFinalResultado1.setText("Venc. SEMIFINAL 1");
			txtFinalResultado2.setText("Venc. SEMIFINAL 2");
			edtFinalResultado1.setText("0");
			edtFinalResultado2.setText("0");
			imgFinalSelecao1.setImageResource(R.drawable.ic_launcher);
			imgFinalSelecao2.setImageResource(R.drawable.ic_launcher);
		}
	}
	
	private void setFinal(){
		
		try {
			if(CollectionFinal.get(0).getSemiIdSelecao() != 0 && CollectionFinal.get(1).getSemiIdSelecao() != 0){
				if(Integer.parseInt(edtFinalResultado1.getText().toString()) > 0 || Integer.parseInt(edtFinalResultado2.getText().toString()) > 0){
					clickAlertdialog();
				}
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Resultados não foram Gravados", Toast.LENGTH_SHORT).show();
		}
	}
	private void updateFinal(){
		
		CollectionFinal.get(0).setSemiResultado(Integer.parseInt(edtFinalResultado1.getText().toString()));
		CollectionFinal.get(1).setSemiResultado(Integer.parseInt(edtFinalResultado2.getText().toString()));
		
		SemiFinalDAO.getInstance().updateFinal(CollectionFinal);
	}
	
    public void clickAlertdialog(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(FinalActivity.this);
        builder.setMessage("GRAVAR?");
        builder.setCancelable(false);
        builder.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateFinal();
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
    protected void onDestroy() {
    	setFinal();
    	super.onDestroy();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.finais, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case id.Atualizar:
			if(Parametros.getParSemi().equalsIgnoreCase("T"))
				launchDialog();
			else
				Toast.makeText(getApplicationContext(), "Não há atualização disponível.", 2000).show();
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
    private void launchDialog(){
    	progressDialog = ProgressDialog.show(FinalActivity.this,null, "Verificando Servidor!!", true);
    	progressDialog.setCancelable(true);
    
    	new Thread(new Runnable() {
    		Handler handler = new Handler();
			
			@Override
			public void run() {
				try {
					if (ServidorController.getInstance(Utils.URL + "/status").statusServidor()) {

						if(Parametros.getParClassif().equalsIgnoreCase("T")){
							new FinalController(Utils.URL+ "/final/getall/").updateFinal();
							handler.post(new Runnable() {
								@Override
								public void run() {
									progressDialog.setMessage("Atualizando Final...");
									onStop();
								}
							});
							Thread.sleep(10000);
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
    	getFinal();
    	super.onStop();
    }
}
