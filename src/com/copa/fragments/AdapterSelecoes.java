package com.copa.fragments;

import java.util.List;

import com.copa.Model.Selecoes;
import com.copa.jogoscopa.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterSelecoes extends BaseAdapter {

	private Context ctx;
	private List<Selecoes> CllectionSelecoes;
	
	public AdapterSelecoes(Context contexto, List<Selecoes> selecoes) {
		ctx = contexto;
		CllectionSelecoes = selecoes;

	}
	
	@Override
	public int getCount() {
		return CllectionSelecoes.size();
	}

	@Override
	public Object getItem(int position) {
		return CllectionSelecoes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Selecoes selecao= CllectionSelecoes.get(position);
		
		LayoutInflater Inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			convertView =Inflater.inflate(R.layout.selecoes_jog_item, null);
		}
		
		ImageView imgbandeira = (ImageView) convertView.findViewById(R.id.jogselbandeira);
		TextView txtSelecao = (TextView) convertView.findViewById(R.id.jogselNome);
		
		imgbandeira.setImageResource(selecao.getSelFlag());		
		txtSelecao.setText(selecao.getSelNome());

		return convertView;
	}

}
