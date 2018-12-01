package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe dos curr�culos associados aos candidatos
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

	private String caminhoXML;						// String que guarda o caminho especificado pelo usu�rio para leitura do XML de curr�culo do candidato
	
	private Document curriculo;						// Documento que guarda as informa��es do arquivo XML
	
	
    /** M�todo construtor da classe XMLModel. Recebe o caminho indicado pelo usu�rio para leitura do arquivo XML de curr�culo dos candidatos.
     *   @param - Caminho para leitura do arquivo XML
     *   @return void */  
	public CurriculoModel(String caminhoXML) throws SAXException, IOException, ParserConfigurationException{		// M�todo construtor da classe XML. Recebe o caminho do arquivo XML e a quantidade de semestres sem reprova��o passada pelo usu�rio
		
		this.caminhoXML = caminhoXML;
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		curriculo = docBuilder.parse (new File(caminhoXML));
			
	}
	
	

	
    /** M�todo que retorna o caminho de leitura do arquivo XML de curr�culo

     *   @return String - CaminhoDoArquivo */  
	public String getCaminhoDoXML() { 		    // M�todo que retorna o caminho onde se encontra o arquivo XML do curr�culo
		
		return caminhoXML;
		
	}


	
    /** M�todo que retorna o documento do curr�culo

     *   @return Document - curriculo */ 
	public Document getCurriculo() {
		
		return curriculo;						    // Retorna o documento com o XML do curr�culo
	
	}
	
	
	
}