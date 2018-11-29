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

public abstract class Arquivo {
	
	protected String caminhoDoArquivo;
		
	public abstract void escreveArquivo(String caminhoArquivo, String texto) throws IOException;       // Método abstrato que é sobrescrito nas classes filhas que define como deve o padrão de escrita de arquivos.
	
	public abstract String getCaminhoDoArquivo();  	    // Método que retorna o caminho onde se encontra o arquivo
	
}
	

