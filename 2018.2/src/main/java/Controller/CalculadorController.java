package Controller;
/**
 * 
 * @author Cibele
 * 
 * Classe que tem como objetivo realizar os c�lculos de pontua��o dos curr�culos dos candidatos
 *
 */

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Model.ArquivoModel;
import Model.CandidatoModel;

public class CalculadorController {
	
	static final Integer anoAtual = 2018;													// Ano base para verifica��o de validade de pr�mio (somente se considera pr�mios ganhos h� menos de 10 anos)
	
	static final String nomeElemento = "CURRICULO-VITAE", nomeNo = "DADOS-GERAIS" ,
						premiosElemento = "PREMIOS-TITULOS", premiosNo = "PREMIO-TITULO", premiosAtributoAno = "ANO-DA-PREMIACAO", premiosAtributoNome = "NOME-DO-PREMIO-OU-TITULO" ,
					    artigosElemento = "ARTIGO-PUBLICADO", artigosNo = "DADOS-BASICOS-DO-ARTIGO", artigosAtributoAno = "ANO-DO-ARTIGO", artigosAtributoNome = "TITULO-DO-ARTIGO" ,
					    eventosElemento = "PARTICIPACAO-EM-EVENTOS-CONGRESSOS", eventosAtributoAno = "ANO", eventosAtributoNome = "NOME-DO-EVENTO" ,
					    eventosSimposioNo1 = "DADOS-BASICOS-DA-PARTICIPACAO-EM-SIMPOSIO", eventosSimposioNo2 = "DETALHAMENTO-DA-PARTICIPACAO-EM-SIMPOSIO" ,
					    eventosCongressosNo1 = "DADOS-BASICOS-DA-PARTICIPACAO-EM-CONGRESSO", eventosCongressosNo2 = "DETALHAMENTO-DA-PARTICIPACAO-EM-CONGRESSO" ,
					    eventosSeminariosNo1 = "DADOS-BASICOS-DA-PARTICIPACAO-EM-SEMINARIO", eventosSeminariosNo2 = "DETALHAMENTO-DA-PARTICIPACAO-EM-SEMINARIO" ,
						vinculoPesquisaElemento = "ATIVIDADES-DE-PARTICIPACAO-EM-PROJETO", vinculoPesquisaNo = "PROJETO-DE-PESQUISA", vinculoPesquisaAtributoDescricao = "DESCRICAO-DO-PROJETO", vinculoPesquisaAtributoNome = "NOME-DO-PROJETO", vinculoPesquisaAtributoAno = "ANO-INICIO" , 
						vinculoFormacaoElemento = "FORMACAO-ACADEMICA-TITULACAO", vinculoFormacaoNo = "MESTRADO", vinculoFormacaoAtributoInstituicao = "NOME-INSTITUICAO", vinculoFormacaoAtributoConclusao = "ANO-DE-CONCLUSAO", vinculoFormacaoAtributoInicio = "ANO-DE-INICIO" ;
	
	
	private ArquivoModel txtSaida = null;
	private ArquivoModel txtLog = null;
	
	private ArrayList<CandidatoModel> listaDeCandidatos = null;
		
	
    /** M�todo construtor da classe CalculadorController. Recebe um arquivo de sa�da .txt, um arquivo de log .txt, uma lista de candidatos, e as vari�vel booleana modoverboso que indica se o usu�rio deseja sa�da textual completa. Tamb�m chamado o m�todo da pr�pria classe CalculatorController para o preenchimento dos nomes dos candidatos.

     *   @return void */  
	public CalculadorController(ArquivoModel txtSaida, ArquivoModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos) throws IOException {
		
		this.txtSaida = txtSaida;
		this.txtLog = txtLog;
		this.listaDeCandidatos = listaDeCandidatos;
		
		preencheNomesCandidatos(listaDeCandidatos);
			
	}
	
	
	
	
    /** M�todo chamado no construtor da classe que preenche os nomes dos candidatos

     *   @return void */  
	public void preencheNomesCandidatos(ArrayList<CandidatoModel> listaDeCandidatos) {
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName(nomeElemento);	
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if( rootElement == null) {
				listaDeCandidatos.get(auxCandidatos).setNomeCandidato("Sem nome");	
				break;
			}
			
			NodeList notesList = rootElement.getElementsByTagName(nomeNo);
		
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
				
				listaDeCandidatos.get(auxCandidatos).setNomeCandidato(NoteElement.getAttribute("NOME-COMPLETO"));
		
			}
			
		}
		
	}
	
	
	
	
    /** M�todo que chama todos os m�todos de sa�da do programa

     *   @return void */  
	public void calculaModoCompleto(boolean modoVerboso) throws IOException {
		
		calculaModoPremios(modoVerboso);
		calculaModoArtigos(modoVerboso);
		calculaModoEventos(modoVerboso);
		calculaModoVinculoUnirio(modoVerboso);
		
	}
	
	
	
	
    /** M�todo que calcula a pontua��o por semestres sem reprova��o dos candidatos

     *   @return ArrayList<CandidatoModel> - listaDeCandidatos */  
	public ArrayList<CandidatoModel> calculaPontuacaoSemestres() throws IOException {		
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) { 
			listaDeCandidatos.get(auxCandidatos).setPontuacaoSemestresSemReprovacao(listaDeCandidatos.get(auxCandidatos).getSemestresSemReprovacao());		
			listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(listaDeCandidatos.get(auxCandidatos).getPontuacaoSemestresSemReprovacao());
		}
		
		return listaDeCandidatos;
	
	}
	
	
	
	
    /** M�todo que calcula a pontua��o por pr�mios obtidos dos candidatos

     *   @return void */ 
	public void calculaModoPremios(boolean modoVerboso) throws IOException {
				
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName(premiosElemento);
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			NodeList notesList = rootElement.getElementsByTagName(premiosNo);
			
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
				
				if(Integer.parseInt(NoteElement.getAttribute(premiosAtributoAno)) >= anoAtual - 10 )		// Verifica se o pr�mio foi ganho h� menos de 10 anos. Caso sim, prossegue para incremento de pontua��o e escrita no arquivo (modo verboso ativado)
					if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de sa�da o nome e ano da premia��o
						
						txtSaida.escreveArquivo("Pr�mio referente ao curr�culo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
						txtSaida.escreveArquivo("Nome do pr�mio: " +NoteElement.getAttribute(premiosAtributoNome));  // Escreve no arquivo o nome do pr�mio obtido 
						txtSaida.escreveArquivo("Ano do pr�mio: " +NoteElement.getAttribute(premiosAtributoAno));  // Escreve no arquivo o ano do pr�mio obtido 
						txtSaida.escreveArquivo("\n\n\n");
						
						listaDeCandidatos.get(auxCandidatos).setPontuacaoPremios(listaDeCandidatos.get(auxCandidatos).getPontuacaoPremios() + 1);		// Incrementa a pontua��o do candidato
					}
            
					else {		 //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
						listaDeCandidatos.get(auxCandidatos).setPontuacaoPremios(listaDeCandidatos.get(auxCandidatos).getPontuacaoPremios() + 1);
						
					}
				
			}
			
			listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(listaDeCandidatos.get(auxCandidatos).getPontuacaoPremios());
		
		}
		
	}
	
	
	
	
    /** M�todo que calcula a pontua��o por artigos escritos dos candidatos

     *   @return void */ 
	public void calculaModoArtigos(boolean modoVerboso) throws IOException {	
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName(artigosElemento);	
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			NodeList notesList = rootElement.getElementsByTagName(artigosNo);
		
			
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
				
				
				
				if(Integer.parseInt(NoteElement.getAttribute(artigosAtributoAno)) >= anoAtual - 10 )		// Verifica se o pr�mio foi ganho h� menos de 10 anos. Caso sim, prossegue para incremento de pontua��o e escrita no arquivo (modo verboso ativado)
					if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de sa�da o nome e ano do artigo
						
						txtSaida.escreveArquivo("Artigo referente ao curr�culo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
						txtSaida.escreveArquivo("Nome do artigo: " +NoteElement.getAttribute(artigosAtributoNome));  // Escreve no arquivo o nome do artigo escrito
						txtSaida.escreveArquivo("Ano do artigo: " +NoteElement.getAttribute(artigosAtributoAno));  // Escreve no arquivo o ano do artigo escrito 
						txtSaida.escreveArquivo("\n\n\n");
						
						listaDeCandidatos.get(auxCandidatos).setPontuacaoArtigos(listaDeCandidatos.get(auxCandidatos).getPontuacaoArtigos() + 1);		// Incrementa a pontua��o do candidato
					}
            
					else {		 //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
						listaDeCandidatos.get(auxCandidatos).setPontuacaoArtigos(listaDeCandidatos.get(auxCandidatos).getPontuacaoArtigos() + 1);
						
					}
				
			}
			
			listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(listaDeCandidatos.get(auxCandidatos).getPontuacaoArtigos());
		
		}
		
	}
	
	
	
	
    /** M�todo que calcula a pontua��o por eventos participados dos candidatos

     *   @return void */ 
	public void calculaModoEventos(boolean modoVerboso) throws IOException {
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName(eventosElemento);	
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			Element rootElement2 = (Element) rootNode;
			NodeList notesList = rootElement.getElementsByTagName(eventosSimposioNo1);
			NodeList notesList2 = rootElement2.getElementsByTagName(eventosSimposioNo2);
			
			
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
				
				Node theNote2 = notesList2.item(i);
				Element NoteElement2 = (Element) theNote2;
				
				
				
				if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de sa�da o nome e ano do artigo
						
					txtSaida.escreveArquivo("Evento referente ao curr�culo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
					txtSaida.escreveArquivo("Nome do simp�sio: " +NoteElement2.getAttribute(eventosAtributoNome));  		// Escreve no arquivo o nome do evento
					txtSaida.escreveArquivo("Ano do simp�sio: " +NoteElement.getAttribute(eventosAtributoAno));  					// Escreve no arquivo o ano do evento

					txtSaida.escreveArquivo("\n\n\n");
						
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);		// Incrementa a pontua��o do candidato
				}
            
				else {		   //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);
						
				}
				
			}
			
		}
			
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
				
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName(eventosElemento);
			
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			Element rootElement2 = (Element) rootNode;
			NodeList notesList = rootElement.getElementsByTagName(eventosCongressosNo1);
			NodeList notesList2 = rootElement2.getElementsByTagName(eventosCongressosNo2);
				
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
					
				Node theNote2 = notesList2.item(i);
				Element NoteElement2 = (Element) theNote2;
					
					
				if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de sa�da o nome e ano do artigo
							
					txtSaida.escreveArquivo("Evento referente ao curr�culo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
					txtSaida.escreveArquivo("Nome do congresso: " +NoteElement2.getAttribute(eventosAtributoNome));  // Escreve no arquivo o nome do evento
					txtSaida.escreveArquivo("Ano do congresso: " +NoteElement.getAttribute(eventosAtributoAno));  // Escreve no arquivo o ano do evento

					txtSaida.escreveArquivo("\n\n\n");
							
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);		// Incrementa a pontua��o do candidato
				}
	            
				else {		 //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);
							
				}
					
			}
			
		}
		
		
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName(eventosElemento);		
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			Element rootElement2 = (Element) rootNode;
			NodeList notesList = rootElement.getElementsByTagName(eventosSeminariosNo1);
			NodeList notesList2 = rootElement2.getElementsByTagName(eventosSeminariosNo2);
				
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
					
				Node theNote2 = notesList2.item(i);
				Element NoteElement2 = (Element) theNote2;
					
					
				if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de sa�da o nome e ano do artigo
							
					txtSaida.escreveArquivo("Evento referente ao curr�culo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
					txtSaida.escreveArquivo("Nome do semin�rio: " +NoteElement2.getAttribute(eventosAtributoNome));  // Escreve no arquivo o nome do evento
					txtSaida.escreveArquivo("Ano do semin�rio: " +NoteElement.getAttribute(eventosAtributoAno));  // Escreve no arquivo o ano do evento

					txtSaida.escreveArquivo("\n\n\n");
							
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);		// Incrementa a pontua��o do candidato
				}
	            
				else {		 //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);
							
				}
					
			}
					
		}
		
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			if(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() > 5)
				listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(5);
			
			else
				listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos());
		}
		
	}
		

	
    /** M�todo que calcula a pontua��o por v�nculos com a UNIRIO (Forma��o Acad�mica e Projetos de Pesquisa)

     *   @return void */ 
	public void calculaModoVinculoUnirio(boolean modoVerboso) throws IOException {
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName(vinculoPesquisaElemento);	
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			NodeList notesList = rootElement.getElementsByTagName(vinculoPesquisaNo);
			
			
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;			
				
				String descricaoVinculo;
				descricaoVinculo = NoteElement.getAttribute(vinculoPesquisaAtributoDescricao);
				
				if((descricaoVinculo.contains("unirio")) || (descricaoVinculo.contains("Unirio")) || (descricaoVinculo.contains("UNIRIO"))  || (descricaoVinculo.contains("UniRio"))) {
					if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de sa�da o nome e ano do artigo
						
						txtSaida.escreveArquivo("V�nculo com a UNIRIO referente ao curr�culo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
						txtSaida.escreveArquivo("Nome do Projeto de Pesquisa: " +NoteElement.getAttribute(vinculoPesquisaAtributoNome));  // Escreve no arquivo o nome do evento
						txtSaida.escreveArquivo("Ano do v�nculo: " +NoteElement.getAttribute(vinculoPesquisaAtributoAno));  // Escreve no arquivo o ano do evento

						txtSaida.escreveArquivo("\n\n\n");
						
						listaDeCandidatos.get(auxCandidatos).setPontuacaoVinculoUnirio(listaDeCandidatos.get(auxCandidatos).getPontuacaoVinculoUnirio() + 1);		// Incrementa a pontua��o do candidato
					}
            
					else 		 //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
						listaDeCandidatos.get(auxCandidatos).setPontuacaoVinculoUnirio(listaDeCandidatos.get(auxCandidatos).getPontuacaoVinculoUnirio() + 1);
						
					}				
			
			}
		
		}
		
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName(vinculoFormacaoElemento);	
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			NodeList notesList = rootElement.getElementsByTagName(vinculoFormacaoNo);
			
			
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;			
				
				String nomeInstituicao;
				nomeInstituicao = NoteElement.getAttribute(vinculoFormacaoAtributoInstituicao );
				
				if((nomeInstituicao.contains("unirio")) || (nomeInstituicao.contains("Unirio")) || (nomeInstituicao.contains("UNIRIO"))  || (nomeInstituicao.contains("UniRio") || (nomeInstituicao.contains("Universidade Federal do Estado do Rio de Janeiro")))) {
					if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de sa�da o nome e ano do artigo
						
						txtSaida.escreveArquivo("Candidato " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato() + " � Mestre/Mestrando pela UNIRIO");
						
						if(NoteElement.getAttribute(vinculoFormacaoAtributoConclusao) == "") {
							txtSaida.escreveArquivo("Ano de in�cio: " + NoteElement.getAttribute(vinculoFormacaoAtributoInicio) + ".Mestrado em andamento." );  // Escreve no arquivo o ano do evento
							txtSaida.escreveArquivo("\n\n\n");
						}
						
						else {
							txtSaida.escreveArquivo("Ano de conclus�o: " +NoteElement.getAttribute(vinculoFormacaoAtributoConclusao));  // Escreve no arquivo o ano do evento
							txtSaida.escreveArquivo("\n\n\n");
						}
						
						listaDeCandidatos.get(auxCandidatos).setPontuacaoVinculoUnirio(listaDeCandidatos.get(auxCandidatos).getPontuacaoVinculoUnirio() + 1);
					
					}
            
					else 		 //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
						listaDeCandidatos.get(auxCandidatos).setPontuacaoVinculoUnirio(listaDeCandidatos.get(auxCandidatos).getPontuacaoVinculoUnirio() + 1);
						
				}
				
			}			
			
		}
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			if(listaDeCandidatos.get(auxCandidatos).getPontuacaoVinculoUnirio() > 2)
				listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(2);
			
			else
				listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(listaDeCandidatos.get(auxCandidatos).getPontuacaoVinculoUnirio());
		
		}
		
	}
	
	
	
	
    /** M�todo que calcula o ranking final dos candidatos

     *   @return ArrayList<CandidatoModel> - listaDeCandidatos */ 
	public ArrayList<CandidatoModel> calculaRankingFinal() throws IOException {
		
		for (int i = 0; i < listaDeCandidatos.size() -1 ; i++){ 			  // Algoritmo Bubble Sort para ordenar a lista que guarda a pontua��o dos candidatos
			for (int j = 0; j < listaDeCandidatos.size() -i -1; j++) 
			    if (listaDeCandidatos.get(j).getPontuacaoTotal() < listaDeCandidatos.get(j + 1).getPontuacaoTotal()) {                 
			        CandidatoModel temp = listaDeCandidatos.get(j); 
			        listaDeCandidatos.set(j, listaDeCandidatos.get(j + 1));
			        listaDeCandidatos.set(j + 1, temp); 
			    } 
		
		}
		
		return listaDeCandidatos;			// Retorna a lista dos candidatos ordenada por pontua��o
		
	}
	
	
	
}