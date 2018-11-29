/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe Arquivo, que possui m�todos sobrescritos espec�ficos da classe XML (arquivo de curr�culo do candidato)
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

	
	private int pontuacaoCurriculo = 0;				// Pontua��o inicial do curr�culo (0)
	private int semestresSemReprovacao;				// Recebe a quantidade de semestres sem reprova��o fornecida pelo usu�rio
	private Document curriculo;						// Documento que guarda as informa��es do arquivo XML
	
	public XML(String caminhoXML, int semestresSemReprovacao) throws SAXException, IOException, ParserConfigurationException{		// M�todo construtor da classe XML. Recebe o caminho do arquivo XML e a quantidade de semestres sem reprova��o passada pelo usu�rio
		caminhoDoArquivo = caminhoXML;
		this.semestresSemReprovacao = semestresSemReprovacao;
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		curriculo = docBuilder.parse (new File(caminhoXML));
			
	}
	
		
	@Override
	public void escreveArquivo(String caminhoArquivo, String texto) {
	}
	
	
	public void calculaPontuacaoSemestres() {		// M�todo que calcula a quantidade de pontos por semestres sem reprova��o
		
		this.pontuacaoCurriculo = 1 * semestresSemReprovacao;
				
	}
	
	
	public void calculaPontuacaoPremios(Arquivo txtSaida, boolean modoVerboso) throws IOException {			// M�todo que calcula a quantidade de pontos por pr�mios 
		int i;
		int anoLimiteValidade = 2008;			// Ano base para verifica��o de validade de pr�mio (somente se considera pr�mios ganhos h� menos de 10 anos)
		
		curriculo.getDocumentElement ().normalize ();
		
		NodeList rootNodes = curriculo.getElementsByTagName("PREMIOS-TITULOS");
		
		if( rootNodes == null) {
			txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "N�o h� pr�mios para este curr�culo");
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
            	
            	txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "Nome do pr�mio: " +NoteElement.getAttribute("NOME-DO-PREMIO-OU-TITULO"));  // Escreve no arquivo o nome do pr�mio obtido 
            	txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "Ano do pr�mio: " +NoteElement.getAttribute("ANO-DA-PREMIACAO"));  // Escreve no arquivo o ano do pr�mio obtido 
            	txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "\n\n\n");
            	pontuacaoCurriculo = pontuacaoCurriculo + 1;		// Incrementa a pontua��o do curr�culo
            	
            	}
            
            	else		 //  Caso o modo verboso n�o esteja ativado, nada deve ser escrito no arquivo txt de sa�da, somente incrementada a pontua��o
            		pontuacaoCurriculo = pontuacaoCurriculo + 1;
            
        }	
						
	}
	
	
	public void calculaPontuacaoQualis(Arquivo txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	public void calculaPontuacaoQualisRestrito(Arquivo txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	public void calculaPontuacaoEventos(Arquivo txtSaida, boolean modoVerboso) {	
		
				
	}
	
	
	
	public int getPontuacaoCurriculo() {		    // M�todo que retorna a pontua��o do curr�culo	
		
		return pontuacaoCurriculo;	
		
	}
	
	
	@Override
	public String getCaminhoDoArquivo() { 		    // M�todo que retorna o caminho onde se encontra o arquivo XML do curr�culo
		
		return caminhoDoArquivo;
		
	}
	
}
