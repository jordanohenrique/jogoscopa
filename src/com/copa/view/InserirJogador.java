package com.copa.view;

import java.util.ArrayList;
import java.util.List;

import com.copa.DAO.JogadoresDAO;
import com.copa.DAO.SelecoesDAO;
import com.copa.Model.Jogadores;
import com.copa.Model.Selecoes;
import com.copa.jogoscopa.R;
import com.copa.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InserirJogador extends Activity{
	
	Spinner spPosicao, spSelecao;
	EditText edJogNome;
	CheckBox cxTitular;
	Button btGravar;
	String posicao = "Goleiro";
	Jogadores jogador = new Jogadores();
	Selecoes selecao = new Selecoes();
	List<Selecoes> CollectionSelecao = new ArrayList<Selecoes>();
	int position = 0;
	Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityinserejogador);
		
		spPosicao = (Spinner) findViewById(R.id.spPosicao);
		spSelecao = (Spinner) findViewById(R.id.spSelecao);
		edJogNome = (EditText) findViewById(R.id.edJogNome);
		cxTitular = (CheckBox) findViewById(R.id.cxTitular);
		btGravar = (Button) findViewById(R.id.btGravaJogador);
		
		Intent intent = getIntent();
        bundle = intent.getExtras();
        if(bundle.getLong("jogador") != 0){
        	jogador.setIdJogador(bundle.getLong("jogador"));
        	position = bundle.getInt("selecao");
            loadJogador(jogador.getIdJogador());
        }
        if(bundle.getInt("selecao") >= 0){
        	position = bundle.getInt("selecao");
        }
        getAllSelecoes();
		
		ArrayAdapter<CharSequence> adapterPosicao = ArrayAdapter.createFromResource(this, R.array.posicao, android.R.layout.simple_spinner_item);
		adapterPosicao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spPosicao.setAdapter(adapterPosicao);
		
		final ArrayAdapter<Selecoes> adapterSelecao = new ArrayAdapter<Selecoes>(this, android.R.layout.simple_spinner_item, (List<Selecoes>)CollectionSelecao);
		spSelecao.setAdapter(adapterSelecao);
		if(jogador.getIdJogador() != 0){
			loadJogador(jogador.getIdJogador());
		}else{
			loadJogador(0);
		}
		
		spPosicao.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				posicao = spPosicao.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {				
			}
		});
		
		spSelecao.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
				selecao = adapterSelecao.getItem(position);
				jogador.setSelecao_id(selecao.getIdSelecao());
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) { }
		});
		
		btGravar.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setJogador();
				JogadoresDAO.getInstance().setJogadores(jogador);
				Toast.makeText(getApplicationContext(), "Gravado com sucesso!", Toast.LENGTH_SHORT).show();	
				//InserirJogador.this.finish();
			}
		});
	}
	
	private Jogadores setJogador(){
		try {
			
			jogador.setJogNome(edJogNome.getText().toString().toUpperCase());
			if(posicao.equalsIgnoreCase("Goleiro"))
				jogador.setJogPosicao(Utils.GL);
			if(posicao.equalsIgnoreCase("Zagueiro"))
				jogador.setJogPosicao(Utils.ZA);
			if(posicao.equalsIgnoreCase("Lateral"))
				jogador.setJogPosicao(Utils.LA);
			if(posicao.equalsIgnoreCase("Meio-Campo"))
				jogador.setJogPosicao(Utils.MC);
			if(posicao.equalsIgnoreCase("Atacante"))
				jogador.setJogPosicao(Utils.AT);
			if(posicao.equalsIgnoreCase("Técnico"))
				jogador.setJogPosicao(Utils.TE);
			//verifica checkbox
			if(cxTitular.isChecked())
				jogador.setJogStatus("T");
			else
				jogador.setJogStatus("F");
			
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Falha ao carregar dados!!", Toast.LENGTH_SHORT).show();
		}	
		return jogador;
	}
	
	private void loadJogador(long _id){
		
		if (_id != 0) {
			jogador = JogadoresDAO.getInstance().getJogador(_id);

			edJogNome.setText(jogador.getJogNome());

			if (jogador.getJogPosicao().toString().equalsIgnoreCase(Utils.GL))
				spPosicao.setSelection(0);
			if (jogador.getJogPosicao().toString().equalsIgnoreCase(Utils.ZA))
				spPosicao.setSelection(1);
			if (jogador.getJogPosicao().toString().equalsIgnoreCase(Utils.LA))
				spPosicao.setSelection(2);
			if (jogador.getJogPosicao().toString().equalsIgnoreCase(Utils.MC))
				spPosicao.setSelection(3);
			if (jogador.getJogPosicao().toString().equalsIgnoreCase(Utils.AT))
				spPosicao.setSelection(4);
			if (jogador.getJogPosicao().toString().equalsIgnoreCase(Utils.TE))
				spPosicao.setSelection(5);
			// verifica checkbox
			if (jogador.getJogStatus().toString().equals("T"))
				cxTitular.setChecked(true);
			else
				cxTitular.setChecked(false);
		}
		//setar Selecao
		spSelecao.setSelection(position);
		
	}
	
	private void getAllSelecoes(){
		
		CollectionSelecao = SelecoesDAO.getInstance().Selecoes();
	}

	
	@Override
	public void onBackPressed() {
		InserirJogador.this.onDestroy();
		super.onBackPressed();
	}
}
