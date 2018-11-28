/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe Arquivo, que possui m�todos sobrescritos espec�ficos da classe Log (arquivo de log de erros do programa)
 *
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Log extends Arquivo {

	
	public Log(String caminhoDoArquivo) {			// M�todo construtor da classe Log. Recebe o caminho que o usu�rio especificou para a cria��o do arquivo txt de log
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	@Override
	public void escreveArquivo(String caminhoArquivo, String texto) throws IOException {  			  // M�todo sobrescrito da classe Arquivo que recebe o caminho que o usu�rio indicou para salvamento do log de erros e o texto a ser adicionado.
		
		caminhoDoArquivo = caminhoArquivo;
		
		BufferedWriter arquivoLog = new BufferedWriter(new FileWriter(caminhoArquivo, true));         // Cria o arquivo de log de erros
		
		arquivoLog.write(texto);  		     // Escreve o texto passado (vari�vel texto) para o m�todo
		arquivoLog.newLine(); 		         // Cria uma nova linha de texto dentro do arquivo de log
		arquivoLog.close();  			     // Fecha a escrita do arquivo
		
	}
	

	@Override
	public String getCaminhoDoArquivo() {		  // M�todo que retorna o caminho onde se encontra o arquivo de Log
		
		return caminhoDoArquivo;
		
	}

}
