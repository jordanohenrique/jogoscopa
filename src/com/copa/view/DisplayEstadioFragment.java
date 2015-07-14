package com.copa.view;

import com.copa.DAO.DadosBD;
import com.copa.Model.Estadio;
import com.copa.jogoscopa.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayEstadioFragment extends Fragment{

	 private Estadio estadio = new Estadio();
     long _id=0;
     
    public DisplayEstadioFragment() {
		// TODO Auto-generated constructor stub
	}
     
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.estadios_fragment, container, false);
         _id = getArguments().getLong("estadio_id");

         ImageView imgEstadio = (ImageView) rootView.findViewById(R.id.image);
         TextView txtEstApelido = (TextView) rootView.findViewById(R.id.txtEstApelido);
         TextView txtEstNome = (TextView) rootView.findViewById(R.id.txtEstNome);
         TextView txtEstCidade = (TextView) rootView.findViewById(R.id.txtEstCidade);
         TextView txtEstEndereco = (TextView) rootView.findViewById(R.id.txtEstEndereco);
         TextView txtEstCapacidade= (TextView) rootView.findViewById(R.id.txtEstCapacidade);
         TextView txtEstVagas = (TextView) rootView.findViewById(R.id.txtEstVagas);
         
         getEstadio(_id);
         
         imgEstadio.setImageResource(estadio.getEstImagem());
         txtEstApelido.setText(estadio.getEstApelido());
         txtEstNome.setText(estadio.getEstNome());
         txtEstCidade.setText(estadio.getEstCidade());
         txtEstEndereco.setText(estadio.getEstEndereco());
         txtEstCapacidade.setText(estadio.getEstCapacidade());
         txtEstVagas.setText(estadio.getEstVagas());
         
         return rootView;
     }
     
     private void getEstadio(long _id){
     	try {
				if(_id > 0){
					estadio = DadosBD.getInstance().getEstadios(_id);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
     }
}
