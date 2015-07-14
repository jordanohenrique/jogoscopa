package com.copa.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.copa.DAO.DadosBD;
import com.copa.Model.Estadio;
import com.copa.fragments.AdapterEstadios;
import com.copa.jogoscopa.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class EstadiosActivity extends Activity {


	private DrawerLayout mDrawer;
	private ListView mListDrawer;
	private CharSequence mTitle;
	private ActionBarDrawerToggle mDrawerToggle;
	private List<Estadio> CollectionEstadios = new ArrayList<Estadio>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estadios);
        
		getAllEstadios();
        mTitle = getTitle();
        mDrawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        mListDrawer = (ListView) findViewById(R.id.leftDrawer);
 
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // Set the adapter for the list view
        mListDrawer.setAdapter(new AdapterEstadios(this, CollectionEstadios));
        // Set the list's click listener
        mListDrawer.setOnItemClickListener(new DrawerItemClickListener());
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
 
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawer,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.open_drawer,  /* "open drawer" description */
                R.string.close_drawer  /* "close drawer" description */
        ) {
 
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
            }
 
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mTitle);
            }
        };
 
        // Set the drawer toggle as the DrawerListener
        mDrawer.setDrawerListener(mDrawerToggle);
        if(savedInstanceState == null){
        	selectItem(0);
        } 
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.estadio, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
 
        return super.onOptionsItemSelected(item);
    }
    
    public void getAllEstadios(){
    	CollectionEstadios = DadosBD.getInstance().getEstadios();
    }
 
    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        // update the main content by replacing fragments
        DisplayEstadioFragment fragment = new DisplayEstadioFragment();
        Bundle args = new Bundle();
        args.putLong("estadio_id", CollectionEstadios.get(position).getIdEstadio());
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameDrawer, fragment).commit();

        // update selected item and title, then close the drawer
        mListDrawer.setItemChecked(position, true);
        setTitle(CollectionEstadios.get(position).getEstApelido());
        mDrawer.closeDrawer(mListDrawer);
    }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
 
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);			
		}
    }

}
