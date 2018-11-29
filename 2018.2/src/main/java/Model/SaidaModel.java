package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe ArquivoModel, que possui métodos sobrescritos específicos da classe Saida (arquivo de saída do resultado do programa)
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SaidaModel extends ArquivoModel {

    /** Método construtor da classe SaidaModel. Recebe o caminho desejado pelo usuário para salvamento do arquivo .txt de saída (resultado do programa)

     *   @return void */  
	public SaidaModel(String caminhoDoArquivo) {			// Método construtor da classe Saida. Recebe o caminho que o usuário especificou para a criação do arquivo txt de saída
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	
    /** Método sobrescrito da classe Arquivo que efetua a escrita da String passada no arquivo .txt de saída

     *   @return void */ 
	@Override
	public void escreveArquivo(String texto) throws IOException {  		     // Método sobrescrito da classe Arquivo que recebe o caminho que o usuário indicou para salvamento do arquivo e o texto a ser adicionado.
			
		BufferedWriter arquivoSaida = new BufferedWriter(new FileWriter(caminhoDoArquivo, true));      // Cria o arquivo de saída
		
		arquivoSaida.write(texto);  		 // Escreve o texto passado (variável texto) para o método
		arquivoSaida.newLine();  		     // Cria uma nova linha de texto dentro do arquivo de saída
		arquivoSaida.close();  				 // Fecha a escrita do arquivo
		
	}
	
	
	
    /** Método que retorna o caminho de salvamento/leitura do arquivo de saída

     *   @return String - CaminhoDoArquivo */  
	@Override
	public String getCaminhoDoArquivo() {		  // Método que retorna o caminho onde se encontra o arquivo de saída
		
		return caminhoDoArquivo;
		
	}
	
	
	
}