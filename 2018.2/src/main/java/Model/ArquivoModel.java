package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe que cria um arquivo para posterior escrita
 * *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoModel {

	private String caminhoDoArquivo;		// String que guarda o caminho especificado pelo usu�rio para salvamento dos arquivos (sa�da e log)
	
    /** M�todo construtor da classe LogModel. Recebe o caminho desejado pelo usu�rio para salvamento do arquivo .txt
     *  @param - Caminho para salvamento do arquivo .txt
     *  @return void */  
	public ArquivoModel(String caminhoDoArquivo) {			
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	
    /** M�todo que efetua a escrita da String passada no arquivo .txt
     *   @param - Texto a ser escrito no arquivo .txt
     *   @return void */ 
	public void escreveArquivo(String texto) throws IOException {  			  
		
		BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoDoArquivo, true));         // Cria o arquivo de log de erros
		
		arquivo.write(texto);  		     // Escreve o texto passado (vari�vel texto) para o m�todo
		arquivo.newLine(); 		         // Cria uma nova linha de texto dentro do arquivo de log
		arquivo.close();  			     // Fecha a escrita do arquivo
		
	}
	
	
	
    /** M�todo que retorna o caminho de salvamento/leitura do arquivo de log 

     *   @return String - CaminhoDoArquivo */  
	public String getCaminhoDoArquivo() {		  
		
		return caminhoDoArquivo;		     // Retorna a string com o caminho do arquivo .txt de log
		
	}

	
	
}