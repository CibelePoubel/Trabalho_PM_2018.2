package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe dos currículos associados aos candidatos
 *
 */

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CurriculoModel { 

	private String caminhoXML;						// String que guarda o caminho especificado pelo usuário para leitura do XML de currículo do candidato
	
	private Document curriculo;						// Documento que guarda as informações do arquivo XML
	
	
    /** Método construtor da classe XMLModel. Recebe o caminho indicado pelo usuário para leitura do arquivo XML de currículo dos candidatos.
     *   @param - Caminho para leitura do arquivo XML
     *   @return void */  
	public CurriculoModel(String caminhoXML) throws SAXException, IOException, ParserConfigurationException{		// Método construtor da classe XML. Recebe o caminho do arquivo XML e a quantidade de semestres sem reprovação passada pelo usuário
		
		this.caminhoXML = caminhoXML;
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		curriculo = docBuilder.parse (new File(caminhoXML));
			
	}
	
	

	
    /** Método que retorna o caminho de leitura do arquivo XML de currículo

     *   @return String - CaminhoDoArquivo */  
	public String getCaminhoDoXML() { 		    // Método que retorna o caminho onde se encontra o arquivo XML do currículo
		
		return caminhoXML;
		
	}


	
    /** Método que retorna o documento do currículo

     *   @return Document - curriculo */ 
	public Document getCurriculo() {
		
		return curriculo;						    // Retorna o documento com o XML do currículo
	
	}
	
	
	
}