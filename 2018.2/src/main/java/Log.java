/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe Arquivo, que possui métodos sobrescritos específicos da classe Log (arquivo de log de erros do programa)
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

	
	public Log(String caminhoDoArquivo) {			// Método construtor da classe Log. Recebe o caminho que o usuário especificou para a criação do arquivo txt de log
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	@Override
	public void escreveArquivo(String caminhoArquivo, String texto) throws IOException {  			  // Método sobrescrito da classe Arquivo que recebe o caminho que o usuário indicou para salvamento do log de erros e o texto a ser adicionado.
		
		caminhoDoArquivo = caminhoArquivo;
		
		BufferedWriter arquivoLog = new BufferedWriter(new FileWriter(caminhoArquivo, true));         // Cria o arquivo de log de erros
		
		arquivoLog.write(texto);  		     // Escreve o texto passado (variável texto) para o método
		arquivoLog.newLine(); 		         // Cria uma nova linha de texto dentro do arquivo de log
		arquivoLog.close();  			     // Fecha a escrita do arquivo
		
	}
	

	@Override
	public String getCaminhoDoArquivo() {		  // Método que retorna o caminho onde se encontra o arquivo de Log
		
		return caminhoDoArquivo;
		
	}

}
