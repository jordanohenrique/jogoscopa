package com.copa.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;

import com.copa.DAO.GrupoDAO;
import com.copa.DAO.OitavasDAO;
import com.copa.DAO.QuartasDAO;
import com.copa.DAO.ResultadosDAO;
import com.copa.DAO.SelecoesDAO;
import com.copa.DAO.SemiFinalDAO;
import com.copa.Model.Grupo;
import com.copa.Model.OitavasFinal;
import com.copa.Model.QuartasFinal;
import com.copa.Model.Resultados;
import com.copa.Model.Selecoes;
import com.copa.Model.SemiFinal;

public class VerificaResultados {
	
	private int resSelecao1, resSelecao2;
	private Resultados Resultado = new Resultados();
	private Selecoes selecao1 = new Selecoes();
	private Selecoes selecao2 = new Selecoes();
	private ArrayList<Selecoes> arraySelecoes;
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String data, dia, mes, ano;
	
	public VerificaResultados() {
	}
	
	public ArrayList<Selecoes> verificaResult(long idSelecao1, long idSelecao2, int resSelecao1, int resSelecao2){
		
		this.resSelecao1 = resSelecao1;
		this.resSelecao2 = resSelecao2;
		arraySelecoes = new ArrayList<Selecoes>(); 
		data = sdf.format(date);
		ano = data.substring(6, 10);
		mes = data.substring(3, 5);
		dia = data.substring(0, 2);
		carregaLista(idSelecao1, idSelecao2);
		
		
		if(resSelecao1 == resSelecao2 && Resultado.getJgData().trim().substring(0, 2).equals(dia)
				&& Resultado.getJgData().trim().substring(3, 5).equals(mes)) {
			if(selecao1.getSelNumJogos() == 0){
				setEmpate();
				
			}else if (resSelecao1 != Resultado.getJgResultado1() && resSelecao2 != Resultado.getJgResultado2()) {
				if (selecao1.getSelNumJogos() > 0) {
					if (Resultado.getJgResultado1() > Resultado.getJgResultado2()) {
						reverteResultadoSelecao1();
						if (lastResultado(arraySelecoes)) {
							carregaLista(idSelecao1, idSelecao2);
							setEmpate();
						}
					} else if(Resultado.getJgResultado2() > Resultado.getJgResultado1()) {
						reverteResultadoSelecao2();
						if (lastResultado(arraySelecoes)) {
							carregaLista(idSelecao1, idSelecao2);
							setEmpate();
						}
					} else{
						setEmpate();
					}
				}		
			}else if (resSelecao1 == Resultado.getJgResultado1() && (resSelecao2 > Resultado.getJgResultado2() || resSelecao2 < Resultado.getJgResultado2())) {
				if (selecao1.getSelNumJogos() > 0) {
					if (Resultado.getJgResultado1() > Resultado.getJgResultado2()) {
						reverteResultadoSelecao1();
						if (lastResultado(arraySelecoes)) {
							carregaLista(idSelecao1, idSelecao2);
							setEmpate();
						}
					} else if(Resultado.getJgResultado2() > Resultado.getJgResultado1()) {
						reverteResultadoSelecao2();
						if (lastResultado(arraySelecoes)) {
							carregaLista(idSelecao1, idSelecao2);
							setEmpate();
						}
					}
				}
			}else if ((resSelecao1 > Resultado.getJgResultado1() || resSelecao1 < Resultado.getJgResultado1()) && resSelecao2 == Resultado.getJgResultado2()) {
				if (selecao1.getSelNumJogos() > 0) {
					if (Resultado.getJgResultado1() > Resultado.getJgResultado2()) {
						reverteResultadoSelecao1();
						if (lastResultado(arraySelecoes)) {
							carregaLista(idSelecao1, idSelecao2);
							setEmpate();
						}
					} else if(Resultado.getJgResultado2() > Resultado.getJgResultado1()) {
						reverteResultadoSelecao2();
						if (lastResultado(arraySelecoes)) {
							carregaLista(idSelecao1, idSelecao2);
							setEmpate();
						}
					}
				}
			}
		} else if (resSelecao1 != Resultado.getJgResultado1() || resSelecao2 != Resultado.getJgResultado2()) {
			if (resSelecao1 > resSelecao2) {
			  if(!(Resultado.getJgResultado1() == Resultado.getJgResultado2())){
				if (resSelecao1 != Resultado.getJgResultado1()) {
					if (resSelecao1 > Resultado.getJgResultado1()) {
						if (Resultado.getJgResultado1() > Resultado.getJgResultado2()) {
							if (selecao1.getSelNumJogos() < 4) {
								// se estiver apenas atualizando o placar para a
								// selecao1
								atualizaPlacar();
							}
						} else {
							if (selecao1.getSelNumJogos() == 0) {
								// selecao1
								selecao1.setSelNumJogos(1);
								selecao1.setSelPontos(3);
								selecao1.setSelVitorias(1);
								selecao1.setSelDerrotas(0);
								selecao1.setSelEmpates(0);
								selecao1.setSelSaldoGols(resSelecao1 - resSelecao2);
								selecao1.setSelGolsPro(resSelecao1);
								selecao1.setSelGolsCont(resSelecao2);

								arraySelecoes.add(selecao1);
								// selecao2
								selecao2.setSelNumJogos(1);
								selecao2.setSelVitorias(0);
								selecao2.setSelDerrotas(1);
								selecao2.setSelEmpates(0);
								selecao2.setSelSaldoGols(resSelecao2 - resSelecao1);
								selecao2.setSelGolsPro(resSelecao2);
								selecao2.setSelGolsCont(resSelecao1);

								arraySelecoes.add(selecao2);

							}else if(Resultado.getJgResultado2() > Resultado.getJgResultado1()){
								// Quando o resultado inverter a favor da
								// selecao1
								invertePlacarSelecao1();
								
							}else if(selecao1.getSelNumJogos()>0){
								setResultadoSelecao1();
							}
						}
					} else if (Resultado.getJgResultado1() > Resultado.getJgResultado2()) {
						if (resSelecao1 > Resultado.getJgResultado2()) {
							// Seta os resultados a favor da selecão 1
							setResultadoSelecao1();
						}
					} else {
						// Inverte os resultados se result selecoa 2 era >
						// selecao 1
						invertePlacarSelecao1();
					}
				} else {
					atualizaPlacar();
				}
			  }else{
				  if(selecao1.getSelEmpates() > 0 && selecao2.getSelEmpates() > 0){
					  reverteEmpate();
					  setResultadoSelecao1();
				  
				  }else{
					  setResultadoSelecao1();
				  }
			  }
			}else if (resSelecao2 > resSelecao1) {
			  if(!(Resultado.getJgResultado1() == Resultado.getJgResultado2())){
				if (resSelecao2 != Resultado.getJgResultado2()) {
					if (resSelecao2 > Resultado.getJgResultado2()) {
						if (Resultado.getJgResultado2() > Resultado.getJgResultado1()) {
							if (selecao2.getSelNumJogos() < 4) {
								// atualiza placar selecao 2 > selecao 1
								atualizaPlacar();
							}
						} else {
							if (selecao2.getSelNumJogos() == 0) {
								// selecao1
								selecao2.setSelNumJogos(1);
								selecao2.setSelPontos(3);
								selecao2.setSelVitorias(1);
								selecao2.setSelDerrotas(0);
								selecao2.setSelEmpates(0);
								selecao2.setSelSaldoGols(resSelecao2 - resSelecao1);
								selecao2.setSelGolsPro(resSelecao2);
								selecao2.setSelGolsCont(resSelecao1);
								// selecao1
								selecao1.setSelNumJogos(1);
								selecao1.setSelVitorias(0);
								selecao1.setSelDerrotas(1);
								selecao1.setSelEmpates(0);
								selecao1.setSelSaldoGols(resSelecao1 - resSelecao2);
								selecao1.setSelGolsPro(resSelecao1);
								selecao1.setSelGolsCont(resSelecao2);
								arraySelecoes.add(selecao1);
								arraySelecoes.add(selecao2);
								
							} else if(Resultado.getJgResultado1() > Resultado.getJgResultado2()){
								// Quando o resultado inverter a favor da
								// selecao1
								invertePlacarSelecao2();
							}else if(selecao2.getSelNumJogos() > 0){
								setResultadoSelecao2();
							}
						}
					} else if (Resultado.getJgResultado2() > Resultado.getJgResultado1()) {
						if (resSelecao2 > Resultado.getJgResultado1()) {
							// seta resultado a favor selecao 2
							setResultadoSelecao2();
						}

					} else {
						// inverte os resultados se result selecao 1 era > 2
						invertePlacarSelecao2();
					}
				} else {
					atualizaPlacar();
				}
			  }else{
				if(selecao1.getSelEmpates() > 0 && selecao2.getSelEmpates() > 0){
						reverteEmpate();
						setResultadoSelecao2();
				}else{
					setResultadoSelecao2();
				}
			  }
		   }
		}
		return arraySelecoes;
	 }
	
	private List<Selecoes> invertePlacarSelecao1(){
		arraySelecoes = new ArrayList<Selecoes>();
		//selecao1
		selecao1.setSelNumJogos(selecao1.getSelNumJogos());
		selecao1.setSelPontos(selecao1.getSelPontos() + 3);
		selecao1.setSelVitorias(selecao1.getSelVitorias() + 1);
		selecao1.setSelDerrotas(selecao1.getSelDerrotas() - 1);
		selecao1.setSelEmpates(selecao1.getSelEmpates());
		selecao1.setSelSaldoGols(((selecao1.getSelSaldoGols() - Resultado.getJgResultado1() + Resultado.getJgResultado2()) + resSelecao1) - resSelecao2);
		selecao1.setSelGolsPro((selecao1.getSelGolsPro() - Resultado.getJgResultado1()) + resSelecao1);
		selecao1.setSelGolsCont((selecao1.getSelGolsCont() - Resultado.getJgResultado2()) + resSelecao2);
		arraySelecoes.add(selecao1);
		// selecao2
		selecao2.setSelNumJogos(selecao2.getSelNumJogos());
		selecao2.setSelPontos(selecao2.getSelPontos() - 3);
		selecao2.setSelVitorias(selecao2.getSelVitorias() - 1);
		selecao2.setSelDerrotas(selecao2.getSelDerrotas() + 1);
		selecao2.setSelEmpates(selecao2.getSelEmpates());
		selecao2.setSelSaldoGols(((selecao2.getSelSaldoGols() - Resultado.getJgResultado2() + Resultado.getJgResultado1()) + resSelecao2) - resSelecao1);
		selecao2.setSelGolsPro((selecao2.getSelGolsPro() - Resultado.getJgResultado2()) + resSelecao2);
		selecao2.setSelGolsCont((selecao2.getSelGolsCont() - Resultado.getJgResultado1()) + resSelecao1);
		arraySelecoes.add(selecao2);			
		
		return arraySelecoes;
	}
	private List<Selecoes> invertePlacarSelecao2(){
		arraySelecoes = new ArrayList<Selecoes>();
		
		//selecao1
		selecao1.setSelNumJogos(selecao1.getSelNumJogos());
		selecao1.setSelPontos(selecao1.getSelPontos() - 3);
		selecao1.setSelVitorias(selecao1.getSelVitorias() - 1);
		selecao1.setSelDerrotas(selecao1.getSelDerrotas() + 1);
		selecao1.setSelEmpates(selecao1.getSelEmpates());
		selecao1.setSelSaldoGols(((selecao1.getSelSaldoGols() - Resultado.getJgResultado1() + Resultado.getJgResultado2()) + resSelecao1) - resSelecao2);
		selecao1.setSelGolsPro((selecao1.getSelGolsPro() - Resultado.getJgResultado1()) + resSelecao1);
		selecao1.setSelGolsCont((selecao1.getSelGolsCont() - Resultado.getJgResultado2()) + resSelecao2);
		arraySelecoes.add(selecao1);
		// selecao2
		selecao2.setSelNumJogos(selecao2.getSelNumJogos());
		selecao2.setSelPontos(selecao2.getSelPontos() + 3);
		selecao2.setSelVitorias(selecao2.getSelVitorias() + 1);
		selecao2.setSelDerrotas(selecao2.getSelDerrotas() - 1);
		selecao2.setSelEmpates(selecao2.getSelEmpates());
		selecao2.setSelSaldoGols(resSelecao2 + (selecao2.getSelSaldoGols() - Resultado.getJgResultado2() + Resultado.getJgResultado1()) - resSelecao1);
		selecao2.setSelGolsPro((selecao2.getSelGolsPro() - Resultado.getJgResultado2()) + resSelecao2);
		selecao2.setSelGolsCont((selecao2.getSelGolsCont() - Resultado.getJgResultado1()) + resSelecao1);
		arraySelecoes.add(selecao2);
		
		return arraySelecoes;
	}
	private List<Selecoes> atualizaPlacar(){
		arraySelecoes = new ArrayList<Selecoes>();
		// selecao1
		selecao1.setSelSaldoGols(((selecao1.getSelSaldoGols() - Resultado.getJgResultado1() + Resultado.getJgResultado2()) + resSelecao1) - resSelecao2);
		selecao1.setSelGolsPro((selecao1.getSelGolsPro()  - Resultado.getJgResultado1()) + resSelecao1);
		selecao1.setSelGolsCont((selecao1 .getSelGolsCont() - Resultado.getJgResultado2()) + resSelecao2);
		arraySelecoes.add(selecao1);
		// selecao2
		selecao2.setSelSaldoGols(((selecao2.getSelSaldoGols() - Resultado.getJgResultado2() + Resultado.getJgResultado1()) + resSelecao2) - resSelecao1);
		selecao2.setSelGolsPro((selecao2.getSelGolsPro() - Resultado.getJgResultado2()) + resSelecao2);
		selecao2.setSelGolsCont((selecao2.getSelGolsCont()  - Resultado.getJgResultado1()) + resSelecao1);
		arraySelecoes.add(selecao2);
		
		return arraySelecoes;
	}

	private List<Selecoes> setResultadoSelecao1(){
		arraySelecoes = new ArrayList<Selecoes>();
		// selecao2
		selecao1.setSelNumJogos(selecao1.getSelNumJogos() + 1);
		selecao1.setSelPontos(selecao1.getSelPontos() + 3);
		selecao1.setSelVitorias(selecao1.getSelVitorias() + 1);
		selecao1.setSelDerrotas(selecao1.getSelDerrotas());
		selecao1.setSelEmpates(selecao1.getSelEmpates());
		selecao1.setSelSaldoGols((selecao1.getSelSaldoGols() + resSelecao1) - resSelecao2);
		selecao1.setSelGolsPro(selecao1.getSelGolsPro() + resSelecao1);
		selecao1.setSelGolsCont(selecao1 .getSelGolsCont() + resSelecao2);
		arraySelecoes.add(selecao1);
		// selecao2
		selecao2.setSelNumJogos(selecao2.getSelNumJogos() + 1);
		selecao2.setSelVitorias(selecao2.getSelVitorias());
		selecao2.setSelDerrotas(selecao2.getSelDerrotas() + 1);
		selecao2.setSelEmpates(selecao2.getSelEmpates());
		selecao2.setSelSaldoGols((selecao2.getSelSaldoGols()  + resSelecao2) - resSelecao1);
		selecao2.setSelGolsPro(selecao2.getSelGolsPro() + resSelecao2);
		selecao2.setSelGolsCont(selecao2.getSelGolsCont() + resSelecao1);
		arraySelecoes.add(selecao2);
		
		return arraySelecoes;
	}
	private List<Selecoes> setResultadoSelecao2(){
		arraySelecoes = new ArrayList<Selecoes>();
		// selecao2
		selecao2.setSelNumJogos(selecao2.getSelNumJogos() + 1);
		selecao2.setSelPontos(selecao2.getSelPontos() + 3);
		selecao2.setSelVitorias(selecao2.getSelVitorias() + 1);
		selecao2.setSelDerrotas(selecao2.getSelDerrotas());
		selecao2.setSelEmpates(selecao2.getSelEmpates());
		selecao2.setSelSaldoGols((selecao2.getSelSaldoGols() + resSelecao2) - resSelecao1);
		selecao2.setSelGolsPro(selecao2.getSelGolsPro() + resSelecao2);
		selecao2.setSelGolsCont(selecao2.getSelGolsCont() + resSelecao1);
		//selecao1
		selecao1.setSelNumJogos(selecao1.getSelNumJogos() + 1);
		selecao1.setSelVitorias(selecao1.getSelVitorias());
		selecao1.setSelDerrotas(selecao1.getSelDerrotas() + 1);
		selecao1.setSelEmpates(selecao1.getSelEmpates());
		selecao1.setSelSaldoGols((selecao1.getSelSaldoGols()  + resSelecao1) - resSelecao2);
		selecao1.setSelGolsPro(selecao1.getSelGolsPro() + resSelecao1);
		selecao1.setSelGolsCont(selecao1.getSelGolsCont() + resSelecao2);
		arraySelecoes.add(selecao1);
		arraySelecoes.add(selecao2);
		
		return arraySelecoes;
	}
	
	private List<Selecoes> setEmpate(){
		arraySelecoes = new ArrayList<Selecoes>();
		
		// Selecao1
		selecao1.setSelNumJogos(selecao1.getSelNumJogos() + 1);
		selecao1.setSelPontos(selecao1.getSelPontos() + 1);
		selecao1.setSelEmpates(selecao1.getSelEmpates() + 1);
		selecao1.setSelSaldoGols(selecao1.getSelSaldoGols() + resSelecao1 - resSelecao2);
		selecao1.setSelGolsPro(selecao1.getSelGolsPro() + resSelecao1);
		selecao1.setSelGolsCont(selecao1.getSelGolsCont() + resSelecao2);
		arraySelecoes.add(selecao1);

		// Selecao2
		selecao2.setSelNumJogos(selecao2.getSelNumJogos() + 1);
		selecao2.setSelPontos(selecao2.getSelPontos() + 1);
		selecao2.setSelEmpates(selecao2.getSelEmpates() + 1);
		selecao2.setSelSaldoGols(selecao2.getSelSaldoGols() + resSelecao2 - resSelecao1);
		selecao2.setSelGolsPro(selecao2.getSelGolsPro() + resSelecao2);
		selecao2.setSelGolsCont(selecao2.getSelGolsCont() + resSelecao1);
		arraySelecoes.add(selecao2);
		
		return arraySelecoes;
	}
	
	private List<Selecoes> reverteEmpate(){
		arraySelecoes = new ArrayList<Selecoes>();
		
		// Selecao1
		selecao1.setSelNumJogos(selecao1.getSelNumJogos() - 1);
		selecao1.setSelPontos(selecao1.getSelPontos() - 1);
		selecao1.setSelEmpates(selecao1.getSelEmpates() - 1);
		selecao1.setSelSaldoGols(selecao1.getSelSaldoGols() - Resultado.getJgResultado1() + Resultado.getJgResultado2());
		selecao1.setSelGolsPro(selecao1.getSelGolsPro() - Resultado.getJgResultado1());
		selecao1.setSelGolsCont(selecao1.getSelGolsCont() - Resultado.getJgResultado2());
		arraySelecoes.add(selecao1);

		// Selecao2
		selecao2.setSelNumJogos(selecao2.getSelNumJogos() - 1);
		selecao2.setSelPontos(selecao2.getSelPontos() - 1);
		selecao2.setSelEmpates(selecao2.getSelEmpates() - 1);
		selecao2.setSelSaldoGols(selecao2.getSelSaldoGols() - Resultado.getJgResultado2() + Resultado.getJgResultado1());
		selecao2.setSelGolsPro(selecao2.getSelGolsPro() - Resultado.getJgResultado2());
		selecao2.setSelGolsCont(selecao2.getSelGolsCont() - Resultado.getJgResultado1());
		arraySelecoes.add(selecao2);
		
		return arraySelecoes;
	}
	
	private void reverteResultadoSelecao1() {
		
		//selecao1
		selecao1.setSelNumJogos(selecao1.getSelNumJogos() - 1);
		selecao1.setSelPontos(selecao1.getSelPontos() - 3);
		selecao1.setSelVitorias(selecao1.getSelVitorias() - 1);
		selecao1.setSelSaldoGols(((selecao1.getSelSaldoGols() - Resultado.getJgResultado1() + Resultado.getJgResultado2()) + resSelecao1) - resSelecao2);
		selecao1.setSelGolsPro((selecao1.getSelGolsPro() - Resultado.getJgResultado1()));
		selecao1.setSelGolsCont((selecao1.getSelGolsCont() - Resultado.getJgResultado2()));
		arraySelecoes.add(selecao1);

		// selecao2
		selecao2.setSelNumJogos(selecao2.getSelNumJogos() - 1);
		selecao2.setSelDerrotas(selecao2.getSelDerrotas() - 1);
		selecao2.setSelSaldoGols(resSelecao2 + (selecao2.getSelSaldoGols() - Resultado.getJgResultado2() + Resultado.getJgResultado1()) - resSelecao1);
		selecao2.setSelGolsPro((selecao2.getSelGolsPro() - Resultado.getJgResultado2()));
		selecao2.setSelGolsCont((selecao2.getSelGolsCont() - Resultado.getJgResultado1()));
		arraySelecoes.add(selecao2);

	}
	
	private void reverteResultadoSelecao2() {

		//selecao1
		selecao1.setSelNumJogos(selecao1.getSelNumJogos() - 1);
		selecao1.setSelDerrotas(selecao1.getSelDerrotas() - 1);
		selecao1.setSelSaldoGols(((selecao1.getSelSaldoGols() - Resultado.getJgResultado1() + Resultado.getJgResultado2()) + resSelecao1) - resSelecao2);
		selecao1.setSelGolsPro((selecao1.getSelGolsPro() - Resultado.getJgResultado1()));
		selecao1.setSelGolsCont((selecao1.getSelGolsCont() - Resultado.getJgResultado2()));
		arraySelecoes.add(selecao1);

		// selecao2
		selecao2.setSelNumJogos(selecao2.getSelNumJogos() - 1);
		selecao2.setSelPontos(selecao2.getSelPontos() - 3);
		selecao2.setSelDerrotas(selecao2.getSelVitorias() - 1);
		selecao2.setSelSaldoGols(resSelecao2 + (selecao2.getSelSaldoGols() - Resultado.getJgResultado2() + Resultado.getJgResultado1()) - resSelecao1);
		selecao2.setSelGolsPro((selecao2.getSelGolsPro() - Resultado.getJgResultado2()));
		selecao2.setSelGolsCont((selecao2.getSelGolsCont() - Resultado.getJgResultado1()));
		arraySelecoes.add(selecao2);

	}
	
	private boolean lastResultado(List<Selecoes> listSelecoes){
		boolean result = false;
		try {

			for (Selecoes selecao : listSelecoes) {
				ContentValues values = new ContentValues();
				
				values.put("selPontos", selecao.getSelPontos());
				values.put("selNumJogos", selecao.getSelNumJogos());
				values.put("selEmpates", selecao.getSelEmpates());
				values.put("selVitorias", selecao.getSelVitorias());
				values.put("selDerrotas", selecao.getSelDerrotas());
				values.put("selGolsPro", selecao.getSelGolsPro());
				values.put("selGolsCont", selecao.getSelGolsCont());
				values.put("selSaldoGols", selecao.getSelSaldoGols());

				Utils.db.update("SELECAO", values, "idSelecao = "+ selecao.getIdSelecao(), null);
				result = true;
			}
		} catch (Exception e) {
			return result = false;
		}
		
		return result;
	}
	
	private void carregaLista(long idSelecao1, long idSelecao2){
		
		Resultado = ResultadosDAO.getInstance().loadResultado(idSelecao1, idSelecao2);
		selecao1 = new Selecoes();
		selecao2 = new Selecoes();
		selecao1 = SelecoesDAO.getInstance().loadSelecao(Resultado.getJgIdSelecao1());
		selecao2 = SelecoesDAO.getInstance().loadSelecao(Resultado.getJgIdSelecao2());
	}
	
	public List<OitavasFinal> fechaReultadosClassificacao(){
		int referencia = 1, soma;
		OitavasFinal oitavasFinal = null;
		List<Grupo> listGrupos = new ArrayList<Grupo>();
		List<Selecoes> listSelecoes = new ArrayList<Selecoes>();
		ArrayList<OitavasFinal> arrayOitavas = new ArrayList<OitavasFinal>();
		listGrupos= GrupoDAO.getIstance().AllGrupos();
		for(Grupo grupo : listGrupos){
			listSelecoes = SelecoesDAO.getInstance().getSelecoesByGrupo(grupo.getGruDescricao());
			soma = 0;
			for(Selecoes selecao : listSelecoes){
				soma = soma + selecao.getSelNumJogos();
				if(soma >= 12){
					oitavasFinal = new OitavasFinal();
					oitavasFinal.setOitIdGrupo(listSelecoes.get(0).getGrupoId());
					oitavasFinal.setOitIdSelecao(listSelecoes.get(0).getIdSelecao());
					oitavasFinal.setOitSelNome(listSelecoes.get(0).getSelNome());
					oitavasFinal.setOitPosicao(1);
					arrayOitavas.add(oitavasFinal);
					//Segundo lugar
					oitavasFinal = new OitavasFinal();
					oitavasFinal.setOitIdGrupo(listSelecoes.get(1).getGrupoId());
					oitavasFinal.setOitIdSelecao(listSelecoes.get(1).getIdSelecao());
					oitavasFinal.setOitSelNome(listSelecoes.get(1).getSelNome());
					oitavasFinal.setOitPosicao(2);
					arrayOitavas.add(oitavasFinal);
				}
			}
			referencia++;
		}
		return arrayOitavas;
	}
	
	public List<QuartasFinal> fechaResultadosOitavas(){
		int s1 = 0, s2 = 1;
		QuartasFinal quartasFinal;
		List<QuartasFinal> listQuartasFinal = new ArrayList<QuartasFinal>();
		List<OitavasFinal> listOitavas = new ArrayList<OitavasFinal>();
		listOitavas = OitavasDAO.getInstance().AllOitavas();
		
		while (s1 < listOitavas.size()) {
			quartasFinal = new QuartasFinal();
			if(listOitavas.get(s1).getOitResultado() > listOitavas.get(s2).getOitResultado()){
				quartasFinal.setQuaIdSelecao(listOitavas.get(s1).getOitIdSelecao());
				quartasFinal.setQuaSelNome(listOitavas.get(s1).getOitSelNome());
				quartasFinal.setQuaIdGrupo(listOitavas.get(s1).getOitIdGrupo());
				quartasFinal.setQuaReferencia(listOitavas.get(s1).getOitReferencia());
				listQuartasFinal.add(quartasFinal);
			}else if(listOitavas.get(s1).getOitResultado() < listOitavas.get(s2).getOitResultado()){
				quartasFinal.setQuaIdSelecao(listOitavas.get(s2).getOitIdSelecao());
				quartasFinal.setQuaSelNome(listOitavas.get(s2).getOitSelNome());
				quartasFinal.setQuaIdGrupo(listOitavas.get(s2).getOitIdGrupo());
				quartasFinal.setQuaReferencia(listOitavas.get(s2).getOitReferencia());
				listQuartasFinal.add(quartasFinal);
			}			
			s1=s1+2;
			s2=s2+2;
		}
		
		return listQuartasFinal;
	}
	
	public List<SemiFinal> fechaResultadosQuartas(){
		int s1 = 0, s2 = 1;
		SemiFinal semiFinal;
		List<SemiFinal> listSemiFinal = new ArrayList<SemiFinal>();
		List<QuartasFinal> listQuartas = new ArrayList<QuartasFinal>();
		listQuartas = QuartasDAO.getInstance().AllQuartas();
		
		while (s1 < listQuartas.size()) {
			semiFinal = new SemiFinal();
			if(listQuartas.get(s1).getQuaResultado() > listQuartas.get(s2).getQuaResultado()){
				semiFinal.setSemiIdSelecao(listQuartas.get(s1).getQuaIdSelecao());
				semiFinal.setSemiSelNome(listQuartas.get(s1).getQuaSelNome());
				semiFinal.setSemiIdGrupo(listQuartas.get(s1).getQuaIdGrupo());
				semiFinal.setSemiReferencia(listQuartas.get(s1).getQuaReferencia() + listQuartas.get(s2).getQuaReferencia());
				listSemiFinal.add(semiFinal);
			}else if(listQuartas.get(s1).getQuaResultado() < listQuartas.get(s2).getQuaResultado()){
				semiFinal.setSemiIdSelecao(listQuartas.get(s2).getQuaIdSelecao());
				semiFinal.setSemiSelNome(listQuartas.get(s2).getQuaSelNome());
				semiFinal.setSemiIdGrupo(listQuartas.get(s2).getQuaIdGrupo());
				semiFinal.setSemiReferencia(listQuartas.get(s1).getQuaReferencia() + listQuartas.get(s2).getQuaReferencia());
				listSemiFinal.add(semiFinal);
			}			
			s1=s1+2;
			s2=s2+2;
		}
		
		return listSemiFinal;
	}
	
	public List<SemiFinal> fechaResultadosSemi(){
		int s1 = 0, s2 = 1;
		SemiFinal semiFinal;
		List<SemiFinal> listFinal = new ArrayList<SemiFinal>();
		List<SemiFinal> listSemiFinal = new ArrayList<SemiFinal>();
		listSemiFinal = SemiFinalDAO.getInstance().getAllSemiFinal();
		
		while (s1 < listSemiFinal.size()) {
			semiFinal = new SemiFinal();
			if(listSemiFinal.get(s1).getSemiResultado() > listSemiFinal.get(s2).getSemiResultado()){
				semiFinal.setSemiIdSelecao(listSemiFinal.get(s1).getSemiIdSelecao());
				semiFinal.setSemiSelNome(listSemiFinal.get(s1).getSemiSelNome());
				semiFinal.setSemiIdGrupo(listSemiFinal.get(s1).getSemiIdGrupo());
				semiFinal.setSemiPosicao(s1);
				
				listFinal.add(semiFinal);
				
			}else if(listSemiFinal.get(s1).getSemiResultado() < listSemiFinal.get(s2).getSemiResultado()){
				semiFinal.setSemiIdSelecao(listSemiFinal.get(s2).getSemiIdSelecao());
				semiFinal.setSemiSelNome(listSemiFinal.get(s2).getSemiSelNome());
				semiFinal.setSemiIdGrupo(listSemiFinal.get(s2).getSemiIdGrupo());
				semiFinal.setSemiPosicao(s1);

				listFinal.add(semiFinal);
			}			
			s1=s1+2;
			s2=s2+2;
		}
		
		return listSemiFinal;
	}
	
}
