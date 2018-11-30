package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe abstrata que define quais métodos um arquivo deve possuir, para que depois sejam sobrescritos nas classes filhas.
 *
 */

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class ArquivoModel {
	
	protected String caminhoDoArquivo;			// Variável que guarda a String de caminho de salvamento/leitura do arquivo
		
	
    /** Método que efetua a escrita da String passada no arquivo

     *   @return void */ 
	public abstract void escreveArquivo(String texto) throws IOException;       
	
	
    /** Método que retorna o caminho de salvamento/leitura do arquivo 

     *   @return String - CaminhoDoArquivo */  
	public abstract String getCaminhoDoArquivo();  	   
	
	
	
}