package com.copa.fragments;

import java.util.List;

import com.copa.Model.Estadio;
import com.copa.Model.Selecoes;
import com.copa.jogoscopa.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterEstadios extends BaseAdapter {

	private Context ctx;
	private List<Estadio> CollectionEstadios;
	
	public AdapterEstadios(Context contexto, List<Estadio> estadios) {
		ctx = contexto;
		CollectionEstadios = estadios;

	}
	
	@Override
	public int getCount() {
		return CollectionEstadios.size();
	}

	@Override
	public Object getItem(int position) {
		return CollectionEstadios.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Estadio estadio= CollectionEstadios.get(position);
		
		LayoutInflater Inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			convertView =Inflater.inflate(R.layout.selecoes_jog_item, null);
		}
		
		ImageView imgbandeira = (ImageView) convertView.findViewById(R.id.jogselbandeira);
		TextView txtSelecao = (TextView) convertView.findViewById(R.id.jogselNome);
		
		imgbandeira.setImageResource(estadio.getEstImagem());		
		txtSelecao.setText(estadio.getEstApelido());

		return convertView;
	}

}
