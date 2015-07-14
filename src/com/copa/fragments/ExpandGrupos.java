package com.copa.fragments;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.copa.Model.*;
import com.copa.jogoscopa.R;

public class ExpandGrupos extends BaseExpandableListAdapter{
	
	private Activity context;
    private Map<String, List<Selecoes>> SelecoesCollections;
    private List<String> Selecoes;
 
    public ExpandGrupos(Activity context, List<String> Selecoes, Map<String, List<Selecoes>> SelecoesCollections) {
        this.context = context;
        this.SelecoesCollections = SelecoesCollections;
        this.Selecoes = Selecoes;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
    	Object selecoeschild = SelecoesCollections.get(Selecoes.get(groupPosition)).get(childPosition).getSelNome();
    	
        return selecoeschild;
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String selecao = (String) getChild(groupPosition, childPosition);
        Object bandChild = SelecoesCollections.get(Selecoes.get(groupPosition)).get(childPosition).getSelFlag();
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item_grupos,parent,false);
        }
 
        ImageView imgBandeira = (ImageView) convertView.findViewById(R.id.selbandeira);
        imgBandeira.setImageResource((Integer)bandChild);

        TextView item = (TextView) convertView.findViewById(R.id.selNome);
        item.setText(selecao);
        
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return SelecoesCollections.get(Selecoes.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return Selecoes.get(groupPosition);
    }
 
    public int getGroupCount() {
        return Selecoes.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
    	
        String GrupoName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_group,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.Grupos);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(GrupoName);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
