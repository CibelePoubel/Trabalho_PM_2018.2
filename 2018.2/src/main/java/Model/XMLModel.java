package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe ArquivoModel, que possui m�todos sobrescritos espec�ficos da classe XML (arquivo de curr�culo do candidato)
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

	private Document curriculo;						// Documento que guarda as informa��es do arquivo XML
	
    /** M�todo construtor da classe XMLModel. Recebe o caminho indicado pelo usu�rio para leitura do arquivo XML de curr�culo dos candidatos.

     *   @return void */  
	public XMLModel(String caminhoXML) throws SAXException, IOException, ParserConfigurationException{		// M�todo construtor da classe XML. Recebe o caminho do arquivo XML e a quantidade de semestres sem reprova��o passada pelo usu�rio
		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		curriculo = docBuilder.parse (new File(caminhoXML));
			
	}
	
	
	
    /** M�todo sobrescrito da classe Arquivo. N�o efetua nenhum tipo de escrita.

     *   @return void */ 	
	@Override
	public void escreveArquivo(String texto) {
	}
	
	
	
    /** M�todo que retorna o caminho de leitura do arquivo XML de curr�culo

     *   @return String - CaminhoDoArquivo */  
	@Override
	public String getCaminhoDoArquivo() { 		    // M�todo que retorna o caminho onde se encontra o arquivo XML do curr�culo
		
		return caminhoDoArquivo;
		
	}


	
    /** M�todo que retorna o documento do curr�culo

     *   @return Document - curriculo */ 
	public Document getCurriculo() {
		return curriculo;
	}
	
	
	
}