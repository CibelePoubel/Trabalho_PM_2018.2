/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe ArquivoModel, que possui métodos sobrescritos específicos da classe XML (arquivo de currículo do candidato)
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

public class XMLModel extends ArquivoModel { 

	
	private int pontuacaoCurriculo = 0;				// Pontuação inicial do currículo (0)
	private Document nos;						// Documento que guarda as informações do arquivo XML
	
	public XMLModel(String caminhoXML) throws SAXException, IOException, ParserConfigurationException{		// Método construtor da classe XML. Recebe o caminho do arquivo XML e a quantidade de semestres sem reprovação passada pelo usuário
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		nos = docBuilder.parse (new File(caminhoXML));
			
	}
	
	
	
	
		
	@Override
	public void escreveArquivo(String texto) {
	}
	

	
	
	public void calculaPontuacaoPremios(ArquivoModel txtSaida, boolean modoVerboso) throws IOException {			// Método que calcula a quantidade de pontos por prêmios 
		int i;
		int anoLimiteValidade = 2008;			// Ano base para verificação de validade de prêmio (somente se considera prêmios ganhos há menos de 10 anos)
		
		nos.getDocumentElement ().normalize ();
		
		NodeList rootNodes = nos.getElementsByTagName("PREMIOS-TITULOS");
		
		if( rootNodes == null) {
			txtSaida.escreveArquivo("Não há prêmios para este currículo");
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
            	
            	txtSaida.escreveArquivo("Nome do prêmio: " +NoteElement.getAttribute("NOME-DO-PREMIO-OU-TITULO"));  // Escreve no arquivo o nome do prêmio obtido 
            	txtSaida.escreveArquivo("Ano do prêmio: " +NoteElement.getAttribute("ANO-DA-PREMIACAO"));  // Escreve no arquivo o ano do prêmio obtido 
            	txtSaida.escreveArquivo("\n\n\n");
            	pontuacaoCurriculo = pontuacaoCurriculo + 1;		// Incrementa a pontuação do currículo
            	
            	}
            
            	else		 //  Caso o modo verboso não esteja ativado, nada deve ser escrito no arquivo txt de saída, somente incrementada a pontuação
            		pontuacaoCurriculo = pontuacaoCurriculo + 1;
            
        }	
						
	}
	
	
	
	
	
	public void calculaPontuacaoQualis(ArquivoModel txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	
	
	
	public void calculaPontuacaoQualisRestrito(ArquivoModel txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	
	
	
	public void calculaPontuacaoEventos(ArquivoModel txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	
	
	public int getPontuacaoCurriculo() {		    // Método que retorna a pontuação do currículo	
		
		return pontuacaoCurriculo;	
		
	}
	
	
	
	
	@Override
	public String getCaminhoDoArquivo() { 		    // Método que retorna o caminho onde se encontra o arquivo XML do currículo
		
		return caminhoDoArquivo;
		
	}




	public Document getNos() {
		return nos;
	}
	
	

	
}