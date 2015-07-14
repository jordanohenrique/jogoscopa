package com.copa.fragments;

import java.util.List;

import com.copa.DAO.GrupoDAO;
import com.copa.Model.OitavasFinal;
import com.copa.jogoscopa.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterOitavas extends BaseAdapter {

	private Context ctx;
	private List<OitavasFinal> CollectionOitavas;
	
	public AdapterOitavas(Context contexto, List<OitavasFinal> listOitavas) {
		ctx = contexto;
		CollectionOitavas = listOitavas;
	}
	
	@Override
	public int getCount() {
		return CollectionOitavas.size();
	}

	@Override
	public Object getItem(int position) {
		return CollectionOitavas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		OitavasFinal Oitavas= CollectionOitavas.get(position);
		
		
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
		
		txtOitavas1.setText(Oitavas.getOitSelNome()+" - "+Oitavas.getOitPosicao()+"º   "+ GrupoDAO.getIstance().getGrupo(Oitavas.getOitIdGrupo()).getGruDescricao().toString().substring(6, 7));		
		txtOitavas3.setText(String.valueOf(Oitavas.getOitResultado()));
		imgSelecao1.setImageResource((Integer) Oitavas.getSelFlag());
		txtLocalJogo.setText(Oitavas.getOitLocal());
		
		txtOitavas2.setText(Oitavas.getOitSelNome1()+" - "+Oitavas.getOitPosicao1()+"º   "+ GrupoDAO.getIstance().getGrupo(Oitavas.getOitIdGrupo1()).getGruDescricao().toString().substring(6, 7));
		txtOitavas4.setText(String.valueOf(Oitavas.getOitResultado1()));
		imgSelecao2.setImageResource((Integer) Oitavas.getSelFlag1());
		

		return convertView;
	}

}
