package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe ArquivoModel, que possui m�todos sobrescritos espec�ficos da classe Log (arquivo de log de erros do programa)
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

public class LogModel extends ArquivoModel {

    /** M�todo construtor da classe LogModel. Recebe o caminho desejado pelo usu�rio para salvamento do arquivo .txt de log (erros do programa)

     *   @return void */  
	public LogModel(String caminhoDoArquivo) {			// M�todo construtor da classe Log. Recebe o caminho que o usu�rio especificou para a cria��o do arquivo txt de log
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	
    /** M�todo sobrescrito da classe Arquivo que efetua a escrita da String passada no arquivo .txt de log

     *   @return void */ 
	@Override
	public void escreveArquivo(String texto) throws IOException {  			  // M�todo sobrescrito da classe Arquivo que recebe o caminho que o usu�rio indicou para salvamento do log de erros e o texto a ser adicionado.
		
		BufferedWriter arquivoLog = new BufferedWriter(new FileWriter(caminhoDoArquivo, true));         // Cria o arquivo de log de erros
		
		arquivoLog.write(texto);  		     // Escreve o texto passado (vari�vel texto) para o m�todo
		arquivoLog.newLine(); 		         // Cria uma nova linha de texto dentro do arquivo de log
		arquivoLog.close();  			     // Fecha a escrita do arquivo
		
	}
	
	
	
    /** M�todo que retorna o caminho de salvamento/leitura do arquivo de log 

     *   @return String - CaminhoDoArquivo */  
	@Override
	public String getCaminhoDoArquivo() {		  // M�todo que retorna o caminho onde se encontra o arquivo de Log
		
		return caminhoDoArquivo;
		
	}

	
	
}