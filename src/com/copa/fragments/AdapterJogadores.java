package com.copa.fragments;

import java.util.List;

import com.copa.Model.Jogadores;
import com.copa.jogoscopa.R;
import com.copa.utils.Utils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AdapterJogadores extends BaseAdapter{

	private Context ctx;
	private List<Jogadores> CollectionJogadores;
	
	public AdapterJogadores(Context contexto, List<Jogadores> jogadores) {
		this.ctx = contexto;
		this.CollectionJogadores = jogadores;
	}
	
	@Override
	public int getCount() {
		return CollectionJogadores.size();
	}

	@Override
	public Object getItem(int position) {
		return CollectionJogadores.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Jogadores jogador= CollectionJogadores.get(position);
		
		LayoutInflater Inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			convertView =Inflater.inflate(R.layout.jogadores_sel_item, null);
		}
		
		TextView txtPosicao = (TextView) convertView.findViewById(R.id.txtjogPosicao);
		TextView txtNome = (TextView) convertView.findViewById(R.id.txtjogNome);
		CheckBox cxStatus = (CheckBox) convertView.findViewById(R.id.cxStatus);
		
		convertView.setBackgroundResource(colorItem(position));
		 if(jogador.getJogStatus() != null){
	        	if(jogador.getJogStatus().equalsIgnoreCase("T")){
	        		cxStatus.setChecked(true);
	        		cxStatus.setText("");
	        		convertView.setBackgroundResource(colorItem(position));	        		
	        	}else{
	        		cxStatus.setChecked(false);
	        		cxStatus.setText("");
	        		convertView.setBackgroundResource(colorItem(position));
	        	}
	        }
	        txtNome.setText(jogador.getJogNome());
	        txtPosicao.setText(jogador.getJogPosicao());
	        
	        return convertView;
	}
	
	private int colorItem(int position){
		int _idcor = 0;
		if(CollectionJogadores.get(position).getJogPosicao().equalsIgnoreCase("GL")){
			_idcor = R.color.yellowlight_1;
		}
		if(CollectionJogadores.get(position).getJogPosicao().equalsIgnoreCase("ZA")){
			_idcor = R.color.greenlight;
		}
		if(CollectionJogadores.get(position).getJogPosicao().equalsIgnoreCase("LA")){
			_idcor = R.color.branco;
		}
		if(CollectionJogadores.get(position).getJogPosicao().equalsIgnoreCase(Utils.MC)){
			_idcor = R.color.bluelight_1;
		}
		if(CollectionJogadores.get(position).getJogPosicao().equalsIgnoreCase(Utils.AT)){
			_idcor = R.color.wheat_1;
		}
		if(CollectionJogadores.get(position).getJogPosicao().equalsIgnoreCase(Utils.TE)){
			_idcor = R.color.cinzalight;
		}
		return _idcor;
	}
	
}
