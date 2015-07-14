package com.copa.view;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.copa.Controller.JogadoresController;
import com.copa.Controller.ServidorController;
import com.copa.DAO.SelecoesDAO;
import com.copa.Model.Selecoes;
import com.copa.fragments.AdapterSelecoes;
import com.copa.jogoscopa.R;
import com.copa.jogoscopa.R.id;
import com.copa.utils.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class SelecoesFragment extends Fragment {

	onSelectedItem onselected;
	ProgressDialog progressDialog;
	List<Selecoes> CollectionSelecoes = new ArrayList<Selecoes>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		setListView();
		//setRetainInstance(true);
	}
	
	public interface onSelectedItem{
		public void SelectedSelecao(Selecoes selecao, int _posicao);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			onselected = (onSelectedItem) activity;
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if(container == null){
			return null;
		}
		View view = inflater.inflate(R.layout.list_selecoes, container, false);
		
		return view;
	}
	
	private void setListView(){
		createSelecoesList();
		ListView listView = (ListView) getView().findViewById(R.id.listSelecoes);
		
		AdapterSelecoes adapter = new AdapterSelecoes(getActivity(), CollectionSelecoes);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				onselected.SelectedSelecao(CollectionSelecoes.get(position), position);
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(getActivity(), InserirJogador.class);
				bundle.putInt("selecao", position);
				intent.putExtras(bundle);
				startActivity(intent);
				return false;
			}
		});
	}
	
	public void createSelecoesList(){

		try {
			CollectionSelecoes = SelecoesDAO.getInstance().createSelecoesList();
			
		} catch (Exception e) {
			Toast.makeText(getActivity(), "Não foi possível exibir Seleções! ", Toast.LENGTH_LONG).show();
		}
	}

}
