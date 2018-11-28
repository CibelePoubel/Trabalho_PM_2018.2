/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe Arquivo, que possui métodos sobrescritos específicos da classe XML (arquivo de currículo do candidato)
 *
 */

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML extends Arquivo { 

	
	private int pontuacaoCurriculo = 0;				// Pontuação inicial do currículo (0)
	private int semestresSemReprovacao;				// Recebe a quantidade de semestres sem reprovação fornecida pelo usuário
	private Document curriculo;						// Documento que guarda as informações do arquivo XML
	
	public XML(String caminhoXML, int semestresSemReprovacao) throws SAXException, IOException, ParserConfigurationException{		// Método construtor da classe XML. Recebe o caminho do arquivo XML e a quantidade de semestres sem reprovação passada pelo usuário
		caminhoDoArquivo = caminhoXML;
		this.semestresSemReprovacao = semestresSemReprovacao;
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		curriculo = docBuilder.parse (new File(caminhoXML));
			
	}
	
		
	@Override
	public void escreveArquivo(String caminhoArquivo, String texto) {
	}
	
	
	public void calculaPontuacaoSemestres() {		// Método que calcula a quantidade de pontos por semestres sem reprovação
		
		this.pontuacaoCurriculo = 1 * semestresSemReprovacao;
				
	}
	
	
	public void calculaPontuacaoPremios(Arquivo txtSaida, boolean modoVerboso) throws IOException {			// Método que calcula a quantidade de pontos por prêmios 
		int i;
		int anoLimiteValidade = 2008;			// Ano base para verificação de validade de prêmio (somente se considera prêmios ganhos há menos de 10 anos)
		
		curriculo.getDocumentElement ().normalize ();
		
		NodeList rootNodes = curriculo.getElementsByTagName("PREMIOS-TITULOS");
		
		if( rootNodes == null) {
			txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "Não há prêmios para este currículo");
			return;
		}
		
		Node rootNode = rootNodes.item(0);
		Element rootElement = (Element) rootNode;
		NodeList notesList = rootElement.getElementsByTagName("PREMIO-TITULO");
		
		for(i = 0; i < notesList.getLength(); i++) {
            Node theNote = notesList.item(i);
            Element NoteElement = (Element) theNote;
            
            if(Integer.parseInt(NoteElement.getAttribute("ANO-DA-PREMIACAO")) >= anoLimiteValidade )		// Verifica se o prêmio foi ganho há menos de 10 anos. Caso sim, prossegue para incremento de pontuação e escrita no arquivo (modo verboso ativado)
            	if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de saída o nome e ano da premiação
            	
            	txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "Nome do prêmio: " +NoteElement.getAttribute("NOME-DO-PREMIO-OU-TITULO"));  // Escreve no arquivo o nome do prêmio obtido 
            	txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "Ano do prêmio: " +NoteElement.getAttribute("ANO-DA-PREMIACAO"));  // Escreve no arquivo o ano do prêmio obtido 
            	txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "\n\n\n");
            	pontuacaoCurriculo = pontuacaoCurriculo + 1;		// Incrementa a pontuação do currículo
            	
            	}
            
            	else		 //  Caso o modo verboso não esteja ativado, nada deve ser escrito no arquivo txt de saída, somente incrementada a pontuação
            		pontuacaoCurriculo = pontuacaoCurriculo + 1;
            
        }	
						
	}
	
	
	public void calculaPontuacaoQualis(Arquivo txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	public void calculaPontuacaoQualisRestrito(Arquivo txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	public void calculaPontuacaoEventos(Arquivo txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	
	public int getPontuacaoCurriculo() {		    // Método que retorna a pontuação do currículo	
		
		return pontuacaoCurriculo;	
		
	}
	
	
	@Override
	public String getCaminhoDoArquivo() { 		    // Método que retorna o caminho onde se encontra o arquivo XML do currículo
		
		return caminhoDoArquivo;
		
	}
	
}
