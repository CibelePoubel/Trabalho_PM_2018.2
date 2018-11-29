package Controller;
/**
 * 
 * @author Cibele
 * 
 * Classe que tem como objetivo realizar os cálculos de pontuação dos currículos dos candidatos
 *
 */

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Model.CandidatoModel;
import Model.LogModel;
import Model.SaidaModel;

public class CalculadorController {
	
	private SaidaModel txtSaida = null;
	private LogModel txtLog = null;
	
	
	private ArrayList<CandidatoModel> listaDeCandidatos = null;
		
	
	
	
	public CalculadorController(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos, boolean modoCompleto, boolean modoVerboso, boolean modoPremios, boolean modoArtigos, boolean modoEventos, boolean modoVinculoUnirio) throws IOException {
		
		this.txtSaida = txtSaida;
		this.txtLog = txtLog;
		this.listaDeCandidatos = listaDeCandidatos;
		
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName("CURRICULO-VITAE");	
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if( rootElement == null) {
				listaDeCandidatos.get(auxCandidatos).setNomeCandidato("Sem nome");	
				break;
			}
			
			NodeList notesList = rootElement.getElementsByTagName("DADOS-GERAIS");
		
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
				
				listaDeCandidatos.get(auxCandidatos).setNomeCandidato(NoteElement.getAttribute("NOME-COMPLETO"));
		
			}
			
		}
		
	}
	
	
	
	
	public void calculaModoCompleto(boolean modoVerboso) throws IOException {
		calculaPontuacaoSemestres();
		calculaModoPremios(modoVerboso);
		calculaModoArtigos(modoVerboso);
		calculaModoEventos(modoVerboso);
		
	}
	
	
	
	
	public ArrayList<CandidatoModel> calculaPontuacaoSemestres() throws IOException {		// Método que calcula a quantidade de pontos por semestres sem reprovação
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) { 
			listaDeCandidatos.get(auxCandidatos).setPontuacaoSemestresSemReprovacao(listaDeCandidatos.get(auxCandidatos).getSemestresSemReprovacao());		
			listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(listaDeCandidatos.get(auxCandidatos).getPontuacaoSemestresSemReprovacao());
		}
		
		return listaDeCandidatos;
	
	}
	
	
	
	
	public void calculaModoPremios(boolean modoVerboso) throws IOException {
			
		int anoLimiteValidade = 2008;			// Ano base para verificação de validade de prêmio (somente se considera prêmios ganhos há menos de 10 anos)
		
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName("PREMIOS-TITULOS");
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			NodeList notesList = rootElement.getElementsByTagName("PREMIO-TITULO");
			
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
				
				if(Integer.parseInt(NoteElement.getAttribute("ANO-DA-PREMIACAO")) >= anoLimiteValidade )		// Verifica se o prêmio foi ganho há menos de 10 anos. Caso sim, prossegue para incremento de pontuação e escrita no arquivo (modo verboso ativado)
					if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de saída o nome e ano da premiação
						
						txtSaida.escreveArquivo("Prêmio referente ao currículo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
						txtSaida.escreveArquivo("Nome do prêmio: " +NoteElement.getAttribute("NOME-DO-PREMIO-OU-TITULO"));  // Escreve no arquivo o nome do prêmio obtido 
						txtSaida.escreveArquivo("Ano do prêmio: " +NoteElement.getAttribute("ANO-DA-PREMIACAO"));  // Escreve no arquivo o ano do prêmio obtido 
						txtSaida.escreveArquivo("\n\n\n");
						
						listaDeCandidatos.get(auxCandidatos).setPontuacaoPremios(listaDeCandidatos.get(auxCandidatos).getPontuacaoPremios() + 1);		// Incrementa a pontuação do candidato
					}
            
					else {		 //  Caso o modo verboso não esteja ativado, nada deve ser escrito no arquivo txt de saída, somente incrementada a pontuação
						listaDeCandidatos.get(auxCandidatos).setPontuacaoPremios(listaDeCandidatos.get(auxCandidatos).getPontuacaoPremios() + 1);
						
					}
				
			}
			
			listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(listaDeCandidatos.get(auxCandidatos).getPontuacaoPremios());
		
		}
		
	}
	
	
	
	
	public void calculaModoArtigos(boolean modoVerboso) throws IOException {
		
		int anoLimiteValidade = 2008;			// Ano base para verificação de validade de prêmio (somente se considera prêmios ganhos há menos de 10 anos)
		
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName("ARTIGO-PUBLICADO");	
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			NodeList notesList = rootElement.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO");
		
			
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
				
				
				
				if(Integer.parseInt(NoteElement.getAttribute("ANO-DO-ARTIGO")) >= anoLimiteValidade )		// Verifica se o prêmio foi ganho há menos de 10 anos. Caso sim, prossegue para incremento de pontuação e escrita no arquivo (modo verboso ativado)
					if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de saída o nome e ano do artigo
						
						txtSaida.escreveArquivo("Artigo referente ao currículo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
						txtSaida.escreveArquivo("Nome do artigo: " +NoteElement.getAttribute("TITULO-DO-ARTIGO"));  // Escreve no arquivo o nome do artigo escrito
						txtSaida.escreveArquivo("Ano do artigo: " +NoteElement.getAttribute("ANO-DO-ARTIGO"));  // Escreve no arquivo o ano do artigo escrito 
						txtSaida.escreveArquivo("\n\n\n");
						
						listaDeCandidatos.get(auxCandidatos).setPontuacaoArtigos(listaDeCandidatos.get(auxCandidatos).getPontuacaoArtigos() + 1);		// Incrementa a pontuação do candidato
					}
            
					else {		 //  Caso o modo verboso não esteja ativado, nada deve ser escrito no arquivo txt de saída, somente incrementada a pontuação
						listaDeCandidatos.get(auxCandidatos).setPontuacaoArtigos(listaDeCandidatos.get(auxCandidatos).getPontuacaoArtigos() + 1);
						
					}
				
			}
			
			listaDeCandidatos.get(auxCandidatos).setPontuacaoTotal(listaDeCandidatos.get(auxCandidatos).getPontuacaoArtigos());
		
		}
		
	}
	
	
	
	
	public void calculaModoEventos(boolean modoVerboso) throws IOException {
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName("PARTICIPACAO-EM-EVENTOS-CONGRESSOS");	
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			Element rootElement2 = (Element) rootNode;
			NodeList notesList = rootElement.getElementsByTagName("DADOS-BASICOS-DA-PARTICIPACAO-EM-SIMPOSIO");
			NodeList notesList2 = rootElement2.getElementsByTagName("DETALHAMENTO-DA-PARTICIPACAO-EM-SIMPOSIO");
			
			
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
				
				Node theNote2 = notesList2.item(i);
				Element NoteElement2 = (Element) theNote2;
				
				
				
				if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de saída o nome e ano do artigo
						
					txtSaida.escreveArquivo("Evento referente ao currículo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
					txtSaida.escreveArquivo("Nome do simpósio: " +NoteElement2.getAttribute("NOME-DO-EVENTO"));  // Escreve no arquivo o nome do evento
					txtSaida.escreveArquivo("Ano do simpósio: " +NoteElement.getAttribute("ANO"));  // Escreve no arquivo o ano do evento

					txtSaida.escreveArquivo("\n\n\n");
						
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);		// Incrementa a pontuação do candidato
				}
            
				else {		 //  Caso o modo verboso não esteja ativado, nada deve ser escrito no arquivo txt de saída, somente incrementada a pontuação
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);
						
				}
				
			}
			
		}
			
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
				
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName("PARTICIPACAO-EM-EVENTOS-CONGRESSOS");
			
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			Element rootElement2 = (Element) rootNode;
			NodeList notesList = rootElement.getElementsByTagName("DADOS-BASICOS-DA-PARTICIPACAO-EM-CONGRESSO");
			NodeList notesList2 = rootElement2.getElementsByTagName("DETALHAMENTO-DA-PARTICIPACAO-EM-CONGRESSO");
				
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
					
				Node theNote2 = notesList2.item(i);
				Element NoteElement2 = (Element) theNote2;
					
					
				if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de saída o nome e ano do artigo
							
					txtSaida.escreveArquivo("Evento referente ao currículo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
					txtSaida.escreveArquivo("Nome do congresso: " +NoteElement2.getAttribute("NOME-DO-EVENTO"));  // Escreve no arquivo o nome do evento
					txtSaida.escreveArquivo("Ano do congresso: " +NoteElement.getAttribute("ANO"));  // Escreve no arquivo o ano do evento

					txtSaida.escreveArquivo("\n\n\n");
							
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);		// Incrementa a pontuação do candidato
				}
	            
				else {		 //  Caso o modo verboso não esteja ativado, nada deve ser escrito no arquivo txt de saída, somente incrementada a pontuação
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);
							
				}
					
			}
			
		}
		
		
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) {
			
			NodeList rootNodes = listaDeCandidatos.get(auxCandidatos).getXML().getCurriculo().getElementsByTagName("PARTICIPACAO-EM-EVENTOS-CONGRESSOS");		
			Node rootNode = rootNodes.item(0);
			Element rootElement = (Element) rootNode;
			
			if(rootElement == null) {	
				break;
			}
			
			Element rootElement2 = (Element) rootNode;
			NodeList notesList = rootElement.getElementsByTagName("DADOS-BASICOS-DA-PARTICIPACAO-EM-SEMINARIO");
			NodeList notesList2 = rootElement2.getElementsByTagName("DETALHAMENTO-DA-PARTICIPACAO-EM-SEMINARIO");
				
			for(int i = 0; i < notesList.getLength(); i++) {
				Node theNote = notesList.item(i);
				Element NoteElement = (Element) theNote;
					
				Node theNote2 = notesList2.item(i);
				Element NoteElement2 = (Element) theNote2;
					
					
				if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de saída o nome e ano do artigo
							
					txtSaida.escreveArquivo("Evento referente ao currículo de " + listaDeCandidatos.get(auxCandidatos).getNomeCandidato());
					txtSaida.escreveArquivo("Nome do seminário: " +NoteElement2.getAttribute("NOME-DO-EVENTO"));  // Escreve no arquivo o nome do evento
					txtSaida.escreveArquivo("Ano do seminário: " +NoteElement.getAttribute("ANO"));  // Escreve no arquivo o ano do evento

					txtSaida.escreveArquivo("\n\n\n");
							
					listaDeCandidatos.get(auxCandidatos).setPontuacaoEventos(listaDeCandidatos.get(auxCandidatos).getPontuacaoEventos() + 1);		// Incrementa a pontuação do candidato
				}
	            
				else {		 //  Caso o modo verboso não esteja ativado, nada deve ser escrito no arquivo txt de saída, somente incrementada a pontuação
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
		

	
	
	public ArrayList<CandidatoModel> calculaRankingFinal() throws IOException {
		
		for (int i = 0; i < listaDeCandidatos.size() -1 ; i++){ 			// Algoritmo Bubble Sort para ordenar a lista que guarda a pontuação dos candidatos
			for (int j = 0; j < listaDeCandidatos.size() -i -1; j++) 
			    if (listaDeCandidatos.get(j).getPontuacaoTotal() < listaDeCandidatos.get(j + 1).getPontuacaoTotal()) {                 
			        CandidatoModel temp = listaDeCandidatos.get(j); 
			        listaDeCandidatos.set(j, listaDeCandidatos.get(j + 1));
			        listaDeCandidatos.set(j + 1, temp); 
			    } 
		
		}
		
		return listaDeCandidatos;
		
	}
	
	
	
}