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

	
	public SaidaModel(String caminhoDoArquivo) {			// Método construtor da classe Saida. Recebe o caminho que o usuário especificou para a criação do arquivo txt de saída
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	
	
	
	@Override
	public void escreveArquivo(String texto) throws IOException {  		     // Método sobrescrito da classe Arquivo que recebe o caminho que o usuário indicou para salvamento do arquivo e o texto a ser adicionado.
			
		BufferedWriter arquivoSaida = new BufferedWriter(new FileWriter(caminhoDoArquivo, true));      // Cria o arquivo de saída
		
		arquivoSaida.write(texto);  		 // Escreve o texto passado (variável texto) para o método
		arquivoSaida.newLine();  		     // Cria uma nova linha de texto dentro do arquivo de saída
		arquivoSaida.close();  				 // Fecha a escrita do arquivo
		
	}
	
	
	
	
	
	@Override
	public String getCaminhoDoArquivo() {		  // Método que retorna o caminho onde se encontra o arquivo de saída
		
		return caminhoDoArquivo;
		
	}
	
}