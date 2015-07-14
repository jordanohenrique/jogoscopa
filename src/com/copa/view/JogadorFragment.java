package com.copa.view;

import java.util.ArrayList;
import java.util.List;

import com.copa.DAO.JogadoresDAO;
import com.copa.Model.Jogadores;
import com.copa.Model.Selecoes;
import com.copa.fragments.AdapterJogadores;
import com.copa.jogoscopa.R;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class JogadorFragment extends Fragment {

	List<Jogadores> CollectionJogadores = new ArrayList<Jogadores>();
	Selecoes selecao = new Selecoes();
	int _posicao=0;
	TextView txtSelecao;
	ImageView imageView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("JogadorFragment", "onCreate");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v("JogadorFragment", "onActivityCreate");
		
		if(selecao.getIdSelecao() > 0){
			setListView();
		}else{
			Jogadores jogador = new Jogadores();
			jogador.setJogNome("JOGADORES!");
			selecao.setSelNome(" SELEÇÃO ");
			selecao.setSelFlag(R.drawable.ic_launcher);
		}
		
		if (!selecao.getSelNome().equals("")) {
			txtSelecao = (TextView) getView().findViewById(R.id.textSelecao);
			imageView = (ImageView) getView().findViewById(R.id.imagSelcao);
			txtSelecao.setText(selecao.getSelNome());
			imageView.setImageResource(selecao.getSelFlag());
		}
		
		//setRetainInstance(true);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("imagem_list", "");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		Log.v("DisplayFragment", "onCreateView");
		View view = inflater.inflate(R.layout.list_jogadores,container, false);

		return view;
		
	}
	
	public void setSelecaoContent(Selecoes selec, int posicao){
		selecao = selec;
		_posicao = posicao;
	}
	
	public void updateSelecaoContent(Selecoes selec, int posicao){
		selecao = selec;
		_posicao = posicao;
		txtSelecao = (TextView) getView().findViewById(R.id.textSelecao);
		imageView = (ImageView) getView().findViewById(R.id.imagSelcao);
		txtSelecao.setText(selecao.getSelNome());
		imageView.setImageResource(selecao.getSelFlag());
		if(selecao.getIdSelecao() > 0){
			setListView();
		}else{
			Jogadores jogador = new Jogadores();
			jogador.setJogNome("JOGADORES!");
			selecao.setSelNome(" SELEÇÃO ");
			selecao.setSelFlag(R.drawable.ic_launcher);
		}
	}
	
	private void setListView(){
		createListJogadores(selecao.getIdSelecao());

		ListView listJog = (ListView) getView().findViewById(R.id.listJogadores);
		
		AdapterJogadores adapter = new AdapterJogadores(getActivity(), CollectionJogadores);
		listJog.setAdapter(adapter);
		
		listJog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(getActivity(), InserirJogador.class);
				bundle.putLong("jogador", CollectionJogadores.get(position).getIdJogador());
				bundle.putInt("selecao", _posicao);
				intent.putExtras(bundle);
				startActivity(intent);				
			}
		});
	}
	
	public void createListJogadores(long id_selecao){
		
		try {
				CollectionJogadores = JogadoresDAO.getInstance().ordenaJogadores(selecao.getIdSelecao());
		} catch (Exception e) {
			Toast.makeText(getActivity(), "Não foi possível exibir Jogadores! ", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onResume() {
		setListView();
		super.onResume();
	}

}
