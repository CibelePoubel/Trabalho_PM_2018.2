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

public abstract class Arquivo {
		
	public abstract void escreveArquivo(String caminhoArquivo, String texto) throws IOException;  // M�todo abstrato que � sobrescrito nas classes filhas que define como deve o padr�o de escrita de arquivos.
	
	public abstract void leArquivo(String caminhoArquivo, String texto) throws IOException, SAXException, ParserConfigurationException;  // M�todo abstrato que � sobrescrito nas classes filhas que define como deve o padr�o de leitura de arquivos. 

}
	

