/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe ArquivoModel, que possui m�todos sobrescritos espec�ficos da classe XML (arquivo de curr�culo do candidato)
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

	
	private int pontuacaoCurriculo = 0;				// Pontua��o inicial do curr�culo (0)
	private Document nos;						// Documento que guarda as informa��es do arquivo XML
	
	public XMLModel(String caminhoXML) throws SAXException, IOException, ParserConfigurationException{		// M�todo construtor da classe XML. Recebe o caminho do arquivo XML e a quantidade de semestres sem reprova��o passada pelo usu�rio
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		nos = docBuilder.parse (new File(caminhoXML));
			
	}
	
	
	
	
		
	@Override
	public void escreveArquivo(String texto) {
	}
	

	
	
	public void calculaPontuacaoPremios(ArquivoModel txtSaida, boolean modoVerboso) throws IOException {			// M�todo que calcula a quantidade de pontos por pr�mios 
		int i;
		int anoLimiteValidade = 2008;			// Ano base para verifica��o de validade de pr�mio (somente se considera pr�mios ganhos h� menos de 10 anos)
		
		nos.getDocumentElement ().normalize ();
		
		NodeList rootNodes = nos.getElementsByTagName("PREMIOS-TITULOS");
		
		if( rootNodes == null) {
			txtSaida.escreveArquivo("N�o h� pr�mios para este curr�culo");
			return;
		}
		
		Node rootNode = rootNodes.item(0);
		Element rootElement = (Element) rootNode;
		NodeList notesList = rootElement.getElementsByTagName("PREMIO-TITULO");
		
		for(i = 0; i < notesList.getLength(); i++) {
            Node theNote = notesList.item(i);
            Element NoteElement = (Element) theNote;
            
            if(Integer.parseInt(NoteElement.getAttribute("ANO-DA-PREMIACAO")) >= anoLimiteValidade )		// Verifica se o pr�mio foi ganho h� menos de 10 anos. Caso sim, prossegue para incremento de pontua��o e escrita no arquivo (modo verboso ativado)
            	if(modoVerboso == true) {		  // Caso o modo verboso esteja ativado, imprime no arquivo txt de sa�da o nome e ano da premia��o
            	
            	txtSaida.escreveArquivo("Nome do pr�mio: " +NoteElement.getAttribute("NOME-DO-PREMIO-OU-TITULO"));  // Escreve no arquivo o nome do pr�mio obtido 
            	txtSaida.escreveArquivo("Ano do pr�mio: " +NoteElement.getAttribute("ANO-DA-PREMIACAO"));  // Escreve no arquivo o ano do pr�mio obtido 
            	txtSaida.escreveArquivo("\n\n\n");
            	pontuacaoCurriculo = pontuacaoCurriculo + 1;		// Incrementa a pontua��o do curr�culo
            	
            	}
            
            	else		 //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
            		pontuacaoCurriculo = pontuacaoCurriculo + 1;
            
        }	
						
	}
	
	
	
	
	
	public void calculaPontuacaoQualis(ArquivoModel txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	
	
	
	public void calculaPontuacaoQualisRestrito(ArquivoModel txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	
	
	
	public void calculaPontuacaoEventos(ArquivoModel txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	
	
	public int getPontuacaoCurriculo() {		    // M�todo que retorna a pontua��o do curr�culo	
		
		return pontuacaoCurriculo;	
		
	}
	
	
	
	
	@Override
	public String getCaminhoDoArquivo() { 		    // M�todo que retorna o caminho onde se encontra o arquivo XML do curr�culo
		
		return caminhoDoArquivo;
		
	}




	public Document getNos() {
		return nos;
	}
	
	

	
}