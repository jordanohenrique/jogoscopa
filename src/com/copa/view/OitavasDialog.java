package com.copa.view;

import com.copa.jogoscopa.R;
import com.copa.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OitavasDialog extends Activity{
	
	private Button btGravar;
	private EditText edtOitavasPlacar1, edtOitavasPlacar2;
	private TextView txtOitDialog1, txtOitDialog2;
	private Bundle bundle;
	private String selecao1 = null, selecao2 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alertdialog);
		
		edtOitavasPlacar1 = (EditText) findViewById(R.id.edtDialogPlacar1);
		edtOitavasPlacar2 = (EditText) findViewById(R.id.edtDialogPlacar2);
		txtOitDialog1 = (TextView) findViewById(R.id.txtDialog1);
		txtOitDialog2= (TextView) findViewById(R.id.txtDialog2);
		btGravar = (Button) findViewById(R.id.btDialogGravar);
		
		Intent intent = getIntent();
		bundle = intent.getExtras();
		if(bundle.getString("selecao1").toString() != ""){
			this.selecao1 = bundle.getString("selecao1").toString();
			this.selecao2 = bundle.getString("selecao2").toString();
			txtOitDialog1.setText(selecao1.substring(0, 3));
			txtOitDialog2.setText(selecao2.substring(0, 3));
			edtOitavasPlacar1.setText(String.valueOf(bundle.getInt("resultado1")));
			edtOitavasPlacar2.setText(String.valueOf(bundle.getInt("resultado2")));
		}	
		
		btGravar.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				atulizaResultado();
				Toast.makeText(getApplicationContext(), "Gravado!!", 2000).show();
			}
		});
	}
	
	private void atulizaResultado(){
		if (selecao1 != null && selecao2 != null) {
			
			Intent intent = new Intent();
			intent.putExtra("resultado1", edtOitavasPlacar1.getText().toString());
			intent.putExtra("resultado2", edtOitavasPlacar2.getText().toString());
			intent.putExtra("selecao1", selecao1);
			intent.putExtra("selecao2", selecao2);
			setResult(1, intent);
			this.finish();

		}
	}

}
