package com.copa.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.copa.DAO.SelecoesDAO;
import com.copa.Model.Selecoes;
import com.copa.fragments.ExpandGrupos;
import com.copa.jogoscopa.R;
import com.copa.jogoscopa.R.id;
import com.copa.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class GruposActivity extends Activity {

    List<String> groupList;
    Map<String, List<Selecoes>> selecoesCollection;
    ExpandableListView expListView;
    String selected = "GRUPO A";
    Bundle bundle;
    Cursor cursor;
        
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitygrupos);
		
		createGroupList();
		 
        createCollection();
        
        expListView = (ExpandableListView) findViewById(R.id.expListSelecoes);
        final ExpandGrupos expListAdapter = new ExpandGrupos
        		(this, groupList, selecoesCollection);
        expListView.setAdapter(expListAdapter);
 
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
				int groupPosition, long id) {
				selected = (String)expListAdapter.getGroup(groupPosition);
				return false;
			}
		});
    }
 
    private void createGroupList() {
        groupList = new ArrayList<String>();
        try {
        	 cursor = Utils.db.rawQuery("SELECT * FROM GRUPO", null);
             
             while(cursor.moveToNext()){
             	groupList.add(cursor.getString(cursor.getColumnIndex("gruDescricao")));
             }
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			cursor.close();
		}
       

    }
 
    private void createCollection() {
    	
    	selecoesCollection = new LinkedHashMap<String, List<Selecoes>>();
    	String _grupostr = null;
        for (String grupo : groupList) {
            if (grupo.contains("GRUPO A")) {
            	_grupostr = grupo;
            	} else if (grupo.contains("GRUPO B")){
            		_grupostr = grupo;
            	}else if (grupo.contains("GRUPO C")){
            		_grupostr = grupo;
        		}else if (grupo.contains("GRUPO D")){
        			_grupostr = grupo;
    			}else if (grupo.contains("GRUPO E")){
    				_grupostr = grupo;
				}else if (grupo.contains("GRUPO F")){
					_grupostr = grupo;
				}else if (grupo.contains("GRUPO H")){
					_grupostr = grupo;
				}else{
					_grupostr = grupo;
				}
 
            selecoesCollection.put(grupo, SelecoesDAO.getInstance().loadChild(_grupostr));
        }
      }
 
   /* private void loadChild(String grupo) {
    	
        childList = new ArrayList<Selecoes>();
        childList = dadosBD.loadChild(grupo);  
    }*/
    
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grupos, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   
    	//loadChild(selected);
    	bundle = new Bundle();
    	switch (item.getItemId()) {
		case id.Jogos:
			Intent intent = new Intent(GruposActivity.this, RodadasActivity.class);
			bundle.putString("grupo", selected);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case id.Estadios:
			Intent Estadios = new Intent(GruposActivity.this, EstadiosActivity.class);
			startActivity(Estadios);
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);

    }
  
}
