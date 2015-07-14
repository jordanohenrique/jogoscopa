package com.copa.fragments;

import java.util.List;
import java.util.Map;

import com.copa.Model.Selecoes;
import com.copa.jogoscopa.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandClassificacao extends BaseExpandableListAdapter {
	
	private Activity context;
    private Map<String, List<Selecoes>> SelecoesClassif;
    private List<String> Grupos;
    
    public ExpandClassificacao(Activity ctx, Map<String, List<Selecoes>> selecaoClassif, List<String> Grupos) {
		this.context = ctx;
		this.Grupos = Grupos;
		this.SelecoesClassif = selecaoClassif;
	}

	@Override
	public Object getChild(int groupPosition, int chilPosition) {
		
		return SelecoesClassif.get(Grupos.get(groupPosition)).get(chilPosition).getSelNome();
	}

	@Override
	public long getChildId(int groupPosition, int chilPosition) {
		return chilPosition;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		
	        LayoutInflater inflater = context.getLayoutInflater();
	      	 
	        if (convertView == null) {
	            convertView = inflater.inflate(R.layout.child_item_classificacao,parent,false);
	        }

	        TextView nome = (TextView) convertView.findViewById(R.id.txtSelNome);
	        TextView jogos = (TextView) convertView.findViewById(R.id.txtJogos);
	        TextView vitorias = (TextView) convertView.findViewById(R.id.txtVitorias);
	        TextView empates = (TextView) convertView.findViewById(R.id.txtEmpates);
	        TextView derrotas = (TextView) convertView.findViewById(R.id.txtDerrotas);
	        TextView golspro = (TextView) convertView.findViewById(R.id.txtGolsPro);
	        TextView golscontra = (TextView) convertView.findViewById(R.id.txtGolsContra);
	        TextView saldogols = (TextView) convertView.findViewById(R.id.txtSaldoGols);

	        nome.setText(SelecoesClassif.get(Grupos.get(groupPosition)).get(childPosition).getSelNome());
	        jogos.setText(String.valueOf(SelecoesClassif.get(Grupos.get(groupPosition)).get(childPosition).getSelNumJogos()));
	        vitorias.setText(String.valueOf(SelecoesClassif.get(Grupos.get(groupPosition)).get(childPosition).getSelVitorias()));
	        empates.setText(String.valueOf(SelecoesClassif.get(Grupos.get(groupPosition)).get(childPosition).getSelEmpates()));
	        derrotas.setText(String.valueOf(SelecoesClassif.get(Grupos.get(groupPosition)).get(childPosition).getSelDerrotas()));
	        golspro.setText(String.valueOf(SelecoesClassif.get(Grupos.get(groupPosition)).get(childPosition).getSelGolsPro()));
	        golscontra.setText(String.valueOf(SelecoesClassif.get(Grupos.get(groupPosition)).get(childPosition).getSelGolsCont()));
	        saldogols.setText(String.valueOf(SelecoesClassif.get(Grupos.get(groupPosition)).get(childPosition).getSelSaldoGols()));
	        
	        return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return SelecoesClassif.get(Grupos.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return Grupos.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return Grupos.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		String Grupo = (String) getGroup(groupPosition);
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_group, null);
		}
			
		TextView item = (TextView) convertView.findViewById(R.id.Grupos);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(Grupo);
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

}
