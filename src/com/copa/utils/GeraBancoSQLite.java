package com.copa.utils;

import com.copa.DAO.DadosBD;
import com.copa.jogoscopa.R;
import com.copa.Model.Parametros;

public class GeraBancoSQLite {
	
	public GeraBancoSQLite() {
		PARAMETROS();
		if(populateParametros()){
			DadosBD.getInstance().setParametros();
			if(Parametros.getParVersaoBD().equalsIgnoreCase("1.0")){
				Grupo();
				Selecao();
				Jogador();
				Resultado();
				Oitavas();
				Quartas();
				Semifinal();
				Final();
				ESTADIOS();
				POPULATETABLE();
			}
		}
	}
	
	// Classes Clientes
		public boolean Grupo()
		{
			Utils.setSQL("");
			try {
				Utils.db.execSQL("CREATE TABLE IF NOT EXISTS GRUPO("+
									" idGrupo INTEGER primary key, "+
									" gruDescricao NVarchar(50) NULL);");
						
				Utils.db.execSQL(Utils.getSQL());
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		// Clientes
		public Boolean Selecao()
		  {
			Utils.setSQL(null);
			try {
				
				Utils.setSQL("CREATE TABLE IF NOT EXISTS SELECAO(" +
						"idSelecao INTEGER Primary Key NOT NULL, "+
						"selNome nvarchar(50) NULL, " +
						"selPontos int NULL, "+
						"selVitorias int NULL, "+
						"selDerrotas int NULL, " +
						"selSaldoGols int NULL, " +
						"selNumJogos int NULL, "+
						"selEmpates int NULL, " +
						"selGolsPro int NULL, " +
						"selGolsCont int NULL, "+
						"selFlagID integer NULL, "+
						"GrupoId int CONSTRAINT [FK_Grupo_Selecao] REFERENCES Grupo(idGrupo));");
				// cria o tabela	
				Utils.db.execSQL(Utils.getSQL());
				
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		public boolean Jogador()
		{
			Utils.setSQL("");
			try {
				Utils.setSQL("CREATE TABLE IF NOT EXISTS JOGADOR("+
									" idJogador INTEGER primary key, "+
									" jogNome nvarchar(50) NULL,"+ 
									" jogPosicao nvarchar(50) NULL,"+
									" jogStatus nvarchar(1) DEFAULT ('A'),"+
									" SelecaoId int CONSTRAINT [FK_Selecao_Jogador] REFERENCES Selecao(idSelecao));");
						
				Utils.db.execSQL(Utils.getSQL());
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		// Clientes
		public Boolean Resultado()
		  {
			Utils.setSQL(null);
			try {
				
				Utils.setSQL("CREATE TABLE IF NOT EXISTS RESULTADO(" +
						"IdJogos INTEGER Primary Key NOT NULL, "+
						"jgIdSelecao1 int NULL, " +
						"jgIdSelecao2 int NULL, "+
						"jgSelecao1 nvarchar(50) NULL, " +
						"jgSelecao2 nvarchar(50) NULL, " +
						"jgReferencia nvarchar(20) NULL, "+
						"jgResultado1 int NULL, " +
						"jgResultado2 int NULL, " +
						"jgData nvarchar(10) NULL, " +
						"jgSituacao nvarchar(1) DEFAULT('A'), "+
						"jgLocal nvarchar(120) NULL, "+
						"GrupoId int CONSTRAINT [FK_Grupo_Resultado] REFERENCES Grupo(idGrupo));");
				// cria o tabela	
				Utils.db.execSQL(Utils.getSQL());
				
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		public boolean Oitavas()
		{
			Utils.setSQL("");
			try {
				Utils.setSQL("CREATE TABLE IF NOT EXISTS OITAVAS("+
									" idOitavas INTEGER primary key, "+
									" oitIdSelecao int NULL,"+
									" oitSelNome nvarchar(100) NULL,"+
									" oitResultado int NULL,"+
									" oitIdGrupo int NULL,"+
									" oitReferencia int NULL,"+
									" oitLocal nvarchar(120) NULL,"+
									" oitPosicao int NULL);");
						
				Utils.db.execSQL(Utils.getSQL());
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		public boolean Quartas()
		{
			Utils.setSQL("");
			try {
				Utils.setSQL("CREATE TABLE IF NOT EXISTS QUARTAS("+
									" idQuartas INTEGER primary key, "+
									" quaIdSelecao int NULL,"+ 
									" quaSelNome nvarchar(100) NULL,"+
									" quaResultado int NULL,"+
									" quaReferencia int NULL,"+
									" quaIdGrupo int NULL,"+
									" quaLocal nvarchar(120) NULL);");
						
				Utils.db.execSQL(Utils.getSQL());
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		public boolean Semifinal()
		{
			Utils.setSQL("");
			try {
				Utils.setSQL("CREATE TABLE IF NOT EXISTS SEMIFINAL("+
									" idSemi INTEGER primary key, "+
									" semiIdSelecao int NULL,"+ 
									" semiSelNome nvarchar(100) NULL,"+
									" semiResultado int NULL,"+
									" semiReferencia int NULL,"+
									" semiIdGrupo int NULL,"+
									" semiLocal nvarchar(120) NULL);");
						
				Utils.db.execSQL(Utils.getSQL());
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		public boolean Final()
		{
			Utils.setSQL("");
			try {
				Utils.setSQL("CREATE TABLE IF NOT EXISTS FINAL("+
									" idFinal INTEGER primary key, "+
									" finIdSelecao int NULL,"+
									" finSelNome nvarchar(80),"+
									" finIdGrupo int NULL,"+
									" finResultado int NULL,"+									
									" finLocal nvarchar(120) NULL,"+
									" finPosicao int NULL);");
						
				Utils.db.execSQL(Utils.getSQL());
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		public boolean PARAMETROS()
		{
			Utils.setSQL("");
			try {
				Utils.setSQL("CREATE TABLE IF NOT EXISTS PARAMETROS("+
									" idParametro INTEGER primary key, "+
									" parURL nvarchar(120) DEFAULT('www'),"+ 
									" parVersao nvarchar(10) DEFAULT('1.0'),"+
									" parVersaoBD nvarchar(10) DEFAULT('1.0'),"+
									" parOitavas nvarchar(1) DEFAULT('F'),"+
									" parQuartas nvarchar(1) DEFAULT('F'),"+
									" parSemi nvarchar(1) DEFAULT('F'),"+
									" parClassif nvarchar(1) DEFAULT('F'));");
						
				Utils.db.execSQL(Utils.getSQL());
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		public boolean ESTADIOS()
		{
			Utils.setSQL("");
			try {
				Utils.setSQL("CREATE TABLE IF NOT EXISTS ESTADIO("+
									" idEstadio INTEGER primary key, "+
									" estCidade nvarchar(120) null,"+ 
									" estEndereco nvarchar(10) null,"+
									" estNome nvarchar(100) null,"+
									" estApelido nvarchar(60) null,"+
									" estCapacidade nvarchar(10) null,"+
									" estVagas nvarchar(8) null,"+
									" estImagem integer);");
						
				Utils.db.execSQL(Utils.getSQL());
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		public boolean POPULATETABLE()
		{
			Utils.setSQL("");
			try {
				//GRUPOS
				Utils.db.execSQL("Insert into GRUPO (gruDescricao) SELECT 'GRUPO A' WHERE NOT EXISTS(SELECT 1 FROM GRUPO WHERE gruDescricao = 'GRUPO A');");
				Utils.db.execSQL("Insert into GRUPO (gruDescricao) SELECT 'GRUPO B' WHERE NOT EXISTS(SELECT 1 FROM GRUPO WHERE gruDescricao = 'GRUPO B');");
				Utils.db.execSQL("Insert into GRUPO (gruDescricao) SELECT 'GRUPO C' WHERE NOT EXISTS(SELECT 1 FROM GRUPO WHERE gruDescricao = 'GRUPO C');");
				Utils.db.execSQL("Insert into GRUPO (gruDescricao) SELECT 'GRUPO D' WHERE NOT EXISTS(SELECT 1 FROM GRUPO WHERE gruDescricao = 'GRUPO D');");
				Utils.db.execSQL("Insert into GRUPO (gruDescricao) SELECT 'GRUPO E' WHERE NOT EXISTS(SELECT 1 FROM GRUPO WHERE gruDescricao = 'GRUPO E');");
				Utils.db.execSQL("Insert into GRUPO (gruDescricao) SELECT 'GRUPO F' WHERE NOT EXISTS(SELECT 1 FROM GRUPO WHERE gruDescricao = 'GRUPO F');");
				Utils.db.execSQL("Insert into GRUPO (gruDescricao) SELECT 'GRUPO G' WHERE NOT EXISTS(SELECT 1 FROM GRUPO WHERE gruDescricao = 'GRUPO G');");
				Utils.db.execSQL("Insert into GRUPO (gruDescricao) SELECT 'GRUPO H' WHERE NOT EXISTS(SELECT 1 FROM GRUPO WHERE gruDescricao = 'GRUPO H');");
				
				//SELECOES
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
				        "selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'BRASIL',0,0,0,0,0,0,0,0,0,1 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'BRASIL');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'CRO�CIA',0,0,0,0,0,0,0,0,0,1 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'CRO�CIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'M�XICO',0,0,0,0,0,0,0,0,0,1 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'M�XICO');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'CAMAR�ES',0,0,0,0,0,0,0,0,0,1 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'CAMAR�ES');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'ESPANHA',0,0,0,0,0,0,0,0,0,2 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'ESPANHA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'HOLANDA',0,0,0,0,0,0,0,0,0,2 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'HOLANDA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'CHILE',0,0,0,0,0,0,0,0,0,2 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'CHILE');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'AUSTR�LIA',0,0,0,0,0,0,0,0,0,2 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'AUSTR�LIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'COL�MBIA',0,0,0,0,0,0,0,0,0,3 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'COL�MBIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'GR�SSIA',0,0,0,0,0,0,0,0,0,3 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'GR�SSIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'COSTA DO MARFIM',0,0,0,0,0,0,0,0,0,3 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'COSTA DO MARFIM');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'JAP�O',0,0,0,0,0,0,0,0,0,3 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'JAP�O');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'URUGUAI',0,0,0,0,0,0,0,0,0,4 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'URUGUAI');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'COSTA RICA',0,0,0,0,0,0,0,0,0,4 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'COSTA RICA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'INGLATERRA',0,0,0,0,0,0,0,0,0,4 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'INGLATERRA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'IT�LIA',0,0,0,0,0,0,0,0,0,4 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'IT�LIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'SUI�A',0,0,0,0,0,0,0,0,0,5 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'SUI�A');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'EQUADOR',0,0,0,0,0,0,0,0,0,5 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'EQUADOR');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'FRAN�A',0,0,0,0,0,0,0,0,0,5 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'FRAN�A');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'HONDURAS',0,0,0,0,0,0,0,0,0,5 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'HONDURAS');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'ARGENTINA',0,0,0,0,0,0,0,0,0,6 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'ARGENTINA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'B�SNIA',0,0,0,0,0,0,0,0,0,6 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'B�SNIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'IR�',0,0,0,0,0,0,0,0,0,6 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'IR�');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'NIGERIA',0,0,0,0,0,0,0,0,0,6 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'NIGERIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'ALEMANHA',0,0,0,0,0,0,0,0,0,7 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'ALEMANHA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'PORTUGAL',0,0,0,0,0,0,0,0,0,7 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'PORTUGAL');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'GANA',0,0,0,0,0,0,0,0,0,7 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'GANA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'EUA',0,0,0,0,0,0,0,0,0,7 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'EUA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'B�LGICA',0,0,0,0,0,0,0,0,0,8 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'B�LGICA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'ARG�LIA',0,0,0,0,0,0,0,0,0,8 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'ARG�LIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'RUSSIA',0,0,0,0,0,0,0,0,0,8 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'RUSSIA');");
				Utils.db.execSQL("Insert into SELECAO (selNome, selPontos, selVitorias, selDerrotas, selSaldoGols, selNumJogos, selEmpates,"+
						"selGolsPro, selGolsCont, selFlagID, GrupoId) SELECT 'COREIA DO SUL',0,0,0,0,0,0,0,0,0,8 WHERE NOT EXISTS(SELECT 1 FROM SELECAO WHERE selNome = 'COREIA DO SUL');");
				
				//RESULTADO
				//RODADAS GRUPO A
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 1,2,'BRASIL','CRO�CIA',121,0,0,'12/06/2014', 'Qui 12/06/2014 - 17:00 Arena Corinthians',1 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='121');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 3,4,'M�XICO','CAMAR�ES',341,0,0,'13/06/2014', 'Sex 13/06/2014 - 13:00 Arena das Dunas',1 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='341');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 1,3,'BRASIL','M�XICO',131,0,0,'17/06/2014', 'Ter 17/06/2014 - 16:00 Castel�o (CE)', 1 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='131');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 2,4,'CRO�CIA','CAMAR�ES',241,0,0,'18/06/2014', 'Qua 18/06/2014 - 19:00 Arena da Amaz�nia',1 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='241');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 1,4,'BRASIL','CAMAR�ES',141,0,0,'23/06/2014', 'Seg 23/06/2014 - 17:00 Man� Garrincha',1 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='141');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 2,3,'CRO�CIA','M�XICO',231,0,0,'23/06/2014', 'Seg 23/06/2014 - 17:00 Arena Pernambuco',1 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='231');");
				//RODADAS GRUPO B
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 5,6,'ESPANHA','HOLANDA',562,0,0,'13/06/2014', 'Sex 13/06/2014 - 16:00 Fonte Nova',2 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='562');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 7,8,'CHILE','AUSTR�LIA',782,0,0,'13/06/2014', 'Sex 13/06/2014 - 19:00 Arena Pantanal',2 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='782');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 5,7,'ESPANHA','CHILE',572,0,0,'18/06/2014', 'Qua 18/06/2014 - 16:00 Maracan�',2 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='572');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 6,8,'HOLANDA','AUSTR�LIA',682,0,0,'18/06/2014', 'Qua 18/06/2014 - 13:00 Beira Rio',2 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='682');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 5,8,'ESPANHA','AUSTR�LIA',582,0,0,'23/06/2014', 'Seg 23/06/2014 - 13:00 Arena da Baixada',2 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='582');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 6,7,'HOLANDA','CHILE',672,0,0,'23/06/2014', 'Seg 23/06/2014 - 13:00 Arena Corinthians',2 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='672');");
				//RODADAS GRUPO C
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 9,10,'COL�MBIA','GR�SSIA',9103,0,0,'14/06/2014', 'Sab 14/06/2014 - 13:00 Mineir�o',3 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='9103');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 11,12,'COSTA DO MARFIM','JAP�O',11123,0,0,'14/06/2014', 'Sab 14/06/2014 - 22:00 Arena Pernambuco',3 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='11123');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 9,11,'COL�MBIA','COSTA DO MARFIM',9113,0,0,'19/06/2014', 'Qui 19/06/2014 - 13:00 Man� Garrincha',3 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='9113');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 10,12,'GR�SSIA','JAP�O',10123,0,0,'19/06/2014', 'Qui 19/06/2014 - 19:00 Arena das Dunas',3 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='10123');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 9,12,'COL�MBIA','JAP�O',9123,0,0,'24/06/2014', 'Ter 24/06/2014 - 17:00 Arena Pantanal',3 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='9123');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 10,11,'GR�SSIA','COSTA DO MARFIM',10113,0,0,'24/06/2014', 'Ter 24/06/2014 - 17:00 Castel�o (CE)',3 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='10113');");
				//RODADAS GRUPO D
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 13,14,'URUGUAI','COSTA RICA',13144,0,0,'14/06/2014', 'Sab 14/06/2014 - 16:00 Castel�o (CE)',4 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='13144');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 15,16,'INGLATERRA','IT�LIA',15164,0,0,'14/06/2014', 'Sab 14/06/2014 - 19:00 Arena da Amaz�nia',4 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='15164');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 13,15,'URUGUAI','INGLATERRA',13154,0,0,'19/06/2014', 'Qui 19/06/2014 - 16:00 Arena Corinthians',4 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='13154');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 14,16,'COSTA RICA','IT�LIA',14164,0,0,'20/06/2014', 'Sex 20/06/2014 - 13:00 Arena Pernambuco',4 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='14164');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 13,16,'URUGUAI','IT�LIA',13164,0,0,'24/06/2014', 'Ter 24/06/2014 - 13:00 Arena das Dunas',4 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='13164');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 14,15,'COSTA RICA','INGLATERRA',14154,0,0,'24/06/2014', 'Ter 24/06/2014 - 13:00 Mineir�o',4 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='14154');");
				//RODADAS GRUPO E
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 17,18,'SUI�A','EQUADOR',17185,0,0,'15/06/2014', 'Dom 15/06/2014 - 13:00 Man� Garrincha',5 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='17185');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 19,20,'FRAN�A','HONDURAS',19205,0,0,'15/06/2014', 'Dom 15/06/2014 - 16:00 Beira Rio',5 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='19205');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 17,19,'SUI�A','FRAN�A',17195,0,0,'20/06/2014', 'Sex 20/06/2014 - 16:00 Fonte Nova',5 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='17195');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 18,20,'EQUADOR','HONDURAS',18205,0,0,'20/06/2014', 'Sex 20/06/2014 - 19:00 Arena da Baixada',5 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='18205');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 17,20,'SUI�A','HONDURAS',17205,0,0,'25/06/2014', 'Qua 25/06/2014 - 17:00 Arena da Amaz�nia',5 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='17205');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 18,19,'EQUADOR','FRAN�A',18195,0,0,'25/06/2014', 'Qua 25/06/2014 - 17:00 Maracan�',5 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='18195');");
				//RODADAS GRUPO F
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 21,22,'ARGENTINA','B�SNIA',21226,0,0,'15/06/2014', 'Dom 15/06/2014 - 19:00 Maracan�',6 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='21226');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 23,24,'IR�','NIGERIA',23246,0,0,'16/06/2014', 'Seg 16/06/2014 - 16:00 Arena da Baixada',6 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='23246');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 21,23,'ARGENTINA','IR�',21236,0,0,'21/06/2014', 'Sab 21/06/2014 - 13:00 Mineir�o',6 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='21236');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 22,24,'B�SNIA','NIGERIA',22246,0,0,'21/06/2014', 'Sab 21/06/2014 - 19:00 Arena Pantanal',6 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='22246');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 21,24,'ARGENTINA','NIGERIA',21246,0,0,'25/06/2014', 'Qua 25/06/2014 - 13:00 Beira Rio',6 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='21246');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 22,23,'B�SNIA','IR�',22236,0,0,'25/06/2014', 'Qua 25/06/2014 - 13:00 Fonte Nova',6 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='22236');");
				//RODADAS GRUPO G
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 25,26,'ALEMANHA','PORTUGAL',25267,0,0,'16/06/2014', 'Seg 16/06/2014 - 13:00 Fonte Nova',7 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='25267');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 27,28,'GANA','EUA',27287,0,0,'16/06/2014', 'Seg 16/06/2014 - 19:00 Arena das Dunas',7 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='27287');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 25,27,'ALEMANHA','GANA',25277,0,0,'21/06/2014', 'Sab 21/06/2014 - 16:00 Castel�o (CE)',7 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='25277');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 26,28,'PORTUGAL','EUA',26287,0,0,'22/06/2014', 'Dom 22/06/2014 - 19:00 Arena da Amaz�nia',7 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='26287');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 25,28,'ALEMANHA','EUA',25287,0,0,'26/06/2014', 'Qui 26/06/2014 - 13:00 Arena Pernambuco',7 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='25287');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 26,27,'PORTUGAL','GANA',26277,0,0,'26/06/2014', 'Qui 26/06/2014 - 13:00 Man� Garrincha',7 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='26277');");
				//RODADAS GRUPO H
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 29,30,'B�LGICA','ARG�LIA',29308,0,0,'17/06/2014', 'Ter 17/06/2014 - 13:00 Mineir�o',8 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='29308');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 31,32,'RUSSIA','COREIA DO SUL',31328,0,0,'17/06/2014', 'Ter 17/06/2014 - 19:00 Arena Pantanal',8 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='31328');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 29,31,'B�LGICA','RUSSIA',29318,0,0,'22/06/2014', 'Dom 22/06/2014 - 13:00 Maracan�',8 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='29318');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 30,32,'ARG�LIA','COREIA DO SUL',30328,0,0,'22/06/2014', 'Dom 22/06/2014 - 16:00 Beira Rio',8 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='30328');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 29,32,'B�LGICA','COREIA DO SUL',29328,0,0,'26/06/2014', 'Qui 26/06/2014 - 17:00 Arena Corinthians',8 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='29328');");
				Utils.db.execSQL("Insert into RESULTADO (jgIdSelecao1, jgIdSelecao2, jgSelecao1, jgSelecao2, jgReferencia, jgResultado1,"+
						"jgResultado2, jgData, jgLocal, GrupoId) SELECT 30,31,'ARG�LIA','RUSSIA',30318,0,0,'26/06/2014', 'Qui 26/06/2014 - 17:00 Arena da Baixada',8 WHERE NOT EXISTS(SELECT 1 FROM RESULTADO WHERE jgReferencia ='30318');");
				
				//Inserindo ID Flags
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.brasil+" where idSelecao = 1;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.croacia+" where idSelecao = 2;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.mexico+" where idSelecao = 3;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.camaroes+" where idSelecao = 4;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.espanha+" where idSelecao = 5;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.holanda+" where idSelecao = 6;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.chile+" where idSelecao = 7;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.australia+" where idSelecao = 8;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.colombia+" where idSelecao = 9;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.grecia+" where idSelecao = 10;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.costamarfim+" where idSelecao = 11;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.japao+" where idSelecao = 12;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.uruguai+" where idSelecao = 13;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.costarica+" where idSelecao = 14;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.inglaterra+" where idSelecao = 15;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.italia+" where idSelecao = 16;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.suica+" where idSelecao = 17;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.equador+" where idSelecao = 18;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.franca+" where idSelecao = 19;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.honduras+" where idSelecao = 20;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.argentina+" where idSelecao = 21;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.bosnia+" where idSelecao = 22;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.ira+" where idSelecao = 23;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.nigeria+" where idSelecao = 24;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.alemanha+" where idSelecao = 25;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.portugal+" where idSelecao = 26;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.gana+" where idSelecao = 27;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.estadosunidos+" where idSelecao = 28;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.belgica+" where idSelecao = 29;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.argelia+" where idSelecao = 30;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.russia+" where idSelecao = 31;");
				Utils.db.execSQL("Update Selecao set selFlagID = "+R.drawable.korea+" where idSelecao = 32;");
				
				
				//Seta referncias para oitavas de final
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 1,1,1,0,1,'Sab 28/06/2014 - 13:00 Mineir�o' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 1);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 2,2,2,0,1,'Sab 28/06/2014 - 13:00 Mineir�o' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 2);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 3,1,3,0,2,'Sab 28/06/2014 - 17:00 Maracan�' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 3);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 4,2,4,0,2,'Sab 28/06/2014 - 17:00 Maracan�' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 4);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 5,1,5,0,3,'Seg 30/06/2014 - 13:00 Man� Garrincha' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 5);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 6,2,6,0,3,'Seg 30/06/2014 - 13:00 Man� Garrincha' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 6);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 7,1,7,0,4,'Seg 30/06/2014 - 17:00 Beira Rio' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 7);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 8,2,8,0,4,'Seg 30/06/2014 - 17:00 Beira Rio' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 8);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 9,1,2,0,5,'Dom 29/06/2014 - 13:00 Castel�o (CE)' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 9);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 10,2,1,0,5,'Dom 29/06/2014 - 13:00 Castel�o (CE)' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 10);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 11,1,4,0,6,'Dom 29/06/2014 - 17:00 Arena Pernambuco'WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 11);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 12,2,3,0,6,'Dom 29/06/2014 - 17:00 Arena Pernambuco' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 12);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 13,1,6,0,7,'Ter 01/07/2014 - 13:00 Arena Corinthians' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 13);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 14,2,5,0,7,'Ter 01/07/2014 - 13:00 Arena Corinthians' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 14);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 15,1,8,0,8,'Ter 01/07/2014 - 17:00 Fonte Nova' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 15);");
				Utils.db.execSQL("Insert into Oitavas(idOitavas, oitPosicao, oitIdGrupo, oitResultado, oitReferencia, oitLocal) SELECT 16,2,7,0,8,'Ter 01/07/2014 - 17:00 Fonte Nova' WHERE NOT EXISTS(SELECT 1 FROM Oitavas WHERE idOitavas = 16);");
				
				//seta quartas de final
				Utils.db.execSQL("Insert into QUARTAS(idQuartas, quaReferencia, quaLocal) SELECT 1,1,'Sex 04/07/2014 - 17:00 Castel�o (CE)' WHERE NOT EXISTS(SELECT 1 FROM QUARTAS WHERE idQuartas = 1);");
				Utils.db.execSQL("Insert into QUARTAS(idQuartas, quaReferencia, quaLocal) SELECT 2,2,'Sex 04/07/2014 - 17:00 Castel�o (CE)' WHERE NOT EXISTS(SELECT 1 FROM QUARTAS WHERE idQuartas = 2);");
				Utils.db.execSQL("Insert into QUARTAS(idQuartas, quaReferencia, quaLocal) SELECT 3,3,'Sex 04/07/2014 - 13:00 Maracan�' WHERE NOT EXISTS(SELECT 1 FROM QUARTAS WHERE idQuartas = 3);");
				Utils.db.execSQL("Insert into QUARTAS(idQuartas, quaReferencia, quaLocal) SELECT 4,4,'Sex 04/07/2014 - 13:00 Maracan�' WHERE NOT EXISTS(SELECT 1 FROM QUARTAS WHERE idQuartas = 4);");
				Utils.db.execSQL("Insert into QUARTAS(idQuartas, quaReferencia, quaLocal) SELECT 5,5,'Sab 05/07/2014 - 17:00 Fonte Nova' WHERE NOT EXISTS(SELECT 1 FROM QUARTAS WHERE idQuartas = 5);");
				Utils.db.execSQL("Insert into QUARTAS(idQuartas, quaReferencia, quaLocal) SELECT 6,6,'Sab 05/07/2014 - 17:00 Fonte Nova' WHERE NOT EXISTS(SELECT 1 FROM QUARTAS WHERE idQuartas = 6);");
				Utils.db.execSQL("Insert into QUARTAS(idQuartas, quaReferencia, quaLocal) SELECT 7,7,'Sab 05/07/2014 - 13:00 Man� Garrincha' WHERE NOT EXISTS(SELECT 1 FROM QUARTAS WHERE idQuartas = 7);");
				Utils.db.execSQL("Insert into QUARTAS(idQuartas, quaReferencia, quaLocal) SELECT 8,8,'Sab 05/07/2014 - 13:00 Man� Garrincha' WHERE NOT EXISTS(SELECT 1 FROM QUARTAS WHERE idQuartas = 8);");
				
				//seta semifinal
				Utils.db.execSQL("Insert into SEMIFINAL(idSemi, semiReferencia, semiLocal) SELECT 1,12,'Ter 08/07/2014 - 17:00 Mineir�o' WHERE NOT EXISTS(SELECT 1 FROM SEMIFINAL WHERE idSemi= 1);");
				Utils.db.execSQL("Insert into SEMIFINAL(idSemi, semiReferencia, semiLocal) SELECT 2,34,'Ter 08/07/2014 - 17:00 Mineir�o' WHERE NOT EXISTS(SELECT 1 FROM SEMIFINAL WHERE idSemi= 2);");
				Utils.db.execSQL("Insert into SEMIFINAL(idSemi, semiReferencia, semiLocal) SELECT 3,56,'Qua 09/07/2014 - 17:00 Arena Corinthians' WHERE NOT EXISTS(SELECT 1 FROM SEMIFINAL WHERE idSemi= 3);");
				Utils.db.execSQL("Insert into SEMIFINAL(idSemi, semiReferencia, semiLocal) SELECT 4,78,'Qua 09/07/2014 - 17:00 Arena Corinthians' WHERE NOT EXISTS(SELECT 1 FROM SEMIFINAL WHERE idSemi= 4);");
				
				//seta final
				Utils.db.execSQL("Insert into FINAL(idFinal, finPosicao, finLocal) SELECT 1,0,'Dom 13/07/2014 - 16:00 Maracan�' WHERE NOT EXISTS(SELECT 1 FROM FINAL WHERE idFinal = 1);");
				Utils.db.execSQL("Insert into FINAL(idFinal, finPosicao, finLocal) SELECT 2,1,'Dom 13/07/2014 - 16:00 Maracan�' WHERE NOT EXISTS(SELECT 1 FROM FINAL WHERE idFinal = 2);");
				
				
				//seta Estadios
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'BELO HORIZONTE-MG', 'Av. Ant�nio Abrah�o Caram - 1001 - S�o Luis - 31275000',"
						+ " 'Est�dio Gov. Magalh�es Pinto' , 'MINEIR�O', '62.547', '2.670', "+R.drawable.mineirao+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 1);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'BRAS�LIA-DF', 'Complexo Poliesportivo Ayrton Senna - Asa Norte - 70077-000',"
						+ " 'Est�dio Nacional Man� Garrincha' , 'MAN� GARRINCHA', '70.064', '8.557', "+R.drawable.estadiomanegarrincha+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 2);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'CUIAB�-MT', 'Av. Agr�cola Paes de Barros - s/n�, Verd�o - 78030-210',"
						+ " 'Est�dio Jos� Fragelli' , 'ARENA PANTANAL', '42.968', '2.831', "+R.drawable.arenapantanal+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 3);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'CURITIBA-PR', 'Rua Buenos Aires - s/n� - �gua Verde - 80250-070',"
						+ " 'Est�dio Joaquim Am�rico Guimar�es' , 'ARENA DA BAIXADA', '41.456', '1.908', "+R.drawable.arenabaixada+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 4);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'FORTALEZA-CE', 'Av. Alberto Craveiro - 2901 - Castel�o - 60861-211',"
						+ " 'Est�dio Governador Pl�cido Castelo' , 'CASTEL�O', '64.846', '1.900', "+R.drawable.castelao+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 5);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'MANAUS-AM', 'Av. Constantino Nery - s/n� - Flores - 69058-795',"
						+ " 'Arena Amaz�nia' , 'ARENA AMAZ�NIA', '62.374', '400', "+R.drawable.arenaamazonia+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 6);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'NATAL-RN', 'Avenida Presidente Prudente de Morais - 5121 - Lagoa Nova - 59056-200',"
						+ " 'Arena Das Dunas' , 'ARENA DA DUNAS', '42.086', '2.617', "+R.drawable.arenadunas+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 7);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'PORTO ALEGRE-RS', 'Av. Padre Cacique - 891 - Praia de Belas - 90810-240',"
						+ " 'Est�dio Jos� Pinheiro Borda' , 'BEIRA-RIO', '48.849', '7.000', "+R.drawable.beirario+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 8);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'RECIFE-PE', 'Rua Deus � Fiel, 01 - Jardim Tenedo - em S�o Louren�o da Mata - 54710-010',"
						+ " 'Arena Pernambuco' , 'ARENA PERNAMBUCO', '44.248', '4.700', "+R.drawable.arenapernambuco+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 9);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'RIO DE JANEIRO-RJ', 'Rua Professor Eurico Rabelo - Maracan� - 20271-150',"
						+ " 'Est�dio M�rio Filho' , 'MARACAN�', '78.639', '328', "+R.drawable.maracana+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 10);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'SALVADOR-BA', 'Av. Pres. Costa e Silva - Nazare - 40050-360',"
						+ " 'Est�dio Ot�vio Mangabeira' , 'FONTE NOVA', '48.747', '1.978', "+R.drawable.arenafontenova+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 11);");
				
				Utils.db.execSQL("Insert into ESTADIO(estCidade, estEndereco, estNome, estApelido, estCapacidade, estVagas, estImagem)"
						+ " SELECT 'SAO PAULO-SP', 'Rua Doutor Lu�s Aires - s/n - Itaquera - Itaquera - 08295-005',"
						+ " 'ARENA CORINTHIANS' , 'ITAQUERAO', '69.160', '3.500', "+R.drawable.arenacorinthians+" WHERE NOT EXISTS(SELECT 1 FROM ESTADIO WHERE idEstadio = 12);");
				
				Utils.db.execSQL("Update Parametros set parVersaoBD = '1.1' WHERE idParametro = 1;");
				
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		
		private boolean populateParametros(){
			
			try {
				//seta parametros
				Utils.db.execSQL("Insert into Parametros(idParametro, parURL, parVersao, parVersaoBD, parOitavas, parQuartas, parSemi, parClassif) "
								+ "SELECT 1,'www', '1.0','1.0','F','F','F','F' WHERE NOT EXISTS(SELECT 1 FROM Parametros WHERE idParametro = 1);");
				return true;
			} catch (Exception e) {
				return false;
			}			
		}
}
