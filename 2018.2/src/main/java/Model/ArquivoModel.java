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

	private String caminhoDoArquivo;		// String que guarda o caminho especificado pelo usuário para salvamento dos arquivos (saída e log)
	
    /** Método construtor da classe LogModel. Recebe o caminho desejado pelo usuário para salvamento do arquivo .txt
     *  @param - Caminho para salvamento do arquivo .txt
     *  @return void */  
	public ArquivoModel(String caminhoDoArquivo) {			
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	
    /** Método que efetua a escrita da String passada no arquivo .txt
     *   @param - Texto a ser escrito no arquivo .txt
     *   @return void */ 
	public void escreveArquivo(String texto) throws IOException {  			  
		
		BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoDoArquivo, true));         // Cria o arquivo de log de erros
		
		arquivo.write(texto);  		     // Escreve o texto passado (variável texto) para o método
		arquivo.newLine(); 		         // Cria uma nova linha de texto dentro do arquivo de log
		arquivo.close();  			     // Fecha a escrita do arquivo
		
	}
	
	
	
    /** Método que retorna o caminho de salvamento/leitura do arquivo de log 

     *   @return String - CaminhoDoArquivo */  
	public String getCaminhoDoArquivo() {		  
		
		return caminhoDoArquivo;		     // Retorna a string com o caminho do arquivo .txt de log
		
	}

	
	
}