package com.copa.fragments;

import java.util.List;

import com.copa.Model.QuartasFinal;
import com.copa.jogoscopa.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterQuartas extends BaseAdapter {

	private Context ctx;
	private List<QuartasFinal> CollectionQuartas;
	
	public AdapterQuartas(Context contexto, List<QuartasFinal> listQuartas) {
		ctx = contexto;
		CollectionQuartas = listQuartas;
	}
	
	@Override
	public int getCount() {
		return CollectionQuartas.size();
	}

	@Override
	public Object getItem(int position) {
		return CollectionQuartas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		QuartasFinal Quartas= CollectionQuartas.get(position);
		
		LayoutInflater Inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			convertView =Inflater.inflate(R.layout.activityoitavas, null);
		}
		
		TextView txtOitavas1 = (TextView) convertView.findViewById(R.id.txtFinalSelecao1);
		TextView txtOitavas2 = (TextView) convertView.findViewById(R.id.txtFinalSelecao2);
		TextView txtLocalJogo = (TextView) convertView.findViewById(R.id.txtlocalOit);
		
		TextView txtOitavas3 = (TextView) convertView.findViewById(R.id.txtOitResultado1);
		TextView txtOitavas4 = (TextView) convertView.findViewById(R.id.txtOitResultado2);
		
		ImageView imgSelecao1 = (ImageView) convertView.findViewById(R.id.imgSelOitavas1);
		ImageView imgSelecao2 = (ImageView) convertView.findViewById(R.id.imgSelOitavas2);
		
		txtOitavas1.setText(Quartas.getQuaSelNome());		
		txtOitavas3.setText(String.valueOf(Quartas.getQuaResultado()));
		imgSelecao1.setImageResource(Quartas.getSelFlag());
		txtLocalJogo.setText(Quartas.getQuaLocal());
		
		txtOitavas2.setText(Quartas.getQuaSelNome1());
		txtOitavas4.setText(String.valueOf(Quartas.getQuaResultado1()));
		imgSelecao2.setImageResource(Quartas.getSelFlag1());

		return convertView;
	}

}
