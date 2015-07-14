package com.copa.view;

import java.util.ArrayList;
import java.util.List;

import com.copa.Controller.FinalController;
import com.copa.Controller.SemiFinalController;
import com.copa.Controller.ServidorController;
import com.copa.DAO.SemiFinalDAO;
import com.copa.Model.Parametros;
import com.copa.Model.SemiFinal;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SemiFinalActivity extends Activity{
	
	private List<SemiFinal> CollectionSemiFinal= new ArrayList<SemiFinal>();
	private EditText edtSemiResSelecao1, edtSemiResSelecao2, edtSemiResSelecao3, edtSemiResSelecao4;
	private TextView txtSemiSel1, txtSemiSel2, txtSemiSel3, txtSemiSel4, txtLocalJogo1, txtLocalJogo2;
	private ImageView imgSemiSelecao1, imgSemiSelecao2, imgSemiSelecao3, imgSemiSelecao4;
	private Button btGravar;
	private ProgressDialog progressDialog;
	private boolean acao = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitysemifinal);
		
		edtSemiResSelecao1 = (EditText)findViewById(R.id.edtResulSelecao1);
		edtSemiResSelecao2 = (EditText)findViewById(R.id.edtResulSelecao2);
		edtSemiResSelecao3 = (EditText)findViewById(R.id.edtResulSelecao3);
		edtSemiResSelecao4 = (EditText)findViewById(R.id.edtResulSelecao4);
		
		txtSemiSel1 = (TextView) findViewById(R.id.txtSemiSel1);
		txtSemiSel2 = (TextView) findViewById(R.id.txtSemiSel2);
		txtSemiSel3 = (TextView) findViewById(R.id.txtSemiSel3);
		txtSemiSel4 = (TextView) findViewById(R.id.txtSemiSel4);
		txtLocalJogo1 = (TextView) findViewById(R.id.txtLocalSemi1);
		txtLocalJogo2 = (TextView) findViewById(R.id.txtLocalSemi2);
		
		imgSemiSelecao1 = (ImageView) findViewById(R.id.imgSemiSelecao1);
		imgSemiSelecao2 = (ImageView) findViewById(R.id.imgSemiSelecao2);
		imgSemiSelecao3 = (ImageView) findViewById(R.id.imgSemiSelecao3);
		imgSemiSelecao4 = (ImageView) findViewById(R.id.imgSemiSelecao4);
		
		Intent intent = getIntent();
		Bundle bundleoit = intent.getExtras();
		if(String.valueOf(bundleoit.getString("A")).equalsIgnoreCase("TRUE")){
			acao = true;
		}
		
		btGravar = (Button) findViewById(R.id.btSemiGravar);
		getSemiFinal();
		
		btGravar.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				updateSemiFinal();
			}
		});
		
	}

	private void updateSemiFinal(){
		try {
			if(CollectionSemiFinal.get(0).getSemiIdSelecao() != 0 && CollectionSemiFinal.get(2).getSemiIdSelecao() != 0){
				if(Integer.parseInt(edtSemiResSelecao1.getText().toString()) > 0 || Integer.parseInt(edtSemiResSelecao2.getText().toString()) > 0){
					CollectionSemiFinal.get(0).setSemiResultado(Integer.parseInt(edtSemiResSelecao1.getText().toString()));
					CollectionSemiFinal.get(1).setSemiResultado(Integer.parseInt(edtSemiResSelecao2.getText().toString()));
				}
				if(Integer.parseInt(edtSemiResSelecao3.getText().toString()) > 0 || Integer.parseInt(edtSemiResSelecao4.getText().toString()) > 0){
					CollectionSemiFinal.get(2).setSemiResultado(Integer.parseInt(edtSemiResSelecao3.getText().toString()));
					CollectionSemiFinal.get(3).setSemiResultado(Integer.parseInt(edtSemiResSelecao4.getText().toString()));
				}
				SemiFinalDAO.getInstance().updateSemiFinal(CollectionSemiFinal);
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Não foi possível atualizar resultados!", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void getSemiFinal(){
		CollectionSemiFinal = SemiFinalDAO.getInstance().getAllSemiFinal();
		try {
			if (acao) {
				if (CollectionSemiFinal.get(0).getSemiIdSelecao() != 0&& CollectionSemiFinal.get(1).getSemiIdSelecao() != 0
						&& CollectionSemiFinal.get(2).getSemiIdSelecao() != 0&& CollectionSemiFinal.get(3).getSemiIdSelecao() != 0) {

					txtSemiSel1.setText(CollectionSemiFinal.get(0).getSemiSelNome());
					txtSemiSel2.setText(CollectionSemiFinal.get(1).getSemiSelNome());
					txtSemiSel3.setText(CollectionSemiFinal.get(2).getSemiSelNome());
					txtSemiSel4.setText(CollectionSemiFinal.get(3).getSemiSelNome());
					
					txtLocalJogo1.setText(CollectionSemiFinal.get(0).getSemiLocal());
					txtLocalJogo2.setText(CollectionSemiFinal.get(2).getSemiLocal());

					edtSemiResSelecao1.setText(CollectionSemiFinal.get(0).getSemiResultado());
					edtSemiResSelecao2.setText(CollectionSemiFinal.get(1).getSemiResultado());
					edtSemiResSelecao3.setText(CollectionSemiFinal.get(2).getSemiResultado());
					edtSemiResSelecao4.setText(CollectionSemiFinal.get(3).getSemiResultado());
					
					imgSemiSelecao1.setImageResource(CollectionSemiFinal.get(0).getSelFlag());
					imgSemiSelecao1.setImageResource(CollectionSemiFinal.get(1).getSelFlag());
					imgSemiSelecao1.setImageResource(CollectionSemiFinal.get(2).getSelFlag());
					imgSemiSelecao1.setImageResource(CollectionSemiFinal.get(3).getSelFlag());
					

				}
			} else{
				txtSemiSel1.setText("Vencedor - Quartas 1");
				txtSemiSel2.setText("Vencedor - Quartas 2");
				txtSemiSel3.setText("Vencedor - Quartas 3");
				txtSemiSel4.setText("Vencedor - Quartas 4");
				txtLocalJogo1.setText(SemiFinalDAO.getInstance().localJogo(1));
				txtLocalJogo2.setText(SemiFinalDAO.getInstance().localJogo(3));
				
				edtSemiResSelecao1.setText("0");
				edtSemiResSelecao2.setText("0");
				edtSemiResSelecao3.setText("0");
				edtSemiResSelecao4.setText("0");
				
				imgSemiSelecao1.setImageResource(R.drawable.trofeucopa);
				imgSemiSelecao2.setImageResource(R.drawable.trofeucopa);
				imgSemiSelecao3.setImageResource(R.drawable.trofeucopa);
				imgSemiSelecao4.setImageResource(R.drawable.trofeucopa);
				
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Não foi possível carregar os dados", Toast.LENGTH_SHORT).show();
		}
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
			if(fechaSemi()){
				Intent intent = new Intent(SemiFinalActivity.this, FinalActivity.class);
				bundle.putString("A", "TRUE");
				intent.putExtras(bundle);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}else{
				Intent intent = new Intent(SemiFinalActivity.this, FinalActivity.class);
				bundle.putString("A", "FALSE");
				intent.putExtras(bundle);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}
			break;
		case id.Atualizar:
			if(Parametros.getParSemi().equalsIgnoreCase("T"))
				launchDialog();
			else
				Toast.makeText(getApplicationContext(), "Não há atualização disponível.", 2000).show();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private boolean fechaSemi(){
		boolean acao = false;
		List<SemiFinal> listSemiFinal = new ArrayList<SemiFinal>();
		VerificaResultados veificaResultado = new VerificaResultados();
		try {
			listSemiFinal = veificaResultado.fechaResultadosSemi();
			if(listSemiFinal.size() >= 2){
				acao = SemiFinalDAO.getInstance().updateFinal(listSemiFinal);
				if(acao){
					Utils.db.execSQL("Update Parametros set parSemi = 'T'");
					Toast.makeText(getApplicationContext(), "SemiFinal Colcuida!!", Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(getApplicationContext(), "SemiFinal não foi finalizada! Verifique todos resultados. ", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "SemiFinal não foi finalizada! Verifique todos resultados. ", Toast.LENGTH_LONG).show();
		}
		
		return acao;
	}
	
	private void launchDialog(){
    	progressDialog = ProgressDialog.show(SemiFinalActivity.this,null, "Verificando Servidor!!", true);
    	progressDialog.setCancelable(true);
    
    	new Thread(new Runnable() {
    		Handler handler = new Handler();
			
			@Override
			public void run() {
				try {
					if (ServidorController.getInstance(Utils.URL + "/status").statusServidor()) {

						if(Parametros.getParClassif().equalsIgnoreCase("T")){
							new SemiFinalController(Utils.URL+ "/semi/getall/").updateSemi();
							handler.post(new Runnable() {
								@Override
								public void run() {
									progressDialog.setMessage("Atualizando SemiFinal...");
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
		getSemiFinal();
		super.onStop();
	}
}
