package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe abstrata que define quais m�todos um arquivo deve possuir, para que depois sejam sobrescritos nas classes filhas.
 *
 */

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class ArquivoModel {
	
	protected String caminhoDoArquivo;			// Vari�vel que guarda a String de caminho de salvamento/leitura do arquivo
		
	
    /** M�todo que efetua a escrita da String passada no arquivo

     *   @return void */ 
	public abstract void escreveArquivo(String texto) throws IOException;       // M�todo abstrato que � sobrescrito nas classes filhas que define como deve o padr�o de escrita de arquivos.
	
	
    /** M�todo que retorna o caminho de salvamento/leitura do arquivo 

     *   @return String - CaminhoDoArquivo */  
	public abstract String getCaminhoDoArquivo();  	    // M�todo que retorna o caminho onde se encontra o arquivo
	
	
	
}