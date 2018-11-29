package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe ArquivoModel, que possui métodos sobrescritos específicos da classe XML (arquivo de currículo do candidato)
 *
 */

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLModel extends ArquivoModel { 

	private Document curriculo;						// Documento que guarda as informações do arquivo XML
	
    /** Método construtor da classe XMLModel. Recebe o caminho indicado pelo usuário para leitura do arquivo XML de currículo dos candidatos.

     *   @return void */  
	public XMLModel(String caminhoXML) throws SAXException, IOException, ParserConfigurationException{		// Método construtor da classe XML. Recebe o caminho do arquivo XML e a quantidade de semestres sem reprovação passada pelo usuário
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		curriculo = docBuilder.parse (new File(caminhoXML));
			
	}
	
	
	
    /** Método sobrescrito da classe Arquivo. Não efetua nenhum tipo de escrita.

     *   @return void */ 	
	@Override
	public void escreveArquivo(String texto) {
	}
	
	
	
    /** Método que retorna o caminho de leitura do arquivo XML de currículo

     *   @return String - CaminhoDoArquivo */  
	@Override
	public String getCaminhoDoArquivo() { 		    // Método que retorna o caminho onde se encontra o arquivo XML do currículo
		
		return caminhoDoArquivo;
		
	}


	
    /** Método que retorna o documento do currículo

     *   @return Document - curriculo */ 
	public Document getCurriculo() {
		return curriculo;
	}
	
	
	
}