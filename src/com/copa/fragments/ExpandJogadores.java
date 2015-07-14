package com.copa.fragments;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.copa.Model.Jogadores;
import com.copa.Model.Selecoes;
import com.copa.jogoscopa.R;
import com.copa.utils.Utils;

public class ExpandJogadores extends BaseExpandableListAdapter {
	
	private Activity context;
    private Map<Selecoes, List<Jogadores>> JogadoresCollection;
    private List<Selecoes> listSelecoes;
    
    public ExpandJogadores(Activity ctx, Map<Selecoes, List<Jogadores>> JogadoresClassif, List<Selecoes> Selecoes) {
		this.context = ctx;
		this.listSelecoes = Selecoes;
		this.JogadoresCollection = JogadoresClassif;
	}

	@Override
	public Object getChild(int groupPosition, int chilPosition) {
		return JogadoresCollection.get(listSelecoes.get(groupPosition)).get(chilPosition).getJogNome();
	}

	@Override
	public long getChildId(int groupPosition, int chilPosition) {
		return chilPosition;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
	        LayoutInflater inflater = context.getLayoutInflater();
	      	 
	        if (convertView == null) {
	            convertView = inflater.inflate(R.layout.jogadores_sel_item,parent,false);
	        }
	        CheckBox cxStatus = (CheckBox) convertView.findViewById(R.id.cxStatus);
	        TextView nome = (TextView) convertView.findViewById(R.id.txtjogNome);
	        TextView posicao = (TextView) convertView.findViewById(R.id.txtjogPosicao);
	        
	        if(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogStatus() != null){
	        	if(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogStatus().equalsIgnoreCase("T")){
	        		cxStatus.setChecked(true);
	        		cxStatus.setText("TL");
	        		posicao.setBackgroundColor(colorItem(groupPosition, childPosition));	        		
	        	}else{
	        		cxStatus.setChecked(false);
	        		cxStatus.setText("RE");
	        		posicao.setBackgroundColor(colorItem(groupPosition, childPosition));
	        	}
	        }
	        nome.setText(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogNome());
	        posicao.setText(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogPosicao());
	        
	        return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return JogadoresCollection.get(listSelecoes.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return listSelecoes.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return listSelecoes.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		Object bandSelecao = listSelecoes.get(groupPosition).getSelFlag();
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.selecoes_jog_item, null);
		}
		
		ImageView imgJogador = (ImageView) convertView.findViewById(R.id.jogselbandeira);
		imgJogador.setImageResource((Integer)bandSelecao);
		TextView item = (TextView) convertView.findViewById(R.id.jogselNome);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(listSelecoes.get(groupPosition).getSelNome());
        return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	private int colorItem(int groupPosition, int childPosition){
		int _idcor = 0;
		if(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogPosicao().equalsIgnoreCase(Utils.GL)){
			_idcor = R.color.yellowlight_1;
		}
		if(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogPosicao().equalsIgnoreCase(Utils.ZA)){
			_idcor = R.color.orangelight_1;
		}
		if(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogPosicao().equalsIgnoreCase(Utils.LA)){
			_idcor = R.color.greenlight_1;
		}
		if(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogPosicao().equalsIgnoreCase(Utils.MC)){
			_idcor = R.color.bluelight_1;
		}
		if(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogPosicao().equalsIgnoreCase(Utils.AT)){
			_idcor = R.color.wheat_1;
		}
		if(JogadoresCollection.get(listSelecoes.get(groupPosition)).get(childPosition).getJogPosicao().equalsIgnoreCase(Utils.TE)){
			_idcor = R.color.cinzalight;
		}
		return _idcor;
	}

}
